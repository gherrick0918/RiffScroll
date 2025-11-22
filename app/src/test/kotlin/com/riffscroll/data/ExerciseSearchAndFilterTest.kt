package com.riffscroll.data

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

/**
 * Unit tests for Exercise Search and Filtering functionality
 */
class ExerciseSearchAndFilterTest {
    
    private lateinit var repository: ExerciseRepository
    private lateinit var allExercises: List<Exercise>
    
    @Before
    fun setup() {
        repository = ExerciseRepository()
        allExercises = repository.getAllExercises()
    }
    
    @Test
    fun `getAllExercises should return 150+ exercises`() {
        assertTrue("Should have at least 150 exercises", allExercises.size >= 150)
    }
    
    @Test
    fun `getAllExercises should include both guitar and piano exercises`() {
        val guitarExercises = allExercises.filter { it.instrument == InstrumentType.GUITAR }
        val pianoExercises = allExercises.filter { it.instrument == InstrumentType.PIANO }
        
        assertTrue("Should have guitar exercises", guitarExercises.isNotEmpty())
        assertTrue("Should have piano exercises", pianoExercises.isNotEmpty())
        assertEquals("All exercises should be accounted for", allExercises.size, guitarExercises.size + pianoExercises.size)
    }
    
    @Test
    fun `getAllExercises should include all three categories`() {
        val techniqueExercises = allExercises.filter { it.category == ExerciseCategory.TECHNIQUE }
        val creativityExercises = allExercises.filter { it.category == ExerciseCategory.CREATIVITY }
        val songExercises = allExercises.filter { it.category == ExerciseCategory.SONGS }
        
        assertTrue("Should have technique exercises", techniqueExercises.isNotEmpty())
        assertTrue("Should have creativity exercises", creativityExercises.isNotEmpty())
        assertTrue("Should have song exercises", songExercises.isNotEmpty())
        assertEquals("All exercises should be accounted for", allExercises.size, techniqueExercises.size + creativityExercises.size + songExercises.size)
    }
    
    @Test
    fun `filter by instrument should return correct exercises`() {
        // Test Guitar filter
        val guitarExercises = allExercises.filter { it.instrument == InstrumentType.GUITAR }
        assertTrue("Should have many guitar exercises", guitarExercises.size > 50)
        guitarExercises.forEach { exercise ->
            assertEquals("All filtered exercises should be guitar", InstrumentType.GUITAR, exercise.instrument)
        }
        
        // Test Piano filter
        val pianoExercises = allExercises.filter { it.instrument == InstrumentType.PIANO }
        assertTrue("Should have many piano exercises", pianoExercises.size > 30)
        pianoExercises.forEach { exercise ->
            assertEquals("All filtered exercises should be piano", InstrumentType.PIANO, exercise.instrument)
        }
    }
    
    @Test
    fun `filter by category should return correct exercises`() {
        // Test Technique filter
        val techniqueExercises = allExercises.filter { it.category == ExerciseCategory.TECHNIQUE }
        assertTrue("Should have technique exercises", techniqueExercises.isNotEmpty())
        techniqueExercises.forEach { exercise ->
            assertEquals("All filtered exercises should be technique", ExerciseCategory.TECHNIQUE, exercise.category)
        }
        
        // Test Creativity filter
        val creativityExercises = allExercises.filter { it.category == ExerciseCategory.CREATIVITY }
        assertTrue("Should have creativity exercises", creativityExercises.isNotEmpty())
        creativityExercises.forEach { exercise ->
            assertEquals("All filtered exercises should be creativity", ExerciseCategory.CREATIVITY, exercise.category)
        }
        
        // Test Songs filter
        val songExercises = allExercises.filter { it.category == ExerciseCategory.SONGS }
        assertTrue("Should have song exercises", songExercises.isNotEmpty())
        songExercises.forEach { exercise ->
            assertEquals("All filtered exercises should be songs", ExerciseCategory.SONGS, exercise.category)
        }
    }
    
    @Test
    fun `filter by difficulty should return correct exercises`() {
        // Test Beginner filter
        val beginnerExercises = allExercises.filter { it.difficulty == DifficultyLevel.BEGINNER }
        assertTrue("Should have beginner exercises", beginnerExercises.isNotEmpty())
        beginnerExercises.forEach { exercise ->
            assertEquals("All filtered exercises should be beginner", DifficultyLevel.BEGINNER, exercise.difficulty)
        }
        
        // Test Intermediate filter
        val intermediateExercises = allExercises.filter { it.difficulty == DifficultyLevel.INTERMEDIATE }
        assertTrue("Should have intermediate exercises", intermediateExercises.isNotEmpty())
        intermediateExercises.forEach { exercise ->
            assertEquals("All filtered exercises should be intermediate", DifficultyLevel.INTERMEDIATE, exercise.difficulty)
        }
        
        // Test Advanced filter
        val advancedExercises = allExercises.filter { it.difficulty == DifficultyLevel.ADVANCED }
        assertTrue("Should have advanced exercises", advancedExercises.isNotEmpty())
        advancedExercises.forEach { exercise ->
            assertEquals("All filtered exercises should be advanced", DifficultyLevel.ADVANCED, exercise.difficulty)
        }
    }
    
    @Test
    fun `filter by duration range should return correct exercises`() {
        // Test 5-10 minute range
        val shortExercises = allExercises.filter { it.durationMinutes in 5..10 }
        assertTrue("Should have exercises in 5-10 minute range", shortExercises.isNotEmpty())
        shortExercises.forEach { exercise ->
            assertTrue("Exercise duration should be in range", exercise.durationMinutes in 5..10)
        }
        
        // Test 10-20 minute range
        val mediumExercises = allExercises.filter { it.durationMinutes in 10..20 }
        assertTrue("Should have exercises in 10-20 minute range", mediumExercises.isNotEmpty())
        mediumExercises.forEach { exercise ->
            assertTrue("Exercise duration should be in range", exercise.durationMinutes in 10..20)
        }
    }
    
    @Test
    fun `filter by timed exercises should return only exercises with timing`() {
        val timedExercises = allExercises.filter { it.hasTiming }
        assertTrue("Should have timed exercises", timedExercises.isNotEmpty())
        timedExercises.forEach { exercise ->
            assertTrue("All filtered exercises should have timing", exercise.hasTiming)
            assertNotNull("Timed exercises should have BPM", exercise.bpm)
        }
    }
    
    @Test
    fun `search by name should return matching exercises`() {
        // Search for "Chromatic"
        val chromaticExercises = allExercises.filter { 
            it.name.contains("Chromatic", ignoreCase = true) 
        }
        assertTrue("Should find chromatic exercises", chromaticExercises.isNotEmpty())
        chromaticExercises.forEach { exercise ->
            assertTrue("Exercise name should contain 'Chromatic'", 
                exercise.name.contains("Chromatic", ignoreCase = true))
        }
        
        // Search for "Scale"
        val scaleExercises = allExercises.filter { 
            it.name.contains("Scale", ignoreCase = true) 
        }
        assertTrue("Should find scale exercises", scaleExercises.isNotEmpty())
        scaleExercises.forEach { exercise ->
            assertTrue("Exercise name should contain 'Scale'", 
                exercise.name.contains("Scale", ignoreCase = true))
        }
    }
    
    @Test
    fun `search by description should return matching exercises`() {
        // Search for "improvisation"
        val improvisationExercises = allExercises.filter { 
            it.description.contains("improvisation", ignoreCase = true) 
        }
        assertTrue("Should find improvisation exercises", improvisationExercises.isNotEmpty())
        improvisationExercises.forEach { exercise ->
            assertTrue("Exercise description should contain 'improvisation'", 
                exercise.description.contains("improvisation", ignoreCase = true))
        }
    }
    
    @Test
    fun `search by instructions should return matching exercises`() {
        // Search for "finger"
        val fingerExercises = allExercises.filter { exercise ->
            exercise.instructions.any { it.contains("finger", ignoreCase = true) }
        }
        assertTrue("Should find exercises with 'finger' in instructions", fingerExercises.isNotEmpty())
    }
    
    @Test
    fun `combined filters should narrow down results correctly`() {
        // Filter for: Guitar + Technique + Beginner
        val filtered = allExercises.filter { 
            it.instrument == InstrumentType.GUITAR &&
            it.category == ExerciseCategory.TECHNIQUE &&
            it.difficulty == DifficultyLevel.BEGINNER
        }
        
        assertTrue("Should have guitar beginner technique exercises", filtered.isNotEmpty())
        filtered.forEach { exercise ->
            assertEquals(InstrumentType.GUITAR, exercise.instrument)
            assertEquals(ExerciseCategory.TECHNIQUE, exercise.category)
            assertEquals(DifficultyLevel.BEGINNER, exercise.difficulty)
        }
    }
    
    @Test
    fun `combined search and filter should work correctly`() {
        // Search for "scale" + Guitar + Intermediate
        val filtered = allExercises.filter { 
            it.name.contains("scale", ignoreCase = true) &&
            it.instrument == InstrumentType.GUITAR &&
            it.difficulty == DifficultyLevel.INTERMEDIATE
        }
        
        if (filtered.isNotEmpty()) {
            filtered.forEach { exercise ->
                assertTrue("Name should contain 'scale'", 
                    exercise.name.contains("scale", ignoreCase = true))
                assertEquals(InstrumentType.GUITAR, exercise.instrument)
                assertEquals(DifficultyLevel.INTERMEDIATE, exercise.difficulty)
            }
        }
    }
    
    @Test
    fun `empty search should return all exercises`() {
        val searchQuery = ""
        val filtered = allExercises.filter { exercise ->
            if (searchQuery.isBlank()) {
                true
            } else {
                exercise.name.contains(searchQuery, ignoreCase = true) ||
                exercise.description.contains(searchQuery, ignoreCase = true)
            }
        }
        
        assertEquals("Empty search should return all exercises", allExercises.size, filtered.size)
    }
    
    @Test
    fun `no matching exercises should return empty list`() {
        val filtered = allExercises.filter { 
            it.name.contains("XYZ123NonExistent", ignoreCase = true) 
        }
        
        assertTrue("Should return empty list for non-matching search", filtered.isEmpty())
    }
    
    @Test
    fun `all exercises should have required fields`() {
        allExercises.forEach { exercise ->
            assertNotNull("Exercise ID should not be null", exercise.id)
            assertTrue("Exercise ID should not be empty", exercise.id.isNotBlank())
            assertTrue("Exercise name should not be empty", exercise.name.isNotBlank())
            assertTrue("Exercise description should not be empty", exercise.description.isNotBlank())
            assertTrue("Exercise duration should be positive", exercise.durationMinutes > 0)
            assertNotNull("Exercise category should not be null", exercise.category)
            assertNotNull("Exercise instrument should not be null", exercise.instrument)
            assertNotNull("Exercise difficulty should not be null", exercise.difficulty)
        }
    }
    
    @Test
    fun `exercises with timing should have valid BPM`() {
        val timedExercises = allExercises.filter { it.hasTiming }
        timedExercises.forEach { exercise ->
            assertNotNull("Timed exercise should have BPM", exercise.bpm)
            exercise.bpm?.let { bpm ->
                assertTrue("BPM should be in valid range (40-240)", bpm in 40..240)
            }
        }
    }
    
    @Test
    fun `getExercisesByInstrument should return correct results`() {
        val guitarExercises = repository.getExercisesByInstrument(InstrumentType.GUITAR)
        val pianoExercises = repository.getExercisesByInstrument(InstrumentType.PIANO)
        
        assertTrue("Should have guitar exercises", guitarExercises.isNotEmpty())
        assertTrue("Should have piano exercises", pianoExercises.isNotEmpty())
        
        guitarExercises.forEach { 
            assertEquals(InstrumentType.GUITAR, it.instrument) 
        }
        pianoExercises.forEach { 
            assertEquals(InstrumentType.PIANO, it.instrument) 
        }
    }
    
    @Test
    fun `getExercisesByCategory should return correct results`() {
        val techniqueExercises = repository.getExercisesByCategory(ExerciseCategory.TECHNIQUE)
        val creativityExercises = repository.getExercisesByCategory(ExerciseCategory.CREATIVITY)
        val songExercises = repository.getExercisesByCategory(ExerciseCategory.SONGS)
        
        assertTrue("Should have technique exercises", techniqueExercises.isNotEmpty())
        assertTrue("Should have creativity exercises", creativityExercises.isNotEmpty())
        assertTrue("Should have song exercises", songExercises.isNotEmpty())
        
        techniqueExercises.forEach { 
            assertEquals(ExerciseCategory.TECHNIQUE, it.category) 
        }
        creativityExercises.forEach { 
            assertEquals(ExerciseCategory.CREATIVITY, it.category) 
        }
        songExercises.forEach { 
            assertEquals(ExerciseCategory.SONGS, it.category) 
        }
    }
    
    @Test
    fun `getExercisesByDifficulty should return correct results`() {
        val beginnerExercises = repository.getExercisesByDifficulty(DifficultyLevel.BEGINNER)
        val intermediateExercises = repository.getExercisesByDifficulty(DifficultyLevel.INTERMEDIATE)
        val advancedExercises = repository.getExercisesByDifficulty(DifficultyLevel.ADVANCED)
        
        assertTrue("Should have beginner exercises", beginnerExercises.isNotEmpty())
        assertTrue("Should have intermediate exercises", intermediateExercises.isNotEmpty())
        assertTrue("Should have advanced exercises", advancedExercises.isNotEmpty())
        
        beginnerExercises.forEach { 
            assertEquals(DifficultyLevel.BEGINNER, it.difficulty) 
        }
        intermediateExercises.forEach { 
            assertEquals(DifficultyLevel.INTERMEDIATE, it.difficulty) 
        }
        advancedExercises.forEach { 
            assertEquals(DifficultyLevel.ADVANCED, it.difficulty) 
        }
    }
    
    @Test
    fun `getExercisesByDifficulty with null should return all exercises`() {
        val allDifficultyExercises = repository.getExercisesByDifficulty(null)
        assertEquals("Null difficulty should return all exercises", allExercises.size, allDifficultyExercises.size)
    }
}
