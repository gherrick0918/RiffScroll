package com.riffscroll.data

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Repository for managing saved routines and schedules
 * Currently stores data in-memory. Future enhancement: add persistent storage
 */
class RoutineRepository {
    
    private val savedRoutines = mutableMapOf<String, SavedRoutine>()
    private val schedules = mutableMapOf<String, Schedule>()
    private val calendarSchedules = mutableMapOf<String, CalendarSchedule>()
    private val practiceSchedulePlans = mutableMapOf<String, PracticeSchedulePlan>()
    
    // Saved Routines operations
    
    /**
     * Save a routine with a custom name
     */
    fun saveRoutine(name: String, routine: PracticeRoutine): SavedRoutine {
        val savedRoutine = SavedRoutine(
            id = "saved_${System.currentTimeMillis()}",
            name = name,
            routine = routine
        )
        savedRoutines[savedRoutine.id] = savedRoutine
        return savedRoutine
    }
    
    /**
     * Get all saved routines
     */
    fun getSavedRoutines(): List<SavedRoutine> {
        return savedRoutines.values.sortedByDescending { it.createdAt }
    }
    
    /**
     * Get a saved routine by ID
     */
    fun getSavedRoutine(id: String): SavedRoutine? {
        return savedRoutines[id]
    }
    
    /**
     * Delete a saved routine
     */
    fun deleteSavedRoutine(id: String): Boolean {
        // Also remove from any schedules
        schedules.values.forEach { schedule ->
            if (schedule.routineIds.contains(id)) {
                val updatedSchedule = schedule.copy(
                    routineIds = schedule.routineIds.filter { it != id }
                )
                schedules[schedule.id] = updatedSchedule
            }
        }
        return savedRoutines.remove(id) != null
    }
    
    // Schedule operations
    
    /**
     * Create a new schedule
     */
    fun createSchedule(name: String, description: String = ""): Schedule {
        val schedule = Schedule(
            id = "schedule_${System.currentTimeMillis()}",
            name = name,
            description = description
        )
        schedules[schedule.id] = schedule
        return schedule
    }
    
    /**
     * Get all schedules
     */
    fun getSchedules(): List<Schedule> {
        return schedules.values.sortedByDescending { it.createdAt }
    }
    
    /**
     * Get a schedule by ID
     */
    fun getSchedule(id: String): Schedule? {
        return schedules[id]
    }
    
    /**
     * Update a schedule
     */
    fun updateSchedule(schedule: Schedule): Boolean {
        if (!schedules.containsKey(schedule.id)) return false
        schedules[schedule.id] = schedule
        return true
    }
    
    /**
     * Add a routine to a schedule
     */
    fun addRoutineToSchedule(scheduleId: String, routineId: String): Boolean {
        val schedule = schedules[scheduleId] ?: return false
        val routine = savedRoutines[routineId] ?: return false
        
        if (schedule.routineIds.contains(routineId)) return false
        
        val updatedSchedule = schedule.copy(
            routineIds = schedule.routineIds + routineId
        )
        schedules[scheduleId] = updatedSchedule
        return true
    }
    
    /**
     * Remove a routine from a schedule
     */
    fun removeRoutineFromSchedule(scheduleId: String, routineId: String): Boolean {
        val schedule = schedules[scheduleId] ?: return false
        
        if (!schedule.routineIds.contains(routineId)) return false
        
        val updatedSchedule = schedule.copy(
            routineIds = schedule.routineIds.filter { it != routineId }
        )
        schedules[scheduleId] = updatedSchedule
        return true
    }
    
    /**
     * Delete a schedule
     */
    fun deleteSchedule(id: String): Boolean {
        return schedules.remove(id) != null
    }
    
    /**
     * Get all routines in a schedule
     */
    fun getRoutinesInSchedule(scheduleId: String): List<SavedRoutine> {
        val schedule = schedules[scheduleId] ?: return emptyList()
        return schedule.routineIds.mapNotNull { savedRoutines[it] }
    }
    
    // Calendar Scheduling operations
    
    /**
     * Create a calendar schedule entry for a specific date
     */
    fun createCalendarSchedule(date: Long, routineId: String): CalendarSchedule {
        val calendarSchedule = CalendarSchedule(
            id = "cal_${System.currentTimeMillis()}",
            date = date,
            routineId = routineId
        )
        calendarSchedules[calendarSchedule.id] = calendarSchedule
        return calendarSchedule
    }
    
    /**
     * Get all calendar schedules
     */
    fun getCalendarSchedules(): List<CalendarSchedule> {
        return calendarSchedules.values.sortedBy { it.date }
    }
    
    /**
     * Get calendar schedules for a specific date range
     */
    fun getCalendarSchedulesByDateRange(startDate: Long, endDate: Long): List<CalendarSchedule> {
        return calendarSchedules.values.filter { it.date in startDate..endDate }.sortedBy { it.date }
    }
    
    /**
     * Get calendar schedule for a specific date
     */
    fun getCalendarScheduleByDate(date: Long): CalendarSchedule? {
        // Extract year and day of year from search date once
        val searchDate = java.util.Calendar.getInstance().apply { timeInMillis = date }
        val searchYear = searchDate.get(java.util.Calendar.YEAR)
        val searchDayOfYear = searchDate.get(java.util.Calendar.DAY_OF_YEAR)
        
        return calendarSchedules.values.firstOrNull { 
            // Compare just the date part (ignore time)
            val scheduleDate = java.util.Calendar.getInstance().apply { timeInMillis = it.date }
            scheduleDate.get(java.util.Calendar.YEAR) == searchYear &&
            scheduleDate.get(java.util.Calendar.DAY_OF_YEAR) == searchDayOfYear
        }
    }
    
    /**
     * Mark a calendar schedule as completed
     */
    fun markCalendarScheduleCompleted(id: String): Boolean {
        val schedule = calendarSchedules[id] ?: return false
        calendarSchedules[id] = schedule.copy(isCompleted = true)
        return true
    }
    
    /**
     * Delete a calendar schedule
     */
    fun deleteCalendarSchedule(id: String): Boolean {
        return calendarSchedules.remove(id) != null
    }
    
    /**
     * Create an auto-generated practice schedule plan
     */
    fun createPracticeSchedulePlan(
        name: String,
        startDate: Long,
        endDate: Long,
        instrument: InstrumentType?,
        targetDurationMinutes: Int,
        difficulty: DifficultyLevel?,
        daysPerWeek: Int,
        exerciseRepository: ExerciseRepository
    ): PracticeSchedulePlan {
        val scheduleEntries = mutableListOf<CalendarSchedule>()
        
        // Create date formatter once, outside the loop
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        
        // Generate routines for each day in the date range
        val calendar = java.util.Calendar.getInstance()
        calendar.timeInMillis = startDate
        
        var practiceCount = 0
        var weekStart = calendar.clone() as java.util.Calendar
        var dayIndex = 0  // Track which day we're generating for alternating instruments
        
        while (calendar.timeInMillis <= endDate) {
            // Check if we're starting a new week (Monday)
            if (calendar.get(java.util.Calendar.DAY_OF_WEEK) == java.util.Calendar.MONDAY) {
                weekStart = calendar.clone() as java.util.Calendar
                practiceCount = 0
            }
            
            // Only create routine if we haven't reached the weekly limit
            if (practiceCount < daysPerWeek) {
                // When both instruments are selected (instrument == null), alternate between them
                val dailyInstrument = if (instrument == null) {
                    if (dayIndex % 2 == 0) InstrumentType.GUITAR else InstrumentType.PIANO
                } else {
                    instrument
                }
                
                // Generate a routine for this day
                val routine = exerciseRepository.generateBalancedRoutine(
                    targetDurationMinutes = targetDurationMinutes,
                    difficulty = difficulty,
                    instrument = dailyInstrument
                )
                
                // Save the routine
                val savedRoutine = saveRoutine(
                    name = "Auto-generated for ${dateFormat.format(calendar.time)}",
                    routine = routine
                )
                
                // Create calendar schedule entry
                val calendarSchedule = createCalendarSchedule(
                    date = calendar.timeInMillis,
                    routineId = savedRoutine.id
                )
                
                scheduleEntries.add(calendarSchedule)
                practiceCount++
                dayIndex++  // Increment day index for alternating instruments
            }
            
            // Move to next day
            calendar.add(java.util.Calendar.DAY_OF_MONTH, 1)
        }
        
        // Create the practice schedule plan
        val plan = PracticeSchedulePlan(
            id = "plan_${System.currentTimeMillis()}",
            name = name,
            startDate = startDate,
            endDate = endDate,
            scheduleEntries = scheduleEntries,
            instrument = instrument,
            targetDurationMinutes = targetDurationMinutes,
            difficulty = difficulty,
            daysPerWeek = daysPerWeek
        )
        
        practiceSchedulePlans[plan.id] = plan
        return plan
    }
    
    /**
     * Get all practice schedule plans
     */
    fun getPracticeSchedulePlans(): List<PracticeSchedulePlan> {
        return practiceSchedulePlans.values.sortedByDescending { it.createdAt }
    }
    
    /**
     * Delete a practice schedule plan and its associated calendar schedules
     */
    fun deletePracticeSchedulePlan(id: String): Boolean {
        val plan = practiceSchedulePlans[id] ?: return false
        
        // Delete associated calendar schedules
        plan.scheduleEntries.forEach { entry ->
            calendarSchedules.remove(entry.id)
        }
        
        return practiceSchedulePlans.remove(id) != null
    }
}
