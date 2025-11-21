# Piano Exercises Documentation

## Overview

RiffScroll now includes comprehensive piano exercises alongside guitar exercises, making it a complete practice tool for both instruments.

## Piano Exercise Library

### Technique Exercises (6 total)

#### 1. C Major Scale - Hands Separate
- **Duration**: 5 minutes
- **BPM**: 60
- **Difficulty**: Beginner
- **Focus**: Proper fingering, hand independence
- Teaches the fundamental scale fingering pattern with thumb-under technique

#### 2. Hanon Exercise #1
- **Duration**: 5 minutes
- **BPM**: 60
- **Difficulty**: Beginner
- **Focus**: Finger independence, strength
- Classic Hanon exercise for developing even touch across all fingers

#### 3. Broken Chord Arpeggios
- **Duration**: 5 minutes
- **BPM**: 72
- **Difficulty**: Intermediate
- **Focus**: Hand coordination, arpeggios
- Practice arpeggios in all keys through the circle of fifths

#### 4. Contrary Motion Scales
- **Duration**: 5 minutes
- **BPM**: 60
- **Difficulty**: Intermediate
- **Focus**: Coordination, hand independence
- Both hands play scales simultaneously in opposite directions

#### 5. Trills and Ornaments
- **Duration**: 5 minutes
- **BPM**: 80
- **Difficulty**: Advanced
- **Focus**: Speed, control
- Develop rapid alternation between adjacent notes

#### 6. Octave Practice
- **Duration**: 5 minutes
- **BPM**: 60
- **Difficulty**: Advanced
- **Focus**: Strength, accuracy
- Essential for romantic and virtuosic repertoire

### Creativity Exercises (4 total)

#### 1. Chord Progression Exploration
- **Duration**: 10 minutes
- **Difficulty**: Intermediate
- **Focus**: Improvisation, chord voicings
- Improvise melodies over common chord progressions

#### 2. Blues Scale Improvisation
- **Duration**: 10 minutes
- **BPM**: 90
- **Difficulty**: Intermediate
- **Focus**: Blues vocabulary, expression
- Explore blues scales over 12-bar blues progressions

#### 3. Voicing Practice
- **Duration**: 8 minutes
- **Difficulty**: Advanced
- **Focus**: Chord colors, jazz harmony
- Experiment with different chord voicings and extensions

#### 4. Modal Exploration
- **Duration**: 10 minutes
- **Difficulty**: Advanced
- **Focus**: Modes, improvisation
- Explore different modes and their unique characteristics

### Song Exercises (4 total)

#### 1. Difficult Passage Practice
- **Duration**: 15 minutes
- **Difficulty**: Intermediate
- **Focus**: Problem-solving, technique
- Work on challenging sections with hands separate and slow practice

#### 2. Piece Performance Run-Through
- **Duration**: 10 minutes
- **Difficulty**: Intermediate
- **Focus**: Performance skills, consistency
- Play through entire pieces without stopping

#### 3. Memorization Practice
- **Duration**: 10 minutes
- **Difficulty**: Beginner
- **Focus**: Memory, structure
- Systematic approach to memorizing piano pieces

#### 4. Sight Reading Practice
- **Duration**: 15 minutes
- **Difficulty**: Beginner
- **Focus**: Reading skills
- Improve ability to read new music at the piano

## Routine Generation

Piano exercises are fully integrated into the routine generation system:

### Piano-Only Routines
Generate routines with only piano exercises:
```kotlin
viewModel.generateRoutine(
    targetDurationMinutes = 45,
    difficulty = DifficultyLevel.INTERMEDIATE,
    instrument = InstrumentType.PIANO
)
```

### Mixed Instrument Routines
Generate routines with both guitar and piano exercises:
```kotlin
viewModel.generateRoutine(
    targetDurationMinutes = 60,
    difficulty = null,
    instrument = null  // Both instruments
)
```

## Practice Tips

### For Beginners
1. Start with C Major Scale and Hanon exercises
2. Practice hands separately before combining
3. Use a metronome at comfortable tempos
4. Focus on proper posture and hand position

### For Intermediate Players
1. Work on broken chord arpeggios in all keys
2. Explore chord progressions and improvisation
3. Practice difficult passages methodically
4. Develop sight reading skills regularly

### For Advanced Players
1. Master trills, ornaments, and octave passages
2. Experiment with advanced voicings
3. Explore modal improvisation
4. Work on performance and musicality

## Comparison with Guitar Exercises

| Aspect | Guitar | Piano |
|--------|--------|-------|
| Technique Exercises | 25 | 6 |
| Creativity Exercises | 8 | 4 |
| Song Exercises | 4 | 4 |
| **Total** | **37** | **14** |

Piano exercises focus on essential foundational skills while guitar exercises offer more variety due to the instrument's specific techniques.

## Future Piano Exercise Ideas

- Scales in all keys (major and minor)
- Additional Hanon exercises
- Czerny studies
- Two-hand independence exercises
- Pedaling techniques
- Stride piano patterns
- Jazz comping patterns
- Classical étude practice

## Resources

For more information on piano technique and practice methods:
- Hanon: The Virtuoso Pianist
- Czerny: School of Velocity
- Berklee Music Theory books
- Online piano tutorials and courses
