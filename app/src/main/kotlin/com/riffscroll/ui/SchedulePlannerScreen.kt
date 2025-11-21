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
import com.riffscroll.data.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Screen for creating automatic practice schedule plans
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchedulePlannerScreen(
    practiceSchedulePlans: List<PracticeSchedulePlan>,
    savedRoutines: List<SavedRoutine>,
    onCreatePlan: (String, Long, Long, InstrumentType?, Int, DifficultyLevel?) -> Unit,
    onDeletePlan: (String) -> Unit,
    onLoadRoutine: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showCreateDialog by remember { mutableStateOf(false) }
    var expandedPlanId by remember { mutableStateOf<String?>(null) }
    
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
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = RpgTheme.textPrimary
                )
            }
            RpgHeader(text = "📅 Auto Schedule Planner")
            Spacer(modifier = Modifier.width(48.dp)) // Balance the back button
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Description Card
        RpgCard {
            Column {
                Text(
                    text = "Automatic Practice Scheduling",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.textPrimary
                )
                Spacer(modifier = Modifier.height(8.dp))
                RpgText(
                    text = "Create a multi-day practice plan that automatically generates unique routines for each day. Perfect for maintaining consistent practice habits!",
                    color = RpgTheme.textSecondary
                )
                Spacer(modifier = Modifier.height(16.dp))
                RpgButton(
                    text = "✨ Create New Schedule Plan",
                    onClick = { showCreateDialog = true },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Schedule Plans List
        if (practiceSchedulePlans.isNotEmpty()) {
            RpgCard {
                RpgHeader(text = "Your Schedule Plans", modifier = Modifier.padding(bottom = 8.dp))
                RpgText(
                    text = "${practiceSchedulePlans.size} schedule plan(s)",
                    color = RpgTheme.textSecondary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                practiceSchedulePlans.forEach { plan ->
                    SchedulePlanItem(
                        plan = plan,
                        savedRoutines = savedRoutines,
                        isExpanded = expandedPlanId == plan.id,
                        onToggleExpand = { expandedPlanId = if (expandedPlanId == plan.id) null else plan.id },
                        onLoadRoutine = onLoadRoutine,
                        onDeletePlan = onDeletePlan
                    )
                    
                    if (plan != practiceSchedulePlans.last()) {
                        Divider(
                            color = RpgTheme.border,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                }
            }
        }
    }
    
    // Create Plan Dialog
    if (showCreateDialog) {
        CreateSchedulePlanDialog(
            onDismiss = { showCreateDialog = false },
            onConfirm = { name, startDate, endDate, instrument, duration, difficulty ->
                onCreatePlan(name, startDate, endDate, instrument, duration, difficulty)
                showCreateDialog = false
            }
        )
    }
}

/**
 * Individual schedule plan item
 */
@Composable
fun SchedulePlanItem(
    plan: PracticeSchedulePlan,
    savedRoutines: List<SavedRoutine>,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onLoadRoutine: (String) -> Unit,
    onDeletePlan: (String) -> Unit
) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
    val startDateStr = dateFormat.format(Date(plan.startDate))
    val endDateStr = dateFormat.format(Date(plan.endDate))
    
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = plan.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.textPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$startDateStr - $endDateStr",
                    fontSize = 14.sp,
                    color = RpgTheme.textSecondary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    RpgBadge(
                        text = "${plan.scheduleEntries.size} days",
                        color = RpgTheme.info
                    )
                    if (plan.instrument != null) {
                        RpgBadge(
                            text = "${plan.instrument.emoji} ${plan.instrument.displayName}",
                            color = RpgTheme.secondary
                        )
                    } else {
                        RpgBadge(
                            text = "🎸🎹 Both",
                            color = RpgTheme.secondary
                        )
                    }
                    RpgBadge(
                        text = "${plan.targetDurationMinutes} min",
                        color = RpgTheme.secondary
                    )
                    if (plan.difficulty != null) {
                        RpgBadge(
                            text = plan.difficulty.displayName,
                            color = when (plan.difficulty) {
                                DifficultyLevel.BEGINNER -> RpgTheme.success
                                DifficultyLevel.INTERMEDIATE -> RpgTheme.warning
                                DifficultyLevel.ADVANCED -> RpgTheme.danger
                            }
                        )
                    }
                }
            }
            
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                IconButton(onClick = onToggleExpand) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                        tint = RpgTheme.textPrimary
                    )
                }
                IconButton(onClick = { onDeletePlan(plan.id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = RpgTheme.danger
                    )
                }
            }
        }
        
        if (isExpanded) {
            Spacer(modifier = Modifier.height(12.dp))
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = "Daily Routines:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.textPrimary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                plan.scheduleEntries.forEach { entry ->
                    val routine = savedRoutines.find { it.id == entry.routineId }
                    if (routine != null) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = dateFormat.format(Date(entry.date)),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = RpgTheme.textPrimary
                                )
                                Text(
                                    text = "${routine.routine.exercises.size} exercises • ${routine.routine.totalDurationMinutes} min",
                                    fontSize = 12.sp,
                                    color = RpgTheme.textSecondary
                                )
                                if (entry.isCompleted) {
                                    Text(
                                        text = "✓ Completed",
                                        fontSize = 11.sp,
                                        color = RpgTheme.success
                                    )
                                }
                            }
                            IconButton(onClick = { onLoadRoutine(routine.id) }) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "Load",
                                    tint = RpgTheme.success,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Dialog for creating a new schedule plan
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateSchedulePlanDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, Long, Long, InstrumentType?, Int, DifficultyLevel?) -> Unit
) {
    var planName by remember { mutableStateOf("") }
    var selectedInstrument by remember { mutableStateOf<InstrumentType?>(null) }
    var selectedDifficulty by remember { mutableStateOf<DifficultyLevel?>(null) }
    var selectedDuration by remember { mutableStateOf(45) }
    var numberOfDays by remember { mutableStateOf(7) }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Create Schedule Plan", color = RpgTheme.textPrimary, fontSize = 20.sp)
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Plan Name
                Text("Plan Name:", color = RpgTheme.textSecondary, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = planName,
                    onValueChange = { planName = it },
                    placeholder = { Text("e.g., Weekly Guitar Practice") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Number of Days
                Text(
                    "Number of Days: $numberOfDays",
                    color = RpgTheme.textSecondary,
                    fontSize = 14.sp
                )
                Slider(
                    value = numberOfDays.toFloat(),
                    onValueChange = { numberOfDays = it.toInt() },
                    valueRange = 1f..30f,
                    steps = 28,
                    colors = SliderDefaults.colors(
                        thumbColor = RpgTheme.accent,
                        activeTrackColor = RpgTheme.primary,
                        inactiveTrackColor = RpgTheme.secondary
                    )
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Instrument Selection
                Text("Instrument:", color = RpgTheme.textSecondary, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RpgButton(
                        text = "Both",
                        onClick = { selectedInstrument = null },
                        modifier = Modifier.weight(1f),
                        color = if (selectedInstrument == null) RpgTheme.primary else RpgTheme.secondary,
                        fontSize = 12.sp
                    )
                    RpgButton(
                        text = "🎸",
                        onClick = { selectedInstrument = InstrumentType.GUITAR },
                        modifier = Modifier.weight(1f),
                        color = if (selectedInstrument == InstrumentType.GUITAR) RpgTheme.primary else RpgTheme.secondary,
                        fontSize = 12.sp
                    )
                    RpgButton(
                        text = "🎹",
                        onClick = { selectedInstrument = InstrumentType.PIANO },
                        modifier = Modifier.weight(1f),
                        color = if (selectedInstrument == InstrumentType.PIANO) RpgTheme.primary else RpgTheme.secondary,
                        fontSize = 12.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Difficulty Selection
                Text("Difficulty:", color = RpgTheme.textSecondary, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RpgButton(
                        text = "All",
                        onClick = { selectedDifficulty = null },
                        modifier = Modifier.weight(1f),
                        color = if (selectedDifficulty == null) RpgTheme.primary else RpgTheme.secondary,
                        fontSize = 11.sp
                    )
                    RpgButton(
                        text = "Begin",
                        onClick = { selectedDifficulty = DifficultyLevel.BEGINNER },
                        modifier = Modifier.weight(1f),
                        color = if (selectedDifficulty == DifficultyLevel.BEGINNER) RpgTheme.primary else RpgTheme.secondary,
                        fontSize = 11.sp
                    )
                    RpgButton(
                        text = "Inter",
                        onClick = { selectedDifficulty = DifficultyLevel.INTERMEDIATE },
                        modifier = Modifier.weight(1f),
                        color = if (selectedDifficulty == DifficultyLevel.INTERMEDIATE) RpgTheme.primary else RpgTheme.secondary,
                        fontSize = 11.sp
                    )
                    RpgButton(
                        text = "Adv",
                        onClick = { selectedDifficulty = DifficultyLevel.ADVANCED },
                        modifier = Modifier.weight(1f),
                        color = if (selectedDifficulty == DifficultyLevel.ADVANCED) RpgTheme.primary else RpgTheme.secondary,
                        fontSize = 11.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Duration
                Text(
                    "Daily Duration: $selectedDuration minutes",
                    color = RpgTheme.textSecondary,
                    fontSize = 14.sp
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
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Summary
                Text(
                    "This will create $numberOfDays unique routines, one for each day.",
                    color = RpgTheme.textSecondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (planName.isNotBlank()) {
                        val startDate = System.currentTimeMillis()
                        val endDate = startDate + ((numberOfDays - 1) * 24 * 60 * 60 * 1000L)
                        onConfirm(planName, startDate, endDate, selectedInstrument, selectedDuration, selectedDifficulty)
                    }
                },
                enabled = planName.isNotBlank()
            ) {
                Text("Create")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
