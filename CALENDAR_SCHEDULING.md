# Calendar Scheduling Feature

## Overview

RiffScroll now supports auto-generated practice schedules tied to calendar dates, making it easy to plan your practice routine for days or weeks in advance.

## Features

### Calendar Schedule
A calendar schedule links a practice routine to a specific date. This allows you to:
- Pre-plan your practice sessions
- Track which routines you completed on which dates
- Build consistent practice habits

### Practice Schedule Plan
A practice schedule plan automatically generates routines for multiple days:
- Specify start and end dates (e.g., a week or month)
- Choose instrument type (Guitar, Piano, or Both)
- Set target duration and difficulty level
- The system automatically generates a unique routine for each day

## How to Use

### Creating a Practice Schedule Plan

```kotlin
// Create a 7-day practice plan for guitar
val startDate = System.currentTimeMillis()
val endDate = startDate + (7 * 24 * 60 * 60 * 1000L) // 7 days later

val plan = viewModel.createPracticeSchedulePlan(
    name = "Weekly Guitar Practice",
    startDate = startDate,
    endDate = endDate,
    instrument = InstrumentType.GUITAR,
    targetDurationMinutes = 45,
    difficulty = DifficultyLevel.INTERMEDIATE
)
```

### Loading a Scheduled Routine

```kotlin
// Get today's scheduled routine
val today = System.currentTimeMillis()
val todaySchedule = viewModel.getCalendarScheduleByDate(today)

// Load the routine for practice
if (todaySchedule != null) {
    viewModel.loadRoutineFromCalendarSchedule(todaySchedule.id)
    viewModel.startPracticeSession()
}
```

### Marking a Schedule as Completed

```kotlin
// After completing a practice session
viewModel.markCalendarScheduleCompleted(calendarScheduleId)
```

## Data Models

### CalendarSchedule
```kotlin
data class CalendarSchedule(
    val id: String,
    val date: Long,              // Date in milliseconds
    val routineId: String,       // Reference to saved routine
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
```

### PracticeSchedulePlan
```kotlin
data class PracticeSchedulePlan(
    val id: String,
    val name: String,
    val startDate: Long,
    val endDate: Long,
    val scheduleEntries: List<CalendarSchedule>,
    val instrument: InstrumentType? = null,
    val targetDurationMinutes: Int = 45,
    val difficulty: DifficultyLevel? = null,
    val createdAt: Long = System.currentTimeMillis()
)
```

## Benefits

1. **Consistency**: Pre-planning removes the daily decision of what to practice
2. **Variety**: Each day gets a unique, auto-generated routine
3. **Progress Tracking**: See which days you completed your practice
4. **Flexibility**: Choose instrument type and difficulty for each plan
5. **Motivation**: Having a schedule creates accountability

## Future Enhancements

Potential future improvements include:
- Calendar view UI to visualize schedules
- Recurring schedule templates (e.g., same plan every week)
- Custom rest days
- Integration with device calendar
- Notifications/reminders for scheduled practice
- Statistics on schedule adherence
