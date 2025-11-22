# UI Flow Changes

## Before Changes

### Home Screen (Showing all scheduled routines - takes up too much space!)
```
┌────────────────────────────────────────────┐
│ ⚔️ Practice Quest                          │
├────────────────────────────────────────────┤
│ User Progress Card                         │
│ Level 1 | 5 Routines | 180 Minutes        │
├────────────────────────────────────────────┤
│ Auto Schedule Planner Button               │
├────────────────────────────────────────────┤
│ Generate Routine Section                   │
│ [Instrument] [Difficulty] [Duration]       │
│ [Generate New Routine]                     │
├────────────────────────────────────────────┤
│ Current Routine (if generated)             │
├────────────────────────────────────────────┤
│ Saved Routines Section                     │
│ - Routine 1                                │
│ - Routine 2                                │
│ - Routine 3                                │
├────────────────────────────────────────────┤
│ Schedules Section                          │
│ - Schedule 1                               │
│ - Schedule 2                               │
└────────────────────────────────────────────┘
```

### Schedule Planner Dialog (No days per week option)
```
┌────────────────────────────────────────────┐
│ Create Schedule Plan                       │
├────────────────────────────────────────────┤
│ Plan Name: [________________]              │
│                                            │
│ Number of Days: 7                          │
│ [═══════════════○══]                       │
│                                            │
│ Instrument: [Both] [🎸] [🎹]               │
│                                            │
│ Difficulty: [All] [Begin] [Inter] [Adv]   │
│                                            │
│ Daily Duration: 45 minutes                 │
│ [═══════════════○══]                       │
│                                            │
│ This will create 7 unique routines,        │
│ one for each day.                          │
│                                            │
│                    [Cancel]  [Create]      │
└────────────────────────────────────────────┘
```

## After Changes

### Home Screen (Showing only today's routine - much cleaner!)
```
┌────────────────────────────────────────────┐
│ ⚔️ Practice Quest                          │
├────────────────────────────────────────────┤
│ User Progress Card                         │
│ Level 1 | 5 Routines | 180 Minutes        │
├────────────────────────────────────────────┤
│ Auto Schedule Planner Button               │
├────────────────────────────────────────────┤
│ 📅 Today's Routine                         │
│                                            │
│  [◀] Friday, Nov 22, 2024 [▶]             │
│       [Go to Today]                        │
│                                            │
│  Auto-generated for Nov 22, 2024  [✓ Done]│
│  5 exercises • 45 minutes                  │
│                                            │
│  1. Chromatic scale practice (10 min)     │
│  2. Alternate picking drills (8 min)      │
│  3. Pentatonic improvisation (12 min)     │
│  ... and 2 more                            │
│                                            │
│  [View Details]  [⚔️ Start]                │
│  [Mark as Completed]                       │
├────────────────────────────────────────────┤
│ Generate Routine Section                   │
│ [Instrument] [Difficulty] [Duration]       │
│ [Generate New Routine]                     │
├────────────────────────────────────────────┤
│ (Rest of the screen...)                    │
└────────────────────────────────────────────┘
```

### Schedule Planner Dialog (With days per week slider!)
```
┌────────────────────────────────────────────┐
│ Create Schedule Plan                       │
├────────────────────────────────────────────┤
│ Plan Name: [________________]              │
│                                            │
│ Number of Days: 7                          │
│ [═══════════════○══]                       │
│                                            │
│ Days Per Week: 5  ← NEW!                   │
│ [══════════○═══════]                       │
│                                            │
│ Instrument: [Both] [🎸] [🎹]               │
│                                            │
│ Difficulty: [All] [Begin] [Inter] [Adv]   │
│                                            │
│ Daily Duration: 45 minutes                 │
│ [═══════════════○══]                       │
│                                            │
│ This will create routines for 5 days       │
│ per week over 7 days.  ← Updated!          │
│                                            │
│                    [Cancel]  [Create]      │
└────────────────────────────────────────────┘
```

## Key UI Improvements

### 1. Today's Routine Section
- **Header**: Shows "📅 Today's Routine" or "📅 Scheduled Routine" based on viewing date
- **Date Display**: "Friday, Nov 22, 2024" (or current viewing date)
- **Navigation**: Left/Right arrows to browse days
- **Quick Access**: "Go to Today" button when viewing a different day
- **Routine Preview**: Shows first 3 exercises + count of remaining
- **Action Buttons**: 
  - "View Details" - loads the full routine
  - "⚔️ Start" - loads and starts the practice session
  - "Mark as Completed" - marks the routine as done
- **Completion Badge**: "✓ Done" badge when routine is completed
- **Empty State**: "No routine scheduled for this day" when applicable

### 2. Days Per Week Slider
- **Range**: 1-7 days (slider with 6 steps)
- **Label**: "Days Per Week: 5" (shows current value)
- **Summary**: Updates to show "routines for X days per week over Y days"
- **Badge Display**: Shows "X days/week" badge in plan list when < 7

### 3. Schedule Plan Display
- **Days Badge**: Shows total scheduled days
- **Days/Week Badge**: Only shown when daysPerWeek < 7
- **Instrument Badge**: Shows selected instrument(s)
- **Duration Badge**: Shows daily duration
- **Difficulty Badge**: Shows difficulty level (if specified)

## User Flow Examples

### Example 1: Creating a 5-day/week plan for 2 weeks
1. Open Auto Schedule Planner
2. Click "Create New Schedule Plan"
3. Enter name: "Mon-Fri Practice"
4. Set Number of Days: 14
5. Set Days Per Week: 5 ← NEW!
6. Select Guitar, Intermediate, 45 min
7. Click Create
8. System creates 10 routines (5 per week for 2 weeks)

### Example 2: Viewing and starting today's routine
1. Open app (home screen)
2. See "Today's Routine" section immediately
3. View routine preview (first 3 exercises)
4. Click "⚔️ Start" to begin practice
5. After practice, click "Mark as Completed"
6. Today's routine now shows "✓ Done" badge

### Example 3: Browsing scheduled routines
1. On home screen, see today's routine
2. Click right arrow [▶] to view tomorrow's routine
3. Click right arrow again to view day after
4. Click "Go to Today" to return to today
5. Click left arrow [◀] to view yesterday's routine

## Benefits Summary

✅ **Reduced clutter** - Only one routine visible at a time
✅ **Easy navigation** - Simple arrows to browse days
✅ **Flexible scheduling** - 1-7 days per week option
✅ **Quick start** - Direct "Start" button from today's view
✅ **Progress tracking** - Mark routines as completed
✅ **Rest days built-in** - Days per week < 7 automatically includes rest
