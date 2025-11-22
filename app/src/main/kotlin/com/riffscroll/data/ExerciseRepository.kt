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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
        ),
        // Additional exercises from Guitar Workout and public resources
        Exercise(
            id = "tech_26",
            name = "Finger Rolling Exercise",
            description = "Practice smooth finger rolls for fast note transitions",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Finger rolling is when you use one finger to fret multiple strings",
                "Start with a barre chord shape (F major is classic)",
                "Practice rolling your index finger across strings",
                "Focus on even pressure and clean note articulation",
                "Essential for barre chords and advanced voicings",
                "Build up callus strength gradually",
                "Start with partial barres before full 6-string barres"
            )
        ),
        Exercise(
            id = "tech_27",
            name = "Vibrato Control",
            description = "Develop expressive vibrato technique for sustained notes",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Vibrato adds life and emotion to sustained notes",
                "Hold a note on the B string, 8th fret with your ring finger",
                "Rock your finger back and forth, bending slightly",
                "Keep the motion controlled and rhythmic",
                "Practice slow vibrato, fast vibrato, and wide vibrato",
                "Listen to blues and rock guitarists for inspiration",
                "Use wrist motion, not just finger motion"
            ),
            tablature = """
B|8~~~~ (vibrato on note)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_28",
            name = "Muting and Dampening",
            description = "Master palm muting and string dampening for rhythmic control",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Rest the edge of your picking hand on the strings near the bridge",
                "Strum powerfully while muting for a percussive sound",
                "Practice alternating between muted and unmuted strums",
                "Essential for metal, punk, and rock rhythm playing",
                "Experiment with how much you mute for different tones",
                "Combine with staccato for tight, punchy rhythms",
                "Try different muting positions for varied sounds"
            ),
            tablature = """
PM-----------|  (palm muted)
e|0-0-0-0-0-0-|
            """.trimIndent()
        ),
        Exercise(
            id = "tech_29",
            name = "Legato Runs",
            description = "Smooth connected notes using hammer-ons and pull-offs",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Legato means 'smooth and connected'",
                "Pick only the first note of each string",
                "Use hammer-ons ascending, pull-offs descending",
                "Create long flowing runs without picking every note",
                "Focus on even volume across picked and unpicked notes",
                "Essential for fluid, vocal-like lead playing",
                "Famous technique of Joe Satriani, Allan Holdsworth"
            ),
            tablature = """
e|5h7h8p7p5-7h9h10p9p7-|
Pick only first note
            """.trimIndent()
        ),
        Exercise(
            id = "tech_30",
            name = "Chord Changes Speed Drill",
            description = "Practice rapid chord changes for rhythm playing",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Choose two chords that share no common fingers (e.g., C to Am)",
                "Practice switching between them smoothly",
                "Start slow, aim for zero hesitation between chords",
                "All fingers should move simultaneously, not one at a time",
                "Increase tempo gradually as you build muscle memory",
                "Practice common progressions: G-C-D, Am-F-C-G",
                "Essential foundation for rhythm guitar"
            )
        ),
        Exercise(
            id = "tech_31",
            name = "Finger Stretches",
            description = "Increase finger span and flexibility",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Place fingers on frets 1-2-3-4, one finger per fret",
                "Now stretch to play 1-3-4-6 (skipping fret 2 and 5)",
                "Move pattern up and down the neck",
                "This builds reach for wide interval passages",
                "Start gently to avoid injury",
                "Warm up first with standard chromatic exercises",
                "Beneficial for jazz voicings and extended chords"
            ),
            tablature = """
e|1---3-4---6-|
(stretched pattern)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_32",
            name = "Artificial Harmonics",
            description = "Create bell-like harmonic tones for advanced technique",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Fret a note with your left hand (e.g., 5th fret)",
                "Touch the string lightly 12 frets higher with your pick hand",
                "Pick the string while touching lightly to produce harmonic",
                "Creates a bell-like, shimmering tone",
                "Common in metal and progressive rock",
                "Practice finding the harmonic nodes (12, 7, 5 frets up)",
                "Eddie Van Halen and Steve Vai use this extensively"
            ),
            tablature = """
e|5<12>-| (fret 5, harmonic at 17)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_33",
            name = "Rhythmic Displacement",
            description = "Develop rhythmic independence and syncopation",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Play a simple pattern but start it on different beats",
                "Example: triplet pattern starting on beat 1, then 2, then 3",
                "Creates polyrhythmic effects and interesting grooves",
                "Challenges your internal metronome",
                "Essential for progressive rock and jazz fusion",
                "Use a metronome and count carefully",
                "Creates tension and release in improvisation"
            )
        ),
        Exercise(
            id = "tech_34",
            name = "Open String Ringing",
            description = "Incorporate open strings into fretted passages",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Alternate between fretted notes and open strings",
                "Let open strings ring while fretting other notes",
                "Creates a drone or pedal tone effect",
                "Common in folk, country, and Celtic music",
                "Enriches the harmonic texture",
                "Practice in keys that use open strings (E, A, D, G)",
                "Explore DADGAD and other open tunings"
            ),
            tablature = """
e|--0-3-0-5-0-3-0-|
(Open E mixed with fretted)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_35",
            name = "Slides and Glissandos",
            description = "Smooth sliding between notes for expressive playing",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Pick a note, then slide up or down to another fret",
                "Keep constant pressure on the string while sliding",
                "Practice both short slides (2-3 frets) and long slides",
                "Experiment with slide speed: slow and expressive vs. fast",
                "Common in blues, rock, and country guitar",
                "Use with bends and vibrato for maximum expression",
                "Try sliding into notes from below or above"
            ),
            tablature = """
e|5/7\\5-| (slide up, slide down)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_36",
            name = "Rake Technique",
            description = "Muted string raking for percussive attack",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Mute strings with your fretting hand",
                "Rake across muted strings before hitting the target note",
                "Creates a percussive 'scrape' before the note",
                "Common in blues and rock soloing",
                "Adds aggression and attitude to your playing",
                "Used extensively by Stevie Ray Vaughan",
                "Practice controlling the volume of muted vs. clean notes"
            ),
            tablature = """
    x x 5
e|--x-x-5-| (rake across muted)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_37",
            name = "Double Stops",
            description = "Play two notes simultaneously for harmony",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Play two strings at once, creating intervals",
                "Common intervals: thirds, fourths, fifths, sixths",
                "Barre two strings with one finger or use two fingers",
                "Practice moving double stops up and down the neck",
                "Fundamental to Chuck Berry-style rock and blues",
                "Enriches rhythm and lead playing",
                "Try different interval combinations for varied colors"
            ),
            tablature = """
B|5--|
G|5--| (5th interval double stop)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_38",
            name = "Arpeggio Sweep Picking 5-String",
            description = "Extended sweep picking across 5 strings",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Extend sweep picking technique to 5 strings",
                "Use continuous down-stroke going down, up-stroke coming up",
                "Practice major and minor arpeggio shapes",
                "Keep pick angle consistent throughout the sweep",
                "Mute behind the pick to prevent string noise",
                "Start very slow and build speed gradually",
                "Famous technique in neo-classical shred guitar",
                "Yngwie Malmsteen popularized this technique"
            ),
            tablature = """
e|----12--|
B|--13----|
G|14------|
D|----14--|
A|--12----|
   ∏∏∏∏∏ (sweep down)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_39",
            name = "Thumb Muting Technique",
            description = "Use thumb to mute bass strings while playing",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Wrap your thumb over the top of the neck",
                "Use thumb to mute the low E (and optionally A) string",
                "Allows for aggressive strumming without unwanted bass notes",
                "Essential for modern rock and metal rhythm",
                "Also useful for certain chord voicings",
                "Jimi Hendrix used thumb fretting extensively",
                "Provides extra muting control for tight rhythms"
            )
        ),
        Exercise(
            id = "tech_40",
            name = "Natural Harmonics",
            description = "Ring clear harmonic tones at specific fret positions",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = false,
            instructions = listOf(
                "Lightly touch the string directly over the fret wire",
                "Don't press down, just touch lightly",
                "Pick the string and immediately release your finger",
                "Strong harmonics at frets 12, 7, 5, and 4",
                "Creates bell-like, chime tones",
                "Used for tuning and special effects",
                "Try playing melodies using only harmonics"
            ),
            tablature = """
e|<12>-<7>-<5>-| (harmonics)
            """.trimIndent()
        ),
        Exercise(
            id = "tech_41",
            name = "Accent Pattern Exercise",
            description = "Practice accenting specific notes in patterns",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Play a simple pattern (e.g., 1-2-3-4 on one string)",
                "Accent every 3rd note: LOUD-soft-soft-LOUD-soft-soft",
                "The accents create a rhythmic shift",
                "Builds dynamic control and pick attack variation",
                "Try different accent patterns: every 2nd, 4th, 5th note",
                "Essential for musical phrasing and expression",
                "Makes scale practice sound less mechanical"
            )
        ),
        Exercise(
            id = "tech_42",
            name = "Ascending and Descending 6s",
            description = "Groups of six notes for speed and coordination",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Play patterns in groups of 6 notes",
                "Example: 1-2-3-4-5-6, 2-3-4-5-6-7, 3-4-5-6-7-8",
                "Creates a rolling, flowing pattern",
                "Works well with triplets in 4/4 time",
                "Practice with alternate picking throughout",
                "Excellent for developing smooth runs",
                "Common in rock and metal guitar solos"
            ),
            tablature = """
e|1-2-3-4-5-6-2-3-4-5-6-7-3-4-5-6-7-8-|
            """.trimIndent()
        ),
        Exercise(
            id = "tech_43",
            name = "Finger Independence Builder",
            description = "Isolate and strengthen individual finger control",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Hold down fingers 1, 2, and 3 on frets 1, 2, 3",
                "Lift and hammer finger 4 on fret 4 repeatedly",
                "Keep other fingers pressed down the entire time",
                "Repeat for each finger combination",
                "Builds independence and strength in weak fingers",
                "Essential for complex chord voicings",
                "Practice slowly and with control"
            )
        ),
        Exercise(
            id = "tech_44",
            name = "Speed Bursts",
            description = "Short bursts of maximum speed for building technique",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Play a simple pattern at moderate tempo",
                "For one measure, play as fast as possible",
                "Return to moderate tempo",
                "Repeat: moderate-fast-moderate-fast",
                "Builds maximum speed in short controlled bursts",
                "Prevents sloppy playing from trying to play fast too long",
                "Gradually increase burst duration as stamina improves"
            )
        ),
        Exercise(
            id = "tech_45",
            name = "Cross-Picking Exercise",
            description = "Alternate picking across non-adjacent strings",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Use strict alternate picking while skipping strings",
                "Example: Low E-G-Low E-G (skipping the A and D strings)",
                "Requires precise pick control and string navigation",
                "Common in bluegrass and country guitar styles",
                "Essential for playing rolling arpeggios",
                "Practice slowly to build accuracy first",
                "Tony Rice and Doc Watson are masters of this technique"
            ),
            tablature = """
G|----7----|----7----|
E|--5----|--5------|
(Cross-picking pattern)
            """.trimIndent()
        ),
        // Additional exercises for comprehensive guitar training
        Exercise(
            id = "tech_46",
            name = "Major Scale - All 7 Positions",
            description = "Master all 7 positions of the major scale across the fretboard",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Learn all 7 CAGED positions for major scales",
                "Start with C major across all positions",
                "Position 1: E shape, Position 2: D shape, etc.",
                "Practice ascending and descending each position",
                "Connect positions by sliding or shifting",
                "Practice in all 12 keys",
                "Essential for understanding the entire fretboard"
            )
        ),
        Exercise(
            id = "tech_47",
            name = "Minor Pentatonic - All 5 Positions",
            description = "Complete pentatonic scale system across the neck",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Master all 5 positions of minor pentatonic",
                "Start with A minor pentatonic",
                "Position 1 at 5th fret, Position 2 at 8th fret, etc.",
                "Practice horizontal (across strings) and vertical (along neck)",
                "Learn to connect all positions seamlessly",
                "Foundation for rock, blues, and metal soloing",
                "Practice in multiple keys"
            )
        ),
        Exercise(
            id = "tech_48",
            name = "Fingerpicking Patterns",
            description = "Classic fingerstyle patterns for accompaniment",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Use thumb (T) for bass notes, fingers (i,m,a) for treble",
                "Pattern 1: T-i-m-a-m-i (classic Travis picking)",
                "Pattern 2: T-i-T-m-T-a (alternating bass)",
                "Keep steady tempo with thumb on beats",
                "Practice with simple chords: C, Am, F, G",
                "Essential for folk, country, and singer-songwriter styles",
                "Build independence between thumb and fingers"
            )
        ),
        Exercise(
            id = "tech_49",
            name = "Barre Chord Transitions",
            description = "Smooth transitions between different barre chord shapes",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Practice E-shape and A-shape barre chords",
                "Transition between F (E-shape) and Bb (A-shape)",
                "Keep barre finger pressure consistent",
                "Minimize hand movement between changes",
                "Practice major, minor, and 7th variations",
                "Essential for rock and pop rhythm guitar",
                "Build hand strength and endurance"
            )
        ),
        Exercise(
            id = "tech_50",
            name = "Rhythmic Strumming Patterns",
            description = "Various strumming patterns for different styles",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 100,
            instructions = listOf(
                "Down-down-up-up-down-up (pop/rock)",
                "Down-up-down-up with accents on 2 and 4 (funk)",
                "All downstrokes (punk rock)",
                "Muted strums for percussive effect (reggae)",
                "Practice with chord progressions",
                "Use metronome for steady timing",
                "Vary dynamics for musical expression"
            )
        ),
        Exercise(
            id = "tech_51",
            name = "Pinch Harmonics",
            description = "Squealing harmonic technique for rock and metal",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Strike string with pick, immediately touch with thumb",
                "Creates high-pitched squeal or scream",
                "Find harmonic nodes by moving hand position",
                "Use gain/distortion for easier execution",
                "Common at 12th, 7th, and 5th frets above picked note",
                "Zakk Wylde and Dimebag Darrell signature technique",
                "Practice on power chords and single notes"
            )
        ),
        Exercise(
            id = "tech_52",
            name = "Chicken Picking",
            description = "Country-style hybrid picking technique",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Combine pick and fingers for fast, snappy attack",
                "Pick bass notes, pluck treble strings with fingers",
                "Add percussive 'snap' by pulling strings",
                "Essential for country and bluegrass lead",
                "Practice on major pentatonic and country licks",
                "Study Brad Paisley, Brent Mason, Albert Lee",
                "Mute aggressively for staccato effect"
            )
        ),
        Exercise(
            id = "tech_53",
            name = "Whammy Bar Techniques",
            description = "Tremolo arm dips, dives, and flutter effects",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Gentle dips for subtle vibrato effect",
                "Dive bombs: press bar all the way down",
                "Scoops: start flat, bend up to pitch",
                "Flutter: rapid small movements for warble",
                "Requires floating tremolo (Floyd Rose, Bigsby, etc.)",
                "Famous users: Eddie Van Halen, Steve Vai, Jeff Beck",
                "Return to pitch carefully for intonation"
            )
        ),
        Exercise(
            id = "tech_54",
            name = "Volume Swells",
            description = "Violin-like swells using volume knob",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Turn volume to zero, pick note, swell volume up",
                "Creates smooth, violin-like attack",
                "Use with delay/reverb for ambient sounds",
                "Practice on chords and single notes",
                "Timing is key: swell in rhythm",
                "Allan Holdsworth and Joe Satriani technique",
                "Great for atmospheric passages"
            )
        ),
        Exercise(
            id = "tech_55",
            name = "String Bending Variations",
            description = "Different types of string bends for expression",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Unison bends: bend one string to match adjacent string",
                "Oblique bends: hold one note, bend another",
                "Double-stop bends: bend two strings simultaneously",
                "Quarter-tone bends for bluesy effect",
                "Pre-bend and release",
                "Bend behind the nut for unique sounds",
                "Essential blues and rock technique"
            )
        ),
        Exercise(
            id = "tech_56",
            name = "Palm-Muted Gallop Rhythm",
            description = "Metal galloping rhythm pattern",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 140,
            instructions = listOf(
                "Palm mute, play: eighth, two sixteenths, eighth",
                "Pattern: DOWN-down-up-DOWN (gallop rhythm)",
                "Famous in Iron Maiden songs",
                "Use all downstrokes for heavier sound",
                "Practice on low E string (power chord root)",
                "Keep palm mute consistent",
                "Increase speed gradually"
            )
        ),
        Exercise(
            id = "tech_57",
            name = "Travis Picking Pattern",
            description = "Classic fingerstyle alternating bass pattern",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Thumb alternates bass notes on beats 1 and 3",
                "Fingers pluck melody on beats 2 and 4",
                "Creates self-accompanying style",
                "Named after Merle Travis",
                "Essential for country and folk guitar",
                "Practice with C, Am, F, G progression",
                "Thumb independence is key"
            )
        ),
        Exercise(
            id = "tech_58",
            name = "Slap and Pop Bass Technique",
            description = "Percussive slap bass technique adapted for guitar",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Thumb slaps the string against fretboard",
                "Fingers pop strings by pulling up and releasing",
                "Creates percussive, funky sound",
                "Common in funk and modern fingerstyle",
                "Requires precise muting technique",
                "Study players like Victor Wooten (on bass)",
                "Adapt technique for guitar applications"
            )
        ),
        Exercise(
            id = "tech_59",
            name = "Pedal Point Exercises",
            description = "Constant bass note with changing upper voices",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Keep one note constant (usually bass)",
                "Change other notes around it",
                "Common in drone-based music",
                "Example: Open E string while moving patterns above",
                "Creates modal, Eastern-influenced sounds",
                "Used in rock, metal, and folk",
                "Explore different pedal tones"
            )
        ),
        Exercise(
            id = "tech_60",
            name = "Flamenco Rasgueado",
            description = "Spanish flamenco strumming technique",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 120,
            instructions = listOf(
                "Fan out fingers in rapid succession",
                "Pinky, ring, middle, index, thumb",
                "Creates machine-gun strumming effect",
                "Keep hand loose and relaxed",
                "Traditional Spanish flamenco technique",
                "Practice slowly, build speed gradually",
                "Essential for flamenco and Latin guitar"
            )
        )
    )
    
    private val creativityExercises = listOf(
        Exercise(
            id = "creative_1",
            name = "Improvisation in Pentatonic",
            description = "Free improvisation using pentatonic scale patterns",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
        ),
        Exercise(
            id = "creative_9",
            name = "Blues Phrasing Practice",
            description = "Develop authentic blues phrasing and feel",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Use the blues scale with emphasis on bends and vibrato",
                "Practice call-and-response: play a phrase, answer it",
                "Leave space between phrases - silence is powerful",
                "Bend up to the major 3rd from the minor 3rd",
                "Use string bends, slides, and vibrato liberally",
                "Listen to BB King, Stevie Ray Vaughan for inspiration",
                "Focus on emotion and feel, not just notes"
            )
        ),
        Exercise(
            id = "creative_10",
            name = "Chord Melody Arrangement",
            description = "Arrange melodies with accompanying chords",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 15,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Choose a simple melody (e.g., Happy Birthday, Amazing Grace)",
                "Harmonize the melody notes with chord tones underneath",
                "Play melody on top strings, chords on lower strings",
                "Common in jazz guitar and fingerstyle arrangements",
                "Requires knowledge of chord voicings and inversions",
                "Start with familiar tunes in easy keys",
                "Study Wes Montgomery and Joe Pass for examples"
            )
        ),
        Exercise(
            id = "creative_11",
            name = "Alternate Tuning Exploration",
            description = "Experiment with open and alternate tunings",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Try Drop D tuning: tune low E down to D",
                "Explore DADGAD tuning popular in Celtic music",
                "Open G (DGDGBD) for slide guitar and blues",
                "Open D (DADF#AD) for bottleneck and fingerstyle",
                "Discover new chord voicings impossible in standard tuning",
                "Used by Joni Mitchell, Nick Drake, Jimmy Page",
                "Write down interesting patterns you discover"
            )
        ),
        Exercise(
            id = "creative_12",
            name = "Dynamics and Expression Practice",
            description = "Control volume and intensity for musical expression",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Play a simple melody very quietly (pianissimo)",
                "Gradually increase to very loud (fortissimo)",
                "Practice sudden dynamic shifts",
                "Use pick attack, right hand position, and picking strength",
                "Combine with vibrato and bends for maximum expression",
                "Essential for emotional, musical performance",
                "Record yourself to hear the difference dynamics make"
            )
        ),
        Exercise(
            id = "creative_13",
            name = "Target Tone Improvisation",
            description = "Improvise while targeting specific chord tones",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Choose a chord progression (e.g., C-Am-F-G)",
                "Land on the root note of each chord as it changes",
                "Then target the 3rd, then the 5th of each chord",
                "Fill the space between targets with passing tones",
                "Creates melodic coherence over chord changes",
                "Essential jazz and fusion technique",
                "Builds awareness of chord-scale relationships"
            )
        ),
        Exercise(
            id = "creative_14",
            name = "Octave Playing",
            description = "Play melodies using octaves for fuller sound",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Play the same note on two strings an octave apart",
                "Common shape: index on low string, pinky on high string",
                "Mute the string in between with index finger",
                "Creates a thick, powerful melody sound",
                "Wes Montgomery made this technique famous",
                "Practice scales and melodies in octaves",
                "Try different octave shapes across the neck"
            ),
            tablature = """
e|--------5--|
D|--5--------|
(Mute A string between)
            """.trimIndent()
        ),
        Exercise(
            id = "creative_15",
            name = "Motivic Development",
            description = "Take a short musical idea and develop it",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Create a 2-3 note motif (musical idea)",
                "Repeat it at different pitches (sequence)",
                "Play it backwards (retrograde)",
                "Invert the intervals (melodic inversion)",
                "Change the rhythm but keep the pitches",
                "This is how composers develop themes",
                "Creates cohesive, memorable solos"
            )
        ),
        Exercise(
            id = "creative_16",
            name = "Call and Response Practice",
            description = "Develop conversational phrasing in solos",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Play a 2-bar phrase (the 'call')",
                "Answer with a different 2-bar phrase (the 'response')",
                "Make response relate to the call melodically or rhythmically",
                "Think of it as a musical conversation",
                "Common in blues and jazz improvisation",
                "Vary the dynamics between call and response",
                "Practice over a 12-bar blues or simple vamp"
            )
        ),
        Exercise(
            id = "creative_17",
            name = "Chord Substitution Exploration",
            description = "Experiment with replacing chords in progressions",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Start with basic progression (e.g., C-Am-F-G)",
                "Try substitute chords: Cmaj7, Am7, Fmaj7, G7",
                "Use diminished chords as passing chords",
                "Try tritone substitutions (Db7 for G7)",
                "Experiment with modal interchange",
                "Common in jazz reharmonization",
                "Creates fresh takes on familiar songs"
            )
        ),
        Exercise(
            id = "creative_18",
            name = "Looping and Layering",
            description = "Create layered compositions using loop techniques",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Start with a simple bass line or rhythm",
                "Layer a chord progression on top",
                "Add a melody or lead line",
                "Experiment with different textures",
                "Common with loop pedals (Boss RC-series)",
                "Ed Sheeran and KT Tunstall use this live",
                "Builds understanding of song arrangement"
            )
        ),
        Exercise(
            id = "creative_19",
            name = "Melodic Minor Scale Exploration",
            description = "Explore the jazz melodic minor scale and its modes",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Melodic minor: natural minor with raised 6th and 7th",
                "Creates unique jazz sound over minor chords",
                "Practice in one key, then apply to improvisation",
                "Explore modes: Dorian b2, Lydian augmented, Lydian dominant",
                "Used extensively in modern jazz",
                "Different from natural and harmonic minor",
                "Adds sophisticated color to solos"
            )
        ),
        Exercise(
            id = "creative_20",
            name = "Tempo Variation Practice",
            description = "Play the same idea at different tempos for different feels",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Choose a simple melodic phrase or lick",
                "Play it very slowly (ballad tempo, 60 BPM)",
                "Play it medium tempo (100 BPM)",
                "Play it fast (140+ BPM)",
                "Notice how tempo changes the feel and expression",
                "Slow = more emotional, fast = more energetic",
                "Essential for versatile musical interpretation"
            )
        )
    )
    
    private val songExercises = listOf(
        Exercise(
            id = "song_1",
            name = "Song Section Practice",
            description = "Work on a specific section of your current song",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
            instrument = InstrumentType.GUITAR,
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
        ),
        Exercise(
            id = "song_5",
            name = "Riff Repetition Drill",
            description = "Master iconic guitar riffs through repetition",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 100,
            instructions = listOf(
                "Choose a famous riff (e.g., Smoke on the Water, Seven Nation Army)",
                "Learn it note-for-note from tab or by ear",
                "Practice slowly until perfectly memorized",
                "Play it 10 times in a row without mistakes",
                "Gradually increase tempo to match original",
                "Focus on tone, timing, and articulation",
                "Great way to build repertoire and technique simultaneously"
            )
        ),
        Exercise(
            id = "song_6",
            name = "Solo Transcription",
            description = "Transcribe guitar solos to improve ear training",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 20,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Choose a short solo (8-16 bars)",
                "Listen repeatedly to internalize the phrases",
                "Figure out notes by ear, write them down",
                "Pay attention to bends, vibrato, and articulation",
                "Learn and play what you transcribed",
                "Analyze the solo: what scales, patterns, techniques?",
                "This deeply ingrains musical vocabulary",
                "Start with simpler solos and work up to complex ones"
            )
        ),
        Exercise(
            id = "song_7",
            name = "Rhythm Part Locking",
            description = "Practice locking in rhythm parts with recordings",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = false,
            instructions = listOf(
                "Choose a song with a clear rhythm guitar part",
                "Learn the chord progression and rhythm pattern",
                "Play along with the original recording",
                "Focus on staying perfectly in time with the track",
                "Match the dynamics and feel of the original",
                "Essential for playing in a band setting",
                "Develops your sense of groove and timing"
            )
        ),
        Exercise(
            id = "song_8",
            name = "Arrangement Practice",
            description = "Arrange songs for solo guitar performance",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 15,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Take a song normally played by multiple instruments",
                "Figure out how to play bass, chords, and melody together",
                "Simplify parts to make them playable on one guitar",
                "Use fingerstyle or hybrid picking techniques",
                "Create a self-contained solo guitar version",
                "Common in acoustic and classical guitar styles",
                "Study Chet Atkins and Tommy Emmanuel for examples"
            )
        ),
        Exercise(
            id = "song_9",
            name = "Genre Study",
            description = "Study characteristic techniques of a specific genre",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Pick a genre: blues, jazz, rock, country, metal, etc.",
                "Learn 2-3 classic songs from that genre",
                "Identify common techniques and patterns",
                "Practice the signature licks and phrases",
                "Study the typical chord progressions",
                "Understand the tone and equipment used",
                "Broadens your musical vocabulary and versatility"
            )
        ),
        Exercise(
            id = "song_10",
            name = "Performance Simulation",
            description = "Practice performing songs under pressure",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Set up a mock performance scenario",
                "Play your song from start to finish, no stopping",
                "Record video or audio of your performance",
                "Simulate performance nerves and pressure",
                "Review the recording critically",
                "Note sections that need more work",
                "Builds confidence and stage readiness",
                "Essential preparation for actual performances"
            )
        ),
        Exercise(
            id = "song_11",
            name = "Backing Track Practice",
            description = "Practice with professional backing tracks",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Find backing tracks on YouTube or apps",
                "Practice chord progressions in different keys",
                "Improvise solos over the changes",
                "Work on timing and playing with others",
                "Try different genres: blues, jazz, rock, funk",
                "Excellent for developing ensemble skills",
                "More fun than practicing alone"
            )
        ),
        Exercise(
            id = "song_12",
            name = "Ear Training - Chord Recognition",
            description = "Train your ear to recognize chord progressions",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Listen to a song and identify the chords by ear",
                "Start with simple 3-chord songs",
                "Identify if chords are major, minor, or 7th",
                "Write down the progression",
                "Check your answer with tabs or chord charts",
                "Essential skill for playing by ear",
                "Gradually work up to more complex songs"
            )
        ),
        Exercise(
            id = "song_13",
            name = "Playing to a Click Track",
            description = "Develop precise timing with metronome",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 100,
            instructions = listOf(
                "Set metronome to song tempo",
                "Play along staying exactly on the beat",
                "Don't rush or drag behind",
                "Practice difficult sections with click",
                "Essential for recording and live performance",
                "Develops internal sense of time",
                "Start slow, increase tempo gradually"
            )
        ),
        Exercise(
            id = "song_14",
            name = "12-Bar Blues Variations",
            description = "Learn and practice different 12-bar blues progressions",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Basic 12-bar: I-I-I-I-IV-IV-I-I-V-IV-I-V",
                "Quick change: IV in bar 2",
                "Practice in different keys (A, E, G)",
                "Add 9th and 13th chord extensions",
                "Learn turnarounds for the last 2 bars",
                "Foundation of blues, rock, and jazz",
                "Thousands of songs use this progression"
            )
        ),
        Exercise(
            id = "song_15",
            name = "Dynamics and Articulation Study",
            description = "Practice playing with varied dynamics in songs",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Play verse quietly, chorus loudly",
                "Use dynamics to create contrast",
                "Practice crescendos (getting louder) and diminuendos",
                "Staccato (short) vs legato (smooth) articulation",
                "Essential for expressive performance",
                "Marks the difference between amateur and pro",
                "Record yourself to hear the difference"
            )
        )
    )
    
    // Piano Exercises
    private val pianoTechniqueExercises = listOf(
        Exercise(
            id = "piano_tech_1",
            name = "C Major Scale - Hands Separate",
            description = "Practice C major scale with proper fingering, hands separately",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Right hand: C(1) D(2) E(3) F(1) G(2) A(3) B(4) C(5)",
                "Thumb under after E, cross over middle finger on F",
                "Left hand: C(5) D(4) E(3) F(2) G(1) A(3) B(2) C(1)",
                "Cross over middle finger after G in left hand",
                "Practice each hand separately first",
                "Focus on even tone and smooth transitions",
                "Gradually increase tempo by 5 BPM when comfortable"
            )
        ),
        Exercise(
            id = "piano_tech_2",
            name = "Hanon Exercise #1",
            description = "Classic Hanon finger independence exercise",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Play the ascending pattern: 12345 13245 14235 15234",
                "Each finger gets a workout in different positions",
                "Keep fingers curved and wrists level",
                "Play with even volume across all fingers",
                "Start slowly, focus on accuracy and finger independence",
                "Use a metronome to maintain steady tempo",
                "Practice hands together once each hand is comfortable"
            )
        ),
        Exercise(
            id = "piano_tech_3",
            name = "Broken Chord Arpeggios",
            description = "Practice broken chord patterns for hand coordination",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 72,
            instructions = listOf(
                "Start with C major arpeggio: C-E-G-C (ascending)",
                "Right hand fingering: 1-2-3-5",
                "Left hand fingering: 5-3-2-1",
                "Practice in all keys, moving through the circle of fifths",
                "Keep wrist relaxed and fluid motion",
                "Add minor arpeggios once major is comfortable",
                "Focus on even timing and smooth transitions"
            )
        ),
        Exercise(
            id = "piano_tech_4",
            name = "Contrary Motion Scales",
            description = "Play scales with both hands moving in opposite directions",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Start with both thumbs on middle C",
                "Both hands play outward: RH ascending, LH descending",
                "Mirror image fingering creates symmetry",
                "Excellent for coordination and hand independence",
                "Practice in C major first, then other keys",
                "Keep both hands perfectly synchronized",
                "Focus on matching dynamics between hands"
            )
        ),
        Exercise(
            id = "piano_tech_5",
            name = "Trills and Ornaments",
            description = "Develop speed and control with trills between adjacent notes",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Trill between two adjacent notes (e.g., C-D-C-D rapidly)",
                "Use fingers 2-3 or 1-2 for most trills",
                "Keep motion in fingers only, not from wrist or arm",
                "Start slowly and build up speed gradually",
                "Maintain even volume throughout the trill",
                "Practice trills on different note pairs",
                "Add mordents and turns for variety"
            )
        ),
        Exercise(
            id = "piano_tech_6",
            name = "Octave Practice",
            description = "Build strength and accuracy playing octaves",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Play octaves using fingers 1 and 5 (thumb and pinky)",
                "Keep hand in a comfortable arch, not stretched flat",
                "Practice ascending and descending octave scales",
                "Add chromatic octave passages",
                "Use arm weight, not just finger strength",
                "Keep wrist flexible and loose",
                "Essential technique for romantic and virtuosic repertoire"
            )
        ),
        Exercise(
            id = "piano_tech_7",
            name = "Alberti Bass Pattern",
            description = "Practice the classic broken chord accompaniment pattern",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 100,
            instructions = listOf(
                "Alberti bass: broken chord pattern (low-high-middle-high)",
                "In C major: C-G-E-G, C-G-E-G repeating",
                "Left hand fingering typically: 5-1-3-1",
                "Keep a steady, flowing rhythm",
                "Common in Classical period music (Mozart, Haydn)",
                "Practice in all major and minor keys",
                "Combine with simple melody in right hand"
            )
        ),
        Exercise(
            id = "piano_tech_8",
            name = "Chromatic Scales",
            description = "Master chromatic scales with proper fingering",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Chromatic scale uses all 12 notes",
                "Right hand: 1-3-1-3-1-2-3-1-3-1-3-1-2 pattern",
                "Left hand: mirror fingering descending",
                "Practice hands separately first",
                "Essential for modern and jazz piano",
                "Builds finger independence and dexterity",
                "Practice slowly for accuracy before speed"
            )
        ),
        Exercise(
            id = "piano_tech_9",
            name = "Scales in Thirds",
            description = "Play scales with two notes at a time, a third apart",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Play C major scale in thirds: C&E, D&F, E&G, F&A, etc.",
                "Both notes played simultaneously",
                "Develops hand strength and coordination",
                "Common in romantic piano music",
                "Practice in all keys",
                "Try sixths and tenths for more advanced work",
                "Builds facility for double-note passages"
            )
        ),
        Exercise(
            id = "piano_tech_10",
            name = "Staccato Touch Exercise",
            description = "Develop crisp staccato articulation",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Staccato means short, detached notes",
                "Play scales or simple melodies staccato",
                "Release the key quickly after striking",
                "Use wrist motion for crisp articulation",
                "Each note should be distinct and separated",
                "Contrast with legato (smooth) playing",
                "Essential for baroque and classical repertoire"
            )
        ),
        Exercise(
            id = "piano_tech_11",
            name = "Chord Inversions Practice",
            description = "Master all inversions of major and minor triads",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "C major triad: C-E-G (root position)",
                "First inversion: E-G-C",
                "Second inversion: G-C-E",
                "Practice all inversions in all 12 keys",
                "Helps with smooth voice leading in progressions",
                "Essential for arranging and improvisation",
                "Memorize the sound of each inversion"
            )
        ),
        Exercise(
            id = "piano_tech_12",
            name = "Hanon Exercise #2",
            description = "Second exercise from the Hanon series for finger strength",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Similar to Hanon #1 but different pattern",
                "Focuses on different finger combinations",
                "Practice hands together with even rhythm",
                "Keep fingers curved, wrists level",
                "Gradually increase tempo as you gain control",
                "Essential daily warm-up exercise",
                "Builds independence in weaker fingers (4 and 5)"
            )
        ),
        Exercise(
            id = "piano_tech_13",
            name = "Thumb-Under Technique",
            description = "Practice smooth thumb-under motion for scales",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Critical for playing smooth scales",
                "After finger 3, thumb passes under to continue scale",
                "Keep thumb relaxed, don't stick it out",
                "Practice the transition: 3-1 slowly",
                "Wrist should rotate slightly to accommodate thumb",
                "Isolate and repeat the difficult transition",
                "Essential foundation for scale playing"
            )
        ),
        Exercise(
            id = "piano_tech_14",
            name = "Hand Independence Exercises",
            description = "Develop independent control of left and right hands",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Right hand plays quarter notes, left plays half notes",
                "Reverse: right plays half, left plays quarters",
                "Try different rhythmic combinations",
                "One hand staccato, other legato simultaneously",
                "Essential for polyphonic music (Bach, etc.)",
                "Challenges your brain's coordination",
                "Start slowly and build up speed"
            )
        ),
        Exercise(
            id = "piano_tech_15",
            name = "Finger Substitution",
            description = "Practice changing fingers on a held note",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Hold a note with one finger (e.g., finger 3)",
                "Without releasing, replace with another finger (e.g., 4)",
                "The note should continue ringing without interruption",
                "Essential for legato playing and voice leading",
                "Used in contrapuntal music and sustained passages",
                "Practice all finger combinations",
                "Common in Bach and romantic piano music"
            )
        ),
        Exercise(
            id = "piano_tech_16",
            name = "Wrist Rotation Exercise",
            description = "Develop fluid wrist rotation for repeated notes",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 100,
            instructions = listOf(
                "Play repeated notes using wrist rotation",
                "Rotate wrist left-right rather than hammering",
                "Essential for fast repeated note passages",
                "Prevents fatigue and builds speed",
                "Practice on all notes with all fingers",
                "Common in virtuoso pieces (Liszt, Chopin)",
                "Relaxed wrist is key to this technique"
            )
        ),
        Exercise(
            id = "piano_tech_17",
            name = "Pedal Technique Practice",
            description = "Master the sustain pedal for legato playing",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Practice syncopated pedaling: press after playing the note",
                "Release and re-pedal as harmony changes",
                "Avoid 'muddy' sound from over-pedaling",
                "Listen carefully to the sustain and blend",
                "Essential for romantic and impressionist music",
                "Practice scales with pedal for smooth legato",
                "Combine with chord progressions"
            )
        ),
        Exercise(
            id = "piano_tech_18",
            name = "Blocked Chords to Arpeggios",
            description = "Convert blocked chords into flowing arpeggios",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Start with a blocked chord (all notes together)",
                "Play the same notes as an arpeggio (one at a time)",
                "Practice ascending and descending patterns",
                "Vary the rhythmic pattern of the arpeggio",
                "Helps transition between chordal and linear playing",
                "Common in classical and romantic piano music",
                "Develops facility with chord tones"
            )
        ),
        Exercise(
            id = "piano_tech_19",
            name = "Hanon Exercise #3",
            description = "Third Hanon exercise for finger dexterity",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Continue the Hanon series with Exercise #3",
                "Different pattern from #1 and #2",
                "Focus on even rhythm and finger independence",
                "Keep fingers curved, wrists level",
                "Practice hands together after mastering separately",
                "Excellent daily warm-up routine",
                "Build gradually to faster tempos"
            )
        ),
        Exercise(
            id = "piano_tech_20",
            name = "Czerny Op. 599 - School of Velocity",
            description = "Practice exercises from Czerny's beginner studies",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Czerny exercises build technique and velocity",
                "Start with Exercise 1 from Op. 599",
                "Focus on evenness and clarity",
                "Public domain exercises, widely available",
                "Essential classical piano pedagogy",
                "Progress through the series gradually",
                "Complement to Hanon exercises"
            )
        ),
        Exercise(
            id = "piano_tech_21",
            name = "All Major Scales",
            description = "Practice major scales in all 12 keys",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Practice all 12 major scales",
                "Start with C, G, D, A, E (sharps)",
                "Then F, Bb, Eb, Ab, Db (flats)",
                "Use correct fingering for each scale",
                "Practice 2 octaves ascending and descending",
                "Hands together when comfortable",
                "Essential foundation for piano playing"
            )
        ),
        Exercise(
            id = "piano_tech_22",
            name = "All Minor Scales",
            description = "Practice natural, harmonic, and melodic minor scales",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Practice all three forms of minor scales",
                "Natural minor: no alterations",
                "Harmonic minor: raised 7th",
                "Melodic minor: raised 6th and 7th ascending",
                "Practice in all 12 keys",
                "Learn the different characters of each form",
                "Essential for classical and jazz piano"
            )
        ),
        Exercise(
            id = "piano_tech_23",
            name = "Thumb Crossing in Scales",
            description = "Perfect the thumb-under technique in scale playing",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 8,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Focus specifically on thumb passing under fingers",
                "Practice C major scale very slowly",
                "After finger 3, prepare thumb early",
                "Rotate wrist slightly to facilitate thumb movement",
                "Keep thumb relaxed, not sticking out",
                "Critical technique for smooth scale playing",
                "Practice with each hand separately"
            )
        ),
        Exercise(
            id = "piano_tech_24",
            name = "Voicing 7th Chords",
            description = "Practice different voicings of 7th chords",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 8,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Major 7th, Dominant 7th, Minor 7th, Half-diminished",
                "Practice in all inversions",
                "Spread voicings across both hands",
                "Drop-2 and drop-3 voicings for jazz",
                "Essential for jazz piano comping",
                "Practice ii-V-I progressions with 7th chords",
                "Learn in all 12 keys"
            )
        ),
        Exercise(
            id = "piano_tech_25",
            name = "Left Hand Independence",
            description = "Develop independent left hand technique",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Practice left hand-only exercises",
                "Play bass lines from songs",
                "Practice stride bass patterns",
                "Work on left hand scales and arpeggios",
                "Left hand is often neglected",
                "Strong left hand crucial for solo piano",
                "Build equal strength in both hands"
            )
        ),
        Exercise(
            id = "piano_tech_26",
            name = "Two-Note Slurs",
            description = "Practice legato two-note slurs for expression",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 80,
            instructions = listOf(
                "Play first note slightly louder and longer",
                "Second note softer and shorter",
                "Creates natural phrasing gesture",
                "Common in classical music notation",
                "Practice on scales and simple melodies",
                "Essential for musical expression",
                "Develop sensitivity to touch"
            )
        ),
        Exercise(
            id = "piano_tech_27",
            name = "Finger Staccato vs Wrist Staccato",
            description = "Master different types of staccato articulation",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 100,
            instructions = listOf(
                "Finger staccato: quick finger release",
                "Wrist staccato: light wrist bounce",
                "Use finger staccato for softer, lighter passages",
                "Use wrist staccato for louder, more percussive",
                "Practice scales with both types",
                "Essential for varied articulation",
                "Common in classical and contemporary piano"
            )
        ),
        Exercise(
            id = "piano_tech_28",
            name = "Crossing Hands Exercise",
            description = "Practice coordination when hands cross over each other",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 5,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 70,
            instructions = listOf(
                "Practice simple patterns where hands cross",
                "Left hand plays in treble, right in bass",
                "Keep smooth, avoid collisions",
                "Common in romantic piano music",
                "Builds spatial awareness at keyboard",
                "Start slowly, build confidence",
                "Found in pieces by Chopin, Liszt"
            )
        ),
        Exercise(
            id = "piano_tech_29",
            name = "Rotation Technique",
            description = "Master forearm rotation for fluid playing",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 8,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Rotate forearm naturally with note patterns",
                "Essential for fast passages and jumps",
                "Prevents tension and injury",
                "Taubman technique advocates this approach",
                "Practice on scales and arpeggios",
                "Keep shoulder and upper arm relaxed",
                "Modern piano pedagogy emphasizes rotation"
            )
        ),
        Exercise(
            id = "piano_tech_30",
            name = "Quick Chord Changes",
            description = "Practice rapid transitions between chord voicings",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.PIANO,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Practice quick changes between different chords",
                "Start with simple progression: C-F-G-C",
                "Lift hands together, land together",
                "Prepare hand shape in the air",
                "Minimize unnecessary motion",
                "Essential for accompaniment and comping",
                "Gradually increase tempo"
            )
        )
    )
    
    private val pianoCreativityExercises = listOf(
        Exercise(
            id = "piano_creative_1",
            name = "Chord Progression Exploration",
            description = "Improvise melodies over common chord progressions",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Choose a common progression (e.g., I-IV-V-I in C major: C-F-G-C)",
                "Play the progression in the left hand",
                "Improvise melodies in the right hand using chord tones",
                "Add passing tones and neighbor tones for interest",
                "Experiment with different rhythmic patterns",
                "Try inversions to create smoother bass lines",
                "Record yourself to capture interesting ideas"
            )
        ),
        Exercise(
            id = "piano_creative_2",
            name = "Blues Scale Improvisation",
            description = "Explore blues scale patterns and improvisation",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Use C blues scale: C-Eb-F-F#-G-Bb-C",
                "Play a 12-bar blues progression in left hand",
                "Improvise using the blues scale in right hand",
                "Add bends (grace notes) and slides for expression",
                "Experiment with call and response phrases",
                "Use triplet rhythms and syncopation",
                "Listen to blues pianists for inspiration"
            )
        ),
        Exercise(
            id = "piano_creative_3",
            name = "Voicing Practice",
            description = "Experiment with different chord voicings and colors",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 8,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Take a simple chord (e.g., C major: C-E-G)",
                "Play it in root position, then first and second inversions",
                "Spread the notes across two hands",
                "Add extensions: 7ths, 9ths, 11ths, 13ths",
                "Experiment with open vs. closed voicings",
                "Try drop-2 and drop-3 voicings",
                "Listen to how each voicing changes the color and feel"
            )
        ),
        Exercise(
            id = "piano_creative_4",
            name = "Modal Exploration",
            description = "Explore different modes and their unique characteristics",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Choose a mode (e.g., D Dorian: D-E-F-G-A-B-C-D)",
                "Play the scale ascending and descending",
                "Improvise phrases emphasizing the characteristic notes",
                "For Dorian, emphasize the raised 6th degree",
                "Create vamps using modal chords",
                "Try different modes: Mixolydian, Phrygian, Lydian",
                "Compare the mood and color of each mode"
            )
        ),
        Exercise(
            id = "piano_creative_5",
            name = "Jazz Comping Patterns",
            description = "Practice jazz chord voicings and comping rhythms",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 120,
            instructions = listOf(
                "Use rootless voicings (3rd and 7th in left hand)",
                "Play shell voicings with rhythmic comping patterns",
                "Practice ii-V-I progression in all keys",
                "Add extensions (9ths, 11ths, 13ths) for color",
                "Comp behind an imaginary soloist",
                "Listen to Bill Evans, Oscar Peterson for style",
                "Essential for jazz piano accompaniment"
            )
        ),
        Exercise(
            id = "piano_creative_6",
            name = "Two-Hand Stride Pattern",
            description = "Learn classic stride piano left-hand patterns",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 100,
            instructions = listOf(
                "Stride: bass note on beats 1 and 3, chord on 2 and 4",
                "Left hand jumps between low and mid register",
                "Practice in C major: C bass, CEG chord, C bass, CEG chord",
                "Gradually increase tempo for authentic stride feel",
                "Add right hand melody once left hand is solid",
                "Classic ragtime and jazz piano style",
                "Study Fats Waller and James P. Johnson"
            )
        ),
        Exercise(
            id = "piano_creative_7",
            name = "Polyrhythm Practice",
            description = "Develop independence with polyrhythmic patterns",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 8,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 60,
            instructions = listOf(
                "Play 3 against 2: right hand triplets, left hand duplets",
                "Then try 4 against 3, or 5 against 4",
                "Count out loud to establish independence",
                "Start very slowly and build coordination",
                "Common in modern classical and jazz piano",
                "Challenges your rhythmic independence",
                "Found in music by Chopin, Brahms, and modern composers"
            )
        ),
        Exercise(
            id = "piano_creative_8",
            name = "Reharmonization Practice",
            description = "Experiment with alternate harmonies for melodies",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Take a simple melody (e.g., Mary Had a Little Lamb)",
                "Harmonize with unexpected chords",
                "Use substitute chords (tritone subs, secondary dominants)",
                "Try modal interchange (borrow from parallel minor)",
                "Add passing chords and chromatic movement",
                "Essential skill for jazz arranging",
                "Expands your harmonic vocabulary"
            )
        ),
        Exercise(
            id = "piano_creative_9",
            name = "Left Hand Independence",
            description = "Develop strong bass line improvisation",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 8,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 90,
            instructions = listOf(
                "Create walking bass lines in left hand",
                "Use chord tones and passing tones",
                "Keep steady quarter note pulse",
                "Add simple chords or melody in right hand",
                "Essential for solo piano and jazz styles",
                "Practice in different keys",
                "Study bass players for line ideas"
            )
        ),
        Exercise(
            id = "piano_creative_10",
            name = "Cluster Chords and Modern Harmony",
            description = "Explore contemporary piano sounds and techniques",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Play tone clusters: adjacent notes played together",
                "Use forearm or palm for wide clusters",
                "Experiment with quartal harmony (chords built on 4ths)",
                "Try playing inside the piano (prepared piano)",
                "Explore impressionist and contemporary sounds",
                "Common in 20th century classical music",
                "Study Debussy, Bartók, and Cowell"
            )
        ),
        Exercise(
            id = "piano_creative_11",
            name = "Gospel Piano Patterns",
            description = "Learn traditional gospel piano techniques",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 85,
            instructions = listOf(
                "Use major 7th and add9 chords extensively",
                "Practice typical gospel progressions",
                "Add chromaticism and passing chords",
                "Use rhythmic syncopation",
                "Learn characteristic runs and fills",
                "Essential for church musicians",
                "Study traditional gospel pianists"
            )
        ),
        Exercise(
            id = "piano_creative_12",
            name = "Ragtime Syncopation",
            description = "Master ragtime's characteristic syncopated rhythms",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 100,
            instructions = listOf(
                "Steady stride bass in left hand",
                "Syncopated melody in right hand",
                "Right hand plays off the beat",
                "Classic ragtime style (Scott Joplin)",
                "Practice 'The Entertainer' patterns",
                "Essential American piano tradition",
                "Builds independence and rhythmic precision"
            )
        ),
        Exercise(
            id = "piano_creative_13",
            name = "Impressionist Harmony",
            description = "Explore Debussy-style impressionist harmonies",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Use whole tone and pentatonic scales",
                "Experiment with parallel chord motion",
                "Add 9th, 11th, and 13th extensions",
                "Create wash of sound with pedal",
                "Avoid traditional functional harmony",
                "Study Debussy and Ravel",
                "Creates dreamy, atmospheric sound"
            )
        ),
        Exercise(
            id = "piano_creative_14",
            name = "Boogie-Woogie Bass Lines",
            description = "Master classic boogie-woogie left hand patterns",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = true,
            bpm = 120,
            instructions = listOf(
                "Classic pattern: octave, 5th, 6th, flatted 7th",
                "Keep steady eighth note pulse",
                "Practice in C, F, and G for blues",
                "Right hand improvises blues phrases",
                "Essential early jazz piano style",
                "Study Meade Lux Lewis, Albert Ammons",
                "Builds left hand strength and independence"
            )
        ),
        Exercise(
            id = "piano_creative_15",
            name = "Block Chord Melody",
            description = "Harmonize melodies with block chord style",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Play melody note on top of chord voicing",
                "Use close voicings, all notes in right hand",
                "Common in jazz piano (Red Garland, Wynton Kelly)",
                "Practice with simple melodies first",
                "Maintain smooth voice leading",
                "Creates thick, full sound",
                "Essential jazz piano technique"
            )
        )
    )
    
    private val pianoSongExercises = listOf(
        Exercise(
            id = "piano_song_1",
            name = "Difficult Passage Practice",
            description = "Work on challenging sections of your current piece",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Identify the most challenging 4-8 measures",
                "Practice hands separately first",
                "Slow down to 50% tempo, focus on accuracy",
                "Use rhythmic variations (dotted, reverse dotted)",
                "Practice with different articulations",
                "Gradually increase tempo by 5 BPM increments",
                "Once comfortable at full tempo, add back into full piece"
            )
        ),
        Exercise(
            id = "piano_song_2",
            name = "Piece Performance Run-Through",
            description = "Play through entire piece without stopping",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Choose a piece you know well",
                "Play from beginning to end without stopping",
                "Don't stop for mistakes - keep going",
                "Focus on musicality and expression",
                "Record yourself to evaluate performance",
                "Note sections that need more work",
                "Practice performing under pressure"
            )
        ),
        Exercise(
            id = "piano_song_3",
            name = "Memorization Practice",
            description = "Work on memorizing pieces without sheet music",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = false,
            instructions = listOf(
                "Choose a short section (4-8 measures)",
                "Analyze the harmony and structure",
                "Memorize one hand at a time",
                "Play from memory, checking against score as needed",
                "Visualize the score away from the piano",
                "Practice starting from different points in the piece",
                "Test your memory by playing in different tempos"
            )
        ),
        Exercise(
            id = "piano_song_4",
            name = "Sight Reading Practice",
            description = "Improve reading skills with new material",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 15,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = false,
            instructions = listOf(
                "Choose music slightly below your current level",
                "Scan the piece before playing: key, time signature, tempo",
                "Look for patterns and familiar elements",
                "Keep your eyes on the music, not your hands",
                "Don't stop for mistakes, keep steady tempo",
                "Practice reading hands separately if needed",
                "Read a little ahead of what you're playing"
            )
        ),
        Exercise(
            id = "piano_song_5",
            name = "Classical Piece Study",
            description = "Deep dive into a classical piano composition",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 20,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Choose a piece by Bach, Mozart, Beethoven, or Chopin",
                "Study the score: harmony, form, phrasing",
                "Learn hands separately at slow tempo",
                "Understand the musical structure and motifs",
                "Work on interpretation and dynamics",
                "Practice difficult sections with varied rhythms",
                "Essential for developing classical technique",
                "Builds deep musical understanding"
            )
        ),
        Exercise(
            id = "piano_song_6",
            name = "Pop/Rock Song Arrangement",
            description = "Arrange contemporary songs for piano",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Choose a favorite pop or rock song",
                "Learn the chord progression by ear or from charts",
                "Create a piano arrangement with melody and chords",
                "Add rhythmic patterns that capture the feel",
                "Experiment with different voicings",
                "Simplify or embellish based on your level",
                "Makes practice fun and relevant to modern music"
            )
        ),
        Exercise(
            id = "piano_song_7",
            name = "Jazz Standard Learning",
            description = "Learn and internalize jazz standards",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 20,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Choose a standard (e.g., Autumn Leaves, All the Things You Are)",
                "Learn the melody and chord changes",
                "Practice in multiple keys (at least 3)",
                "Create your own voicings and left-hand patterns",
                "Improvise over the changes",
                "Essential repertoire for jazz pianists",
                "Study recordings by great jazz pianists"
            )
        ),
        Exercise(
            id = "piano_song_8",
            name = "Hymn and Chorale Practice",
            description = "Practice four-part harmony and voice leading",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Choose a hymn or chorale (Bach chorales are excellent)",
                "Practice playing all four voices",
                "Focus on smooth voice leading between chords",
                "Listen to how each voice moves independently",
                "Excellent for understanding harmony",
                "Builds chord reading and voicing skills",
                "Essential for church pianists and accompanists"
            )
        ),
        Exercise(
            id = "piano_song_9",
            name = "Romantic Etude Practice",
            description = "Work on expressive romantic period pieces",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 15,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Choose an etude by Chopin, Liszt, or Rachmaninoff",
                "Focus on musical expression and dynamics",
                "Use rubato and pedal for romantic sound",
                "Practice technical passages slowly",
                "Build up to performance tempo gradually",
                "Record yourself to hear interpretation",
                "Essential for developing artistic playing"
            )
        ),
        Exercise(
            id = "piano_song_10",
            name = "Movie Theme Learning",
            description = "Learn recognizable film and TV themes",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Choose a movie theme (e.g., Star Wars, Harry Potter, Inception)",
                "Find sheet music or learn by ear",
                "Focus on capturing the emotional quality",
                "Practice the memorable melodies",
                "Add your own interpretation",
                "Great for building repertoire people recognize",
                "Makes practice enjoyable and rewarding"
            )
        ),
        Exercise(
            id = "piano_song_11",
            name = "Ear Training with Songs",
            description = "Develop ability to play songs by ear",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Choose a simple song you know well",
                "Find the key by trial and error",
                "Pick out the melody on piano",
                "Figure out the chord progression",
                "Add bass notes in left hand",
                "Essential skill for all musicians",
                "Start with children's songs, work up to pop songs"
            )
        ),
        Exercise(
            id = "piano_song_12",
            name = "Scale and Arpeggio in Context",
            description = "Apply technical exercises to actual pieces",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Identify scales and arpeggios within pieces",
                "Practice those sections as technical exercises",
                "Return them to the context of the piece",
                "Understand how technique applies to music",
                "Makes technical practice more meaningful",
                "Bridges gap between exercises and repertoire",
                "Essential pedagogical approach"
            )
        ),
        Exercise(
            id = "piano_song_13",
            name = "Accompaniment Practice",
            description = "Practice accompanying a singer or instrumentalist",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Learn accompaniment parts from sheet music",
                "Practice staying in background, supporting soloist",
                "Follow the melody, be flexible with tempo",
                "Use appropriate dynamics and articulation",
                "Essential skill for collaborative musicians",
                "Practice with recordings or other musicians",
                "Different from solo piano playing"
            )
        ),
        Exercise(
            id = "piano_song_14",
            name = "Baroque Piece Study",
            description = "Study Bach or other Baroque keyboard works",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 20,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = false,
            instructions = listOf(
                "Choose a Bach invention or prelude",
                "Analyze the counterpoint and voice leading",
                "Practice each voice separately",
                "Bring out different voices with dynamics",
                "Study ornamentation (trills, mordents)",
                "Essential for classical piano training",
                "Builds finger independence and musicality"
            )
        ),
        Exercise(
            id = "piano_song_15",
            name = "Contemporary Pop Arrangement",
            description = "Create piano versions of current pop songs",
            category = ExerciseCategory.SONGS,
            instrument = InstrumentType.PIANO,
            durationMinutes = 15,
            difficulty = DifficultyLevel.INTERMEDIATE,
            hasTiming = false,
            instructions = listOf(
                "Choose a current pop song you enjoy",
                "Learn the chord progression and melody",
                "Create a piano arrangement",
                "Add rhythmic patterns that capture the feel",
                "Simplify complex production to piano essence",
                "Makes practice relevant and fun",
                "Great for performers and social situations"
            )
        )
    )
    
    fun getAllExercises(): List<Exercise> {
        return techniqueExercises + creativityExercises + songExercises + 
               pianoTechniqueExercises + pianoCreativityExercises + pianoSongExercises
    }
    
    fun getExercisesByCategory(category: ExerciseCategory): List<Exercise> {
        return getAllExercises().filter { it.category == category }
    }
    
    fun getExercisesByInstrument(instrument: InstrumentType): List<Exercise> {
        return getAllExercises().filter { it.instrument == instrument }
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
        difficulty: DifficultyLevel? = null,
        instrument: InstrumentType? = null
    ): PracticeRoutine {
        val exercises = mutableListOf<Exercise>()
        var totalDuration = 0
        
        // Get all exercises, filter by instrument if specified
        val allExercises = if (instrument != null) {
            getAllExercises().filter { it.instrument == instrument }
        } else {
            getAllExercises()
        }
        
        // Filter exercises by difficulty if specified
        val availableTech = allExercises
            .filter { it.category == ExerciseCategory.TECHNIQUE }
            .let { if (difficulty != null) it.filter { ex -> ex.difficulty == difficulty } else it }
        
        val availableCreative = allExercises
            .filter { it.category == ExerciseCategory.CREATIVITY }
            .let { if (difficulty != null) it.filter { ex -> ex.difficulty == difficulty } else it }
        
        val availableSongs = allExercises
            .filter { it.category == ExerciseCategory.SONGS }
            .let { if (difficulty != null) it.filter { ex -> ex.difficulty == difficulty } else it }
        
        // Shuffle exercises for variety on each generation
        val techShuffled = availableTech.shuffled()
        val creativeShuffled = availableCreative.shuffled()
        val songsShuffled = availableSongs.shuffled()
        
        // Select exercises that fit within the target duration
        // Try to pick one from each category first
        val techExercise = techShuffled.firstOrNull { 
            totalDuration + it.durationMinutes <= targetDurationMinutes 
        }
        if (techExercise != null) {
            exercises.add(techExercise)
            totalDuration += techExercise.durationMinutes
        }
        
        val creativeExercise = creativeShuffled.firstOrNull { 
            totalDuration + it.durationMinutes <= targetDurationMinutes 
        }
        if (creativeExercise != null) {
            exercises.add(creativeExercise)
            totalDuration += creativeExercise.durationMinutes
        }
        
        val songExercise = songsShuffled.firstOrNull { 
            totalDuration + it.durationMinutes <= targetDurationMinutes 
        }
        if (songExercise != null) {
            exercises.add(songExercise)
            totalDuration += songExercise.durationMinutes
        }
        
        // Fill remaining time with exercises that fit, prioritizing variety
        val availableExercises = (availableTech + availableCreative + availableSongs)
            .filter { !exercises.contains(it) }
            .shuffled()
        
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
