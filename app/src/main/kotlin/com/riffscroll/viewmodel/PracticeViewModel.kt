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
    
    private var timerJob: Job? = null
    private var metronomeJob: Job? = null
    private var toneGenerator: ToneGenerator? = null
    
    /**
     * Generate a new practice routine
     */
    fun generateRoutine(targetDurationMinutes: Int = 45) {
        val routine = repository.generateBalancedRoutine(targetDurationMinutes)
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
            isPaused = false,
            elapsedSeconds = 0
        )
        
        _currentSession.value = session
        _timerSeconds.value = 0
        
        // Set metronome for first exercise if applicable
        val firstExercise = routine.exercises.firstOrNull()
        if (firstExercise?.hasTiming == true && firstExercise.bpm != null) {
            _metronomeBpm.value = firstExercise.bpm
        }
        
        startTimer()
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
        
        stopMetronome()
        
        if (nextIndex >= session.routine.exercises.size) {
            // Routine complete
            completeRoutine()
            return
        }
        
        _currentSession.value = session.copy(currentExerciseIndex = nextIndex)
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
                // Play metronome beep
                try {
                    toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 100)
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
    
    override fun onCleared() {
        super.onCleared()
        stopTimer()
        stopMetronome()
        toneGenerator?.release()
    }
}
