# RiffScroll Development Guide

## Building the App

This project requires Android Studio and the Android SDK to build. The Android Gradle Plugin (AGP) is not available in CI environments without the full Android SDK.

### Prerequisites

1. **Android Studio** - Hedgehog (2023.1.1) or later
2. **Android SDK** - API 34 (Android 14)
3. **JDK** - Version 17 or later
4. **Gradle** - Version 8.2 (automatically downloaded by wrapper)

### Build Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/gherrick0918/RiffScroll.git
   cd RiffScroll
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned repository
   - Wait for Gradle sync to complete

3. **Build the project**
   - From the menu: Build → Make Project
   - Or use the command line:
     ```bash
     ./gradlew assembleDebug
     ```

4. **Run on device/emulator**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio
   - Or use: `./gradlew installDebug`

## Project Architecture

### MVVM Pattern

The app uses the MVVM (Model-View-ViewModel) architecture pattern:

```
┌─────────────┐
│    View     │ (Jetpack Compose UI)
│  (UI Layer) │
└──────┬──────┘
       │ observes
       ▼
┌─────────────┐
│  ViewModel  │ (PracticeViewModel)
└──────┬──────┘
       │ uses
       ▼
┌─────────────┐
│    Model    │ (Data classes & Repository)
└─────────────┘
```

### Key Components

#### Data Layer (`data/`)

- **Models.kt**: Core data classes
  - `Exercise`: Individual practice exercise
  - `ExerciseCategory`: Enum for exercise types
  - `PracticeRoutine`: Collection of exercises
  - `PracticeSession`: Active practice state
  - `UserProgress`: RPG-style progress tracking

- **ExerciseRepository.kt**: Data source
  - Provides predefined exercises
  - Generates balanced routines
  - Categories: Techniques, Creativity, Songs

#### ViewModel Layer (`viewmodel/`)

- **PracticeViewModel.kt**: Business logic
  - Manages practice state
  - Timer functionality
  - Metronome control
  - XP and level progression
  - Uses Kotlin Coroutines for async operations

#### UI Layer (`ui/`)

- **RpgComponents.kt**: Reusable themed components
  - RpgCard, RpgButton, RpgProgressBar
  - RpgHeader, RpgText, RpgBadge
  - Consistent RPG theme styling

- **HomeScreen.kt**: Main interface
  - User progress display
  - Routine generation controls
  - Exercise list preview

- **PracticeSessionScreen.kt**: Active practice
  - Exercise details and instructions
  - Timer display
  - Metronome controls
  - Session navigation

- **MainActivity.kt**: App entry point
  - Manages navigation between screens
  - Collects ViewModel state

## Design Principles

### 1. Old School RPG Theme

The app features a retro RPG aesthetic:

- **Color Palette**:
  - Background: Dark blue-black (#1a1a2e)
  - Surface: Deep blue (#16213e)
  - Primary: Crimson red (#e94560)
  - Accent: Gold (#f39c12)
  - Success: Green (#27ae60)

- **Visual Elements**:
  - Bordered cards with pixel-art inspiration
  - Progress bars with gradient effects
  - Badge/tag system for categories
  - Level and XP system

### 2. Responsive Design

The app adapts to different screen sizes:

- **Portrait Mode**: Vertical scrolling layout
- **Landscape Mode**: Same layout with horizontal spacing
- **Tablets**: Utilizes extra space with larger components
- **Phones**: Compact, efficient layout

The `configChanges` attribute in the manifest handles orientation changes without recreating the Activity.

### 3. Practice Routine Balance

The `generateBalancedRoutine()` function ensures variety:

1. **Minimum Coverage**: At least one exercise from each category
2. **Time-Based**: Fits within target duration (default 45 min)
3. **Randomized**: Different routines each generation
4. **No Duplicates**: Each exercise appears only once

Example routine:
```
- Technique: Alternate Picking (5 min, 80 BPM)
- Creativity: Improvisation (10 min, 90 BPM)
- Song: Section Practice (15 min)
- Technique: String Skipping (5 min, 70 BPM)
- Creativity: Rhythm Creation (8 min, 100 BPM)
Total: 43 minutes
```

### 4. Timer and Metronome Integration

Each exercise can have:

- **Duration**: Target practice time in minutes
- **Timing**: Whether it uses a metronome
- **BPM**: Suggested tempo (40-240 range)

The timer runs continuously during practice, while the metronome can be toggled on/off and adjusted as needed.

#### Metronome Implementation

The metronome uses Android's `AudioTrack` API to generate clean click sounds:

- **Audio Generation**: Sine waves at 800Hz (regular beats) and 1200Hz (accented beats)
- **Time Signature**: 4/4 time with accent on first beat of each measure
- **Precision**: Millisecond-level timing to avoid drift
- **Duration**: 50ms click sounds with linear decay envelope
- **Sample Rate**: 44.1kHz (CD quality)
- **Format**: 16-bit PCM mono audio

The implementation provides:
- Real-time BPM adjustment (40-240 BPM)
- Smooth start/stop without clicks or pops
- Low CPU usage with pre-generated audio buffers
- Graceful error handling for audio initialization failures

### 5. Progress System

RPG-style progression mechanics:

- **XP Gain**: 2 XP per minute of practice
- **Level Up**: XP requirement increases by 50% each level
- **Stats Tracked**:
  - Total practice minutes
  - Completed routines
  - Current level and XP

## Exercise Content

### Technique Exercises (5)

Focus on mechanical skills and dexterity:
- Chromatic Scale Practice
- Alternate Picking Drill
- String Skipping Exercise
- Hammer-On & Pull-Off Practice
- Bending Accuracy

### Creativity Exercises (4)

Encourage musical expression:
- Improvisation in Pentatonic
- Melodic Composition
- Rhythm Creation
- Modal Exploration

### Song Exercises (4)

Apply skills to real music:
- Song Section Practice
- Full Song Play-Through
- Song Memorization
- Cover Song Learning

Each exercise includes:
- Clear name and description
- Step-by-step instructions
- Appropriate duration
- Optional BPM for timing

## Testing

### Manual Testing Checklist

- [ ] Generate routine with different durations (15, 30, 45, 60, 90 min)
- [ ] Start practice session
- [ ] Verify timer counts correctly
- [ ] Test pause/resume functionality
- [ ] Navigate through exercises with "Next" button
- [ ] Toggle metronome on/off
- [ ] Adjust metronome BPM (40-240 range)
- [ ] Complete full routine and verify XP gain
- [ ] Test level progression
- [ ] Rotate device between portrait and landscape
- [ ] Test on phone-sized screen
- [ ] Test on tablet-sized screen

### Automated Tests

Unit tests can be added for:
- `ExerciseRepository.generateBalancedRoutine()`
- `PracticeViewModel` state transitions
- XP calculation logic
- Timer functionality

Integration tests for:
- Full practice session flow
- Screen navigation
- Data persistence (when added)

## Future Enhancements

Potential features to add:

1. **Data Persistence**
   - Save user progress locally
   - Store practice history
   - Custom exercise creation

2. **Audio Integration**
   - ✅ Metronome sound playback (implemented with AudioTrack)
   - Backing tracks for practice
   - Audio recording for self-evaluation

3. **Advanced Features**
   - Custom routine templates
   - Practice goals and scheduling
   - Statistics and charts
   - Achievement system

4. **Social Features**
   - Share routines
   - Practice challenges
   - Leaderboards

5. **Customization**
   - Exercise difficulty levels
   - Focus area selection
   - Adjustable category weights

## Troubleshooting

### Gradle Sync Issues

If Gradle sync fails:
1. Check Android Studio and AGP versions match
2. Verify JDK 17+ is installed
3. Clear Gradle cache: `./gradlew clean`
4. Invalidate caches in Android Studio

### Build Errors

Common solutions:
1. Update Android SDK to latest version
2. Check internet connection for dependency downloads
3. Sync project with Gradle files
4. Rebuild project from scratch

### Runtime Issues

If app crashes or misbehaves:
1. Check Logcat for error messages
2. Verify minimum SDK version (API 24+)
3. Test on different devices/emulators
4. Check for null pointer exceptions in ViewModel

## Contributing

When adding new features:

1. Follow the existing MVVM architecture
2. Maintain the RPG theme consistency
3. Add appropriate comments and documentation
4. Test on multiple screen sizes
5. Ensure proper state management in ViewModel
6. Use Jetpack Compose best practices

### Adding New Exercises

The app is designed to make adding new exercises straightforward. Here's how:

#### 1. Define the Exercise

Add a new `Exercise` object to the appropriate category in `ExerciseRepository.kt`:

```kotlin
// For technique exercises, add to techniqueExercises list:
Exercise(
    id = "tech_6",  // Use unique ID
    name = "Sweep Picking Exercise",
    description = "Develop smooth sweep picking technique",
    category = ExerciseCategory.TECHNIQUE,
    durationMinutes = 5,
    hasTiming = true,
    bpm = 80,
    instructions = listOf(
        "Start with a simple Am arpeggio pattern",
        "Use one continuous pick motion across strings",
        "Focus on muting strings after playing them",
        "Practice slowly, ensuring each note rings clearly"
    ),
    tablature = """
e|-----5----|
B|---5------|
G|-5--------|
D|----------|
A|----------|
E|----------|
    """.trimIndent()
)
```

#### 2. Exercise Properties Explained

- **id**: Unique identifier (e.g., "tech_6", "creative_5", "song_5")
- **name**: Display name shown to users
- **description**: Brief one-line description
- **category**: One of `TECHNIQUE`, `CREATIVITY`, or `SONGS`
- **durationMinutes**: How long the exercise should take (5-15 min typical)
- **hasTiming**: Set to `true` if metronome is useful for this exercise
- **bpm**: Suggested BPM if `hasTiming` is true (40-240 range), null otherwise
- **instructions**: List of step-by-step instructions (4-8 steps recommended)
- **tablature**: Optional guitar tab notation (use `"""...""".trimIndent()` for multi-line)

#### 3. Exercise Categories

**TECHNIQUE** - Mechanical skills and technical exercises:
- Picking patterns
- Scales and arpeggios
- Finger exercises
- String control
- Fretting techniques

**CREATIVITY** - Musical expression and composition:
- Improvisation exercises
- Songwriting prompts
- Melody creation
- Rhythm development
- Theory application

**SONGS** - Practical application with real music:
- Song learning techniques
- Performance practice
- Memorization methods
- Style studies

#### 4. Best Practices

- **Duration**: Keep exercises between 5-15 minutes
  - Short exercises (5 min): Focused drills, warm-ups
  - Medium exercises (8-10 min): Moderate complexity
  - Long exercises (15 min): Complex work, song practice

- **Instructions**: 
  - Be specific and actionable
  - Include 4-8 clear steps
  - Start with setup/preparation
  - Progress from basic to advanced

- **Tablature**:
  - Use standard ASCII tab notation
  - Keep it simple and readable
  - Include rhythm notation if helpful
  - Add fingering hints (h=hammer, p=pull-off, b=bend, etc.)

- **BPM Selection**:
  - Beginner tempo: 60-80 BPM
  - Intermediate: 80-120 BPM
  - Advanced: 120-180 BPM
  - Set `hasTiming = false` for non-rhythmic exercises

#### 5. Testing Your Exercise

After adding an exercise:

1. Build and run the app
2. Generate several routines to see if your exercise appears
3. Verify all text displays correctly
4. Check tablature formatting
5. Test the metronome at the specified BPM
6. Ensure instructions are clear and complete

#### 6. Example Exercise Templates

**Simple Technique Exercise:**
```kotlin
Exercise(
    id = "tech_X",
    name = "Exercise Name",
    description = "One-line description",
    category = ExerciseCategory.TECHNIQUE,
    durationMinutes = 5,
    hasTiming = true,
    bpm = 60,
    instructions = listOf(
        "Step 1",
        "Step 2",
        "Step 3",
        "Step 4"
    ),
    tablature = "e|--0--1--2--3--|"
)
```

**Creativity Exercise (no tab):**
```kotlin
Exercise(
    id = "creative_X",
    name = "Creative Exercise",
    description = "One-line description",
    category = ExerciseCategory.CREATIVITY,
    durationMinutes = 10,
    hasTiming = false,
    instructions = listOf(
        "Step 1",
        "Step 2",
        "Step 3",
        "Step 4"
    )
)
```

#### 7. Routine Generation

The `generateBalancedRoutine()` algorithm automatically:
- Ensures at least one exercise from each category
- Randomizes exercise selection for variety
- Respects the target duration
- Prevents duplicate exercises in one routine

No changes needed to the algorithm when adding exercises - they'll automatically be included in future routine generation!

## License

Copyright 2024 RiffScroll
