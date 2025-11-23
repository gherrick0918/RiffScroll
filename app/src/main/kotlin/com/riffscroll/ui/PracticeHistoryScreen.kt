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
 * Screen displaying practice history and statistics
 */
@Composable
fun PracticeHistoryScreen(
    practiceHistory: List<PracticeHistoryEntry>,
    practiceStatistics: PracticeStatistics,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RpgTheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header with back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = RpgTheme.textPrimary
                )
            }
            RpgHeader(text = "📊 Practice History", modifier = Modifier.weight(1f))
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Statistics Overview Card
        StatisticsOverviewCard(statistics = practiceStatistics)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Streaks Card
        StreaksCard(statistics = practiceStatistics)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Recent Activity Card
        RecentActivityCard(statistics = practiceStatistics)
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Practice History List
        PracticeHistoryList(history = practiceHistory)
    }
}

/**
 * Overview statistics card
 */
@Composable
fun StatisticsOverviewCard(statistics: PracticeStatistics) {
    RpgCard {
        RpgHeader(text = "📈 Overview", modifier = Modifier.padding(bottom = 12.dp))
        
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            // Total sessions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Sessions:",
                    fontSize = 16.sp,
                    color = RpgTheme.textSecondary
                )
                Text(
                    text = "${statistics.totalSessions}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.accent
                )
            }
            
            Divider(color = RpgTheme.border)
            
            // Total practice time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Practice Time:",
                    fontSize = 16.sp,
                    color = RpgTheme.textSecondary
                )
                Text(
                    text = "${statistics.totalMinutes} min",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.accent
                )
            }
            
            Divider(color = RpgTheme.border)
            
            // Average session length
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Average Session:",
                    fontSize = 16.sp,
                    color = RpgTheme.textSecondary
                )
                Text(
                    text = "${statistics.averageSessionMinutes} min",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.primary
                )
            }
            
            // Favorite instrument (if available)
            if (statistics.favoriteInstrument != null) {
                Divider(color = RpgTheme.border)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Favorite Instrument:",
                        fontSize = 16.sp,
                        color = RpgTheme.textSecondary
                    )
                    Text(
                        text = "${statistics.favoriteInstrument.emoji} ${statistics.favoriteInstrument.displayName}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = RpgTheme.success
                    )
                }
            }
        }
    }
}

/**
 * Streaks display card
 */
@Composable
fun StreaksCard(statistics: PracticeStatistics) {
    RpgCard {
        RpgHeader(text = "🔥 Streaks", modifier = Modifier.padding(bottom = 12.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Current streak
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "🔥",
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${statistics.currentStreak}",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.accent
                )
                Text(
                    text = "Current Streak",
                    fontSize = 14.sp,
                    color = RpgTheme.textSecondary
                )
                Text(
                    text = if (statistics.currentStreak == 1) "day" else "days",
                    fontSize = 12.sp,
                    color = RpgTheme.textSecondary
                )
            }
            
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .height(100.dp),
                color = RpgTheme.border
            )
            
            // Longest streak
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "🏆",
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${statistics.longestStreak}",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.warning
                )
                Text(
                    text = "Longest Streak",
                    fontSize = 14.sp,
                    color = RpgTheme.textSecondary
                )
                Text(
                    text = if (statistics.longestStreak == 1) "day" else "days",
                    fontSize = 12.sp,
                    color = RpgTheme.textSecondary
                )
            }
        }
    }
}

/**
 * Recent activity summary card
 */
@Composable
fun RecentActivityCard(statistics: PracticeStatistics) {
    RpgCard {
        RpgHeader(text = "📅 Recent Activity", modifier = Modifier.padding(bottom = 12.dp))
        
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            // This week
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "This Week:",
                    fontSize = 16.sp,
                    color = RpgTheme.textSecondary
                )
                RpgBadge(
                    text = "${statistics.sessionsThisWeek} sessions",
                    color = RpgTheme.primary
                )
            }
            
            Divider(color = RpgTheme.border)
            
            // This month
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "This Month:",
                    fontSize = 16.sp,
                    color = RpgTheme.textSecondary
                )
                RpgBadge(
                    text = "${statistics.sessionsThisMonth} sessions",
                    color = RpgTheme.accent
                )
            }
            
            // Last practice
            if (statistics.lastPracticeDate != null) {
                Divider(color = RpgTheme.border)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Last Practice:",
                        fontSize = 16.sp,
                        color = RpgTheme.textSecondary
                    )
                    Text(
                        text = formatRelativeDate(statistics.lastPracticeDate),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = RpgTheme.textPrimary
                    )
                }
            }
        }
    }
}

/**
 * List of practice history entries
 */
@Composable
fun PracticeHistoryList(history: List<PracticeHistoryEntry>) {
    RpgCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RpgHeader(text = "📚 Session History", modifier = Modifier.padding(bottom = 8.dp))
            RpgBadge(text = "${history.size} total")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        if (history.isEmpty()) {
            Text(
                text = "No practice sessions yet. Complete a routine to see it here!",
                fontSize = 14.sp,
                color = RpgTheme.textSecondary,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {
            Column {
                history.take(20).forEachIndexed { index, entry ->
                    PracticeHistoryItem(entry = entry)
                    
                    // Show divider between items (but not after the last one)
                    if (index < 19 && index < history.size - 1) {
                        Divider(
                            color = RpgTheme.border,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
                
                if (history.size > 20) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "... and ${history.size - 20} more sessions",
                        fontSize = 12.sp,
                        color = RpgTheme.textSecondary,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

/**
 * Individual practice history entry
 */
@Composable
fun PracticeHistoryItem(entry: PracticeHistoryEntry) {
    var expanded by remember { mutableStateOf(false) }
    
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = entry.routineName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.textPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formatDate(entry.completedAt),
                    fontSize = 13.sp,
                    color = RpgTheme.textSecondary
                )
            }
            
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "+${entry.xpEarned} XP",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.accent
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            RpgBadge(
                text = "${entry.durationMinutes} min",
                color = RpgTheme.secondary
            )
            
            RpgBadge(
                text = "${entry.exerciseCount} exercises",
                color = RpgTheme.info
            )
            
            entry.instrument?.let { instrument ->
                RpgBadge(
                    text = "${instrument.emoji} ${instrument.displayName}",
                    color = RpgTheme.primary
                )
            }
            
            entry.difficulty?.let { difficulty ->
                RpgBadge(
                    text = difficulty.displayName,
                    color = when (difficulty) {
                        DifficultyLevel.BEGINNER -> RpgTheme.success
                        DifficultyLevel.INTERMEDIATE -> RpgTheme.warning
                        DifficultyLevel.ADVANCED -> RpgTheme.danger
                    }
                )
            }
            
            // Show badge if notes exist
            if (entry.notes.isNotEmpty() || entry.exerciseFeedback.isNotEmpty()) {
                RpgBadge(
                    text = "📝 ${entry.notes.size + entry.exerciseFeedback.size}",
                    color = RpgTheme.accent
                )
            }
        }
        
        // Show/hide notes button if notes exist
        if (entry.notes.isNotEmpty() || entry.exerciseFeedback.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = { expanded = !expanded },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = RpgTheme.accent
                )
            ) {
                Text(
                    text = if (expanded) "▼ Hide Notes & Feedback" else "▶ Show Notes & Feedback",
                    fontSize = 12.sp
                )
            }
        }
        
        // Expanded notes and feedback section
        if (expanded && (entry.notes.isNotEmpty() || entry.exerciseFeedback.isNotEmpty())) {
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(RpgTheme.background, androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Display notes
                if (entry.notes.isNotEmpty()) {
                    Text(
                        text = "📝 Practice Notes:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = RpgTheme.accent
                    )
                    entry.notes.forEach { note ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(RpgTheme.surface, androidx.compose.foundation.shape.RoundedCornerShape(6.dp))
                                .padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = note.text,
                                    fontSize = 13.sp,
                                    color = RpgTheme.textPrimary,
                                    modifier = Modifier.weight(1f)
                                )
                                if (note.rating != null) {
                                    Text(
                                        text = "${"⭐".repeat(note.rating)}",
                                        fontSize = 12.sp,
                                        color = RpgTheme.accent
                                    )
                                }
                            }
                        }
                    }
                }
                
                // Display feedback
                if (entry.exerciseFeedback.isNotEmpty()) {
                    Text(
                        text = "💭 Exercise Feedback:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = RpgTheme.accent
                    )
                    entry.exerciseFeedback.values.forEach { feedback ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(RpgTheme.surface, androidx.compose.foundation.shape.RoundedCornerShape(6.dp))
                                .padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "Difficulty: ${feedback.difficulty.displayName}",
                                fontSize = 12.sp,
                                color = RpgTheme.textSecondary
                            )
                            Text(
                                text = "Enjoyment: ${"⭐".repeat(feedback.enjoyment)}",
                                fontSize = 12.sp,
                                color = RpgTheme.textSecondary
                            )
                            if (feedback.notes.isNotBlank()) {
                                Text(
                                    text = feedback.notes,
                                    fontSize = 12.sp,
                                    color = RpgTheme.textPrimary
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
 * Format a date as a relative time string (e.g., "2 hours ago", "Yesterday")
 */
private fun formatRelativeDate(timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp
    
    val seconds = diff / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    
    return when {
        seconds < 60 -> "Just now"
        minutes < 60 -> "$minutes ${if (minutes == 1L) "minute" else "minutes"} ago"
        hours < 24 -> "$hours ${if (hours == 1L) "hour" else "hours"} ago"
        days == 1L -> "Yesterday"
        days < 7 -> "$days days ago"
        else -> formatDate(timestamp)
    }
}

/**
 * Format a timestamp as a date string
 */
private fun formatDate(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy 'at' h:mm a", Locale.US)
    return dateFormat.format(Date(timestamp))
}
