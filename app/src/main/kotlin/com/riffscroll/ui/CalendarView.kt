package com.riffscroll.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.riffscroll.data.CalendarSchedule
import java.text.SimpleDateFormat
import java.util.*

/**
 * Calendar view component for visualizing scheduled routines
 */
@Composable
fun CalendarView(
    calendarSchedules: List<CalendarSchedule>,
    selectedDate: Long?,
    onDateSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    // Use current time as initial value for saveable state
    val initialMonth = remember { Calendar.getInstance().timeInMillis }
    var currentMonthMillis by rememberSaveable { mutableStateOf(initialMonth) }
    val currentMonth = remember(currentMonthMillis) {
        Calendar.getInstance().apply { timeInMillis = currentMonthMillis }
    }
    
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(RpgTheme.surface, RoundedCornerShape(8.dp))
            .border(2.dp, RpgTheme.border, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        // Month header with navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                val newMonth = Calendar.getInstance().apply {
                    timeInMillis = currentMonthMillis
                    add(Calendar.MONTH, -1)
                }
                currentMonthMillis = newMonth.timeInMillis
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous Month",
                    tint = RpgTheme.textPrimary
                )
            }
            
            Text(
                text = SimpleDateFormat("MMMM yyyy", Locale.US).format(currentMonth.time),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = RpgTheme.textPrimary
            )
            
            IconButton(onClick = {
                val newMonth = Calendar.getInstance().apply {
                    timeInMillis = currentMonth.timeInMillis
                    add(Calendar.MONTH, 1)
                }
                currentMonth = newMonth
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next Month",
                    tint = RpgTheme.textPrimary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Days of week header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { day ->
                Text(
                    text = day,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = RpgTheme.textSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Calendar grid
        val firstDayOfMonth = Calendar.getInstance().apply {
            timeInMillis = currentMonth.timeInMillis
            set(Calendar.DAY_OF_MONTH, 1)
        }
        
        val daysInMonth = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK) - 1
        
        // Calculate how many weeks we need
        val totalCells = firstDayOfWeek + daysInMonth
        val numWeeks = (totalCells + 6) / 7
        
        Column(modifier = Modifier.fillMaxWidth()) {
            for (week in 0 until numWeeks) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (dayOfWeek in 0 until 7) {
                        val cellIndex = week * 7 + dayOfWeek
                        val dayOfMonth = cellIndex - firstDayOfWeek + 1
                        
                        if (dayOfMonth in 1..daysInMonth) {
                            val dateCalendar = Calendar.getInstance().apply {
                                timeInMillis = currentMonth.timeInMillis
                                set(Calendar.DAY_OF_MONTH, dayOfMonth)
                                set(Calendar.HOUR_OF_DAY, 0)
                                set(Calendar.MINUTE, 0)
                                set(Calendar.SECOND, 0)
                                set(Calendar.MILLISECOND, 0)
                            }
                            
                            CalendarDayCell(
                                dayOfMonth = dayOfMonth,
                                dateMillis = dateCalendar.timeInMillis,
                                isToday = isSameDay(dateCalendar.timeInMillis, System.currentTimeMillis()),
                                isSelected = selectedDate != null && isSameDay(dateCalendar.timeInMillis, selectedDate),
                                hasSchedule = calendarSchedules.any { isSameDay(it.date, dateCalendar.timeInMillis) },
                                isCompleted = calendarSchedules.any { 
                                    isSameDay(it.date, dateCalendar.timeInMillis) && it.isCompleted 
                                },
                                onClick = { onDateSelected(dateCalendar.timeInMillis) },
                                modifier = Modifier.weight(1f)
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
                
                if (week < numWeeks - 1) {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

/**
 * Individual calendar day cell
 */
@Composable
fun CalendarDayCell(
    dayOfMonth: Int,
    dateMillis: Long,
    isToday: Boolean,
    isSelected: Boolean,
    hasSchedule: Boolean,
    isCompleted: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                when {
                    isSelected -> RpgTheme.primary
                    isToday -> RpgTheme.accent.copy(alpha = 0.3f)
                    hasSchedule && isCompleted -> RpgTheme.success.copy(alpha = 0.3f)
                    hasSchedule -> RpgTheme.warning.copy(alpha = 0.3f)
                    else -> Color.Transparent
                }
            )
            .border(
                width = if (isToday) 2.dp else 1.dp,
                color = if (isToday) RpgTheme.accent else RpgTheme.border.copy(alpha = 0.3f),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = dayOfMonth.toString(),
                fontSize = 14.sp,
                fontWeight = if (hasSchedule) FontWeight.Bold else FontWeight.Normal,
                color = when {
                    isSelected -> Color.White
                    isToday -> RpgTheme.accent
                    hasSchedule -> RpgTheme.textPrimary
                    else -> RpgTheme.textSecondary
                },
                textAlign = TextAlign.Center
            )
            
            if (hasSchedule) {
                Text(
                    text = if (isCompleted) "✓" else "●",
                    fontSize = 8.sp,
                    color = when {
                        isSelected -> Color.White
                        isCompleted -> RpgTheme.success
                        else -> RpgTheme.warning
                    }
                )
            }
        }
    }
}

/**
 * Helper function to check if two timestamps are on the same day
 */
private fun isSameDay(millis1: Long, millis2: Long): Boolean {
    val cal1 = Calendar.getInstance().apply { timeInMillis = millis1 }
    val cal2 = Calendar.getInstance().apply { timeInMillis = millis2 }
    
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
           cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}
