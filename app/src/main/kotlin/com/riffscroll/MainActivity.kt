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
import com.riffscroll.ui.*
import com.riffscroll.viewmodel.PracticeViewModel

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
    val viewModel: PracticeViewModel = viewModel()
    
    val currentRoutine by viewModel.currentRoutine.collectAsState()
    val currentSession by viewModel.currentSession.collectAsState()
    val userProgress by viewModel.userProgress.collectAsState()
    val timerSeconds by viewModel.timerSeconds.collectAsState()
    val metronomeBpm by viewModel.metronomeBpm.collectAsState()
    val isMetronomeActive by viewModel.isMetronomeActive.collectAsState()
    
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = RpgTheme.background
    ) {
        if (currentSession?.isActive == true) {
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
        } else {
            HomeScreen(
                userProgress = userProgress,
                currentRoutine = currentRoutine,
                onGenerateRoutine = { duration -> viewModel.generateRoutine(duration) },
                onStartPractice = { viewModel.startPracticeSession() }
            )
        }
    }
}
