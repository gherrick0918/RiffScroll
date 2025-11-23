package com.riffscroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.riffscroll.ui.*
import com.riffscroll.viewmodel.PracticeViewModel
import com.riffscroll.viewmodel.PracticeViewModelFactory
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RiffScrollApp()
        }
    }
}

@Composable
fun RiffScrollApp() {
    val context = LocalContext.current
    val viewModel: PracticeViewModel = viewModel(
        factory = PracticeViewModelFactory(context)
    )
    
    val currentRoutine by viewModel.currentRoutine.collectAsState()
    val currentSession by viewModel.currentSession.collectAsState()
    val userProgress by viewModel.userProgress.collectAsState()
    val timerSeconds by viewModel.timerSeconds.collectAsState()
    val metronomeBpm by viewModel.metronomeBpm.collectAsState()
    val isMetronomeActive by viewModel.isMetronomeActive.collectAsState()
    val savedRoutines by viewModel.savedRoutines.collectAsState()
    val schedules by viewModel.schedules.collectAsState()
    val practiceSchedulePlans by viewModel.practiceSchedulePlans.collectAsState()
    val calendarSchedules by viewModel.calendarSchedules.collectAsState()
    val currentViewingDate by viewModel.currentViewingDate.collectAsState()
    val practiceHistory by viewModel.practiceHistory.collectAsState()
    val practiceStatistics by viewModel.practiceStatistics.collectAsState()
    
    var currentScreen by remember { mutableStateOf("home") }
    var editingExerciseId by remember { mutableStateOf<String?>(null) }
    
    // Load initial data
    LaunchedEffect(Unit) {
        viewModel.refreshSavedRoutines()
        viewModel.refreshSchedules()
        viewModel.refreshPracticeSchedulePlans()
        viewModel.refreshCalendarSchedules()
    }
    
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        color = RpgTheme.background
    ) {
        when {
            currentSession?.isActive == true -> {
                PracticeSessionScreen(
                    session = currentSession!!,
                    timerSeconds = timerSeconds,
                    metronomeBpm = metronomeBpm,
                    isMetronomeActive = isMetronomeActive,
                    onPause = { viewModel.pauseSession() },
                    onResume = { viewModel.resumeSession() },
                    onNext = { viewModel.nextExercise() },
                    onEnd = { viewModel.endSession() },
                    onToggleMetronome = { viewModel.toggleMetronome() },
                    onSetBpm = { bpm -> viewModel.setMetronomeBpm(bpm) }
                )
            }
            currentScreen == "schedule_planner" -> {
                SchedulePlannerScreen(
                    practiceSchedulePlans = practiceSchedulePlans,
                    savedRoutines = savedRoutines,
                    onCreatePlan = { name, startDate, endDate, instrument, duration, difficulty, daysPerWeek ->
                        viewModel.createPracticeSchedulePlan(name, startDate, endDate, instrument, duration, difficulty, daysPerWeek)
                    },
                    onDeletePlan = { id -> viewModel.deletePracticeSchedulePlan(id) },
                    onLoadRoutine = { id -> 
                        viewModel.loadSavedRoutine(id)
                        currentScreen = "home"
                    },
                    onBack = { currentScreen = "home" }
                )
            }
            currentScreen == "history" -> {
                PracticeHistoryScreen(
                    practiceHistory = practiceHistory,
                    practiceStatistics = practiceStatistics,
                    onBack = { currentScreen = "home" }
                )
            }
            currentScreen == "exercise_browser" -> {
                ExerciseBrowserScreen(
                    allExercises = viewModel.getAllExercises(),
                    onBack = { currentScreen = "home" },
                    onAddToRoutine = null,  // Can be enhanced later to add exercises to custom routines
                    onEditExercise = { exerciseId ->
                        editingExerciseId = exerciseId
                        currentScreen = "custom_exercise"
                    },
                    onDeleteExercise = { exerciseId ->
                        viewModel.deleteCustomExercise(exerciseId)
                    },
                    onCreateNew = {
                        editingExerciseId = null
                        currentScreen = "custom_exercise"
                    }
                )
            }
            currentScreen == "custom_exercise" -> {
                val existingExercise = editingExerciseId?.let { viewModel.getExerciseById(it) }
                CustomExerciseScreen(
                    existingExercise = existingExercise,
                    onSave = { name, description, category, instrument, durationMinutes, difficulty, hasTiming, bpm, instructions, tablature ->
                        if (existingExercise != null) {
                            // Update existing exercise
                            val updated = existingExercise.copy(
                                name = name,
                                description = description,
                                category = category,
                                instrument = instrument,
                                durationMinutes = durationMinutes,
                                difficulty = difficulty,
                                hasTiming = hasTiming,
                                bpm = bpm,
                                instructions = instructions,
                                tablature = tablature
                            )
                            viewModel.updateCustomExercise(updated)
                        } else {
                            // Create new exercise
                            viewModel.addCustomExercise(
                                name = name,
                                description = description,
                                category = category,
                                instrument = instrument,
                                durationMinutes = durationMinutes,
                                difficulty = difficulty,
                                hasTiming = hasTiming,
                                bpm = bpm,
                                instructions = instructions,
                                tablature = tablature
                            )
                        }
                        currentScreen = "exercise_browser"
                        editingExerciseId = null
                    },
                    onCancel = { 
                        currentScreen = "exercise_browser"
                        editingExerciseId = null
                    }
                )
            }
            else -> {
                HomeScreen(
                    userProgress = userProgress,
                    currentRoutine = currentRoutine,
                    savedRoutines = savedRoutines,
                    schedules = schedules,
                    onGenerateRoutine = { duration, difficulty, instrument -> viewModel.generateRoutine(duration, difficulty, instrument) },
                    onStartPractice = { viewModel.startPracticeSession() },
                    onSaveRoutine = { name -> viewModel.saveCurrentRoutine(name) },
                    onLoadRoutine = { id -> viewModel.loadSavedRoutine(id) },
                    onDeleteRoutine = { id -> viewModel.deleteSavedRoutine(id) },
                    onCreateSchedule = { name, description -> viewModel.createSchedule(name, description) },
                    onDeleteSchedule = { id -> viewModel.deleteSchedule(id) },
                    onAddRoutineToSchedule = { scheduleId, routineId -> viewModel.addRoutineToSchedule(scheduleId, routineId) },
                    onRemoveRoutineFromSchedule = { scheduleId, routineId -> viewModel.removeRoutineFromSchedule(scheduleId, routineId) },
                    onNavigateToSchedulePlanner = { currentScreen = "schedule_planner" },
                    onNavigateToHistory = { currentScreen = "history" },
                    onNavigateToExerciseBrowser = { currentScreen = "exercise_browser" },
                    calendarSchedules = calendarSchedules,
                    currentViewingDate = currentViewingDate,
                    onNavigatePreviousDay = { viewModel.navigateToPreviousDay() },
                    onNavigateNextDay = { viewModel.navigateToNextDay() },
                    onNavigateToday = { viewModel.navigateToToday() },
                    onLoadCalendarRoutine = { id -> viewModel.loadSavedRoutine(id) },
                    onMarkScheduleCompleted = { id -> viewModel.markCalendarScheduleCompleted(id) }
                )
            }
        }
    }
}
