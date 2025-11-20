# RiffScroll - Save Routines & Schedules Feature

## Visual Feature Map

```
┌─────────────────────────────────────────────────────────────────┐
│                        RIFFSCROLL APP                           │
│                     ⚔️ Practice Quest                           │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│  USER PROGRESS                                                  │
│  ⭐ Level 3      🎸 12 Routines    ⏱️ 540 Minutes              │
│  XP: 150/225    ▓▓▓▓▓▓▓▓░░░░░░░░░░                             │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│  GENERATE ROUTINE                                               │
│  Difficulty: [All] [Beginner] [Intermediate] [Advanced]        │
│  Duration: 45 minutes  ▓▓▓▓▓▓▓▓▓▓░░░░░                         │
│  [🎲 Generate New Routine]                                     │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│  YOUR ROUTINE                                        Lv 2       │
│  6 exercises • 45 minutes                                       │
│                                                                 │
│  1. Chromatic Scale Practice                                   │
│     ⚡ Technique  Beginner  5 min  ♩ 60 BPM                   │
│  2. Improvisation in Pentatonic                                │
│     🎨 Creative  Intermediate  10 min  ♩ 90 BPM                │
│  ...                                                            │
│                                                                 │
│  [💾 Save]  [⚔️ Start Practice]  ← NEW SAVE BUTTON           │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│  📚 SAVED ROUTINES                              ← NEW SECTION   │
│  3 saved routine(s)                                             │
│                                                                 │
│  ▼ Morning Warm-up                    [▶️] [🗑️]               │
│     5 exercises • 30 min                                        │
│     1. Chromatic Scale (5 min)                                 │
│     2. Alternate Picking (5 min)                               │
│     ...                                                         │
│                                                                 │
│  ▶ Speed Training                     [▶️] [🗑️]               │
│     4 exercises • 25 min                                        │
│                                                                 │
│  ▶ Song Practice Session              [▶️] [🗑️]               │
│     6 exercises • 60 min                                        │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│  🗓️ SCHEDULES                        [+] ← NEW SECTION         │
│  2 schedule(s)                                                  │
│                                                                 │
│  ▼ Weekly Plan                     [▼] [+] [🗑️]               │
│     Practice schedule for the week                              │
│     3 routine(s)                                                │
│                                                                 │
│     ├─ Morning Warm-up                [▶️] [✕]                 │
│     │  5 exercises • 30 min                                     │
│     ├─ Speed Training                 [▶️] [✕]                 │
│     │  4 exercises • 25 min                                     │
│     └─ Song Practice Session          [▶️] [✕]                 │
│        6 exercises • 60 min                                     │
│                                                                 │
│  ▶ 30-Day Challenge               [▼] [+] [🗑️]                │
│     Progressive difficulty increase                             │
│     0 routine(s)                                                │
└─────────────────────────────────────────────────────────────────┘

DIALOGS:
┌──────────────────────────┐  ┌──────────────────────────┐
│ Save Routine             │  │ Create Schedule          │
│                          │  │                          │
│ Enter a name:            │  │ Schedule name:           │
│ [Morning Warm-up    ]    │  │ [Weekly Plan        ]    │
│                          │  │ Description:             │
│       [Cancel] [Save]    │  │ [Daily practice...  ]    │
└──────────────────────────┘  │                          │
                              │   [Cancel] [Create]      │
┌──────────────────────────┐  └──────────────────────────┘
│ Add Routine to Schedule  │
│                          │
│ Select a routine:        │
│                          │
│ • Morning Warm-up (30m)  │
│ • Speed Training (25m)   │
│ • Song Practice (60m)    │
│                          │
│        [Cancel]          │
└──────────────────────────┘
```

## Feature Flow Diagrams

### Save Routine Flow
```
Generate/Have Routine
        ↓
  Click "💾 Save"
        ↓
  Enter Routine Name
        ↓
   Click "Save"
        ↓
Routine Added to List
```

### Create Schedule Flow
```
Click "+" in Schedules
        ↓
 Enter Name & Description
        ↓
   Click "Create"
        ↓
Schedule Added to List
```

### Add Routine to Schedule Flow
```
 Expand Schedule
        ↓
Click "+" Add Routine
        ↓
Select Routine from List
        ↓
Routine Added to Schedule
```

### Practice from Schedule Flow
```
  Expand Schedule
        ↓
View Routines in Schedule
        ↓
 Click "▶️" on Routine
        ↓
Routine Loaded as Current
        ↓
Click "⚔️ Start Practice"
        ↓
   Begin Session
```

## Data Structure

```
SavedRoutine
├── id: String
├── name: String
├── routine: PracticeRoutine
└── createdAt: Long

Schedule
├── id: String
├── name: String
├── description: String
├── routineIds: List<String>
└── createdAt: Long

Relationships:
Schedule --has-many--> SavedRoutine
```

## UI Components Hierarchy

```
HomeScreen
├── UserProgressCard
├── Generate Routine Section
│   └── [Generate Button]
├── RoutinePreviewCard (when routine exists)
│   ├── Exercise List
│   └── Action Buttons
│       ├── [💾 Save] ← NEW
│       └── [⚔️ Start Practice]
├── SavedRoutinesSection (when routines exist) ← NEW
│   └── For each saved routine:
│       ├── Routine Header
│       ├── [▼/▶] Expand/Collapse
│       ├── [▶️] Load
│       └── [🗑️] Delete
└── SchedulesSection ← NEW
    ├── [+] Create Schedule
    └── For each schedule:
        ├── Schedule Header
        ├── [▼/▶] Expand/Collapse
        ├── [+] Add Routine
        ├── [🗑️] Delete
        └── Routine List (when expanded)
            └── For each routine:
                ├── [▶️] Load
                └── [✕] Remove
```

## Icons Reference

| Icon | Meaning | Usage |
|------|---------|-------|
| 💾 | Save | Save current routine |
| ▶️ | Play/Load | Load routine to practice |
| 🗑️ | Delete | Remove routine or schedule |
| + | Add | Create schedule or add routine |
| ✕ | Remove | Remove routine from schedule |
| ▼ | Expanded | List is expanded, click to collapse |
| ▶ | Collapsed | List is collapsed, click to expand |
| ⚔️ | Start | Begin practice session |
| 🎲 | Generate | Create new random routine |
| 📚 | Library | Saved routines section |
| 🗓️ | Schedule | Schedules section |

## Button Colors

| Button Type | Color | Usage |
|-------------|-------|-------|
| Primary | Blue | Main action (Generate) |
| Success | Green | Positive action (Start, Load) |
| Danger | Red | Destructive action (Delete) |
| Secondary | Gray | Alternative action (Save) |
| Accent | Gold | Special action (Add, Create) |
