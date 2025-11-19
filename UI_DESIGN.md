# RiffScroll UI Mockup Guide

## Screen Layouts

### 1. Home Screen (Portrait)

```
┌────────────────────────────────┐
│  ⚔️ Practice Quest           │
├────────────────────────────────┤
│ ╔════════════════════════════╗ │
│ ║ ⭐ Level 1                 ║ │
│ ║                            ║ │
│ ║ 🎸 0 Routines  ⏱️ 0 Minutes ║ │
│ ║                            ║ │
│ ║ XP: 0 / 100                ║ │
│ ║ [████████░░░░░░░░░░] 50%  ║ │
│ ╚════════════════════════════╝ │
│                                │
│ ╔════════════════════════════╗ │
│ ║ Generate Routine           ║ │
│ ║                            ║ │
│ ║ Create a balanced practice ║ │
│ ║ routine tailored to level  ║ │
│ ║                            ║ │
│ ║ Duration: 45 minutes       ║ │
│ ║ [━━━━━━━●━━━━━━━━━━━━━]   ║ │
│ ║                            ║ │
│ ║ [ 🎲 Generate New Routine ]║ │
│ ╚════════════════════════════╝ │
│                                │
│ ╔════════════════════════════╗ │
│ ║ Your Routine         [Lv 2]║ │
│ ║ 6 exercises • 43 minutes   ║ │
│ ║─────────────────────────────║ │
│ ║ 1. Alternate Picking       ║ │
│ ║    Master alternate pick-  ║ │
│ ║    ing for speed          ║ │
│ ║    [⚡Technique] [5 min]    ║ │
│ ║    [♩ 80 BPM]              ║ │
│ ║─────────────────────────────║ │
│ ║ 2. Improvisation           ║ │
│ ║    Free improv using       ║ │
│ ║    pentatonic patterns    ║ │
│ ║    [🎨Creative] [10 min]    ║ │
│ ║    [♩ 90 BPM]              ║ │
│ ║─────────────────────────────║ │
│ ║ ...                        ║ │
│ ║                            ║ │
│ ║ [ ⚔️ Start Practice ]      ║ │
│ ╚════════════════════════════╝ │
└────────────────────────────────┘
```

### 2. Practice Session Screen (Portrait)

```
┌────────────────────────────────┐
│  ⚔️ Practice Quest         [✕] │
├────────────────────────────────┤
│ ╔════════════════════════════╗ │
│ ║ Exercise 2 of 6            ║ │
│ ║ [████████░░░░░░░░░░░░] 33% ║ │
│ ╚════════════════════════════╝ │
│                                │
│ ╔════════════════════════════╗ │
│ ║        ⏱️                  ║ │
│ ║                            ║ │
│ ║       05:32                ║ │
│ ║                            ║ │
│ ║   Target: 10 minutes       ║ │
│ ║ [█████████░░░░░░░░░░] 55%  ║ │
│ ╚════════════════════════════╝ │
│                                │
│ ╔════════════════════════════╗ │
│ ║ Improvisation in Pentatonic║ │
│ ║                            ║ │
│ ║ Free improvisation using   ║ │
│ ║ pentatonic scale patterns  ║ │
│ ║                            ║ │
│ ║ Instructions:              ║ │
│ ║ 1. Choose a backing track  ║ │
│ ║ 2. Explore the fretboard   ║ │
│ ║ 3. Experiment with rhythm  ║ │
│ ║ 4. Focus on expression     ║ │
│ ╚════════════════════════════╝ │
│                                │
│ ╔════════════════════════════╗ │
│ ║ 🎵 Metronome       [ON]    ║ │
│ ║ 90 BPM                     ║ │
│ ║                            ║ │
│ ║ [-] ━━━━━━●━━━━━━━━ [+]   ║ │
│ ╚════════════════════════════╝ │
│                                │
│ [ ⏸ Pause ]  [ Next ➜ ]      │
└────────────────────────────────┘
```

### 3. Landscape Layout (Tablet)

```
┌───────────────────────────────────────────────────────────┐
│  ⚔️ Practice Quest                                    [✕] │
├───────────────────────────────────────────────────────────┤
│ ╔══════════════════════════╗  ╔═════════════════════════╗ │
│ ║ Exercise 2 of 6          ║  ║       ⏱️               ║ │
│ ║ [████████░░░░░░░] 33%    ║  ║                         ║ │
│ ╚══════════════════════════╝  ║      05:32              ║ │
│                               ║                         ║ │
│ ╔══════════════════════════╗  ║  Target: 10 minutes     ║ │
│ ║ Improvisation Pentatonic ║  ║ [████████░░░░░░] 55%   ║ │
│ ║                          ║  ╚═════════════════════════╝ │
│ ║ Free improvisation using ║                             │
│ ║ pentatonic patterns      ║  ╔═════════════════════════╗ │
│ ║                          ║  ║ 🎵 Metronome      [ON] ║ │
│ ║ Instructions:            ║  ║ 90 BPM                  ║ │
│ ║ 1. Choose backing track  ║  ║                         ║ │
│ ║ 2. Explore fretboard     ║  ║ [-] ━━━●━━━━━━ [+]     ║ │
│ ║ 3. Experiment w/ rhythm  ║  ╚═════════════════════════╝ │
│ ║ 4. Focus on expression   ║                             │
│ ╚══════════════════════════╝  [ ⏸ Pause ]  [ Next ➜ ]   │
└───────────────────────────────────────────────────────────┘
```

## Color Scheme

The app uses an old school RPG theme with the following colors:

- **Background**: #1a1a2e (Dark Navy)
- **Surface/Cards**: #16213e (Deep Blue)
- **Primary**: #e94560 (Crimson)
- **Secondary**: #0f3460 (Midnight Blue)
- **Accent**: #f39c12 (Gold)
- **Text Primary**: #ecf0f1 (Off-White)
- **Text Secondary**: #bdc3c7 (Gray)
- **Border**: #34495e (Slate)
- **Success**: #27ae60 (Green)
- **Warning**: #f39c12 (Orange)

## Key UI Elements

### Badges
```
[⚡Technique]  - Red (#e94560)
[🎨Creative]  - Gold (#f39c12)
[🎵Song]      - Green (#27ae60)
[5 min]       - Blue (#0f3460)
[♩ 80 BPM]    - Blue (#0f3460)
[Lv 2]        - Gold (#f39c12)
```

### Progress Bars
- XP Bar: Gold gradient
- Exercise Progress: Gold gradient
- Timer Progress: Gold gradient

### Buttons
- Generate Routine: Crimson
- Start Practice: Green
- Pause: Orange
- Next: Crimson
- End (X): Red

## Interaction Flow

1. **App Launch** → Home Screen
   - View user progress (level, XP)
   - Adjust routine duration slider
   - Generate new routine

2. **Generate Routine** → Updated Home Screen
   - Shows list of exercises
   - Display total duration
   - "Start Practice" button appears

3. **Start Practice** → Practice Session Screen
   - Shows current exercise
   - Timer starts automatically
   - Metronome available if exercise has BPM

4. **During Practice**
   - Can pause/resume
   - Can skip to next exercise
   - Can adjust metronome BPM
   - Can end session early

5. **Complete Exercise** → Next Exercise
   - Progress bar updates
   - New exercise loads
   - Timer resets
   - Metronome BPM adjusts

6. **Complete Routine** → Back to Home Screen
   - XP awarded (2 per minute)
   - Level up if threshold reached
   - Stats updated

## Responsive Behavior

### Phone (Portrait)
- Single column layout
- Full-width cards
- Vertical scrolling
- Stacked controls

### Phone (Landscape)
- Same as portrait
- More horizontal space
- Reduced vertical spacing

### Tablet (Portrait)
- Larger cards with more padding
- Increased font sizes
- More whitespace

### Tablet (Landscape)
- Two-column layout for practice screen
- Exercise details on left
- Timer/controls on right
- Better space utilization

## Accessibility

- Large touch targets (min 48dp)
- High contrast text on dark background
- Clear visual hierarchy
- Descriptive content descriptions for icons
- Support for system text size settings
