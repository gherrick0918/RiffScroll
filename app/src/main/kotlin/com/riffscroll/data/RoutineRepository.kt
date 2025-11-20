package com.riffscroll.data

/**
 * Repository for managing saved routines and schedules
 * Currently stores data in-memory. Future enhancement: add persistent storage
 */
class RoutineRepository {
    
    private val savedRoutines = mutableMapOf<String, SavedRoutine>()
    private val schedules = mutableMapOf<String, Schedule>()
    
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
}
