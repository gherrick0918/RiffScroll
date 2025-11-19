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
        )
    )
    
    private val creativityExercises = listOf(
        Exercise(
            id = "creative_1",
            name = "Improvisation in Pentatonic",
            description = "Free improvisation using pentatonic scale patterns",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 10,
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
        )
    )
    
    private val songExercises = listOf(
        Exercise(
            id = "song_1",
            name = "Song Section Practice",
            description = "Work on a specific section of your current song",
            category = ExerciseCategory.SONGS,
            durationMinutes = 15,
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
    
    /**
     * Generate a balanced practice routine
     * Ensures variety across all three categories
     */
    fun generateBalancedRoutine(targetDurationMinutes: Int = 45): PracticeRoutine {
        val exercises = mutableListOf<Exercise>()
        var totalDuration = 0
        
        // Get exercises from each category sorted by duration
        val techByDuration = techniqueExercises.sortedBy { it.durationMinutes }
        val creativeByDuration = creativityExercises.sortedBy { it.durationMinutes }
        val songsByDuration = songExercises.sortedBy { it.durationMinutes }
        
        // Pick one from each category, preferring shorter exercises for short routines
        val techExercise = if (targetDurationMinutes <= 20) 
            techByDuration.first() else techByDuration.random()
        val creativeExercise = if (targetDurationMinutes <= 20)
            creativeByDuration.first() else creativeByDuration.random()
        val songExercise = if (targetDurationMinutes <= 20)
            songsByDuration.first() else songsByDuration.random()
        
        exercises.add(techExercise)
        exercises.add(creativeExercise)
        exercises.add(songExercise)
        totalDuration = techExercise.durationMinutes + 
                       creativeExercise.durationMinutes + 
                       songExercise.durationMinutes
        
        // Fill remaining time with random exercises, but stay within target
        val allExercises = getAllExercises().shuffled()
        for (exercise in allExercises) {
            if (!exercises.contains(exercise) &&
                totalDuration + exercise.durationMinutes <= targetDurationMinutes) {
                exercises.add(exercise)
                totalDuration += exercise.durationMinutes
            }
        }
        
        return PracticeRoutine(
            id = "routine_${System.currentTimeMillis()}",
            exercises = exercises,
            totalDurationMinutes = totalDuration,
            difficulty = Random.nextInt(1, 4)
        )
    }
}
