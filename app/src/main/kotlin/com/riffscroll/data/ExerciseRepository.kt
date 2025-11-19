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
                "Start on the low E string, 5th fret",
                "Play one finger per fret: 5-6-7-8",
                "Move to the next string and repeat",
                "Increase tempo as you improve"
            )
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
                "Use strict down-up picking pattern",
                "Practice on a single string first",
                "Keep wrist relaxed and movements small",
                "Gradually increase speed"
            )
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
                "Skip strings in a controlled manner",
                "Practice patterns: E-G, A-D, etc.",
                "Focus on clean note separation",
                "Start slow and build speed"
            )
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
                "Practice ascending hammer-ons",
                "Practice descending pull-offs",
                "Combine both techniques",
                "Focus on even volume"
            )
        ),
        Exercise(
            id = "tech_5",
            name = "Bending Accuracy",
            description = "Master string bending technique and pitch accuracy",
            category = ExerciseCategory.TECHNIQUE,
            durationMinutes = 5,
            hasTiming = false,
            instructions = listOf(
                "Practice half-step bends",
                "Practice whole-step bends",
                "Check pitch accuracy with tuner",
                "Work on pre-bends and releases"
            )
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
                "Choose a backing track or play over a drone",
                "Explore the full fretboard",
                "Experiment with rhythm and phrasing",
                "Focus on musical expression"
            )
        ),
        Exercise(
            id = "creative_2",
            name = "Melodic Composition",
            description = "Compose short melodic phrases and motifs",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 10,
            hasTiming = false,
            instructions = listOf(
                "Create a 4-bar melody",
                "Vary rhythm and note choice",
                "Try different scales and modes",
                "Record your ideas"
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
                "Create syncopated patterns",
                "Experiment with muted strings",
                "Try different pick attack styles",
                "Combine with chord progressions"
            )
        ),
        Exercise(
            id = "creative_4",
            name = "Modal Exploration",
            description = "Explore different modes and their unique flavors",
            category = ExerciseCategory.CREATIVITY,
            durationMinutes = 10,
            hasTiming = false,
            instructions = listOf(
                "Choose a mode (Dorian, Phrygian, etc.)",
                "Play through the scale shape",
                "Improvise using the mode",
                "Notice the characteristic sound"
            )
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
                "Choose the most challenging section",
                "Break it down into smaller parts",
                "Practice slowly and accurately",
                "Gradually increase to full tempo"
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
                "Don't stop for mistakes",
                "Focus on consistency",
                "Record yourself to evaluate",
                "Note areas for improvement"
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
                "Play without sheet music",
                "Visualize chord changes",
                "Memorize song structure",
                "Practice transitions between sections"
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
                "Listen to a section repeatedly",
                "Figure out the notes or chords",
                "Match the tone and feel",
                "Practice along with recording"
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
        
        // Ensure at least one exercise from each category
        val techExercise = techniqueExercises.random()
        val creativeExercise = creativityExercises.random()
        val songExercise = songExercises.random()
        
        exercises.add(techExercise)
        exercises.add(creativeExercise)
        exercises.add(songExercise)
        totalDuration = techExercise.durationMinutes + 
                       creativeExercise.durationMinutes + 
                       songExercise.durationMinutes
        
        // Fill remaining time with random exercises
        val allExercises = getAllExercises().shuffled()
        for (exercise in allExercises) {
            if (totalDuration + exercise.durationMinutes <= targetDurationMinutes && 
                !exercises.contains(exercise)) {
                exercises.add(exercise)
                totalDuration += exercise.durationMinutes
            }
            if (totalDuration >= targetDurationMinutes - 5) break
        }
        
        return PracticeRoutine(
            id = "routine_${System.currentTimeMillis()}",
            exercises = exercises,
            totalDurationMinutes = totalDuration,
            difficulty = Random.nextInt(1, 4)
        )
    }
}
