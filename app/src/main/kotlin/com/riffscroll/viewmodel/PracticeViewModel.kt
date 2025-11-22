package com.riffscroll.viewmodel

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riffscroll.data.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing practice routines and sessions.
 * 
 * Handles:
 * - Routine generation and management
 * - Practice session state and timing
 * - Metronome control
 * - User progress and XP tracking
 */
class PracticeViewModel : ViewModel() {
    
    private val repository = ExerciseRepository()
    private val routineRepository = RoutineRepository()
    
    private val _currentRoutine = MutableStateFlow<PracticeRoutine?>(null)
    val currentRoutine: StateFlow<PracticeRoutine?> = _currentRoutine.asStateFlow()
    
    private val _currentSession = MutableStateFlow<PracticeSession?>(null)
    val currentSession: StateFlow<PracticeSession?> = _currentSession.asStateFlow()
    
    private val _userProgress = MutableStateFlow(UserProgress())
    val userProgress: StateFlow<UserProgress> = _userProgress.asStateFlow()
    
    private val _timerSeconds = MutableStateFlow(0)
    val timerSeconds: StateFlow<Int> = _timerSeconds.asStateFlow()
    
    private val _metronomeBpm = MutableStateFlow(60)
    val metronomeBpm: StateFlow<Int> = _metronomeBpm.asStateFlow()
    
    private val _isMetronomeActive = MutableStateFlow(false)
    val isMetronomeActive: StateFlow<Boolean> = _isMetronomeActive.asStateFlow()
    
    // Saved routines and schedules
    private val _savedRoutines = MutableStateFlow<List<SavedRoutine>>(emptyList())
    val savedRoutines: StateFlow<List<SavedRoutine>> = _savedRoutines.asStateFlow()
    
    private val _schedules = MutableStateFlow<List<Schedule>>(emptyList())
    val schedules: StateFlow<List<Schedule>> = _schedules.asStateFlow()
    
    // Calendar scheduling
    private val _calendarSchedules = MutableStateFlow<List<CalendarSchedule>>(emptyList())
    val calendarSchedules: StateFlow<List<CalendarSchedule>> = _calendarSchedules.asStateFlow()
    
    private val _practiceSchedulePlans = MutableStateFlow<List<PracticeSchedulePlan>>(emptyList())
    val practiceSchedulePlans: StateFlow<List<PracticeSchedulePlan>> = _practiceSchedulePlans.asStateFlow()
    
    private var timerJob: Job? = null
    private var metronomeJob: Job? = null
    private var toneGenerator: ToneGenerator? = null
    private var beatCounter = 0  // Track beats for accenting first beat of measure
    
    /**
     * Generate a new practice routine
     */
    fun generateRoutine(
        targetDurationMinutes: Int = 45, 
        difficulty: DifficultyLevel? = null,
        instrument: InstrumentType? = null
    ) {
        val routine = repository.generateBalancedRoutine(targetDurationMinutes, difficulty, instrument)
        _currentRoutine.value = routine
    }
    
    /**
     * Start a practice session with the current routine
     */
    fun startPracticeSession() {
        val routine = _currentRoutine.value ?: return
        
        val session = PracticeSession(
            routine = routine,
            currentExerciseIndex = 0,
            isActive = true,
            isPaused = true,  // Start paused so user can review exercise first
            elapsedSeconds = 0
        )
        
        _currentSession.value = session
        _timerSeconds.value = 0
        
        // Set metronome for first exercise if applicable
        val firstExercise = routine.exercises.firstOrNull()
        if (firstExercise?.hasTiming == true && firstExercise.bpm != null) {
            _metronomeBpm.value = firstExercise.bpm
        }
        
        // Timer will start when user clicks Resume button
    }
    
    /**
     * Pause the current practice session
     */
    fun pauseSession() {
        _currentSession.value = _currentSession.value?.copy(isPaused = true)
        stopTimer()
        stopMetronome()
    }
    
    /**
     * Resume the current practice session
     */
    fun resumeSession() {
        _currentSession.value = _currentSession.value?.copy(isPaused = false)
        startTimer()
        
        // Resume metronome if current exercise uses it
        val session = _currentSession.value ?: return
        val currentExercise = session.routine.exercises.getOrNull(session.currentExerciseIndex)
        if (currentExercise?.hasTiming == true) {
            startMetronome()
        }
    }
    
    /**
     * Move to the next exercise in the routine
     */
    fun nextExercise() {
        val session = _currentSession.value ?: return
        val nextIndex = session.currentExerciseIndex + 1
        
        stopTimer()
        stopMetronome()
        
        if (nextIndex >= session.routine.exercises.size) {
            // Routine complete
            completeRoutine()
            return
        }
        
        _currentSession.value = session.copy(
            currentExerciseIndex = nextIndex,
            isPaused = true  // Pause so user can review the new exercise
        )
        _timerSeconds.value = 0
        
        // Update metronome for new exercise
        val nextExercise = session.routine.exercises[nextIndex]
        if (nextExercise.hasTiming && nextExercise.bpm != null) {
            _metronomeBpm.value = nextExercise.bpm
        }
    }
    
    /**
     * End the current practice session
     */
    fun endSession() {
        stopTimer()
        stopMetronome()
        _currentSession.value = null
        _timerSeconds.value = 0
    }
    
    /**
     * Complete the routine and award XP
     */
    private fun completeRoutine() {
        val session = _currentSession.value ?: return
        val progress = _userProgress.value
        
        // Award XP based on routine completion
        val xpGained = session.routine.totalDurationMinutes * 2
        val newXp = progress.xp + xpGained
        val newLevel = if (newXp >= progress.xpToNextLevel) progress.level + 1 else progress.level
        val newXpToNextLevel = if (newXp >= progress.xpToNextLevel) 
            (progress.xpToNextLevel * 1.5).toInt() else progress.xpToNextLevel
        
        _userProgress.value = progress.copy(
            level = newLevel,
            xp = if (newXp >= progress.xpToNextLevel) newXp - progress.xpToNextLevel else newXp,
            xpToNextLevel = newXpToNextLevel,
            totalPracticeMinutes = progress.totalPracticeMinutes + session.routine.totalDurationMinutes,
            completedRoutines = progress.completedRoutines + 1
        )
        
        stopTimer()
        stopMetronome()
        _currentSession.value = session.copy(isActive = false)
    }
    
    /**
     * Start the practice timer
     */
    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _timerSeconds.value += 1
                _currentSession.value = _currentSession.value?.copy(
                    elapsedSeconds = _timerSeconds.value
                )
            }
        }
    }
    
    /**
     * Stop the practice timer
     */
    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }
    
    /**
     * Start the metronome
     */
    fun startMetronome() {
        if (_isMetronomeActive.value) return
        
        _isMetronomeActive.value = true
        beatCounter = 0  // Reset beat counter when starting
        metronomeJob?.cancel()
        
        // Initialize ToneGenerator for metronome beeps
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 80)
        } catch (e: Exception) {
            // Handle case where ToneGenerator initialization fails
            toneGenerator = null
        }
        
        metronomeJob = viewModelScope.launch {
            val bpm = _metronomeBpm.value
            val intervalMs = (60000.0 / bpm).toLong()
            
            while (_isMetronomeActive.value) {
                // Play metronome beep with accent on first beat (4/4 time)
                try {
                    if (beatCounter % 4 == 0) {
                        // Accented first beat - higher pitch and louder
                        toneGenerator?.startTone(ToneGenerator.TONE_DTMF_1, 100)
                    } else {
                        // Regular beats
                        toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 100)
                    }
                    beatCounter++
                } catch (e: Exception) {
                    // Silently handle tone generation errors
                }
                delay(intervalMs)
            }
        }
    }
    
    /**
     * Stop the metronome
     */
    fun stopMetronome() {
        _isMetronomeActive.value = false
        beatCounter = 0  // Reset beat counter when stopping
        metronomeJob?.cancel()
        metronomeJob = null
        
        // Release ToneGenerator resources
        try {
            toneGenerator?.release()
            toneGenerator = null
        } catch (e: Exception) {
            // Silently handle release errors
        }
    }
    
    /**
     * Toggle metronome on/off
     */
    fun toggleMetronome() {
        if (_isMetronomeActive.value) {
            stopMetronome()
        } else {
            startMetronome()
        }
    }
    
    /**
     * Adjust metronome BPM
     */
    fun setMetronomeBpm(bpm: Int) {
        _metronomeBpm.value = bpm.coerceIn(40, 240)
        
        if (_isMetronomeActive.value) {
            stopMetronome()
            startMetronome()
        }
    }
    
    // Saved Routines Management
    
    /**
     * Save the current routine with a name
     */
    fun saveCurrentRoutine(name: String): SavedRoutine? {
        val routine = _currentRoutine.value ?: return null
        val savedRoutine = routineRepository.saveRoutine(name, routine)
        refreshSavedRoutines()
        return savedRoutine
    }
    
    /**
     * Load a saved routine as the current routine
     */
    fun loadSavedRoutine(id: String) {
        val savedRoutine = routineRepository.getSavedRoutine(id)
        if (savedRoutine != null) {
            _currentRoutine.value = savedRoutine.routine
        }
    }
    
    /**
     * Delete a saved routine
     */
    fun deleteSavedRoutine(id: String) {
        routineRepository.deleteSavedRoutine(id)
        refreshSavedRoutines()
        refreshSchedules()
    }
    
    /**
     * Refresh the list of saved routines
     */
    fun refreshSavedRoutines() {
        _savedRoutines.value = routineRepository.getSavedRoutines()
    }
    
    // Schedule Management
    
    /**
     * Create a new schedule
     */
    fun createSchedule(name: String, description: String = ""): Schedule {
        val schedule = routineRepository.createSchedule(name, description)
        refreshSchedules()
        return schedule
    }
    
    /**
     * Add a routine to a schedule
     */
    fun addRoutineToSchedule(scheduleId: String, routineId: String): Boolean {
        val result = routineRepository.addRoutineToSchedule(scheduleId, routineId)
        if (result) refreshSchedules()
        return result
    }
    
    /**
     * Remove a routine from a schedule
     */
    fun removeRoutineFromSchedule(scheduleId: String, routineId: String): Boolean {
        val result = routineRepository.removeRoutineFromSchedule(scheduleId, routineId)
        if (result) refreshSchedules()
        return result
    }
    
    /**
     * Delete a schedule
     */
    fun deleteSchedule(id: String) {
        routineRepository.deleteSchedule(id)
        refreshSchedules()
    }
    
    /**
     * Get routines in a schedule
     */
    fun getRoutinesInSchedule(scheduleId: String): List<SavedRoutine> {
        return routineRepository.getRoutinesInSchedule(scheduleId)
    }
    
    /**
     * Refresh the list of schedules
     */
    fun refreshSchedules() {
        _schedules.value = routineRepository.getSchedules()
    }
    
    // Calendar Scheduling Management
    
    /**
     * Create a practice schedule plan with auto-generated routines
     */
    fun createPracticeSchedulePlan(
        name: String,
        startDate: Long,
        endDate: Long,
        instrument: InstrumentType?,
        targetDurationMinutes: Int,
        difficulty: DifficultyLevel?
    ): PracticeSchedulePlan {
        val plan = routineRepository.createPracticeSchedulePlan(
            name = name,
            startDate = startDate,
            endDate = endDate,
            instrument = instrument,
            targetDurationMinutes = targetDurationMinutes,
            difficulty = difficulty,
            exerciseRepository = repository
        )
        refreshSavedRoutines()
        refreshCalendarSchedules()
        refreshPracticeSchedulePlans()
        return plan
    }
    
    /**
     * Get calendar schedule for a specific date
     */
    fun getCalendarScheduleByDate(date: Long): CalendarSchedule? {
        return routineRepository.getCalendarScheduleByDate(date)
    }
    
    /**
     * Get calendar schedules for a date range
     */
    fun getCalendarSchedulesByDateRange(startDate: Long, endDate: Long): List<CalendarSchedule> {
        return routineRepository.getCalendarSchedulesByDateRange(startDate, endDate)
    }
    
    /**
     * Mark a calendar schedule as completed
     */
    fun markCalendarScheduleCompleted(id: String) {
        routineRepository.markCalendarScheduleCompleted(id)
        refreshCalendarSchedules()
    }
    
    /**
     * Delete a calendar schedule
     */
    fun deleteCalendarSchedule(id: String) {
        routineRepository.deleteCalendarSchedule(id)
        refreshCalendarSchedules()
    }
    
    /**
     * Delete a practice schedule plan
     */
    fun deletePracticeSchedulePlan(id: String) {
        routineRepository.deletePracticeSchedulePlan(id)
        refreshCalendarSchedules()
        refreshPracticeSchedulePlans()
    }
    
    /**
     * Refresh calendar schedules
     */
    fun refreshCalendarSchedules() {
        _calendarSchedules.value = routineRepository.getCalendarSchedules()
    }
    
    /**
     * Refresh practice schedule plans
     */
    fun refreshPracticeSchedulePlans() {
        _practiceSchedulePlans.value = routineRepository.getPracticeSchedulePlans()
    }
    
    /**
     * Load routine from calendar schedule
     */
    fun loadRoutineFromCalendarSchedule(calendarScheduleId: String) {
        val calendarSchedule = _calendarSchedules.value.find { it.id == calendarScheduleId }
        if (calendarSchedule != null) {
            loadSavedRoutine(calendarSchedule.routineId)
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        stopTimer()
        stopMetronome()
        toneGenerator?.release()
    }
}
