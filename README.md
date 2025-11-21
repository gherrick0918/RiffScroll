# RiffScroll

⚔️ **A Music Practice Routine App with Old School RPG Theme**

## Overview

RiffScroll is an Android app that dynamically generates balanced practice routines for guitar and piano with an old school RPG theme. The app helps musicians structure their practice sessions across techniques, creativity, and song learning, with support for both instruments.

## Features

- **Multi-Instrument Support**: Practice routines for both guitar 🎸 and piano 🎹
- **Dynamic Routine Generation**: Creates balanced practice routines covering techniques, creativity, and songs
- **Instrument Filtering**: Generate routines for guitar only, piano only, or both instruments
- **Calendar Scheduling**: Auto-generate practice schedules tied to calendar dates
- **Exercise Display**: Shows detailed instructions for each exercise
- **Timer Integration**: Built-in timer for practice segments
- **Metronome Support**: Adjustable metronome (40-240 BPM) for timing-based exercises
- **RPG Theme**: Old school RPG-inspired UI with level progression and XP system
- **Responsive Design**: Supports portrait and landscape modes on phones and tablets
- **Progress Tracking**: Track completed routines, practice time, and level advancement

## Technical Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## Project Structure

```
app/src/main/kotlin/com/riffscroll/
├── data/                      # Data models and repository
│   ├── Models.kt             # Exercise, Routine, Session data models
│   └── ExerciseRepository.kt # Exercise data and routine generation
├── viewmodel/                 # ViewModels
│   └── PracticeViewModel.kt  # Manages practice state and logic
├── ui/                        # UI components
│   ├── RpgComponents.kt      # Reusable RPG-themed components
│   ├── HomeScreen.kt         # Main screen with routine generation
│   └── PracticeSessionScreen.kt # Active practice session UI
└── MainActivity.kt            # Entry point
```

## Exercise Categories

### Guitar Exercises

#### Techniques (25 exercises)
- Chromatic scale practice
- Alternate picking drills
- String skipping exercises
- Hammer-on & pull-off practice
- Bending accuracy
- Economy picking
- Trills and tremolo picking
- Sweep picking
- And more...

#### Creativity (8 exercises)
- Improvisation in pentatonic
- Melodic composition
- Rhythm creation
- Modal exploration
- Scale patterns and sequences
- Triad practice
- And more...

#### Songs (4 exercises)
- Song section practice
- Full song play-through
- Song memorization
- Cover song learning

### Piano Exercises

#### Techniques (6 exercises)
- C Major scale practice
- Hanon finger exercises
- Broken chord arpeggios
- Contrary motion scales
- Trills and ornaments
- Octave practice

#### Creativity (4 exercises)
- Chord progression exploration
- Blues scale improvisation
- Voicing practice
- Modal exploration

#### Songs (4 exercises)
- Difficult passage practice
- Piece performance run-through
- Memorization practice
- Sight reading practice

## Building the App

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run tests
./gradlew test
```

## Requirements

- Android Studio Hedgehog or later
- Gradle 8.2+
- JDK 17+

## License

Copyright 2024 RiffScroll