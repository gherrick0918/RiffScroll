# Practice History & Statistics Feature

## Overview

This feature adds comprehensive practice history tracking and statistics to RiffScroll, allowing users to:
- View all completed practice sessions
- Track practice streaks (current and longest)
- See trends and patterns in their practice habits
- Monitor progress over time

## Key Features

### 1. Automatic Session Tracking
Every time you complete a practice routine, the app automatically records:
- Date and time of completion
- Duration of the session
- XP earned
- Number of exercises completed
- Instrument(s) practiced
- Difficulty level

### 2. Practice Statistics
The app calculates and displays:
- **Total Sessions**: Count of all completed practice sessions
- **Total Practice Time**: Sum of all practice minutes
- **Average Session Length**: Mean duration of practice sessions
- **Favorite Instrument**: Most frequently practiced instrument
- **Sessions This Week**: Count of sessions in the last 7 days
- **Sessions This Month**: Count of sessions in the last 30 days
- **Last Practice Date**: When you last completed a session

### 3. Streak Tracking
Motivation through consistency:
- **Current Streak**: Number of consecutive days you've practiced
- **Longest Streak**: Your personal best for consecutive practice days
- Visual indicators with fire (🔥) and trophy (🏆) emojis

### 4. Session History
A detailed list showing:
- Routine name
- Completion date and time
- XP earned
- Duration and exercise count
- Instrument and difficulty badges
- Up to 20 most recent sessions displayed

## How to Use

### Viewing Your History
1. From the home screen, tap the **"📊 View Practice History"** button in the User Progress card
2. This opens the Practice History screen with all your statistics and recent sessions

### Understanding Your Stats
- **Overview Card**: Shows your total practice metrics and favorite instrument
- **Streaks Card**: Displays your current streak and longest streak side-by-side
- **Recent Activity Card**: Shows activity from this week and month
- **Session History**: Lists individual practice sessions with full details

### Building Streaks
- Practice at least once per day to build your streak
- Streaks are calculated based on calendar days (not 24-hour periods)
- Missing a day resets your current streak to 0
- Your longest streak is preserved as a personal record

## Technical Implementation

### Data Models
```kotlin
data class PracticeHistoryEntry(
    val id: String,
    val completedAt: Long,
    val durationMinutes: Int,
    val xpEarned: Int,
    val routineName: String,
    val exerciseCount: Int,
    val instrument: InstrumentType?,
    val difficulty: DifficultyLevel?
)

data class PracticeStatistics(
    val totalSessions: Int,
    val totalMinutes: Int,
    val currentStreak: Int,
    val longestStreak: Int,
    val averageSessionMinutes: Int,
    val favoriteInstrument: InstrumentType?,
    val sessionsThisWeek: Int,
    val sessionsThisMonth: Int,
    val lastPracticeDate: Long?
)
```

### Persistence
- All practice history is automatically saved to device storage
- History persists across app restarts
- User progress (level, XP) is also saved and restored

### Statistics Calculation
The app intelligently calculates streaks by:
1. Grouping sessions by calendar date
2. Finding consecutive practice days
3. Determining current streak (only if last practice was today or yesterday)
4. Tracking the longest streak ever achieved

## User Benefits

1. **Motivation**: See your progress and maintain practice streaks
2. **Accountability**: Clear visibility into practice frequency
3. **Insights**: Understand your practice patterns and preferences
4. **Goals**: Track improvement toward practice goals
5. **Gamification**: XP and streaks make practice more engaging

## Future Enhancements

Potential additions to consider:
- Charts showing practice frequency over time
- Weekly/monthly practice goals
- Export history to CSV or other formats
- Practice reminders based on streak status
- Achievements and milestones
- Filter history by instrument or date range
- Compare current period with previous periods
