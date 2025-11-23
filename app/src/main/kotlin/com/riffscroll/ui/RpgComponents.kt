package com.riffscroll.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * RPG-themed color palette
 */
object RpgTheme {
    val background = Color(0xFF1a1a2e)
    val surface = Color(0xFF16213e)
    val primary = Color(0xFFe94560)
    val secondary = Color(0xFF0f3460)
    val accent = Color(0xFFf39c12)
    val textPrimary = Color(0xFFecf0f1)
    val textSecondary = Color(0xFFbdc3c7)
    val border = Color(0xFF34495e)
    val success = Color(0xFF27ae60)
    val warning = Color(0xFFf39c12)
    val danger = Color(0xFFe74c3c)
    val info = Color(0xFF3498db)
}

/**
 * RPG-styled card component
 */
@Composable
fun RpgCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .border(2.dp, RpgTheme.border, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = RpgTheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}

/**
 * RPG-styled button
 */
@Composable
fun RpgButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = RpgTheme.primary,
    fontSize: TextUnit = 16.sp
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .border(2.dp, RpgTheme.border, RoundedCornerShape(8.dp)),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = RpgTheme.secondary
        ),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            color = RpgTheme.textPrimary,
            textAlign = TextAlign.Center,
            maxLines = 1,
            softWrap = false
        )
    }
}

/**
 * RPG-styled progress bar
 */
@Composable
fun RpgProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = RpgTheme.accent
) {
    Box(
        modifier = modifier
            .height(24.dp)
            .border(2.dp, RpgTheme.border, RoundedCornerShape(4.dp))
            .background(RpgTheme.secondary, RoundedCornerShape(4.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(color, color.copy(alpha = 0.7f))
                    ),
                    shape = RoundedCornerShape(4.dp)
                )
        )
    }
}

/**
 * RPG-styled header text
 */
@Composable
fun RpgHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = RpgTheme.accent
    )
}

/**
 * RPG-styled body text
 */
@Composable
fun RpgText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = RpgTheme.textPrimary
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 16.sp,
        color = color
    )
}

/**
 * RPG-styled badge/tag
 */
@Composable
fun RpgBadge(
    text: String,
    color: Color = RpgTheme.accent,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color, RoundedCornerShape(12.dp))
            .border(1.dp, RpgTheme.border, RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = RpgTheme.textPrimary
        )
    }
}

/**
 * Dialog for adding a practice note
 */
@Composable
fun AddNoteDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    var noteText by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf<Int?>(null) }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "📝 Add Practice Note",
                color = RpgTheme.accent,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Note text field
                OutlinedTextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    label = { Text("Your Note") },
                    placeholder = { Text("e.g., Struggled with the fast part...") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = RpgTheme.surface,
                        unfocusedContainerColor = RpgTheme.surface,
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary
                    ),
                    minLines = 3,
                    maxLines = 5
                )
                
                // Optional rating
                Text(
                    "How did it go? (optional)",
                    color = RpgTheme.textSecondary,
                    fontSize = 14.sp
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..5) {
                        RpgButton(
                            text = if (rating == i) "⭐" else "☆",
                            onClick = { 
                                rating = if (rating == i) null else i 
                            },
                            color = if (rating == i) RpgTheme.accent else RpgTheme.secondary,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            RpgButton(
                text = "Save",
                onClick = { 
                    if (noteText.isNotBlank()) {
                        onConfirm(noteText, rating)
                    }
                },
                color = RpgTheme.success,
                enabled = noteText.isNotBlank()
            )
        },
        dismissButton = {
            RpgButton(
                text = "Cancel",
                onClick = onDismiss,
                color = RpgTheme.secondary
            )
        },
        containerColor = RpgTheme.surface,
        modifier = modifier
    )
}

/**
 * Dialog for adding exercise feedback
 */
@Composable
fun AddFeedbackDialog(
    onDismiss: () -> Unit,
    onConfirm: (com.riffscroll.data.DifficultyRating, Int, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var difficulty by remember { mutableStateOf<com.riffscroll.data.DifficultyRating?>(null) }
    var enjoyment by remember { mutableStateOf(3) }
    var notes by remember { mutableStateOf("") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "💭 Exercise Feedback",
                color = RpgTheme.accent,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Difficulty rating
                Text(
                    "How difficult was this exercise?",
                    color = RpgTheme.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    com.riffscroll.data.DifficultyRating.values().forEach { rating ->
                        RpgButton(
                            text = rating.displayName,
                            onClick = { difficulty = rating },
                            color = if (difficulty == rating) RpgTheme.accent else RpgTheme.secondary,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                
                // Enjoyment rating
                Text(
                    "How enjoyable was it?",
                    color = RpgTheme.textPrimary,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("😞", fontSize = 20.sp)
                    for (i in 1..5) {
                        RpgButton(
                            text = if (enjoyment == i) "⭐" else "☆",
                            onClick = { enjoyment = i },
                            color = if (enjoyment == i) RpgTheme.accent else RpgTheme.secondary,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Text("😊", fontSize = 20.sp)
                }
                
                // Optional notes
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Additional Notes (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = RpgTheme.surface,
                        unfocusedContainerColor = RpgTheme.surface,
                        focusedTextColor = RpgTheme.textPrimary,
                        unfocusedTextColor = RpgTheme.textPrimary
                    ),
                    minLines = 2,
                    maxLines = 3
                )
            }
        },
        confirmButton = {
            RpgButton(
                text = "Save Feedback",
                onClick = { 
                    difficulty?.let { 
                        onConfirm(it, enjoyment, notes)
                    }
                },
                color = RpgTheme.success,
                enabled = difficulty != null
            )
        },
        dismissButton = {
            RpgButton(
                text = "Skip",
                onClick = onDismiss,
                color = RpgTheme.secondary
            )
        },
        containerColor = RpgTheme.surface,
        modifier = modifier
    )
}
