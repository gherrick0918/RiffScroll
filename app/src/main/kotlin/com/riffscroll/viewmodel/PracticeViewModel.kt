package com.riffscroll.viewmodel

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riffscroll.data.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.sin

/**
 * ViewModel for managing practice routines and sessions.
 * 
 * Handles:
 * - Routine generation and management
 * - Practice session state and timing
 * - Metronome control
 * - User progress and XP tracking
 * - Data persistence
 */
class PracticeViewModel(
    private val persistenceManager: PersistenceManager? = null
) : ViewModel() {
    
    private val repository = ExerciseRepository()
    private val routineRepository = RoutineRepository(persistenceManager)
    
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
    
    // Current viewing date for calendar schedules (defaults to today)
    private val _currentViewingDate = MutableStateFlow(System.currentTimeMillis())
    val currentViewingDate: StateFlow<Long> = _currentViewingDate.asStateFlow()
    
    // Practice history and statistics
    private val _practiceHistory = MutableStateFlow<List<PracticeHistoryEntry>>(emptyList())
    val practiceHistory: StateFlow<List<PracticeHistoryEntry>> = _practiceHistory.asStateFlow()
    
    private val _practiceStatistics = MutableStateFlow(PracticeStatistics())
    val practiceStatistics: StateFlow<PracticeStatistics> = _practiceStatistics.asStateFlow()
    
    private var timerJob: Job? = null
    private var metronomeJob: Job? = null
    private var beatCounter = 0  // Track beats for accenting first beat of measure
    
    // Audio parameters for metronome
    private val sampleRate = 44100
    private val clickDurationMs = 50  // Duration of each click sound
    private val metronomePollDelayMs = 5L  // Delay between timing checks to avoid busy-waiting
    
    init {
        // Load user progress from persistence
        loadUserProgress()
        
        // Load practice history and calculate statistics
        refreshPracticeHistory()
    }
    
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
        saveUserProgress()
        
        // Add to practice history
        val routineName = _currentRoutine.value?.let { routine ->
            // Try to find a saved routine that matches this routine
            _savedRoutines.value.find { it.routine.id == routine.id }?.name
        } ?: "Practice Session"
        
        // Determine primary instrument (if all exercises use same instrument)
        val instruments = session.routine.exercises.map { it.instrument }.distinct()
        val primaryInstrument = if (instruments.size == 1) instruments.first() else null
        
        // Determine difficulty (use highest difficulty in routine)
        val maxDifficulty = session.routine.exercises.maxOfOrNull { it.difficulty }
        
        val historyEntry = PracticeHistoryEntry(
            id = "history_${System.currentTimeMillis()}",
            completedAt = System.currentTimeMillis(),
            durationMinutes = session.routine.totalDurationMinutes,
            xpEarned = xpGained,
            routineName = routineName,
            exerciseCount = session.routine.exercises.size,
            instrument = primaryInstrument,
            difficulty = maxDifficulty
        )
        
        routineRepository.addPracticeHistoryEntry(historyEntry)
        refreshPracticeHistory()
        
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
     * Generate a metronome click sound
     * @param isAccented true for the first beat of a measure (higher pitch)
     * @return ByteArray containing the audio samples
     */
    private fun generateClickSound(isAccented: Boolean): ByteArray {
        val frequency = if (isAccented) 1200.0 else 800.0  // Hz
        val numSamples = (sampleRate * clickDurationMs / 1000.0).toInt()
        val samples = ShortArray(numSamples)
        
        for (i in 0 until numSamples) {
            // Generate sine wave with envelope for click sound
            val time = i.toDouble() / sampleRate
            val envelope = 1.0 - (i.toDouble() / numSamples)  // Linear decay
            val sample = (sin(2.0 * Math.PI * frequency * time) * envelope * Short.MAX_VALUE * 0.8).toInt()
            samples[i] = sample.toShort()
        }
        
        // Convert to bytes
        val bytes = ByteArray(samples.size * 2)
        for (i in samples.indices) {
            bytes[i * 2] = (samples[i].toInt() and 0xFF).toByte()
            bytes[i * 2 + 1] = ((samples[i].toInt() shr 8) and 0xFF).toByte()
        }
        
        return bytes
    }
    
    /**
     * Start the metronome
     */
    fun startMetronome() {
        if (_isMetronomeActive.value) return
        
        _isMetronomeActive.value = true
        beatCounter = 0  // Reset beat counter when starting
        metronomeJob?.cancel()
        
        metronomeJob = viewModelScope.launch {
            try {
                // Pre-generate click sounds
                val accentedClick = generateClickSound(true)
                val regularClick = generateClickSound(false)
                
                // Create AudioTrack for playback
                val bufferSize = AudioTrack.getMinBufferSize(
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT
                )
                
                val audioTrack = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(bufferSize)
                    .setTransferMode(AudioTrack.MODE_STREAM)
                    .build()
                
                audioTrack.play()
                var lastBeatTimeNanos = System.nanoTime()
                
                while (_isMetronomeActive.value) {
                    val currentTimeNanos = System.nanoTime()
                    val bpm = _metronomeBpm.value
                    val intervalNanos = (60_000_000_000.0 / bpm).toLong()
                    
                    // Check if it's time for the next beat
                    if (currentTimeNanos - lastBeatTimeNanos >= intervalNanos) {
                        // Play the appropriate click sound
                        val clickData = if (beatCounter % 4 == 0) accentedClick else regularClick
                        
                        // Write the click to the audio track
                        audioTrack.write(clickData, 0, clickData.size)
                        
                        beatCounter++
                        lastBeatTimeNanos = currentTimeNanos
                    }
                    
                    // Small delay to avoid busy-waiting
                    delay(metronomePollDelayMs)
                }
                
                // Clean up
                audioTrack.stop()
                audioTrack.release()
                
            } catch (e: Exception) {
                // Handle audio errors gracefully
                _isMetronomeActive.value = false
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
        difficulty: DifficultyLevel?,
        daysPerWeek: Int
    ): PracticeSchedulePlan {
        val plan = routineRepository.createPracticeSchedulePlan(
            name = name,
            startDate = startDate,
            endDate = endDate,
            instrument = instrument,
            targetDurationMinutes = targetDurationMinutes,
            difficulty = difficulty,
            daysPerWeek = daysPerWeek,
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
    
    /**
     * Navigate to the previous day
     */
    fun navigateToPreviousDay() {
        val calendar = java.util.Calendar.getInstance()
        calendar.timeInMillis = _currentViewingDate.value
        calendar.add(java.util.Calendar.DAY_OF_MONTH, -1)
        _currentViewingDate.value = calendar.timeInMillis
    }
    
    /**
     * Navigate to the next day
     */
    fun navigateToNextDay() {
        val calendar = java.util.Calendar.getInstance()
        calendar.timeInMillis = _currentViewingDate.value
        calendar.add(java.util.Calendar.DAY_OF_MONTH, 1)
        _currentViewingDate.value = calendar.timeInMillis
    }
    
    /**
     * Navigate to today
     */
    fun navigateToToday() {
        _currentViewingDate.value = System.currentTimeMillis()
    }
    
    /**
     * Get the calendar schedule for the current viewing date
     */
    fun getCurrentViewingSchedule(): CalendarSchedule? {
        return routineRepository.getCalendarScheduleByDate(_currentViewingDate.value)
    }
    
    // Practice History Management
    
    /**
     * Refresh practice history from repository
     */
    fun refreshPracticeHistory() {
        _practiceHistory.value = routineRepository.getPracticeHistory()
        _practiceStatistics.value = routineRepository.calculateStatistics()
    }
    
    /**
     * Get practice history for a date range
     */
    fun getPracticeHistoryByDateRange(startDate: Long, endDate: Long): List<PracticeHistoryEntry> {
        return routineRepository.getPracticeHistoryByDateRange(startDate, endDate)
    }
    
    // User Progress Persistence
    
    /**
     * Load user progress from persistence
     */
    private fun loadUserProgress() {
        persistenceManager?.let { pm ->
            val savedProgress = pm.loadUserProgress()
            if (savedProgress != null) {
                _userProgress.value = savedProgress
            }
        }
    }
    
    /**
     * Save current user progress to persistence
     */
    private fun saveUserProgress() {
        persistenceManager?.saveUserProgress(_userProgress.value)
    }
    
    override fun onCleared() {
        super.onCleared()
        stopTimer()
        stopMetronome()
    }
}
