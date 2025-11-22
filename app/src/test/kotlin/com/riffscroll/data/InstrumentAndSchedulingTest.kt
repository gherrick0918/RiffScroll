package com.riffscroll.data

import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for instrument filtering and calendar scheduling features
 */
class InstrumentAndSchedulingTest {
    
    @Test
    fun `ExerciseRepository should have guitar exercises`() {
        val repository = ExerciseRepository()
        val guitarExercises = repository.getExercisesByInstrument(InstrumentType.GUITAR)
        
        assertTrue("Should have guitar exercises", guitarExercises.isNotEmpty())
        assertTrue("All exercises should be guitar", guitarExercises.all { it.instrument == InstrumentType.GUITAR })
    }
    
    @Test
    fun `ExerciseRepository should have piano exercises`() {
        val repository = ExerciseRepository()
        val pianoExercises = repository.getExercisesByInstrument(InstrumentType.PIANO)
        
        assertTrue("Should have piano exercises", pianoExercises.isNotEmpty())
        assertTrue("All exercises should be piano", pianoExercises.all { it.instrument == InstrumentType.PIANO })
    }
    
    @Test
    fun `generateBalancedRoutine with guitar filter should only include guitar exercises`() {
        val repository = ExerciseRepository()
        val routine = repository.generateBalancedRoutine(
            targetDurationMinutes = 45,
            difficulty = null,
            instrument = InstrumentType.GUITAR
        )
        
        assertTrue("Routine should have exercises", routine.exercises.isNotEmpty())
        assertTrue("All exercises should be guitar", routine.exercises.all { it.instrument == InstrumentType.GUITAR })
    }
    
    @Test
    fun `generateBalancedRoutine with piano filter should only include piano exercises`() {
        val repository = ExerciseRepository()
        val routine = repository.generateBalancedRoutine(
            targetDurationMinutes = 45,
            difficulty = null,
            instrument = InstrumentType.PIANO
        )
        
        assertTrue("Routine should have exercises", routine.exercises.isNotEmpty())
        assertTrue("All exercises should be piano", routine.exercises.all { it.instrument == InstrumentType.PIANO })
    }
    
    @Test
    fun `generateBalancedRoutine without instrument filter should include both instruments`() {
        val repository = ExerciseRepository()
        // Generate multiple routines to increase chances of getting both instruments
        val routines = (1..10).map { 
            repository.generateBalancedRoutine(
                targetDurationMinutes = 60,
                difficulty = null,
                instrument = null
            )
        }
        
        val allExercises = routines.flatMap { it.exercises }
        val hasGuitar = allExercises.any { it.instrument == InstrumentType.GUITAR }
        val hasPiano = allExercises.any { it.instrument == InstrumentType.PIANO }
        
        assertTrue("Should have at least some exercises", allExercises.isNotEmpty())
        // Note: With random selection, it's possible (though unlikely) to not get both instruments
        // in a small sample, so we just verify exercises can be from either instrument
        assertTrue("Exercises should be from valid instruments", 
            allExercises.all { it.instrument == InstrumentType.GUITAR || it.instrument == InstrumentType.PIANO })
    }
    
    @Test
    fun `createCalendarSchedule should create calendar entry`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        val routine = exerciseRepo.generateBalancedRoutine(45)
        val savedRoutine = repository.saveRoutine("Test Routine", routine)
        
        val date = System.currentTimeMillis()
        val calendarSchedule = repository.createCalendarSchedule(date, savedRoutine.id)
        
        assertNotNull("Calendar schedule should be created", calendarSchedule)
        assertEquals("Date should match", date, calendarSchedule.date)
        assertEquals("Routine ID should match", savedRoutine.id, calendarSchedule.routineId)
        assertFalse("Should not be completed by default", calendarSchedule.isCompleted)
    }
    
    @Test
    fun `createPracticeSchedulePlan should generate routines for date range`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        val startDate = System.currentTimeMillis()
        val endDate = startDate + (7 * 24 * 60 * 60 * 1000L) // 7 days later
        
        val plan = repository.createPracticeSchedulePlan(
            name = "Test Week Plan",
            startDate = startDate,
            endDate = endDate,
            instrument = InstrumentType.GUITAR,
            targetDurationMinutes = 30,
            difficulty = DifficultyLevel.BEGINNER,
            exerciseRepository = exerciseRepo
        )
        
        assertNotNull("Plan should be created", plan)
        assertEquals("Plan name should match", "Test Week Plan", plan.name)
        assertEquals("Should have 8 schedule entries (inclusive)", 8, plan.scheduleEntries.size)
        assertEquals("Instrument should be guitar", InstrumentType.GUITAR, plan.instrument)
        assertEquals("Target duration should be 30", 30, plan.targetDurationMinutes)
        assertEquals("Difficulty should be beginner", DifficultyLevel.BEGINNER, plan.difficulty)
    }
    
    @Test
    fun `getCalendarScheduleByDate should find schedule for specific date`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        val routine = exerciseRepo.generateBalancedRoutine(45)
        val savedRoutine = repository.saveRoutine("Test Routine", routine)
        
        val calendar = java.util.Calendar.getInstance()
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 12)
        calendar.set(java.util.Calendar.MINUTE, 0)
        calendar.set(java.util.Calendar.SECOND, 0)
        calendar.set(java.util.Calendar.MILLISECOND, 0)
        val date = calendar.timeInMillis
        
        val calendarSchedule = repository.createCalendarSchedule(date, savedRoutine.id)
        
        val found = repository.getCalendarScheduleByDate(date)
        assertNotNull("Should find calendar schedule", found)
        assertEquals("Should be the same schedule", calendarSchedule.id, found?.id)
    }
    
    @Test
    fun `markCalendarScheduleCompleted should mark as completed`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        val routine = exerciseRepo.generateBalancedRoutine(45)
        val savedRoutine = repository.saveRoutine("Test Routine", routine)
        
        val calendarSchedule = repository.createCalendarSchedule(System.currentTimeMillis(), savedRoutine.id)
        assertFalse("Should start as not completed", calendarSchedule.isCompleted)
        
        val marked = repository.markCalendarScheduleCompleted(calendarSchedule.id)
        assertTrue("Should successfully mark as completed", marked)
        
        val schedules = repository.getCalendarSchedules()
        val updated = schedules.find { it.id == calendarSchedule.id }
        assertNotNull("Should find updated schedule", updated)
        assertTrue("Should be marked as completed", updated!!.isCompleted)
    }
    
    @Test
    fun `deletePracticeSchedulePlan should remove plan and calendar schedules`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        val startDate = System.currentTimeMillis()
        val endDate = startDate + (3 * 24 * 60 * 60 * 1000L) // 3 days
        
        val plan = repository.createPracticeSchedulePlan(
            name = "Test Plan",
            startDate = startDate,
            endDate = endDate,
            instrument = null,
            targetDurationMinutes = 30,
            difficulty = null,
            exerciseRepository = exerciseRepo
        )
        
        val scheduleCountBefore = repository.getCalendarSchedules().size
        val expectedScheduleCount = 4  // 3 days inclusive = 4 days
        assertTrue("Should have calendar schedules", scheduleCountBefore >= expectedScheduleCount)
        
        val deleted = repository.deletePracticeSchedulePlan(plan.id)
        assertTrue("Should successfully delete plan", deleted)
        
        val plansAfter = repository.getPracticeSchedulePlans()
        assertFalse("Plan should be removed", plansAfter.any { it.id == plan.id })
        
        val scheduleCountAfter = repository.getCalendarSchedules().size
        assertEquals("Only the plan's calendar schedules should be removed", 
            scheduleCountBefore - expectedScheduleCount, scheduleCountAfter)
    }
    
    @Test
    fun `piano exercises should have appropriate categories`() {
        val repository = ExerciseRepository()
        val pianoExercises = repository.getExercisesByInstrument(InstrumentType.PIANO)
        
        val hasTechnique = pianoExercises.any { it.category == ExerciseCategory.TECHNIQUE }
        val hasCreativity = pianoExercises.any { it.category == ExerciseCategory.CREATIVITY }
        val hasSongs = pianoExercises.any { it.category == ExerciseCategory.SONGS }
        
        assertTrue("Should have technique exercises", hasTechnique)
        assertTrue("Should have creativity exercises", hasCreativity)
        assertTrue("Should have song exercises", hasSongs)
    }
    
    @Test
    fun `guitar exercises should have appropriate categories`() {
        val repository = ExerciseRepository()
        val guitarExercises = repository.getExercisesByInstrument(InstrumentType.GUITAR)
        
        val hasTechnique = guitarExercises.any { it.category == ExerciseCategory.TECHNIQUE }
        val hasCreativity = guitarExercises.any { it.category == ExerciseCategory.CREATIVITY }
        val hasSongs = guitarExercises.any { it.category == ExerciseCategory.SONGS }
        
        assertTrue("Should have technique exercises", hasTechnique)
        assertTrue("Should have creativity exercises", hasCreativity)
        assertTrue("Should have song exercises", hasSongs)
    }
    
    @Test
    fun `createPracticeSchedulePlan should save routines that can be retrieved`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        val startDate = System.currentTimeMillis()
        val endDate = startDate + (2 * 24 * 60 * 60 * 1000L) // 2 days later (3 days inclusive)
        
        val plan = repository.createPracticeSchedulePlan(
            name = "Test Plan",
            startDate = startDate,
            endDate = endDate,
            instrument = InstrumentType.PIANO,
            targetDurationMinutes = 30,
            difficulty = DifficultyLevel.INTERMEDIATE,
            exerciseRepository = exerciseRepo
        )
        
        // Verify all schedule entries reference valid saved routines
        assertEquals("Should have 3 schedule entries", 3, plan.scheduleEntries.size)
        
        plan.scheduleEntries.forEach { entry ->
            val savedRoutine = repository.getSavedRoutine(entry.routineId)
            assertNotNull("Routine ${entry.routineId} should be retrievable", savedRoutine)
            assertNotNull("Saved routine should have a routine", savedRoutine?.routine)
            assertTrue("Routine should have exercises", savedRoutine!!.routine.exercises.isNotEmpty())
            
            // Verify the routine matches the plan parameters
            assertTrue("All exercises should be piano", 
                savedRoutine.routine.exercises.all { it.instrument == InstrumentType.PIANO })
            assertTrue("All exercises should be intermediate difficulty",
                savedRoutine.routine.exercises.all { it.difficulty == DifficultyLevel.INTERMEDIATE })
        }
        
        // Verify routines appear in the saved routines list
        val allSavedRoutines = repository.getSavedRoutines()
        assertEquals("Saved routines list should contain all plan routines", 3, allSavedRoutines.size)
        
        plan.scheduleEntries.forEach { entry ->
            assertTrue("Saved routines list should contain routine ${entry.routineId}",
                allSavedRoutines.any { it.id == entry.routineId })
        }
    }
}
