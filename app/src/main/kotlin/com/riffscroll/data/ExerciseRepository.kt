package com.riffscroll.data

import kotlin.random.Random

/**
 * Repository for exercise data and routine generation
 */
class ExerciseRepository {
    
    private val techniqueExercises = listOf(
        Exercise(
            id = "tech_1",
            name = "Chromatic Scale Practice",
            description = "Practice chromatic scales to build finger strength and dexterity",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Start on the low E string, 5th fret with your index finger",
                "Play one finger per fret: Index(5)-Middle(6)-Ring(7)-Pinky(8)",
                "Move to the next string (A) and repeat the pattern",
                "Continue across all six strings, then reverse back down",
                "Keep your fingers close to the fretboard",
                "Increase tempo by 5 BPM when comfortable"
            ),
            tablature = """
e|----------5-6-7-8-|
B|------5-6-7-8-----|
G|--5-6-7-8---------|
D|5-6-7-8-----------|
A|------------------|
E|------------------|
            """.trimIndent()
        ),
        Exercise(
            id = "tech_2",
            name = "Alternate Picking Drill",
            description = "Master alternate picking technique for speed and precision",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Use strict down-up picking pattern (∏ ∨ ∏ ∨)",
                "Start on the G string, 12th fret",
                "Play 4 notes per string: 12-13-14-15",
                "Keep your wrist relaxed, use small picking motions",
                "Each string gets the same down-up-down-up pattern",
                "Gradually increase speed while maintaining accuracy"
            ),
            tablature = """
e|12-13-14-15-|
B|12-13-14-15-|
G|12-13-14-15-|
D|12-13-14-15-|
A|12-13-14-15-|
E|12-13-14-15-|
   ∏ ∨  ∏  ∨
            """.trimIndent()
        ),
        Exercise(
            id = "tech_3",
            name = "String Skipping Exercise",
            description = "Develop accuracy and coordination with string skipping patterns",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Skip strings while maintaining pick control",
                "Practice pattern: Low E to G, A to high e",
                "Focus on clean note separation and no string noise",
                "Start slow - accuracy over speed",
                "Use economy of motion in your picking hand",
                "Try both ascending and descending patterns"
            ),
            tablature = """
e|-----8----|-----8----|
B|----------|----------|
G|--7-------|--7-------|
D|----------|----------|
A|5---------|5---------|
E|----------|----------|
            """.trimIndent()
        ),
        Exercise(
            id = "tech_4",
            name = "Hammer-On & Pull-Off Practice",
            description = "Build legato technique with hammer-ons and pull-offs",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Pick only the first note, hammer-on the rest",
                "For pull-offs, pull down/sideways to sound the note",
                "Start with 5-7-8 on the high E string",
                "Ensure even volume across all notes",
                "Practice both ascending (hammer-ons) and descending (pull-offs)",
                "Combine both: 5h7h8p7p5 pattern"
            ),
            tablature = """
Hammer-ons:  e|5h7h8----|
Pull-offs:   e|8p7p5----|
Combined:    e|5h7h8p7p5|
(h=hammer-on, p=pull-off)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_5",
            name = "Bending Accuracy",
            description = "Master string bending technique and pitch accuracy",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Start with half-step bends on the B string, 8th fret",
                "Use your ring finger, support with middle and index",
                "Bend upward toward the ceiling",
                "Check pitch accuracy: 8th fret bent = 9th fret unbent",
                "Practice whole-step bends (8th = 10th fret pitch)",
                "Work on pre-bends and controlled releases"
            ),
            tablature = """
B|8b9----| (half-step bend)
B|8b10---| (whole-step bend)
B|8b10r8-| (bend and release)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_6",
            name = "Four-Fret Chromatic Run",
            description = "Chromatic run up the first 4 frets on each string for warm-up and speed building",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Start on the low E string, 1st fret",
                "Play one finger per fret: Index(1)-Middle(2)-Ring(3)-Pinky(4)",
                "Move to the next string and repeat: 1-2-3-4",
                "Continue across all six strings",
                "Use alternate picking throughout: down-up-down-up",
                "Keep your fingers close to the fretboard",
                "Great warm-up exercise to start practice sessions"
            ),
            tablature = """
e|1-2-3-4----------|
B|------1-2-3-4----|
G|----------1-2-3-4|
D|1-2-3-4----------|
A|------1-2-3-4----|
E|----------1-2-3-4|
            """.trimIndent()
        ),
        Exercise(
            id = "tech_7",
            name = "Three-Note Pattern Exercise",
            description = "Three-note ascending pattern for building alternate picking and finger independence",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Play a repeating three-note pattern: 1-2-3, 2-3-4, 3-4-5...",
                "Use strict alternate picking throughout",
                "The pattern shifts against the beat in 4/4 time",
                "Keep notes clean and even",
                "Start slowly and build speed gradually",
                "Excellent for developing rhythmic independence"
            ),
            tablature = """
e|1-2-3-2-3-4-3-4-5-4-5-6-|
Pattern continues...
            """.trimIndent()
        ),
        Exercise(
            id = "tech_8",
            name = "Finger Isolation 1-2",
            description = "Isolation exercise for index and middle fingers to build strength and coordination",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Use only your 1st (index) and 2nd (middle) fingers",
                "Play on frets 1 and 2 only",
                "Pattern: 1-2-1-2 on each string",
                "Focus on even volume and timing",
                "Use alternate picking throughout",
                "Great for warming up responsive fingers"
            ),
            tablature = """
e|1-2-1-2-1-2-1-2-|
B|1-2-1-2-1-2-1-2-|
G|1-2-1-2-1-2-1-2-|
            """.trimIndent()
        ),
        Exercise(
            id = "tech_9",
            name = "Finger Isolation 3-4",
            description = "Isolation exercise for ring and pinky fingers to strengthen weak fingers",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Use only your 3rd (ring) and 4th (pinky) fingers",
                "Play on frets 3 and 4 (or 3 and 5 for stretch)",
                "Pattern: 3-4-3-4 on each string",
                "This is very challenging - the 3rd finger is actually the weakest",
                "Start slow and focus on clean notes",
                "Builds strength in the most difficult finger combination",
                "Take breaks if you feel cramping"
            ),
            tablature = """
e|3-4-3-4-3-4-3-4-|
B|3-4-3-4-3-4-3-4-|
G|3-4-3-4-3-4-3-4-|
            """.trimIndent()
        ),
        Exercise(
            id = "tech_10",
            name = "Legato Hammer-On Exercise",
            description = "Smooth hammer-on patterns for legato technique without picking",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Pick only the first note of each string",
                "Hammer-on the remaining notes: 1h2h3h4",
                "Focus on making hammer-ons as loud as picked notes",
                "Keep smooth, connected sound (legato)",
                "Build finger strength in your fretting hand",
                "Practice on each string separately first",
                "Gradually increase speed once even volume is achieved"
            ),
            tablature = """
e|1h2h3h4---------|
B|------1h2h3h4---|
G|------------1h2h3h4|
(h=hammer-on)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_11",
            name = "Spider Walk Exercise",
            description = "Wide stretch exercise ascending the fretboard to build finger span",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Start at the 1st fret, one finger per fret (1-2-3-4)",
                "After completing a string, move up one fret",
                "Continue: frets 2-3-4-5, then 3-4-5-6, etc.",
                "Descend back down the fretboard",
                "This \"walks\" your hand up and down the neck",
                "Excellent for building stretch and flexibility",
                "Stop at the 15th fret or wherever comfortable"
            ),
            tablature = """
e|1-2-3-4-2-3-4-5-3-4-5-6...|
Starting position walks up
            """.trimIndent()
        ),
        Exercise(
            id = "tech_12",
            name = "Tremolo Picking Endurance",
            description = "Single-note tremolo picking to build stamina and speed",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 120,
            instructions = listOf(
                "Pick a single note as fast as possible with alternate picking",
                "Start on an open string or any fretted note",
                "Maintain steady, even picking motion",
                "Focus on relaxed wrist, minimal arm movement",
                "Practice for 30-60 seconds without stopping",
                "Take breaks to avoid cramping",
                "This builds endurance for fast passages",
                "Gradually increase duration as stamina improves"
            )
        ),
        // Additional exercises from speed training resources
        Exercise(
            id = "tech_13",
            name = "Economy Picking Across Strings",
            description = "Practice economy picking to reduce pick movement and increase speed",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Economy picking combines alternate picking with sweep picking",
                "When moving to a new string in the same direction, continue that stroke",
                "Example: Down on string 1, down on string 2 (economy), up on string 2",
                "Practice three-note-per-string scales using economy picking",
                "Start with a simple ascending pattern across two strings",
                "Focus on smooth transitions between strings",
                "Gradually increase speed while maintaining clean notes",
                "Common in jazz, fusion, and shred guitar styles"
            ),
            tablature = """
e|7-8-10-|
B|8-10-12| (use economy motion)
Pattern: ∏ ∨ ∏ ∏ ∨ ∏
            """.trimIndent()
        ),
        Exercise(
            id = "tech_14",
            name = "Trill Exercise",
            description = "Rapid hammer-on and pull-off between two notes for finger speed",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 100,
            instructions = listOf(
                "Trill means rapidly alternating between two notes",
                "Pick the first note only, then hammer-on and pull-off repeatedly",
                "Start with frets 5 and 7 on any string (two frets apart)",
                "Practice: 5h7p5h7p5h7p5h7p... for 4 beats",
                "Try different finger combinations: 1-3, 1-4, 2-4",
                "Increase speed while maintaining even volume",
                "Essential technique for fast passages and ornaments",
                "Practice on all strings for complete finger development"
            ),
            tablature = """
e|5h7p5h7p5h7p5h7p|
(h=hammer-on, p=pull-off)
Pick only the first note
            """.trimIndent()
        ),
        Exercise(
            id = "tech_15",
            name = "String Skipping Arpeggio",
            description = "Arpeggio patterns with string skipping for accuracy and speed",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Arpeggios across non-adjacent strings build pick accuracy",
                "Example: A minor arpeggio skipping strings",
                "Play: E string (5th fret), G string (7th fret), B string (5th fret)",
                "Use strict alternate picking throughout",
                "Focus on avoiding unwanted string noise",
                "Mute unused strings with both hands",
                "Start slowly, accuracy is more important than speed",
                "Common in modern rock and metal guitar playing"
            ),
            tablature = """
e|-------8----|
B|----8-------|
G|----------- |
D|------------|
A|5-----------|
E|------------|
            """.trimIndent()
        ),
        Exercise(
            id = "tech_16",
            name = "Descending Pull-Off Runs",
            description = "Fast descending legato runs using pull-offs",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Place all four fingers on frets 5-6-7-8 before starting",
                "Pick only the first note (8th fret)",
                "Pull off rapidly: 8p7p6p5 in one smooth motion",
                "Keep all fingers down until they need to pull off",
                "Practice on each string separately",
                "Move the pattern across all six strings",
                "Combine with ascending hammer-ons for complete runs",
                "Essential for fast legato playing and shred technique"
            ),
            tablature = """
e|8p7p6p5-----|
(Pick first note only)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_17",
            name = "Sweep Picking Triads",
            description = "Basic sweep picking technique across three strings",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Sweep picking uses a single fluid motion across multiple strings",
                "Start with a simple Am triad shape",
                "Use one continuous downstroke across all three notes going down",
                "Then one continuous upstroke coming back up",
                "Keep pick angle consistent, let it glide across strings",
                "Mute behind the pick with your fretting hand",
                "Each note should ring clearly but not bleed together",
                "Practice ascending and descending patterns separately first"
            ),
            tablature = """
G|5------------|
B|--5----------|
e|----5--------|
   ∏  ∏  ∏ (sweep down)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_18",
            name = "Single-String Scale Run",
            description = "Complete scale played on a single string to build left-hand strength",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Play an entire scale on one string using position shifts",
                "Example: G major on the G string: 0-2-4-5-7-9-11-12",
                "Requires large stretches and position shifts",
                "Use alternate picking throughout",
                "Focus on clean fretting and minimal left-hand tension",
                "Practice ascending and descending",
                "Excellent for building finger independence and strength",
                "Try different scales and different strings"
            ),
            tablature = """
G|0-2-4-5-7-9-11-12-|
(G major scale on G string)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_19",
            name = "Two-Hand Tapping Exercise",
            description = "Basic two-hand tapping for extended range and speed",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Use your picking hand's index or middle finger to tap frets",
                "Example pattern: Tap 12, pull-off to 5, hammer-on to 8",
                "Notation: 12t 5 8h (t=tap with right hand)",
                "Start slowly to develop coordination between hands",
                "Tap firmly and pull off cleanly to sound the note",
                "Practice on one string before moving to complex patterns",
                "Keep fretting hand in position, minimize movement",
                "Famous technique used by Eddie Van Halen, Steve Vai"
            ),
            tablature = """
e|12t-5-8h-12t-5-8h|
(t=tap with right hand)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_20",
            name = "Pentatonic Sequence Exercise",
            description = "Sequenced patterns through pentatonic scale for speed and fluidity",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Use A minor pentatonic position 1 (5th position)",
                "Play in sequences: groups of 3, 4, or 6 notes",
                "Example sequence of 4: 5-7-8-5, 7-8-5-7, 8-5-7-8",
                "Creates flowing, musical patterns rather than just scales",
                "Practice with alternate picking throughout",
                "Try different note groupings for variety",
                "Essential for developing smooth, fast lead playing",
                "Sequences help build muscle memory for improvisation"
            ),
            tablature = """
A minor pentatonic (sequence of 4):
e|5-7-8-5-7-8-5-8-|
            """.trimIndent()
        ),
        Exercise(
            id = "tech_21",
            name = "Position Shifting Exercise",
            description = "Practice smooth position shifts along the fretboard",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Play ascending chromatic pattern while shifting positions",
                "Start at 1st position (1-2-3-4), shift to 2nd (2-3-4-5)",
                "Continue shifting up the neck: 3-4-5-6, 4-5-6-7, etc.",
                "Use the same finger pattern in each position",
                "Focus on smooth, quick position changes",
                "Minimize hesitation between positions",
                "Slide your hand, don't jump awkwardly",
                "Descend back down to starting position"
            ),
            tablature = """
e|1-2-3-4-2-3-4-5-3-4-5-6-|
Position shifts up the neck
            """.trimIndent()
        ),
        Exercise(
            id = "tech_22",
            name = "Hybrid Picking Exercise",
            description = "Combine pick and fingers for complex picking patterns",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Hold pick normally, use middle and ring fingers too",
                "Pick bass notes with the pick, higher strings with fingers",
                "Example: Pick low E, pluck G and B strings simultaneously",
                "Common in country, bluegrass, and modern rock",
                "Allows for wider interval jumps and faster patterns",
                "Practice alternating between pick and finger plucks",
                "Start with simple patterns before complex ones",
                "Develops independence between pick and fingers"
            ),
            tablature = """
e|-------5-----|
B|-------5-----| (pluck with fingers)
G|-----5-------| (pluck with fingers)
D|-------------|
A|--3----------| (pick)
E|-------------|
            """.trimIndent()
        ),
        Exercise(
            id = "tech_23",
            name = "Pinkie Anchor Training",
            description = "Reversed chromatic pattern using pinky as anchor point (from 10 Guitar Speed Training Exercises)",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "This is the reversed form of standard chromatic exercises",
                "Start with pinky on 4th fret, work backwards: 4-3-2-1",
                "The pinky becomes the anchor point instead of the index finger",
                "Pattern: pinky, ring, middle, index on each string",
                "This strengthens the weakest fingers (pinky and ring)",
                "Start slowly - this is much harder than forward chromatic",
                "Take breaks if you feel burning in your fretting hand",
                "Excellent for developing pinky independence and strength"
            ),
            tablature = """
e|4-3-2-1------|
B|------4-3-2-1|
G|4-3-2-1------|
D|------4-3-2-1|
(Reversed pattern)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_24",
            name = "Chromatic Slide Extension",
            description = "Chromatic pattern with pinky slides to extend range (from 10 Guitar Speed Training Exercises)",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Similar to standard chromatic but adds a slide at the peak",
                "Pattern: 1-2-3-4, then slide pinky from 4 to 5",
                "Descend from the 5th fret: 5-4-3-2-1",
                "This creates an uneven 5-fret pattern vs standard 4-fret",
                "Develops coordination between left and right hands",
                "Teaches smooth fretboard gliding technique",
                "Continue the pattern up the neck as far as comfortable",
                "Start at even tempo and gradually increase speed"
            ),
            tablature = """
e|1-2-3-4s5-4-3-2-1-|
(s = slide from 4 to 5)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_25",
            name = "Neo-Classical Step-Up Pattern",
            description = "Ascending linear pattern for coordination and neo-classical style (from 10 Guitar Speed Training Exercises)",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Neo-classical exercise pattern for hand coordination",
                "Breaks out of the typical 'box' patterns",
                "Linear series of notes across the fretboard",
                "Focus on coordination between picking and fretting hands",
                "Start very slow - this is technically demanding",
                "Sounds musical even at slow speeds",
                "Study classical composers for arrangement ideas",
                "Great for developing fluid, connected playing across positions"
            ),
            tablature = """
e|-----5-6-7-8-9---|
B|---5-6-7-8-------|
G|-5-6-7-----------|
(Diagonal ascending pattern)
            """.trimIndent()
        )
    )
    
    private val creativityExercises = listOf(
        Exercise(
            id = "creative_1",
            name = "Improvisation in Pentatonic",
            description = "Free improvisation using pentatonic scale patterns",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Use A minor pentatonic: A-C-D-E-G",
                "Start with position 1 at the 5th fret",
                "Choose a backing track in Am or use a drone note",
                "Explore the full fretboard using all 5 positions",
                "Focus on phrasing: use space, dynamics, and rhythm",
                "Try bending, sliding, and vibrato for expression",
                "Record yourself to identify what sounds good"
            ),
            tablature = """
A Minor Pentatonic (Position 1):
e|-----5-8--------|
B|---5-8----------|
G|-5-7------------|
D|5-7-------------|
A|5-7-------------|
E|5-8-------------|
            """.trimIndent()
        ),
        Exercise(
            id = "creative_2",
            name = "Melodic Composition",
            description = "Compose short melodic phrases and motifs",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Create a simple 4-bar melody in one key",
                "Use a mix of quarter, eighth, and half notes",
                "Try different scales: major, minor, pentatonic",
                "Develop your melody with repetition and variation",
                "Experiment with starting on different scale degrees",
                "Record or write down melodies you like",
                "Play your melody in different octaves and positions"
            )
        ),
        Exercise(
            id = "creative_3",
            name = "Rhythm Creation",
            description = "Develop unique rhythm patterns and strumming",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 8,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 100,
            instructions = listOf(
                "Start with basic down strums on the beat (1-2-3-4)",
                "Add upstrokes between beats for eighth notes",
                "Create syncopated patterns by adding accents",
                "Practice palm muting for percussive effects",
                "Experiment with string muting and ghost strums",
                "Combine patterns with chord progressions (e.g., Am-F-C-G)",
                "Try different pick attack angles and strengths"
            ),
            tablature = """
Basic Pattern:  ∏   ∏   ∏   ∏
                1   2   3   4

Syncopated:     ∏ ∨ ∏ ∨ x ∨ ∏ ∨
                1 & 2 & 3 & 4 &
                (x = muted strum)
            """.trimIndent()
        ),
        Exercise(
            id = "creative_4",
            name = "Modal Exploration",
            description = "Explore different modes and their unique flavors",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Choose a mode: Dorian (minor with major 6th)",
                "Example: D Dorian uses C major scale starting on D",
                "Play the scale: D-E-F-G-A-B-C-D",
                "Notice the characteristic intervals and color",
                "Improvise short phrases emphasizing the root note",
                "Try other modes: Phrygian (dark), Lydian (bright), Mixolydian (bluesy)",
                "Compare the mood and feel of each mode"
            ),
            tablature = """
D Dorian Scale:
e|------10-12-13---|
B|----10-12-13-----|
G|--9-10-12--------|
D|10-12-14---------|
A|----------------- |
E|-----------------|
            """.trimIndent()
        ),
        Exercise(
            id = "creative_5",
            name = "Pentatonic Scale Patterns",
            description = "Practice all five positions of the pentatonic scale across the fretboard",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Learn all 5 positions of the minor pentatonic scale",
                "Start with position 1 (root note on 6th string)",
                "Practice each position separately until fluid",
                "Connect positions by moving up the fretboard",
                "Try improvising using different positions",
                "Explore different keys: Am, Em, Dm, etc.",
                "Essential foundation for lead guitar playing"
            ),
            tablature = """
Position 1 (A minor):
e|-----5-8--------|
B|---5-8----------|
G|-5-7------------|
D|5-7-------------|
A|5-7-------------|
E|5-8-------------|
            """.trimIndent()
        ),
        Exercise(
            id = "creative_6",
            name = "Major Scale Position Practice",
            description = "Practice major scales in different positions across the fretboard (from Berklee Guitar Handbook)",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Major scale follows the pattern: W-W-H-W-W-W-H",
                "Practice C major in first position (open position)",
                "Move to second position, then fifth, then seventh",
                "A position is defined by where your first finger plays",
                "Practice the same scale in multiple positions",
                "This helps organize the fretboard visually",
                "Essential for understanding keys and tonal music",
                "Move slowly between positions, ensuring accuracy"
            ),
            tablature = """
C Major (First Position):
e|-----0-1-3-------|
B|---0-1-3---------|
G|-0-2-------------|
(Continue pattern)
            """.trimIndent()
        ),
        Exercise(
            id = "creative_7",
            name = "Triad Practice Across String Sets",
            description = "Practice major triads in all positions (from Berklee Guitar Handbook)",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "A triad is a three-note chord: root, 3rd, and 5th",
                "Practice C major triad: C-E-G",
                "Play triads up the fretboard on strings 1-2-3",
                "Move one fret at a time through all 12 keys",
                "Major triad = major 3rd underneath a minor 3rd",
                "Chordal playing and melodic playing are two sides of same coin",
                "Understanding triads leads to thorough fretboard knowledge",
                "Practice on all string sets: 1-2-3, 2-3-4, 3-4-5, etc."
            ),
            tablature = """
C Major Triad (strings 1-2-3):
e|--8--|
B|--8--|
G|--9--|
            """.trimIndent()
        ),
        Exercise(
            id = "creative_8",
            name = "Rhythmic Variation Development",
            description = "Transform scales into musical solos using rhythmic variation (from Berklee Guitar Handbook)",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Playing scales note-by-note sounds like exercises, not music",
                "Add rests to create space in your solos",
                "Use uniform rhythmic modifications (quarter note + two eighths)",
                "Add triplets at the beginning of measures for ornamentation",
                "Repeat notes and sequences for melodic development",
                "Displace notes by adding rests for syncopation",
                "Add sustain on chord tones (1, 3, 5, 7) with tied notes",
                "Make the process intuitive through practice"
            ),
            tablature = """
Rhythmic pattern example:
Quarter + 2 eighths:
♩ ♪♪ ♩ ♪♪
            """.trimIndent()
        )
    )
    
    private val songExercises = listOf(
        Exercise(
            id = "song_1",
            name = "Song Section Practice",
            description = "Work on a specific section of your current song",
            category = ExerciseCategory.SONGS,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Choose the most challenging section (e.g., solo, bridge, difficult riff)",
                "Isolate a 4-8 bar segment to focus on",
                "Break it down into smaller chunks (1-2 bars at a time)",
                "Practice slowly at 50-60% of target tempo",
                "Focus on accuracy: clean notes, proper timing, correct fingering",
                "Gradually increase speed by 5 BPM increments",
                "Loop the section until you can play it 3 times perfectly",
                "Play along with the original recording at full tempo"
            )
        ),
        Exercise(
            id = "song_2",
            name = "Full Song Play-Through",
            description = "Play through an entire song from start to finish",
            category = ExerciseCategory.SONGS,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Choose a song you know reasonably well",
                "Play from beginning to end without stopping",
                "Don't stop for mistakes - keep the momentum going",
                "Focus on maintaining consistent tempo and feel",
                "Record yourself to evaluate your performance",
                "Note areas that need work (transitions, timing, technique)",
                "Count it as practice for performance endurance",
                "Try playing along with the original recording"
            )
        ),
        Exercise(
            id = "song_3",
            name = "Song Memorization",
            description = "Work on memorizing song structure and parts",
            category = ExerciseCategory.SONGS,
            durationMinutes = 10,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = false,
            instructions = listOf(
                "Put away sheet music, tabs, and chord charts",
                "Map out the song structure: Intro-Verse-Chorus-Bridge-etc.",
                "Visualize chord shapes and changes in your mind",
                "Practice counting bars in each section",
                "Focus on smooth transitions between sections",
                "Use mental cues: lyrics, drum fills, bass lines",
                "Play through memory 3 times, noting where you hesitate",
                "Review trouble spots with the music, then try again"
            )
        ),
        Exercise(
            id = "song_4",
            name = "Cover Song Learning",
            description = "Learn a new song or section by ear",
            category = ExerciseCategory.SONGS,
            durationMinutes = 15,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Choose a song slightly below your current skill level",
                "Listen to a short section (4-8 bars) on repeat",
                "Identify the key and chord progression first",
                "Figure out the bass notes or root notes",
                "Add melody notes or chord voicings",
                "Use slow-down tools if needed (YouTube 0.75x speed)",
                "Match the tone, articulation, and feel",
                "Practice along with the recording to check accuracy"
            )
        )
    )
    
    fun getAllExercises(): List<Exercise> {
        return techniqueExercises + creativityExercises + songExercises
    }
    
    fun getExercisesByCategory(category: ExerciseCategory): List<Exercise> {
        return when (category) {
            ExerciseCategory.TECHNIQUE -> techniqueExercises
            ExerciseCategory.CREATIVITY -> creativityExercises
            ExerciseCategory.SONGS -> songExercises
        }
    }
    
    fun getExercisesByDifficulty(difficulty: DifficultyLevel?): List<Exercise> {
        return if (difficulty == null) {
            getAllExercises()
        } else {
            getAllExercises().filter { it.difficulty == difficulty }
        }
    }
    
    /**
     * Generate a balanced practice routine
     * Ensures variety across all three categories and matches target duration more accurately
     */
    fun generateBalancedRoutine(
        targetDurationMinutes: Int = 45,
        difficulty: DifficultyLevel? = null
    ): PracticeRoutine {
        val exercises = mutableListOf<Exercise>()
        var totalDuration = 0
        
        // Filter exercises by difficulty if specified
        val availableTech = if (difficulty != null) {
            techniqueExercises.filter { it.difficulty == difficulty }
        } else {
            techniqueExercises
        }
        
        val availableCreative = if (difficulty != null) {
            creativityExercises.filter { it.difficulty == difficulty }
        } else {
            creativityExercises
        }
        
        val availableSongs = if (difficulty != null) {
            songExercises.filter { it.difficulty == difficulty }
        } else {
            songExercises
        }
        
        // Sort by duration to help with filling
        val techByDuration = availableTech.sortedBy { it.durationMinutes }
        val creativeByDuration = availableCreative.sortedBy { it.durationMinutes }
        val songsByDuration = availableSongs.sortedBy { it.durationMinutes }
        
        // Select exercises that fit within the target duration
        // Try to pick one from each category first
        val techExercise = techByDuration.firstOrNull { 
            totalDuration + it.durationMinutes <= targetDurationMinutes 
        }
        if (techExercise != null) {
            exercises.add(techExercise)
            totalDuration += techExercise.durationMinutes
        }
        
        val creativeExercise = creativeByDuration.firstOrNull { 
            totalDuration + it.durationMinutes <= targetDurationMinutes 
        }
        if (creativeExercise != null) {
            exercises.add(creativeExercise)
            totalDuration += creativeExercise.durationMinutes
        }
        
        val songExercise = songsByDuration.firstOrNull { 
            totalDuration + it.durationMinutes <= targetDurationMinutes 
        }
        if (songExercise != null) {
            exercises.add(songExercise)
            totalDuration += songExercise.durationMinutes
        }
        
        // Fill remaining time with exercises that fit, prioritizing variety
        val availableExercises = (availableTech + availableCreative + availableSongs)
            .filter { !exercises.contains(it) }
            .sortedBy { it.durationMinutes }
        
        for (exercise in availableExercises) {
            if (totalDuration + exercise.durationMinutes <= targetDurationMinutes) {
                exercises.add(exercise)
                totalDuration += exercise.durationMinutes
            }
        }
        
        // Calculate average difficulty level for the routine
        val avgDifficulty = if (exercises.isNotEmpty()) {
            exercises.map { it.difficulty.level }.average().toInt()
        } else {
            1
        }
        
        return PracticeRoutine(
            id = "routine_${System.currentTimeMillis()}",
            exercises = exercises,
            totalDurationMinutes = totalDuration,
            difficulty = avgDifficulty
        )
    }
}
