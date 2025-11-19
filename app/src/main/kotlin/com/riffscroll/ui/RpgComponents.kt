package com.riffscroll.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    color: Color = RpgTheme.primary
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
        )
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = RpgTheme.textPrimary
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
