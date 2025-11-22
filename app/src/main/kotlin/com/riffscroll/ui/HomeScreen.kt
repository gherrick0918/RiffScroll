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
import com.riffscroll.data.CalendarSchedule
import com.riffscroll.data.DifficultyLevel
import com.riffscroll.data.Exercise
import com.riffscroll.data.ExerciseCategory
import com.riffscroll.data.InstrumentType
import com.riffscroll.data.PracticeRoutine
import com.riffscroll.data.SavedRoutine
import com.riffscroll.data.Schedule
import com.riffscroll.data.UserProgress
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Main home screen showing user progress and routine generation
 */
@Composable
fun HomeScreen(
    userProgress: UserProgress,
    currentRoutine: PracticeRoutine?,
    savedRoutines: List<SavedRoutine>,
    schedules: List<Schedule>,
    onGenerateRoutine: (Int, DifficultyLevel?, InstrumentType?) -> Unit,
    onStartPractice: () -> Unit,
    onSaveRoutine: (String) -> Unit,
    onLoadRoutine: (String) -> Unit,
    onDeleteRoutine: (String) -> Unit,
    onCreateSchedule: (String, String) -> Unit,
    onDeleteSchedule: (String) -> Unit,
    onAddRoutineToSchedule: (String, String) -> Unit,
    onRemoveRoutineFromSchedule: (String, String) -> Unit,
    onNavigateToSchedulePlanner: () -> Unit,
    calendarSchedules: List<CalendarSchedule>,
    currentViewingDate: Long,
    onNavigatePreviousDay: () -> Unit,
    onNavigateNextDay: () -> Unit,
    onNavigateToday: () -> Unit,
    onLoadCalendarRoutine: (String) -> Unit,
    onMarkScheduleCompleted: (String) -> Unit,
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
        
        // Auto Schedule Planner Button - Prominent!
        RpgCard {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "📅",
                                fontSize = 24.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = "Auto Schedule Planner",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = RpgTheme.textPrimary
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        RpgText(
                            text = "Create automatic practice schedules for multiple days",
                            color = RpgTheme.textSecondary
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Open",
                        tint = RpgTheme.accent,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                RpgButton(
                    text = "✨ Open Schedule Planner",
                    onClick = onNavigateToSchedulePlanner,
                    modifier = Modifier.fillMaxWidth(),
                    color = RpgTheme.accent
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Today's Routine Section (from calendar schedules)
        TodaysRoutineSection(
            calendarSchedules = calendarSchedules,
            savedRoutines = savedRoutines,
            currentViewingDate = currentViewingDate,
            onNavigatePreviousDay = onNavigatePreviousDay,
            onNavigateNextDay = onNavigateNextDay,
            onNavigateToday = onNavigateToday,
            onLoadRoutine = onLoadCalendarRoutine,
            onMarkCompleted = onMarkScheduleCompleted,
            onStartPractice = onStartPractice
        )
        
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
            var selectedInstrument by remember { mutableStateOf<InstrumentType?>(null) }
            
            // Instrument Selection
            Text(
                text = "Instrument:",
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
                    text = "Both",
                    onClick = { selectedInstrument = null },
                    modifier = Modifier.weight(1f),
                    color = if (selectedInstrument == null) RpgTheme.primary else RpgTheme.secondary,
                    fontSize = 13.sp
                )
                RpgButton(
                    text = "${InstrumentType.GUITAR.emoji} Guitar",
                    onClick = { selectedInstrument = InstrumentType.GUITAR },
                    modifier = Modifier.weight(1f),
                    color = if (selectedInstrument == InstrumentType.GUITAR) RpgTheme.primary else RpgTheme.secondary,
                    fontSize = 13.sp
                )
                RpgButton(
                    text = "${InstrumentType.PIANO.emoji} Piano",
                    onClick = { selectedInstrument = InstrumentType.PIANO },
                    modifier = Modifier.weight(1f),
                    color = if (selectedInstrument == InstrumentType.PIANO) RpgTheme.primary else RpgTheme.secondary,
                    fontSize = 13.sp
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
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
                    color = if (selectedDifficulty == null) RpgTheme.primary else RpgTheme.secondary,
                    fontSize = 13.sp
                )
                RpgButton(
                    text = "Beginner",
                    onClick = { selectedDifficulty = DifficultyLevel.BEGINNER },
                    modifier = Modifier.weight(1f),
                    color = if (selectedDifficulty == DifficultyLevel.BEGINNER) RpgTheme.primary else RpgTheme.secondary,
                    fontSize = 13.sp
                )
                RpgButton(
                    text = "Intermediate",
                    onClick = { selectedDifficulty = DifficultyLevel.INTERMEDIATE },
                    modifier = Modifier.weight(1f),
                    color = if (selectedDifficulty == DifficultyLevel.INTERMEDIATE) RpgTheme.primary else RpgTheme.secondary,
                    fontSize = 13.sp
                )
                RpgButton(
                    text = "Advanced",
                    onClick = { selectedDifficulty = DifficultyLevel.ADVANCED },
                    modifier = Modifier.weight(1f),
                    color = if (selectedDifficulty == DifficultyLevel.ADVANCED) RpgTheme.primary else RpgTheme.secondary,
                    fontSize = 13.sp
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
                onClick = { onGenerateRoutine(selectedDuration, selectedDifficulty, selectedInstrument) },
                modifier = Modifier.fillMaxWidth()
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Current Routine Display
        if (currentRoutine != null) {
            RoutinePreviewCard(
                routine = currentRoutine,
                onStartPractice = onStartPractice,
                onSaveRoutine = onSaveRoutine
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        // Saved Routines Section
        if (savedRoutines.isNotEmpty()) {
            SavedRoutinesSection(
                savedRoutines = savedRoutines,
                onLoadRoutine = onLoadRoutine,
                onDeleteRoutine = onDeleteRoutine
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        // Schedules Section
        SchedulesSection(
            schedules = schedules,
            savedRoutines = savedRoutines,
            onCreateSchedule = onCreateSchedule,
            onDeleteSchedule = onDeleteSchedule,
            onAddRoutineToSchedule = onAddRoutineToSchedule,
            onRemoveRoutineFromSchedule = onRemoveRoutineFromSchedule,
            onLoadRoutine = onLoadRoutine
        )
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
    onStartPractice: () -> Unit,
    onSaveRoutine: ((String) -> Unit)? = null
) {
    var showSaveDialog by remember { mutableStateOf(false) }
    
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
        
        // Action buttons
        if (onSaveRoutine != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RpgButton(
                    text = "💾 Save",
                    onClick = { showSaveDialog = true },
                    modifier = Modifier.weight(1f),
                    color = RpgTheme.secondary
                )
                RpgButton(
                    text = "⚔️ Start Practice",
                    onClick = onStartPractice,
                    modifier = Modifier.weight(1f),
                    color = RpgTheme.success
                )
            }
        } else {
            RpgButton(
                text = "⚔️ Start Practice",
                onClick = onStartPractice,
                modifier = Modifier.fillMaxWidth(),
                color = RpgTheme.success
            )
        }
    }
    
    // Save routine dialog
    if (showSaveDialog && onSaveRoutine != null) {
        var routineName by remember { mutableStateOf("") }
        
        AlertDialog(
            onDismissRequest = { showSaveDialog = false },
            title = { Text("Save Routine", color = RpgTheme.textPrimary) },
            text = {
                Column {
                    Text("Enter a name for this routine:", color = RpgTheme.textSecondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = routineName,
                        onValueChange = { routineName = it },
                        placeholder = { Text("e.g., Morning Warm-up") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (routineName.isNotBlank()) {
                            onSaveRoutine(routineName)
                            showSaveDialog = false
                        }
                    },
                    enabled = routineName.isNotBlank()
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSaveDialog = false }) {
                    Text("Cancel")
                }
            }
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
                    text = "${exercise.instrument.emoji} ${exercise.instrument.displayName}",
                    color = RpgTheme.info
                )
                
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

/**
 * Section showing saved routines
 */
@Composable
fun SavedRoutinesSection(
    savedRoutines: List<SavedRoutine>,
    onLoadRoutine: (String) -> Unit,
    onDeleteRoutine: (String) -> Unit
) {
    var expandedRoutineId by remember { mutableStateOf<String?>(null) }
    
    RpgCard {
        RpgHeader(text = "📚 Saved Routines", modifier = Modifier.padding(bottom = 8.dp))
        RpgText(
            text = "${savedRoutines.size} saved routine(s)",
            color = RpgTheme.textSecondary,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        savedRoutines.forEach { savedRoutine ->
            val isExpanded = expandedRoutineId == savedRoutine.id
            
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = savedRoutine.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = RpgTheme.textPrimary
                        )
                        Text(
                            text = "${savedRoutine.routine.exercises.size} exercises • ${savedRoutine.routine.totalDurationMinutes} min",
                            fontSize = 14.sp,
                            color = RpgTheme.textSecondary
                        )
                    }
                    
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        IconButton(onClick = { expandedRoutineId = if (isExpanded) null else savedRoutine.id }) {
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = if (isExpanded) "Collapse" else "Expand",
                                tint = RpgTheme.textPrimary
                            )
                        }
                        IconButton(onClick = { onLoadRoutine(savedRoutine.id) }) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Load",
                                tint = RpgTheme.success
                            )
                        }
                        IconButton(onClick = { onDeleteRoutine(savedRoutine.id) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = RpgTheme.danger
                            )
                        }
                    }
                }
                
                if (isExpanded) {
                    Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)) {
                        savedRoutine.routine.exercises.forEachIndexed { index, exercise ->
                            Text(
                                text = "${index + 1}. ${exercise.name} (${exercise.durationMinutes} min)",
                                fontSize = 14.sp,
                                color = RpgTheme.textSecondary,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
                
                if (savedRoutine != savedRoutines.last()) {
                    Divider(
                        color = RpgTheme.border,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

/**
 * Section for managing schedules
 */
@Composable
fun SchedulesSection(
    schedules: List<Schedule>,
    savedRoutines: List<SavedRoutine>,
    onCreateSchedule: (String, String) -> Unit,
    onDeleteSchedule: (String) -> Unit,
    onAddRoutineToSchedule: (String, String) -> Unit,
    onRemoveRoutineFromSchedule: (String, String) -> Unit,
    onLoadRoutine: (String) -> Unit
) {
    var showCreateDialog by remember { mutableStateOf(false) }
    var expandedScheduleId by remember { mutableStateOf<String?>(null) }
    var showAddRoutineDialog by remember { mutableStateOf<String?>(null) }
    
    RpgCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RpgHeader(text = "🗓️ Schedules", modifier = Modifier.padding(bottom = 8.dp))
            IconButton(onClick = { showCreateDialog = true }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create Schedule",
                    tint = RpgTheme.accent
                )
            }
        }
        
        if (schedules.isEmpty()) {
            RpgText(
                text = "No schedules yet. Create one to organize your routines!",
                color = RpgTheme.textSecondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        } else {
            RpgText(
                text = "${schedules.size} schedule(s)",
                color = RpgTheme.textSecondary,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            schedules.forEach { schedule ->
                val isExpanded = expandedScheduleId == schedule.id
                val routinesInSchedule = savedRoutines.filter { schedule.routineIds.contains(it.id) }
                
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = schedule.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = RpgTheme.textPrimary
                            )
                            if (schedule.description.isNotBlank()) {
                                Text(
                                    text = schedule.description,
                                    fontSize = 14.sp,
                                    color = RpgTheme.textSecondary
                                )
                            }
                            Text(
                                text = "${routinesInSchedule.size} routine(s)",
                                fontSize = 14.sp,
                                color = RpgTheme.textSecondary
                            )
                        }
                        
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            IconButton(onClick = { expandedScheduleId = if (isExpanded) null else schedule.id }) {
                                Icon(
                                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = RpgTheme.textPrimary
                                )
                            }
                            IconButton(onClick = { showAddRoutineDialog = schedule.id }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Routine",
                                    tint = RpgTheme.success
                                )
                            }
                            IconButton(onClick = { onDeleteSchedule(schedule.id) }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = RpgTheme.danger
                                )
                            }
                        }
                    }
                    
                    if (isExpanded) {
                        Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)) {
                            if (routinesInSchedule.isEmpty()) {
                                Text(
                                    text = "No routines in this schedule",
                                    fontSize = 14.sp,
                                    color = RpgTheme.textSecondary,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            } else {
                                routinesInSchedule.forEach { routine ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = routine.name,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = RpgTheme.textPrimary
                                            )
                                            Text(
                                                text = "${routine.routine.exercises.size} exercises • ${routine.routine.totalDurationMinutes} min",
                                                fontSize = 12.sp,
                                                color = RpgTheme.textSecondary
                                            )
                                        }
                                        Row {
                                            IconButton(onClick = { onLoadRoutine(routine.id) }) {
                                                Icon(
                                                    imageVector = Icons.Default.PlayArrow,
                                                    contentDescription = "Load",
                                                    tint = RpgTheme.success,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                            IconButton(onClick = { onRemoveRoutineFromSchedule(schedule.id, routine.id) }) {
                                                Icon(
                                                    imageVector = Icons.Default.Close,
                                                    contentDescription = "Remove",
                                                    tint = RpgTheme.danger,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    if (schedule != schedules.last()) {
                        Divider(
                            color = RpgTheme.border,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
    
    // Create schedule dialog
    if (showCreateDialog) {
        var scheduleName by remember { mutableStateOf("") }
        var scheduleDescription by remember { mutableStateOf("") }
        
        AlertDialog(
            onDismissRequest = { showCreateDialog = false },
            title = { Text("Create Schedule", color = RpgTheme.textPrimary) },
            text = {
                Column {
                    Text("Enter schedule details:", color = RpgTheme.textSecondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = scheduleName,
                        onValueChange = { scheduleName = it },
                        placeholder = { Text("Schedule name") },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = scheduleDescription,
                        onValueChange = { scheduleDescription = it },
                        placeholder = { Text("Description (optional)") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (scheduleName.isNotBlank()) {
                            onCreateSchedule(scheduleName, scheduleDescription)
                            showCreateDialog = false
                        }
                    },
                    enabled = scheduleName.isNotBlank()
                ) {
                    Text("Create")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
    
    // Add routine to schedule dialog
    showAddRoutineDialog?.let { scheduleId ->
        AlertDialog(
            onDismissRequest = { showAddRoutineDialog = null },
            title = { Text("Add Routine to Schedule", color = RpgTheme.textPrimary) },
            text = {
                Column {
                    Text("Select a routine to add:", color = RpgTheme.textSecondary)
                    Spacer(modifier = Modifier.height(8.dp))
                    if (savedRoutines.isEmpty()) {
                        Text("No saved routines available", color = RpgTheme.textSecondary)
                    } else {
                        savedRoutines.forEach { routine ->
                            TextButton(
                                onClick = {
                                    onAddRoutineToSchedule(scheduleId, routine.id)
                                    showAddRoutineDialog = null
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "${routine.name} (${routine.routine.totalDurationMinutes} min)",
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showAddRoutineDialog = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}

/**
 * Section showing today's scheduled routine with day navigation
 */
@Composable
fun TodaysRoutineSection(
    calendarSchedules: List<CalendarSchedule>,
    savedRoutines: List<SavedRoutine>,
    currentViewingDate: Long,
    onNavigatePreviousDay: () -> Unit,
    onNavigateNextDay: () -> Unit,
    onNavigateToday: () -> Unit,
    onLoadRoutine: (String) -> Unit,
    onMarkCompleted: (String) -> Unit,
    onStartPractice: () -> Unit
) {
    val dateFormat = SimpleDateFormat("EEEE, MMM dd, yyyy", Locale.US)
    val viewingDateStr = dateFormat.format(Date(currentViewingDate))
    
    // Check if viewing date is today
    val todayCalendar = java.util.Calendar.getInstance()
    val viewingCalendar = java.util.Calendar.getInstance().apply { timeInMillis = currentViewingDate }
    val isToday = todayCalendar.get(java.util.Calendar.YEAR) == viewingCalendar.get(java.util.Calendar.YEAR) &&
                  todayCalendar.get(java.util.Calendar.DAY_OF_YEAR) == viewingCalendar.get(java.util.Calendar.DAY_OF_YEAR)
    
    // Find schedule for current viewing date
    val scheduleForDate = calendarSchedules.firstOrNull { schedule ->
        val scheduleCalendar = java.util.Calendar.getInstance().apply { timeInMillis = schedule.date }
        scheduleCalendar.get(java.util.Calendar.YEAR) == viewingCalendar.get(java.util.Calendar.YEAR) &&
        scheduleCalendar.get(java.util.Calendar.DAY_OF_YEAR) == viewingCalendar.get(java.util.Calendar.DAY_OF_YEAR)
    }
    
    val routine = scheduleForDate?.let { schedule ->
        savedRoutines.find { it.id == schedule.routineId }
    }
    
    RpgCard {
        // Header with date navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigatePreviousDay) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous Day",
                    tint = RpgTheme.textPrimary
                )
            }
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (isToday) "📅 Today's Routine" else "📅 Scheduled Routine",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.textPrimary
                )
                Text(
                    text = viewingDateStr,
                    fontSize = 13.sp,
                    color = RpgTheme.textSecondary
                )
            }
            
            IconButton(onClick = onNavigateNextDay) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next Day",
                    tint = RpgTheme.textPrimary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        if (!isToday) {
            RpgButton(
                text = "Go to Today",
                onClick = onNavigateToday,
                modifier = Modifier.fillMaxWidth(),
                color = RpgTheme.secondary,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        
        if (routine != null && scheduleForDate != null) {
            // Show routine details
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = routine.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = RpgTheme.textPrimary
                    )
                    if (scheduleForDate.isCompleted) {
                        RpgBadge(
                            text = "✓ Completed",
                            color = RpgTheme.success
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "${routine.routine.exercises.size} exercises • ${routine.routine.totalDurationMinutes} minutes",
                    fontSize = 14.sp,
                    color = RpgTheme.textSecondary
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Show first few exercises
                routine.routine.exercises.take(3).forEachIndexed { index, exercise ->
                    Text(
                        text = "${index + 1}. ${exercise.name} (${exercise.durationMinutes} min)",
                        fontSize = 13.sp,
                        color = RpgTheme.textSecondary,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
                
                if (routine.routine.exercises.size > 3) {
                    Text(
                        text = "... and ${routine.routine.exercises.size - 3} more",
                        fontSize = 13.sp,
                        color = RpgTheme.textSecondary,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RpgButton(
                        text = "View Details",
                        onClick = { onLoadRoutine(routine.id) },
                        modifier = Modifier.weight(1f),
                        color = RpgTheme.secondary
                    )
                    RpgButton(
                        text = "⚔️ Start",
                        onClick = { 
                            onLoadRoutine(routine.id)
                            onStartPractice()
                        },
                        modifier = Modifier.weight(1f),
                        color = RpgTheme.success
                    )
                }
                
                if (!scheduleForDate.isCompleted) {
                    Spacer(modifier = Modifier.height(8.dp))
                    RpgButton(
                        text = "Mark as Completed",
                        onClick = { onMarkCompleted(scheduleForDate.id) },
                        modifier = Modifier.fillMaxWidth(),
                        color = RpgTheme.info,
                        fontSize = 13.sp
                    )
                }
            }
        } else {
            // No routine scheduled for this day
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "No routine scheduled for this day",
                    fontSize = 14.sp,
                    color = RpgTheme.textSecondary,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}
