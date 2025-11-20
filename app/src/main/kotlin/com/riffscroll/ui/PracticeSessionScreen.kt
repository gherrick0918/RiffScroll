package com.riffscroll.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riffscroll.data.Exercise
import com.riffscroll.data.PracticeSession

/**
 * Active practice session screen with timer and metronome controls
 */
@Composable
fun PracticeSessionScreen(
    session: PracticeSession,
    timerSeconds: Int,
    metronomeBpm: Int,
    isMetronomeActive: Boolean,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onNext: () -> Unit,
    onEnd: () -> Unit,
    onToggleMetronome: () -> Unit,
    onSetBpm: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentExercise = session.routine.exercises.getOrNull(session.currentExerciseIndex)
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RpgTheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RpgHeader(text = "⚔️ Practice Quest")
            
            IconButton(onClick = onEnd) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "End Practice",
                    tint = RpgTheme.danger
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Progress indicator
        RoutineProgressCard(session = session)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Timer Card
        TimerCard(
            timerSeconds = timerSeconds,
            targetMinutes = currentExercise?.durationMinutes ?: 0
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Current Exercise Card
        if (currentExercise != null) {
            CurrentExerciseCard(exercise = currentExercise)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Metronome Card
        if (currentExercise?.hasTiming == true) {
            MetronomeCard(
                bpm = metronomeBpm,
                isActive = isMetronomeActive,
                onToggle = onToggleMetronome,
                onSetBpm = onSetBpm
            )
            
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        // Control buttons
        PracticeControls(
            isPaused = session.isPaused,
            timerSeconds = timerSeconds,
            onPause = onPause,
            onResume = onResume,
            onNext = onNext
        )
    }
}

/**
 * Display routine progress
 */
@Composable
fun RoutineProgressCard(session: PracticeSession) {
    RpgCard {
        Text(
            text = "Exercise ${session.currentExerciseIndex + 1} of ${session.routine.exercises.size}",
            fontSize = 14.sp,
            color = RpgTheme.textSecondary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        RpgProgressBar(
            progress = (session.currentExerciseIndex + 1).toFloat() / session.routine.exercises.size,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Timer display card
 */
@Composable
fun TimerCard(timerSeconds: Int, targetMinutes: Int) {
    RpgCard {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Timer,
                contentDescription = "Timer",
                tint = RpgTheme.accent,
                modifier = Modifier.size(48.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = formatTime(timerSeconds),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = RpgTheme.accent
            )
            
            Text(
                text = "Target: $targetMinutes minutes",
                fontSize = 14.sp,
                color = RpgTheme.textSecondary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            val progress = if (targetMinutes > 0) {
                (timerSeconds.toFloat() / (targetMinutes * 60)).coerceIn(0f, 1f)
            } else 0f
            
            RpgProgressBar(
                progress = progress,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Current exercise details card
 */
@Composable
fun CurrentExerciseCard(exercise: Exercise) {
    RpgCard {
        RpgHeader(text = exercise.name, modifier = Modifier.padding(bottom = 8.dp))
        
        RpgText(
            text = exercise.description,
            color = RpgTheme.textSecondary,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Display tablature if available
        if (exercise.tablature != null) {
            Text(
                text = "Tablature:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RpgTheme.accent,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            RpgCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = exercise.tablature,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = RpgTheme.textPrimary,
                    fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        
        if (exercise.instructions.isNotEmpty()) {
            Text(
                text = "Instructions:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RpgTheme.accent,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            exercise.instructions.forEachIndexed { index, instruction ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "${index + 1}. ",
                        fontSize = 14.sp,
                        color = RpgTheme.primary
                    )
                    Text(
                        text = instruction,
                        fontSize = 14.sp,
                        color = RpgTheme.textPrimary
                    )
                }
            }
        }
    }
}

/**
 * Metronome control card
 */
@Composable
fun MetronomeCard(
    bpm: Int,
    isActive: Boolean,
    onToggle: () -> Unit,
    onSetBpm: (Int) -> Unit
) {
    RpgCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = "Metronome",
                    tint = if (isActive) RpgTheme.success else RpgTheme.textSecondary,
                    modifier = Modifier.size(32.dp)
                )
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Column {
                    Text(
                        text = "Metronome",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = RpgTheme.textPrimary
                    )
                    Text(
                        text = "$bpm BPM",
                        fontSize = 14.sp,
                        color = RpgTheme.textSecondary
                    )
                }
            }
            
            Switch(
                checked = isActive,
                onCheckedChange = { onToggle() },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = RpgTheme.success,
                    checkedTrackColor = RpgTheme.success.copy(alpha = 0.5f)
                )
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onSetBpm(bpm - 5) },
                enabled = bpm > 40
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Decrease BPM",
                    tint = if (bpm > 40) RpgTheme.textPrimary else RpgTheme.textSecondary
                )
            }
            
            Slider(
                value = bpm.toFloat(),
                onValueChange = { onSetBpm(it.toInt()) },
                valueRange = 40f..240f,
                modifier = Modifier.weight(1f),
                colors = SliderDefaults.colors(
                    thumbColor = RpgTheme.accent,
                    activeTrackColor = RpgTheme.primary,
                    inactiveTrackColor = RpgTheme.secondary
                )
            )
            
            IconButton(
                onClick = { onSetBpm(bpm + 5) },
                enabled = bpm < 240
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Increase BPM",
                    tint = if (bpm < 240) RpgTheme.textPrimary else RpgTheme.textSecondary
                )
            }
        }
    }
}

/**
 * Practice control buttons
 */
@Composable
fun PracticeControls(
    isPaused: Boolean,
    timerSeconds: Int,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (isPaused) {
            val buttonText = if (timerSeconds == 0) "▶ Start" else "▶ Resume"
            RpgButton(
                text = buttonText,
                onClick = onResume,
                modifier = Modifier.weight(1f),
                color = RpgTheme.success
            )
        } else {
            RpgButton(
                text = "⏸ Pause",
                onClick = onPause,
                modifier = Modifier.weight(1f),
                color = RpgTheme.warning
            )
        }
        
        RpgButton(
            text = "Next ➜",
            onClick = onNext,
            modifier = Modifier.weight(1f),
            color = RpgTheme.primary
        )
    }
}

/**
 * Format seconds to MM:SS
 */
private fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", minutes, secs)
}
