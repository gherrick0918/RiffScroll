package com.riffscroll.data

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

/**
 * Unit tests for Custom Exercise functionality
 */
class CustomExerciseTest {
    
    private lateinit var repository: ExerciseRepository
    
    @Before
    fun setup() {
        repository = ExerciseRepository()
    }
    
    @Test
    fun `addCustomExercise should create a new custom exercise with unique ID`() {
        val exercise = Exercise(
            id = "", // Will be generated
            name = "Test Custom Exercise",
            description = "This is a test exercise",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 10,
            difficulty = DifficultyLevel.BEGINNER,
            hasTiming = true,
            bpm = 80,
            instructions = listOf("Step 1", "Step 2", "Step 3"),
            tablature = "Test tablature",
            isCustom = false // Will be set to true by repository
        )
        
        val addedExercise = repository.addCustomExercise(exercise)
        
        assertTrue("Exercise should be marked as custom", addedExercise.isCustom)
        assertTrue("Exercise should have a generated ID", addedExercise.id.startsWith("custom_"))
        assertEquals("Name should match", exercise.name, addedExercise.name)
        assertEquals("Description should match", exercise.description, addedExercise.description)
        assertEquals("Category should match", exercise.category, addedExercise.category)
        assertEquals("Instrument should match", exercise.instrument, addedExercise.instrument)
        assertEquals("Duration should match", exercise.durationMinutes, addedExercise.durationMinutes)
    }
    
    @Test
    fun `custom exercise should be included in getAllExercises`() {
        val initialCount = repository.getAllExercises().size
        
        val customExercise = Exercise(
            id = "",
            name = "Custom Sweep Picking",
            description = "Advanced sweep picking technique",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 8,
            difficulty = DifficultyLevel.ADVANCED,
            hasTiming = true,
            bpm = 120,
            instructions = listOf("Practice arpeggios", "Use economy motion", "Increase speed gradually"),
            isCustom = false
        )
        
        repository.addCustomExercise(customExercise)
        
        val newCount = repository.getAllExercises().size
        assertEquals("Exercise count should increase by 1", initialCount + 1, newCount)
        
        val allExercises = repository.getAllExercises()
        val customExercises = allExercises.filter { it.isCustom }
        assertEquals("Should have 1 custom exercise", 1, customExercises.size)
    }
    
    @Test
    fun `getCustomExercises should return only custom exercises`() {
        // Add two custom exercises
        val custom1 = Exercise(
            id = "",
            name = "Custom Exercise 1",
            description = "First custom exercise",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            instructions = listOf("Step 1"),
            isCustom = false
        )
        
        val custom2 = Exercise(
            id = "",
            name = "Custom Exercise 2",
            description = "Second custom exercise",
            category = ExerciseCategory.CREATIVITY,
            instrument = InstrumentType.PIANO,
            durationMinutes = 10,
            instructions = listOf("Step 1", "Step 2"),
            isCustom = false
        )
        
        repository.addCustomExercise(custom1)
        repository.addCustomExercise(custom2)
        
        val customExercises = repository.getCustomExercises()
        assertEquals("Should have 2 custom exercises", 2, customExercises.size)
        assertTrue("All should be marked as custom", customExercises.all { it.isCustom })
    }
    
    @Test
    fun `updateCustomExercise should modify existing custom exercise`() {
        // Add a custom exercise
        val original = Exercise(
            id = "",
            name = "Original Name",
            description = "Original description",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            instructions = listOf("Original step"),
            isCustom = false
        )
        
        val added = repository.addCustomExercise(original)
        
        // Update it
        val updated = added.copy(
            name = "Updated Name",
            description = "Updated description",
            durationMinutes = 10
        )
        
        val result = repository.updateCustomExercise(updated)
        assertTrue("Update should succeed", result)
        
        // Verify the update
        val retrieved = repository.getExerciseById(added.id)
        assertNotNull("Exercise should still exist", retrieved)
        assertEquals("Name should be updated", "Updated Name", retrieved?.name)
        assertEquals("Description should be updated", "Updated description", retrieved?.description)
        assertEquals("Duration should be updated", 10, retrieved?.durationMinutes)
    }
    
    @Test
    fun `updateCustomExercise should fail for built-in exercises`() {
        // Get a built-in exercise
        val builtInExercise = repository.getBuiltInExercises().first()
        
        // Try to update it
        val modified = builtInExercise.copy(name = "Modified Name")
        val result = repository.updateCustomExercise(modified)
        
        assertFalse("Update should fail for built-in exercises", result)
    }
    
    @Test
    fun `deleteCustomExercise should remove custom exercise`() {
        // Add a custom exercise
        val custom = Exercise(
            id = "",
            name = "To Be Deleted",
            description = "This exercise will be deleted",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            instructions = listOf("Step 1"),
            isCustom = false
        )
        
        val added = repository.addCustomExercise(custom)
        val initialCount = repository.getAllExercises().size
        
        // Delete it
        val result = repository.deleteCustomExercise(added.id)
        assertTrue("Delete should succeed", result)
        
        val newCount = repository.getAllExercises().size
        assertEquals("Exercise count should decrease by 1", initialCount - 1, newCount)
        
        val retrieved = repository.getExerciseById(added.id)
        assertNull("Exercise should no longer exist", retrieved)
    }
    
    @Test
    fun `deleteCustomExercise should fail for built-in exercises`() {
        val builtInExercise = repository.getBuiltInExercises().first()
        val initialCount = repository.getAllExercises().size
        
        // Try to delete it
        val result = repository.deleteCustomExercise(builtInExercise.id)
        
        assertFalse("Delete should fail for built-in exercises", result)
        assertEquals("Exercise count should not change", initialCount, repository.getAllExercises().size)
    }
    
    @Test
    fun `loadCustomExercises should restore custom exercises`() {
        // Create some custom exercises
        val exercises = listOf(
            Exercise(
                id = "custom_1",
                name = "Loaded Exercise 1",
                description = "First loaded exercise",
                category = ExerciseCategory.TECHNIQUE,
                instrument = InstrumentType.GUITAR,
                durationMinutes = 5,
                instructions = listOf("Step 1"),
                isCustom = true
            ),
            Exercise(
                id = "custom_2",
                name = "Loaded Exercise 2",
                description = "Second loaded exercise",
                category = ExerciseCategory.CREATIVITY,
                instrument = InstrumentType.PIANO,
                durationMinutes = 10,
                instructions = listOf("Step 1", "Step 2"),
                isCustom = true
            )
        )
        
        repository.loadCustomExercises(exercises)
        
        val customExercises = repository.getCustomExercises()
        assertEquals("Should have 2 loaded exercises", 2, customExercises.size)
        assertTrue("All should be marked as custom", customExercises.all { it.isCustom })
    }
    
    @Test
    fun `loadCustomExercises should filter out non-custom exercises`() {
        val exercises = listOf(
            Exercise(
                id = "custom_1",
                name = "Custom Exercise",
                description = "A custom exercise",
                category = ExerciseCategory.TECHNIQUE,
                instrument = InstrumentType.GUITAR,
                durationMinutes = 5,
                instructions = listOf("Step 1"),
                isCustom = true
            ),
            Exercise(
                id = "builtin_1",
                name = "Built-in Exercise",
                description = "Not actually built-in",
                category = ExerciseCategory.TECHNIQUE,
                instrument = InstrumentType.GUITAR,
                durationMinutes = 5,
                instructions = listOf("Step 1"),
                isCustom = false // Not marked as custom
            )
        )
        
        repository.loadCustomExercises(exercises)
        
        val customExercises = repository.getCustomExercises()
        assertEquals("Should only load custom exercises", 1, customExercises.size)
        assertTrue("Loaded exercise should be custom", customExercises.first().isCustom)
    }
    
    @Test
    fun `getExerciseById should find both custom and built-in exercises`() {
        // Add a custom exercise
        val custom = Exercise(
            id = "",
            name = "Findable Custom",
            description = "Can be found by ID",
            category = ExerciseCategory.TECHNIQUE,
            instrument = InstrumentType.GUITAR,
            durationMinutes = 5,
            instructions = listOf("Step 1"),
            isCustom = false
        )
        
        val added = repository.addCustomExercise(custom)
        
        // Find the custom exercise
        val foundCustom = repository.getExerciseById(added.id)
        assertNotNull("Custom exercise should be found", foundCustom)
        assertEquals("Should find the correct custom exercise", added.id, foundCustom?.id)
        
        // Find a built-in exercise
        val builtIn = repository.getBuiltInExercises().first()
        val foundBuiltIn = repository.getExerciseById(builtIn.id)
        assertNotNull("Built-in exercise should be found", foundBuiltIn)
        assertEquals("Should find the correct built-in exercise", builtIn.id, foundBuiltIn?.id)
    }
    
    @Test
    fun `custom exercises should be included in routine generation`() {
        // Add custom exercises in different categories
        repository.addCustomExercise(
            Exercise(
                id = "",
                name = "Custom Technique",
                description = "Custom technique exercise",
                category = ExerciseCategory.TECHNIQUE,
                instrument = InstrumentType.GUITAR,
                durationMinutes = 5,
                instructions = listOf("Step 1"),
                isCustom = false
            )
        )
        
        repository.addCustomExercise(
            Exercise(
                id = "",
                name = "Custom Creativity",
                description = "Custom creativity exercise",
                category = ExerciseCategory.CREATIVITY,
                instrument = InstrumentType.GUITAR,
                durationMinutes = 10,
                instructions = listOf("Step 1"),
                isCustom = false
            )
        )
        
        // Generate routine - should potentially include custom exercises
        val routine = repository.generateBalancedRoutine(
            targetDurationMinutes = 30,
            difficulty = null,
            instrument = InstrumentType.GUITAR
        )
        
        // The routine may or may not include custom exercises due to randomization
        // But we can verify that custom exercises are in the pool
        val allExercises = repository.getAllExercises()
        val customInPool = allExercises.filter { it.isCustom && it.instrument == InstrumentType.GUITAR }
        assertEquals("Should have 2 custom guitar exercises in pool", 2, customInPool.size)
    }
    
    @Test
    fun `custom exercises should support all difficulty levels`() {
        val difficulties = listOf(
            DifficultyLevel.BEGINNER,
            DifficultyLevel.INTERMEDIATE,
            DifficultyLevel.ADVANCED
        )
        
        difficulties.forEach { difficulty ->
            val exercise = Exercise(
                id = "",
                name = "Custom ${difficulty.displayName} Exercise",
                description = "Exercise at ${difficulty.displayName} level",
                category = ExerciseCategory.TECHNIQUE,
                instrument = InstrumentType.GUITAR,
                durationMinutes = 5,
                difficulty = difficulty,
                instructions = listOf("Step 1"),
                isCustom = false
            )
            
            val added = repository.addCustomExercise(exercise)
            assertEquals("Difficulty should be preserved", difficulty, added.difficulty)
        }
        
        val customExercises = repository.getCustomExercises()
        assertEquals("Should have exercises at all 3 difficulty levels", 3, customExercises.size)
    }
    
    @Test
    fun `custom exercises should support both instruments`() {
        val instruments = listOf(InstrumentType.GUITAR, InstrumentType.PIANO)
        
        instruments.forEach { instrument ->
            val exercise = Exercise(
                id = "",
                name = "Custom ${instrument.displayName} Exercise",
                description = "Exercise for ${instrument.displayName}",
                category = ExerciseCategory.TECHNIQUE,
                instrument = instrument,
                durationMinutes = 5,
                instructions = listOf("Step 1"),
                isCustom = false
            )
            
            val added = repository.addCustomExercise(exercise)
            assertEquals("Instrument should be preserved", instrument, added.instrument)
        }
        
        val customExercises = repository.getCustomExercises()
        assertEquals("Should have exercises for both instruments", 2, customExercises.size)
    }
}
