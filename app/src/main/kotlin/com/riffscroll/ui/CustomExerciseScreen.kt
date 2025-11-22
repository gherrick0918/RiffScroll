package com.riffscroll.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.riffscroll.data.*

/**
 * Screen for creating or editing custom exercises
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomExerciseScreen(
    existingExercise: Exercise? = null,
    onSave: (
        name: String,
        description: String,
        category: ExerciseCategory,
        instrument: InstrumentType,
        durationMinutes: Int,
        difficulty: DifficultyLevel,
        hasTiming: Boolean,
        bpm: Int?,
        instructions: List<String>,
        tablature: String?
    ) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf(existingExercise?.name ?: "") }
    var description by remember { mutableStateOf(existingExercise?.description ?: "") }
    var category by remember { mutableStateOf(existingExercise?.category ?: ExerciseCategory.TECHNIQUE) }
    var instrument by remember { mutableStateOf(existingExercise?.instrument ?: InstrumentType.GUITAR) }
    var durationMinutes by remember { mutableStateOf(existingExercise?.durationMinutes?.toString() ?: "5") }
    var difficulty by remember { mutableStateOf(existingExercise?.difficulty ?: DifficultyLevel.BEGINNER) }
    var hasTiming by remember { mutableStateOf(existingExercise?.hasTiming ?: false) }
    var bpm by remember { mutableStateOf(existingExercise?.bpm?.toString() ?: "60") }
    var tablature by remember { mutableStateOf(existingExercise?.tablature ?: "") }
    
    // Instructions as separate text fields
    var instruction1 by remember { mutableStateOf(existingExercise?.instructions?.getOrNull(0) ?: "") }
    var instruction2 by remember { mutableStateOf(existingExercise?.instructions?.getOrNull(1) ?: "") }
    var instruction3 by remember { mutableStateOf(existingExercise?.instructions?.getOrNull(2) ?: "") }
    var instruction4 by remember { mutableStateOf(existingExercise?.instructions?.getOrNull(3) ?: "") }
    var instruction5 by remember { mutableStateOf(existingExercise?.instructions?.getOrNull(4) ?: "") }
    var instruction6 by remember { mutableStateOf(existingExercise?.instructions?.getOrNull(5) ?: "") }
    
    // Category dropdown state
    var categoryExpanded by remember { mutableStateOf(false) }
    var instrumentExpanded by remember { mutableStateOf(false) }
    var difficultyExpanded by remember { mutableStateOf(false) }
    
    var showValidationError by remember { mutableStateOf(false) }
    var validationMessage by remember { mutableStateOf("") }

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
            RpgHeader(
                text = if (existingExercise == null) "⚔️ Create Exercise" else "⚔️ Edit Exercise",
                modifier = Modifier.padding(bottom = 16.dp)
            )
            IconButton(onClick = onCancel) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cancel",
                    tint = RpgTheme.textPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Form in RPG Card
        RpgCard {
            Column(modifier = Modifier.padding(8.dp)) {
                // Name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Exercise Name *", color = RpgTheme.textSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary,
                        focusedBorderColor = RpgTheme.primary,
                        unfocusedBorderColor = RpgTheme.textSecondary,
                        cursorColor = RpgTheme.primary
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Description
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description *", color = RpgTheme.textSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary,
                        focusedBorderColor = RpgTheme.primary,
                        unfocusedBorderColor = RpgTheme.textSecondary,
                        cursorColor = RpgTheme.primary
                    ),
                    minLines = 2,
                    maxLines = 4
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Category Dropdown
                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = it }
                ) {
                    OutlinedTextField(
                        value = category.name.lowercase().replaceFirstChar { it.uppercase() },
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Category *", color = RpgTheme.textSecondary) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = RpgTheme.textPrimary,
                            unfocusedTextColor = RpgTheme.textPrimary,
                            focusedBorderColor = RpgTheme.primary,
                            unfocusedBorderColor = RpgTheme.textSecondary
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        ExerciseCategory.values().forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat.name.lowercase().replaceFirstChar { it.uppercase() }) },
                                onClick = {
                                    category = cat
                                    categoryExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Instrument Dropdown
                ExposedDropdownMenuBox(
                    expanded = instrumentExpanded,
                    onExpandedChange = { instrumentExpanded = it }
                ) {
                    OutlinedTextField(
                        value = "${instrument.emoji} ${instrument.displayName}",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Instrument *", color = RpgTheme.textSecondary) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = instrumentExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = RpgTheme.textPrimary,
                            unfocusedTextColor = RpgTheme.textPrimary,
                            focusedBorderColor = RpgTheme.primary,
                            unfocusedBorderColor = RpgTheme.textSecondary
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = instrumentExpanded,
                        onDismissRequest = { instrumentExpanded = false }
                    ) {
                        InstrumentType.values().forEach { inst ->
                            DropdownMenuItem(
                                text = { Text("${inst.emoji} ${inst.displayName}") },
                                onClick = {
                                    instrument = inst
                                    instrumentExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Duration and Difficulty in a row
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Duration
                    OutlinedTextField(
                        value = durationMinutes,
                        onValueChange = { if (it.all { char -> char.isDigit() }) durationMinutes = it },
                        label = { Text("Duration (min) *", color = RpgTheme.textSecondary) },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = RpgTheme.textPrimary,
                            unfocusedTextColor = RpgTheme.textPrimary,
                            focusedBorderColor = RpgTheme.primary,
                            unfocusedBorderColor = RpgTheme.textSecondary,
                            cursorColor = RpgTheme.primary
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )

                    // Difficulty Dropdown
                    ExposedDropdownMenuBox(
                        expanded = difficultyExpanded,
                        onExpandedChange = { difficultyExpanded = it },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = difficulty.displayName,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Difficulty *", color = RpgTheme.textSecondary) },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = difficultyExpanded) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = RpgTheme.textPrimary,
                                unfocusedTextColor = RpgTheme.textPrimary,
                                focusedBorderColor = RpgTheme.primary,
                                unfocusedBorderColor = RpgTheme.textSecondary
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = difficultyExpanded,
                            onDismissRequest = { difficultyExpanded = false }
                        ) {
                            DifficultyLevel.values().forEach { diff ->
                                DropdownMenuItem(
                                    text = { Text(diff.displayName) },
                                    onClick = {
                                        difficulty = diff
                                        difficultyExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Timing Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = hasTiming,
                            onCheckedChange = { hasTiming = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = RpgTheme.primary,
                                uncheckedColor = RpgTheme.textSecondary
                            )
                        )
                        Text("Uses Metronome", color = RpgTheme.textPrimary)
                    }
                }

                if (hasTiming) {
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = bpm,
                        onValueChange = { if (it.all { char -> char.isDigit() }) bpm = it },
                        label = { Text("BPM (40-240)", color = RpgTheme.textSecondary) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = RpgTheme.textPrimary,
                            unfocusedTextColor = RpgTheme.textPrimary,
                            focusedBorderColor = RpgTheme.primary,
                            unfocusedBorderColor = RpgTheme.textSecondary,
                            cursorColor = RpgTheme.primary
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Instructions Section
                Text(
                    text = "Instructions (at least one required)",
                    color = RpgTheme.accent,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = instruction1,
                    onValueChange = { instruction1 = it },
                    label = { Text("Step 1 *", color = RpgTheme.textSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary,
                        focusedBorderColor = RpgTheme.primary,
                        unfocusedBorderColor = RpgTheme.textSecondary,
                        cursorColor = RpgTheme.primary
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = instruction2,
                    onValueChange = { instruction2 = it },
                    label = { Text("Step 2", color = RpgTheme.textSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary,
                        focusedBorderColor = RpgTheme.primary,
                        unfocusedBorderColor = RpgTheme.textSecondary,
                        cursorColor = RpgTheme.primary
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = instruction3,
                    onValueChange = { instruction3 = it },
                    label = { Text("Step 3", color = RpgTheme.textSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary,
                        focusedBorderColor = RpgTheme.primary,
                        unfocusedBorderColor = RpgTheme.textSecondary,
                        cursorColor = RpgTheme.primary
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = instruction4,
                    onValueChange = { instruction4 = it },
                    label = { Text("Step 4", color = RpgTheme.textSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary,
                        focusedBorderColor = RpgTheme.primary,
                        unfocusedBorderColor = RpgTheme.textSecondary,
                        cursorColor = RpgTheme.primary
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = instruction5,
                    onValueChange = { instruction5 = it },
                    label = { Text("Step 5", color = RpgTheme.textSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary,
                        focusedBorderColor = RpgTheme.primary,
                        unfocusedBorderColor = RpgTheme.textSecondary,
                        cursorColor = RpgTheme.primary
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = instruction6,
                    onValueChange = { instruction6 = it },
                    label = { Text("Step 6", color = RpgTheme.textSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary,
                        focusedBorderColor = RpgTheme.primary,
                        unfocusedBorderColor = RpgTheme.textSecondary,
                        cursorColor = RpgTheme.primary
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tablature/Notation (Optional)
                Text(
                    text = "Tablature/Notation (Optional)",
                    color = RpgTheme.accent,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = tablature,
                    onValueChange = { tablature = it },
                    label = { Text("Tablature or music notation", color = RpgTheme.textSecondary) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary,
                        focusedBorderColor = RpgTheme.primary,
                        unfocusedBorderColor = RpgTheme.textSecondary,
                        cursorColor = RpgTheme.primary
                    ),
                    minLines = 4,
                    maxLines = 8
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Validation Error
        if (showValidationError) {
            RpgCard(backgroundColor = RpgTheme.primary.copy(alpha = 0.2f)) {
                Text(
                    text = "⚠️ $validationMessage",
                    color = RpgTheme.primary,
                    modifier = Modifier.padding(12.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            RpgButton(
                text = "Cancel",
                onClick = onCancel,
                backgroundColor = RpgTheme.surface,
                modifier = Modifier.weight(1f)
            )

            RpgButton(
                text = if (existingExercise == null) "Create" else "Save",
                onClick = {
                    // Validation
                    if (name.isBlank()) {
                        validationMessage = "Please enter an exercise name"
                        showValidationError = true
                        return@RpgButton
                    }
                    if (description.isBlank()) {
                        validationMessage = "Please enter a description"
                        showValidationError = true
                        return@RpgButton
                    }
                    if (durationMinutes.toIntOrNull() == null || durationMinutes.toInt() < 1) {
                        validationMessage = "Duration must be at least 1 minute"
                        showValidationError = true
                        return@RpgButton
                    }
                    if (instruction1.isBlank()) {
                        validationMessage = "Please provide at least one instruction"
                        showValidationError = true
                        return@RpgButton
                    }
                    if (hasTiming && (bpm.toIntOrNull() == null || bpm.toInt() !in 40..240)) {
                        validationMessage = "BPM must be between 40 and 240"
                        showValidationError = true
                        return@RpgButton
                    }

                    // Collect instructions
                    val instructions = listOfNotNull(
                        instruction1.takeIf { it.isNotBlank() },
                        instruction2.takeIf { it.isNotBlank() },
                        instruction3.takeIf { it.isNotBlank() },
                        instruction4.takeIf { it.isNotBlank() },
                        instruction5.takeIf { it.isNotBlank() },
                        instruction6.takeIf { it.isNotBlank() }
                    )

                    // Save exercise
                    onSave(
                        name,
                        description,
                        category,
                        instrument,
                        durationMinutes.toInt(),
                        difficulty,
                        hasTiming,
                        if (hasTiming) bpm.toIntOrNull() else null,
                        instructions,
                        tablature.takeIf { it.isNotBlank() }
                    )
                },
                backgroundColor = RpgTheme.primary,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
