package com.riffscroll.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riffscroll.data.DifficultyLevel
import com.riffscroll.data.Exercise
import com.riffscroll.data.ExerciseCategory
import com.riffscroll.data.PracticeRoutine
import com.riffscroll.data.UserProgress

/**
 * Main home screen showing user progress and routine generation
 */
@Composable
fun HomeScreen(
    userProgress: UserProgress,
    currentRoutine: PracticeRoutine?,
    onGenerateRoutine: (Int, DifficultyLevel?) -> Unit,
    onStartPractice: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RpgTheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header with RPG theme
        RpgHeader(text = "⚔️ Practice Quest", modifier = Modifier.padding(bottom = 16.dp))
        
        // User Progress Card
        UserProgressCard(userProgress = userProgress)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Routine Generation Section
        RpgCard {
            RpgHeader(text = "Generate Routine", modifier = Modifier.padding(bottom = 8.dp))
            RpgText(
                text = "Create a balanced practice routine tailored to your level",
                color = RpgTheme.textSecondary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            var selectedDuration by remember { mutableStateOf(45) }
            var selectedDifficulty by remember { mutableStateOf<DifficultyLevel?>(null) }
            
            // Difficulty Selection
            Text(
                text = "Difficulty:",
                color = RpgTheme.textPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RpgButton(
                    text = "All",
                    onClick = { selectedDifficulty = null },
                    modifier = Modifier.weight(1f),
                    color = if (selectedDifficulty == null) RpgTheme.primary else RpgTheme.secondary
                )
                RpgButton(
                    text = "Beginner",
                    onClick = { selectedDifficulty = DifficultyLevel.BEGINNER },
                    modifier = Modifier.weight(1f),
                    color = if (selectedDifficulty == DifficultyLevel.BEGINNER) RpgTheme.primary else RpgTheme.secondary
                )
                RpgButton(
                    text = "Intermediate",
                    onClick = { selectedDifficulty = DifficultyLevel.INTERMEDIATE },
                    modifier = Modifier.weight(1f),
                    color = if (selectedDifficulty == DifficultyLevel.INTERMEDIATE) RpgTheme.primary else RpgTheme.secondary
                )
                RpgButton(
                    text = "Advanced",
                    onClick = { selectedDifficulty = DifficultyLevel.ADVANCED },
                    modifier = Modifier.weight(1f),
                    color = if (selectedDifficulty == DifficultyLevel.ADVANCED) RpgTheme.primary else RpgTheme.secondary
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Duration: $selectedDuration minutes",
                color = RpgTheme.textPrimary,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Slider(
                value = selectedDuration.toFloat(),
                onValueChange = { selectedDuration = it.toInt() },
                valueRange = 15f..90f,
                steps = 14,
                colors = SliderDefaults.colors(
                    thumbColor = RpgTheme.accent,
                    activeTrackColor = RpgTheme.primary,
                    inactiveTrackColor = RpgTheme.secondary
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            RpgButton(
                text = "🎲 Generate New Routine",
                onClick = { onGenerateRoutine(selectedDuration, selectedDifficulty) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Current Routine Display
        if (currentRoutine != null) {
            RoutinePreviewCard(
                routine = currentRoutine,
                onStartPractice = onStartPractice
            )
        }
    }
}

/**
 * Display user progress with RPG stats
 */
@Composable
fun UserProgressCard(userProgress: UserProgress) {
    RpgCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Level",
                        tint = RpgTheme.accent,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Level ${userProgress.level}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = RpgTheme.accent
                    )
                }
            }
            
            Column(horizontalAlignment = Alignment.End) {
                RpgText(text = "🎸 ${userProgress.completedRoutines} Routines")
                RpgText(text = "⏱️ ${userProgress.totalPracticeMinutes} Minutes")
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Column {
            Text(
                text = "XP: ${userProgress.xp} / ${userProgress.xpToNextLevel}",
                fontSize = 14.sp,
                color = RpgTheme.textSecondary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            RpgProgressBar(
                progress = userProgress.xp.toFloat() / userProgress.xpToNextLevel,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Preview of the generated routine
 */
@Composable
fun RoutinePreviewCard(
    routine: PracticeRoutine,
    onStartPractice: () -> Unit
) {
    RpgCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RpgHeader(text = "Your Routine")
            RpgBadge(text = "Lv ${routine.difficulty}")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        RpgText(
            text = "${routine.exercises.size} exercises • ${routine.totalDurationMinutes} minutes",
            color = RpgTheme.textSecondary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Exercise list preview
        routine.exercises.forEachIndexed { index, exercise ->
            ExerciseListItem(exercise = exercise, index = index + 1)
            if (index < routine.exercises.size - 1) {
                Divider(
                    color = RpgTheme.border,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        RpgButton(
            text = "⚔️ Start Practice",
            onClick = onStartPractice,
            modifier = Modifier.fillMaxWidth(),
            color = RpgTheme.success
        )
    }
}

/**
 * Individual exercise list item
 */
@Composable
fun ExerciseListItem(exercise: Exercise, index: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "$index.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = RpgTheme.accent,
            modifier = Modifier.padding(end = 8.dp)
        )
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = exercise.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RpgTheme.textPrimary
            )
            
            Text(
                text = exercise.description,
                fontSize = 14.sp,
                color = RpgTheme.textSecondary,
                modifier = Modifier.padding(top = 4.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RpgBadge(
                    text = when (exercise.category) {
                        ExerciseCategory.TECHNIQUE -> "⚡ Technique"
                        ExerciseCategory.CREATIVITY -> "🎨 Creative"
                        ExerciseCategory.SONGS -> "🎵 Song"
                    },
                    color = when (exercise.category) {
                        ExerciseCategory.TECHNIQUE -> RpgTheme.primary
                        ExerciseCategory.CREATIVITY -> RpgTheme.accent
                        ExerciseCategory.SONGS -> RpgTheme.success
                    }
                )
                
                RpgBadge(
                    text = exercise.difficulty.displayName,
                    color = when (exercise.difficulty) {
                        DifficultyLevel.BEGINNER -> RpgTheme.success
                        DifficultyLevel.INTERMEDIATE -> RpgTheme.warning
                        DifficultyLevel.ADVANCED -> RpgTheme.danger
                    }
                )
                
                RpgBadge(
                    text = "${exercise.durationMinutes} min",
                    color = RpgTheme.secondary
                )
                
                if (exercise.hasTiming && exercise.bpm != null) {
                    RpgBadge(
                        text = "♩ ${exercise.bpm} BPM",
                        color = RpgTheme.secondary
                    )
                }
            }
        }
    }
}
