# Exercise Browser UI Mockup

## Home Screen Integration

```
┌─────────────────────────────────────────┐
│ ⚔️ Practice Quest                        │
├─────────────────────────────────────────┤
│ ┌─────────────────────────────────────┐ │
│ │ ⭐ Level 1                           │ │
│ │              🎸 0 Routines           │ │
│ │              ⏱️ 0 Minutes            │ │
│ │                                      │ │
│ │ XP: 0 / 100                          │ │
│ │ [████░░░░░░░░░░░░░]                  │ │
│ │                                      │ │
│ │ [📊 View Practice History]           │ │
│ └─────────────────────────────────────┘ │
│                                          │
│ ┌─────────────────────────────────────┐ │
│ │ 📅 Auto Schedule Planner       →    │ │
│ │ Create automatic practice schedules  │ │
│ │ for multiple days                    │ │
│ │                                      │ │
│ │ [✨ Open Schedule Planner]           │ │
│ └─────────────────────────────────────┘ │
│                                          │
│ ┌─────────────────────────────────────┐ │ <-- NEW!
│ │ 📚 Exercise Browser            →    │ │
│ │ Search and browse 150+ exercises     │ │
│ │ with advanced filters                │ │
│ │                                      │ │
│ │ [🔍 Browse All Exercises]            │ │
│ └─────────────────────────────────────┘ │
│                                          │
│ ┌─────────────────────────────────────┐ │
│ │ 📅 Today's Routine                   │ │
│ │ ...                                  │ │
└─────────────────────────────────────────┘
```

## Exercise Browser Screen - Initial View

```
┌─────────────────────────────────────────┐
│ ← Back    📚 Exercise Browser    🎛️    │  <-- Top Bar
├─────────────────────────────────────────┤
│ ┌─────────────────────────────────────┐ │
│ │ 🔍 Search exercises...          ✕  │ │  <-- Search Bar
│ └─────────────────────────────────────┘ │
├─────────────────────────────────────────┤
│ 150 exercises found         Clear All   │  <-- Results Count
├─────────────────────────────────────────┤
│                                          │
│ ┌─────────────────────────────────────┐ │
│ │ Chromatic Scale Practice            │ │
│ │ Practice chromatic scales to build  │ │
│ │ finger strength and dexterity       │ │
│ │                                      │ │
│ │ [🎸 Guitar] [⚡] [Beginner] [5m] [♩ 60]│
│ └─────────────────────────────────────┘ │
│                                          │
│ ┌─────────────────────────────────────┐ │
│ │ Alternate Picking Drill             │ │
│ │ Master alternate picking technique  │ │
│ │ for speed and precision             │ │
│ │                                      │ │
│ │ [🎸 Guitar] [⚡] [Intermediate] [5m] [♩ 80]│
│ └─────────────────────────────────────┘ │
│                                          │
│ ┌─────────────────────────────────────┐ │
│ │ C Major Scale - Hands Separate      │ │
│ │ Practice C major scale with proper  │ │
│ │ fingering, hands separately         │ │
│ │                                      │ │
│ │ [🎹 Piano] [⚡] [Beginner] [5m] [♩ 60]│
│ └─────────────────────────────────────┘ │
│                                          │
│ (scrollable list continues...)          │
└─────────────────────────────────────────┘
```

## Exercise Browser Screen - With Filters Open

```
┌─────────────────────────────────────────┐
│ ← Back    📚 Exercise Browser    🎛️    │  <-- Filter icon accent colored
├─────────────────────────────────────────┤
│ ┌─────────────────────────────────────┐ │
│ │ 🔍 chromatic                    ✕  │ │  <-- Active search
│ └─────────────────────────────────────┘ │
├─────────────────────────────────────────┤
│ 5 exercises found           Clear All   │  <-- Filtered count
├─────────────────────────────────────────┤
│ ┌─────────────────────────────────────┐ │
│ │ Filters                              │ │  <-- Filter Panel
│ │                                      │ │
│ │ Instrument                           │ │
│ │ [All] [🎸 Guitar] [🎹 Piano]         │ │
│ │                                      │ │
│ │ Category                             │ │
│ │ [All] [⚡ Tech] [🎨 Creative] [🎵 Songs]│
│ │                                      │ │
│ │ Difficulty                           │ │
│ │ [All] [Beginner] [Intermediate] [Advanced]│
│ │                                      │ │
│ │ Duration: 0 - 60 minutes             │ │
│ │ [●────────────────────────●]         │ │
│ │                                      │ │
│ │ Metronome/Timed only    [  ON  ]    │ │
│ └─────────────────────────────────────┘ │
├─────────────────────────────────────────┤
│ ┌─────────────────────────────────────┐ │
│ │ Chromatic Scale Practice            │ │
│ │ [🎸 Guitar] [⚡] [Beginner] [5m] [♩ 60]│
│ └─────────────────────────────────────┘ │
│                                          │
│ ┌─────────────────────────────────────┐ │
│ │ Four-Fret Chromatic Run             │ │
│ │ [🎸 Guitar] [⚡] [Beginner] [5m] [♩ 60]│
│ └─────────────────────────────────────┘ │
│                                          │
│ (5 total results...)                    │
└─────────────────────────────────────────┘
```

## Exercise Detail Dialog

```
┌─────────────────────────────────────────┐
│ Chromatic Scale Practice                │
├─────────────────────────────────────────┤
│ [🎸 Guitar] [⚡ Technique]               │
│ [Beginner] [5 minutes] [♩ 60 BPM]       │
│                                          │
│ Description                              │
│ Practice chromatic scales to build      │
│ finger strength and dexterity           │
│                                          │
│ Instructions                             │
│ 1. Start on the low E string, 5th fret  │
│    with your index finger                │
│ 2. Play one finger per fret: Index(5)-  │
│    Middle(6)-Ring(7)-Pinky(8)            │
│ 3. Move to the next string (A) and      │
│    repeat the pattern                    │
│ 4. Continue across all six strings,     │
│    then reverse back down                │
│ 5. Keep your fingers close to the       │
│    fretboard                             │
│ 6. Increase tempo by 5 BPM when         │
│    comfortable                           │
│                                          │
│ Tablature                                │
│ ┌─────────────────────────────────────┐ │
│ │ e|----------5-6-7-8-|                │ │
│ │ B|------5-6-7-8-----|                │ │
│ │ G|--5-6-7-8---------|                │ │
│ │ D|5-6-7-8-----------|                │ │
│ │ A|------------------|                │ │
│ │ E|------------------|                │ │
│ └─────────────────────────────────────┘ │
│                                          │
│ [Add to Routine]              [Close]   │
└─────────────────────────────────────────┘
```

## Empty State (No Results)

```
┌─────────────────────────────────────────┐
│ ← Back    📚 Exercise Browser    🎛️    │
├─────────────────────────────────────────┤
│ ┌─────────────────────────────────────┐ │
│ │ 🔍 xyz123                       ✕  │ │
│ └─────────────────────────────────────┘ │
├─────────────────────────────────────────┤
│ 0 exercises found           Clear All   │
├─────────────────────────────────────────┤
│                                          │
│                                          │
│              🔍                          │
│                                          │
│        No exercises found                │
│                                          │
│   Try adjusting your filters or         │
│        search query                      │
│                                          │
│                                          │
│                                          │
└─────────────────────────────────────────┘
```

## Filter Chips States

### Unselected State
```
┌─────────┐
│   All   │  (gray/secondary color)
└─────────┘
```

### Selected State
```
┌─────────┐
│   All   │  (primary/accent color, filled)
└─────────┘
```

## Badge Colors (Consistent with RPG Theme)

- **Instrument Badge** (🎸 Guitar / 🎹 Piano): Blue/Info color
- **Technique Badge** (⚡): Red/Primary color
- **Creative Badge** (🎨): Gold/Accent color
- **Songs Badge** (🎵): Green/Success color
- **Difficulty - Beginner**: Green/Success color
- **Difficulty - Intermediate**: Orange/Warning color
- **Difficulty - Advanced**: Red/Danger color
- **Duration Badge**: Gray/Secondary color
- **BPM Badge** (♩): Gray/Secondary color

## Interaction Flow

### Basic Search Flow
1. User taps "🔍 Browse All Exercises" on home screen
2. Exercise Browser opens with all 150+ exercises
3. User types in search bar
4. Results filter in real-time
5. User taps an exercise card
6. Exercise detail dialog opens
7. User can read full info, then close
8. Back button returns to home

### Filtered Search Flow
1. User opens Exercise Browser
2. User taps filter icon (🎛️)
3. Filter panel expands
4. User selects multiple filters
   - Instrument: Guitar
   - Category: Technique
   - Difficulty: Beginner
5. Results update automatically
6. User sees filtered exercises
7. User can refine further or clear all

### Navigation Patterns
```
Home Screen
    ↓ (tap Browse All Exercises)
Exercise Browser
    ↓ (tap exercise card)
Exercise Detail Dialog
    ↓ (close)
Exercise Browser
    ↓ (back)
Home Screen
```

## Responsive Design Notes

### Portrait Mode (Phone)
- Single column layout
- Filter panel full width
- Exercise cards full width
- Comfortable thumb reach for buttons

### Landscape Mode (Phone)
- Optimized for wider screen
- Filter panel still full width but compact
- Exercise cards may show more info side-by-side

### Tablet
- Potentially two-column layout for exercise cards (future enhancement)
- More breathing room for filters
- Larger touch targets

## Accessibility Features

1. **Clear Labels**: All buttons have descriptive text
2. **Icon + Text**: Icons paired with text for clarity
3. **Touch Targets**: Minimum 48dp touch targets
4. **Color Contrast**: High contrast text on backgrounds
5. **Content Descriptions**: All icons have content descriptions
6. **Clear Hierarchy**: Logical tab order for navigation

## Performance Optimizations

1. **LazyColumn**: Only renders visible items
2. **remember**: Memoizes filter results
3. **State Keys**: Recomputes only when dependencies change
4. **Efficient Filtering**: Single-pass filtering with early exits

## Color Palette (RPG Theme)

From RpgComponents.kt:
- Background: Dark gray (#1a1a1a)
- Card Background: Slightly lighter gray
- Primary: Red tones
- Accent: Gold/yellow tones
- Success: Green
- Warning: Orange
- Danger: Red
- Info: Blue
- Text Primary: Light gray/white
- Text Secondary: Medium gray
