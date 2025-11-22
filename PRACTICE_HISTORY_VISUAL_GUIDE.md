# Practice History & Statistics - Visual Guide

## Feature Overview

The Practice History & Statistics feature adds comprehensive tracking and analytics to help users monitor their progress and maintain practice streaks.

## Screen Layout

### Practice History Screen
```
┌──────────────────────────────────────┐
│  ← Back     📊 Practice History      │
├──────────────────────────────────────┤
│                                      │
│  ┌─────── 📈 Overview ─────────┐   │
│  │ Total Sessions:          15  │   │
│  │ Total Practice Time:  675 min│   │
│  │ Average Session:       45 min│   │
│  │ Favorite Instrument: 🎸 Guitar│   │
│  └──────────────────────────────┘   │
│                                      │
│  ┌──────── 🔥 Streaks ──────────┐  │
│  │   🔥              🏆          │  │
│  │    5            12            │  │
│  │ Current       Longest         │  │
│  │  Streak        Streak         │  │
│  │  days          days           │  │
│  └──────────────────────────────┘   │
│                                      │
│  ┌─── 📅 Recent Activity ──────┐   │
│  │ This Week:        3 sessions │   │
│  │ This Month:      12 sessions │   │
│  │ Last Practice:   2 hours ago │   │
│  └──────────────────────────────┘   │
│                                      │
│  ┌── 📚 Session History (15) ───┐  │
│  │ Morning Practice    +90 XP   │   │
│  │ Nov 22, 2025 at 9:00 AM      │   │
│  │ [45 min] [5 exercises]       │   │
│  │ [🎸 Guitar] [Intermediate]   │   │
│  │ ─────────────────────────    │   │
│  │ Evening Routine     +60 XP   │   │
│  │ Nov 21, 2025 at 6:30 PM      │   │
│  │ [30 min] [4 exercises]       │   │
│  │ [🎹 Piano] [Beginner]        │   │
│  │ ─────────────────────────    │   │
│  │ ... and 13 more sessions     │   │
│  └──────────────────────────────┘   │
└──────────────────────────────────────┘
```

## Home Screen Integration

### User Progress Card (Updated)
```
┌────────── User Progress ──────────┐
│  ⭐ Level 5         🎸 15 Routines│
│                    ⏱️ 675 Minutes│
│                                   │
│  XP: 450 / 750                   │
│  [████████████░░░░░░░] 60%      │
│                                   │
│  [📊 View Practice History]      │
└───────────────────────────────────┘
```

## User Flow

### Completing a Practice Session
```
1. User starts practice session
   └─> Timer counts down
   
2. User completes final exercise
   └─> Routine marked complete
   
3. System automatically records:
   ├─> Date/time of completion
   ├─> Duration (from routine)
   ├─> XP earned (duration × 2)
   ├─> Routine name
   ├─> Exercise count
   ├─> Primary instrument
   └─> Difficulty level
   
4. System updates statistics:
   ├─> Increments total sessions
   ├─> Adds to total minutes
   ├─> Recalculates streaks
   ├─> Updates weekly/monthly counts
   └─> Identifies favorite instrument
   
5. Data persisted to storage
   └─> Available across app restarts
```

### Viewing Practice History
```
1. User on Home Screen
   └─> Sees "📊 View Practice History" button
   
2. User taps button
   └─> Navigation to History Screen
   
3. User sees:
   ├─> Statistics overview
   ├─> Current and longest streaks
   ├─> Recent activity summary
   └─> List of practice sessions
   
4. User taps back button
   └─> Returns to Home Screen
```

## Streak Calculation

### How Streaks Work
```
Practice Days:  Mon  Tue  Wed  Thu  Fri  Sat  Sun
Week 1:         ✓    ✓    ✓    ✗    ✓    ✓    ✗
Week 2:         ✓    ✓    ✓    ✓    ✗    ✗    ✗

Analysis:
- Longest streak: 4 days (Mon-Thu Week 2)
- Current streak: 0 (no practice yesterday or today)

If practiced today:
- Current streak: 1 day

If practiced yesterday:
- Current streak: 1 day (resets to 1 since missed Fri-Sun)
```

### Streak Rules
- **Day Definition**: Calendar day (midnight to midnight)
- **Current Streak**: Only counts if last practice was today or yesterday
- **Longest Streak**: All-time best, preserved permanently
- **Streak Break**: Missing a day resets current streak to 0

## Statistics Calculations

### Total Statistics
```
Total Sessions = Count of all history entries
Total Minutes = Sum of all session durations
Average Session = Total Minutes ÷ Total Sessions
```

### Time-Based Counts
```
Sessions This Week = Sessions in last 7 days
Sessions This Month = Sessions in last 30 days
Last Practice Date = Most recent session timestamp
```

### Favorite Instrument
```
Count sessions by instrument:
  Guitar: 9 sessions
  Piano: 6 sessions

Favorite Instrument = Guitar (most frequent)
```

## Data Persistence

### What Gets Saved
```
SharedPreferences Store:
├─ practice_history
│  └─ [Array of PracticeHistoryEntry objects]
├─ user_progress
│  └─ {level, xp, totalMinutes, completedRoutines}
├─ saved_routines
├─ schedules
└─ calendar_schedules
```

### Saved Entry Format
```json
{
  "id": "history_1732290000000_123456789",
  "completedAt": 1732290000000,
  "durationMinutes": 45,
  "xpEarned": 90,
  "routineName": "Morning Practice",
  "exerciseCount": 5,
  "instrument": "GUITAR",
  "difficulty": "INTERMEDIATE"
}
```

## UI Components Used

### Cards
- **RpgCard**: Container for all sections
- Background: Dark theme
- Border: Accent color
- Padding: 16dp

### Text Styles
- **RpgHeader**: Section titles (18sp, bold)
- **RpgText**: Regular text (16sp)
- Color scheme: Primary/secondary/accent

### Badges
- **RpgBadge**: Tags for metadata
- Duration: Secondary color
- Exercises: Info color
- Instrument: Primary color
- Difficulty: Success/Warning/Danger

### Icons
- Back arrow (⬅️)
- Fire emoji (🔥) for current streak
- Trophy emoji (🏆) for longest streak
- Chart emoji (📊) for statistics
- Calendar emoji (📅) for activity

## Responsive Design

### Portrait Mode
- Single column layout
- Full width cards
- Vertical scrolling
- Touch-friendly buttons

### Landscape Mode
- Same layout (vertical scroll)
- Wider cards
- More content visible
- Horizontal space utilized

## Performance

### Optimization
- History sorted once on load
- Statistics calculated on demand
- Efficient date grouping
- O(1) list access after conversion
- Minimal UI recomposition

### Memory
- History limited to in-memory storage
- Lazy loading for large datasets
- Display limited to 20 recent entries
- Lightweight data structures

## Accessibility

### Features
- High contrast colors (RPG theme)
- Large touch targets (buttons)
- Clear text hierarchy
- Emoji visual indicators
- Relative date formatting

## Error Handling

### Edge Cases
- Empty history (shows placeholder message)
- Single session (shows basic stats)
- No recent practice (current streak = 0)
- Mixed instruments (favorite identified)
- Same-day multiple sessions (counted once for streaks)

## Future Enhancements

### Potential Additions
1. **Charts**: Visual graphs of practice over time
2. **Goals**: Set and track practice targets
3. **Achievements**: Unlock badges and milestones
4. **Export**: Download history as CSV
5. **Insights**: AI-powered practice recommendations
6. **Reminders**: Notifications to maintain streaks
7. **Comparisons**: Compare periods (this month vs last month)
8. **Filtering**: Filter by instrument, date, difficulty
9. **Sharing**: Share stats on social media
10. **Challenges**: Practice challenges with friends

---

**Status**: Feature fully implemented and ready for use!
