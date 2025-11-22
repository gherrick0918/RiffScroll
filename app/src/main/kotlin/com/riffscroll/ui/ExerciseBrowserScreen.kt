package com.riffscroll.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riffscroll.data.DifficultyLevel
import com.riffscroll.data.Exercise
import com.riffscroll.data.ExerciseCategory
import com.riffscroll.data.InstrumentType

/**
 * Exercise Browser Screen - Search and filter through all available exercises
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseBrowserScreen(
    allExercises: List<Exercise>,
    onBack: () -> Unit,
    onAddToRoutine: ((Exercise) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedInstrument by remember { mutableStateOf<InstrumentType?>(null) }
    var selectedCategory by remember { mutableStateOf<ExerciseCategory?>(null) }
    var selectedDifficulty by remember { mutableStateOf<DifficultyLevel?>(null) }
    var showTimedOnly by remember { mutableStateOf(false) }
    var minDuration by remember { mutableStateOf(0) }
    var maxDuration by remember { mutableStateOf(60) }
    var showFilters by remember { mutableStateOf(false) }
    var selectedExercise by remember { mutableStateOf<Exercise?>(null) }

    // Apply filters
    val filteredExercises = remember(
        searchQuery,
        selectedInstrument,
        selectedCategory,
        selectedDifficulty,
        showTimedOnly,
        minDuration,
        maxDuration
    ) {
        allExercises.filter { exercise ->
            // Search query filter
            val matchesSearch = if (searchQuery.isBlank()) {
                true
            } else {
                exercise.name.contains(searchQuery, ignoreCase = true) ||
                        exercise.description.contains(searchQuery, ignoreCase = true) ||
                        exercise.instructions.any { it.contains(searchQuery, ignoreCase = true) }
            }

            // Instrument filter
            val matchesInstrument = selectedInstrument == null || exercise.instrument == selectedInstrument

            // Category filter
            val matchesCategory = selectedCategory == null || exercise.category == selectedCategory

            // Difficulty filter
            val matchesDifficulty = selectedDifficulty == null || exercise.difficulty == selectedDifficulty

            // Timed filter
            val matchesTimed = !showTimedOnly || exercise.hasTiming

            // Duration filter
            val matchesDuration = exercise.durationMinutes >= minDuration && exercise.durationMinutes <= maxDuration

            matchesSearch && matchesInstrument && matchesCategory && matchesDifficulty && matchesTimed && matchesDuration
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RpgTheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            title = { 
                Text(
                    "📚 Exercise Browser",
                    color = RpgTheme.textPrimary,
                    fontWeight = FontWeight.Bold
                ) 
            },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = RpgTheme.textPrimary
                    )
                }
            },
            actions = {
                IconButton(onClick = { showFilters = !showFilters }) {
                    Icon(
                        imageVector = if (showFilters) Icons.Default.FilterAltOff else Icons.Default.FilterAlt,
                        contentDescription = if (showFilters) "Hide Filters" else "Show Filters",
                        tint = if (hasActiveFilters(selectedInstrument, selectedCategory, selectedDifficulty, showTimedOnly, minDuration, maxDuration)) 
                            RpgTheme.accent else RpgTheme.textPrimary
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = RpgTheme.surface
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Search Bar
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search exercises...", color = RpgTheme.textSecondary) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = RpgTheme.textSecondary
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = RpgTheme.textSecondary
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = RpgTheme.surface,
                    unfocusedContainerColor = RpgTheme.surface,
                    focusedTextColor = RpgTheme.textPrimary,
                    unfocusedTextColor = RpgTheme.textPrimary
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Results count
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${filteredExercises.size} exercise${if (filteredExercises.size != 1) "s" else ""} found",
                    color = RpgTheme.textSecondary,
                    fontSize = 14.sp
                )
                
                // Clear all filters button
                if (hasActiveFilters(selectedInstrument, selectedCategory, selectedDifficulty, showTimedOnly, minDuration, maxDuration) || searchQuery.isNotEmpty()) {
                    TextButton(
                        onClick = {
                            searchQuery = ""
                            selectedInstrument = null
                            selectedCategory = null
                            selectedDifficulty = null
                            showTimedOnly = false
                            minDuration = 0
                            maxDuration = 60
                        }
                    ) {
                        Text("Clear All", color = RpgTheme.accent, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Filter Panel
            if (showFilters) {
                FilterPanel(
                    selectedInstrument = selectedInstrument,
                    onInstrumentChange = { selectedInstrument = it },
                    selectedCategory = selectedCategory,
                    onCategoryChange = { selectedCategory = it },
                    selectedDifficulty = selectedDifficulty,
                    onDifficultyChange = { selectedDifficulty = it },
                    showTimedOnly = showTimedOnly,
                    onTimedOnlyChange = { showTimedOnly = it },
                    minDuration = minDuration,
                    onMinDurationChange = { minDuration = it },
                    maxDuration = maxDuration,
                    onMaxDurationChange = { maxDuration = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Exercise List
            if (filteredExercises.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "🔍",
                            fontSize = 48.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = "No exercises found",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = RpgTheme.textPrimary,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Try adjusting your filters or search query",
                            fontSize = 14.sp,
                            color = RpgTheme.textSecondary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredExercises) { exercise ->
                        ExerciseCard(
                            exercise = exercise,
                            onClick = { selectedExercise = exercise },
                            onAddToRoutine = onAddToRoutine
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }

    // Exercise Detail Dialog
    selectedExercise?.let { exercise ->
        ExerciseDetailDialog(
            exercise = exercise,
            onDismiss = { selectedExercise = null },
            onAddToRoutine = onAddToRoutine
        )
    }
}

/**
 * Filter Panel with all filter options
 */
@Composable
fun FilterPanel(
    selectedInstrument: InstrumentType?,
    onInstrumentChange: (InstrumentType?) -> Unit,
    selectedCategory: ExerciseCategory?,
    onCategoryChange: (ExerciseCategory?) -> Unit,
    selectedDifficulty: DifficultyLevel?,
    onDifficultyChange: (DifficultyLevel?) -> Unit,
    showTimedOnly: Boolean,
    onTimedOnlyChange: (Boolean) -> Unit,
    minDuration: Int,
    onMinDurationChange: (Int) -> Unit,
    maxDuration: Int,
    onMaxDurationChange: (Int) -> Unit
) {
    RpgCard {
        Column {
            Text(
                text = "Filters",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RpgTheme.textPrimary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Instrument Filter
            Text(
                text = "Instrument",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = RpgTheme.textPrimary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedInstrument == null,
                    onClick = { onInstrumentChange(null) },
                    label = { Text("All", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedInstrument == InstrumentType.GUITAR,
                    onClick = { onInstrumentChange(InstrumentType.GUITAR) },
                    label = { Text("${InstrumentType.GUITAR.emoji} Guitar", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedInstrument == InstrumentType.PIANO,
                    onClick = { onInstrumentChange(InstrumentType.PIANO) },
                    label = { Text("${InstrumentType.PIANO.emoji} Piano", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Category Filter
            Text(
                text = "Category",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = RpgTheme.textPrimary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedCategory == null,
                    onClick = { onCategoryChange(null) },
                    label = { Text("All", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedCategory == ExerciseCategory.TECHNIQUE,
                    onClick = { onCategoryChange(ExerciseCategory.TECHNIQUE) },
                    label = { Text("⚡ Tech", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedCategory == ExerciseCategory.CREATIVITY,
                    onClick = { onCategoryChange(ExerciseCategory.CREATIVITY) },
                    label = { Text("🎨 Creative", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedCategory == ExerciseCategory.SONGS,
                    onClick = { onCategoryChange(ExerciseCategory.SONGS) },
                    label = { Text("🎵 Songs", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Difficulty Filter
            Text(
                text = "Difficulty",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = RpgTheme.textPrimary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedDifficulty == null,
                    onClick = { onDifficultyChange(null) },
                    label = { Text("All", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedDifficulty == DifficultyLevel.BEGINNER,
                    onClick = { onDifficultyChange(DifficultyLevel.BEGINNER) },
                    label = { Text("Beginner", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedDifficulty == DifficultyLevel.INTERMEDIATE,
                    onClick = { onDifficultyChange(DifficultyLevel.INTERMEDIATE) },
                    label = { Text("Intermediate", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
                FilterChip(
                    selected = selectedDifficulty == DifficultyLevel.ADVANCED,
                    onClick = { onDifficultyChange(DifficultyLevel.ADVANCED) },
                    label = { Text("Advanced", fontSize = 12.sp) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Duration Filter
            Text(
                text = "Duration: $minDuration - $maxDuration minutes",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = RpgTheme.textPrimary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            RangeSlider(
                value = minDuration.toFloat()..maxDuration.toFloat(),
                onValueChange = { range ->
                    onMinDurationChange(range.start.toInt())
                    onMaxDurationChange(range.endInclusive.toInt())
                },
                valueRange = 0f..60f,
                steps = 11,
                colors = SliderDefaults.colors(
                    thumbColor = RpgTheme.accent,
                    activeTrackColor = RpgTheme.primary,
                    inactiveTrackColor = RpgTheme.secondary
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Timed Only Filter
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Metronome/Timed only",
                    fontSize = 14.sp,
                    color = RpgTheme.textPrimary
                )
                Switch(
                    checked = showTimedOnly,
                    onCheckedChange = onTimedOnlyChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = RpgTheme.accent,
                        checkedTrackColor = RpgTheme.primary
                    )
                )
            }
        }
    }
}

/**
 * Exercise Card in the list
 */
@Composable
fun ExerciseCard(
    exercise: Exercise,
    onClick: () -> Unit,
    onAddToRoutine: ((Exercise) -> Unit)?
) {
    RpgCard(
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = exercise.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.textPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = exercise.description,
                    fontSize = 14.sp,
                    color = RpgTheme.textSecondary,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RpgBadge(
                        text = "${exercise.instrument.emoji} ${exercise.instrument.displayName}",
                        color = RpgTheme.info,
                        fontSize = 11.sp
                    )

                    RpgBadge(
                        text = when (exercise.category) {
                            ExerciseCategory.TECHNIQUE -> "⚡"
                            ExerciseCategory.CREATIVITY -> "🎨"
                            ExerciseCategory.SONGS -> "🎵"
                        },
                        color = when (exercise.category) {
                            ExerciseCategory.TECHNIQUE -> RpgTheme.primary
                            ExerciseCategory.CREATIVITY -> RpgTheme.accent
                            ExerciseCategory.SONGS -> RpgTheme.success
                        },
                        fontSize = 11.sp
                    )

                    RpgBadge(
                        text = exercise.difficulty.displayName,
                        color = when (exercise.difficulty) {
                            DifficultyLevel.BEGINNER -> RpgTheme.success
                            DifficultyLevel.INTERMEDIATE -> RpgTheme.warning
                            DifficultyLevel.ADVANCED -> RpgTheme.danger
                        },
                        fontSize = 11.sp
                    )

                    RpgBadge(
                        text = "${exercise.durationMinutes}m",
                        color = RpgTheme.secondary,
                        fontSize = 11.sp
                    )

                    if (exercise.hasTiming && exercise.bpm != null) {
                        RpgBadge(
                            text = "♩ ${exercise.bpm}",
                            color = RpgTheme.secondary,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            if (onAddToRoutine != null) {
                IconButton(onClick = { onAddToRoutine(exercise) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to routine",
                        tint = RpgTheme.success
                    )
                }
            }
        }
    }
}

/**
 * Exercise Detail Dialog
 */
@Composable
fun ExerciseDetailDialog(
    exercise: Exercise,
    onDismiss: () -> Unit,
    onAddToRoutine: ((Exercise) -> Unit)?
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = exercise.name,
                color = RpgTheme.textPrimary,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Badges
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    RpgBadge(
                        text = "${exercise.instrument.emoji} ${exercise.instrument.displayName}",
                        color = RpgTheme.info,
                        fontSize = 11.sp
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
                        },
                        fontSize = 11.sp
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    RpgBadge(
                        text = exercise.difficulty.displayName,
                        color = when (exercise.difficulty) {
                            DifficultyLevel.BEGINNER -> RpgTheme.success
                            DifficultyLevel.INTERMEDIATE -> RpgTheme.warning
                            DifficultyLevel.ADVANCED -> RpgTheme.danger
                        },
                        fontSize = 11.sp
                    )
                    RpgBadge(
                        text = "${exercise.durationMinutes} minutes",
                        color = RpgTheme.secondary,
                        fontSize = 11.sp
                    )
                    if (exercise.hasTiming && exercise.bpm != null) {
                        RpgBadge(
                            text = "♩ ${exercise.bpm} BPM",
                            color = RpgTheme.secondary,
                            fontSize = 11.sp
                        )
                    }
                }

                // Description
                Text(
                    text = "Description",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.textPrimary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = exercise.description,
                    fontSize = 14.sp,
                    color = RpgTheme.textSecondary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Instructions
                if (exercise.instructions.isNotEmpty()) {
                    Text(
                        text = "Instructions",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = RpgTheme.textPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    exercise.instructions.forEachIndexed { index, instruction ->
                        Row(
                            modifier = Modifier.padding(bottom = 8.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "${index + 1}.",
                                fontSize = 14.sp,
                                color = RpgTheme.accent,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = instruction,
                                fontSize = 14.sp,
                                color = RpgTheme.textSecondary
                            )
                        }
                    }
                }

                // Tablature
                if (!exercise.tablature.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tablature",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = RpgTheme.textPrimary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Surface(
                        color = RpgTheme.background,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = exercise.tablature,
                            fontSize = 12.sp,
                            color = RpgTheme.textPrimary,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            if (onAddToRoutine != null) {
                Button(
                    onClick = {
                        onAddToRoutine(exercise)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RpgTheme.success)
                ) {
                    Text("Add to Routine")
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Close", color = RpgTheme.textSecondary)
            }
        }
    )
}

/**
 * Helper function to check if any filters are active
 */
private fun hasActiveFilters(
    selectedInstrument: InstrumentType?,
    selectedCategory: ExerciseCategory?,
    selectedDifficulty: DifficultyLevel?,
    showTimedOnly: Boolean,
    minDuration: Int,
    maxDuration: Int
): Boolean {
    return selectedInstrument != null ||
            selectedCategory != null ||
            selectedDifficulty != null ||
            showTimedOnly ||
            minDuration > 0 ||
            maxDuration < 60
}
