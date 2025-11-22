package com.riffscroll.data

import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for RoutineRepository
 */
class RoutineRepositoryTest {
    
    @Test
    fun `saveRoutine should save and return saved routine`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        val routine = exerciseRepo.generateBalancedRoutine(45)
        
        val savedRoutine = repository.saveRoutine("Test Routine", routine)
        
        assertNotNull(savedRoutine)
        assertEquals("Test Routine", savedRoutine.name)
        assertEquals(routine, savedRoutine.routine)
    }
    
    @Test
    fun `getSavedRoutines should return all saved routines`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        assertTrue(repository.getSavedRoutines().isEmpty())
        
        val routine1 = exerciseRepo.generateBalancedRoutine(30)
        val routine2 = exerciseRepo.generateBalancedRoutine(45)
        
        repository.saveRoutine("Routine 1", routine1)
        repository.saveRoutine("Routine 2", routine2)
        
        val savedRoutines = repository.getSavedRoutines()
        assertEquals(2, savedRoutines.size)
    }
    
    @Test
    fun `deleteSavedRoutine should remove routine`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        val routine = exerciseRepo.generateBalancedRoutine(45)
        
        val savedRoutine = repository.saveRoutine("Test", routine)
        assertEquals(1, repository.getSavedRoutines().size)
        
        val deleted = repository.deleteSavedRoutine(savedRoutine.id)
        assertTrue(deleted)
        assertTrue(repository.getSavedRoutines().isEmpty())
    }
    
    @Test
    fun `createSchedule should create and return schedule`() {
        val repository = RoutineRepository()
        
        val schedule = repository.createSchedule("Weekly Plan", "Practice schedule for the week")
        
        assertNotNull(schedule)
        assertEquals("Weekly Plan", schedule.name)
        assertEquals("Practice schedule for the week", schedule.description)
        assertTrue(schedule.routineIds.isEmpty())
    }
    
    @Test
    fun `addRoutineToSchedule should add routine to schedule`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        val routine = exerciseRepo.generateBalancedRoutine(45)
        val savedRoutine = repository.saveRoutine("Test Routine", routine)
        val schedule = repository.createSchedule("Test Schedule")
        
        val added = repository.addRoutineToSchedule(schedule.id, savedRoutine.id)
        assertTrue(added)
        
        val updatedSchedule = repository.getSchedule(schedule.id)
        assertNotNull(updatedSchedule)
        assertEquals(1, updatedSchedule!!.routineIds.size)
        assertTrue(updatedSchedule.routineIds.contains(savedRoutine.id))
    }
    
    @Test
    fun `removeRoutineFromSchedule should remove routine from schedule`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        val routine = exerciseRepo.generateBalancedRoutine(45)
        val savedRoutine = repository.saveRoutine("Test Routine", routine)
        val schedule = repository.createSchedule("Test Schedule")
        
        repository.addRoutineToSchedule(schedule.id, savedRoutine.id)
        val removed = repository.removeRoutineFromSchedule(schedule.id, savedRoutine.id)
        
        assertTrue(removed)
        val updatedSchedule = repository.getSchedule(schedule.id)
        assertTrue(updatedSchedule!!.routineIds.isEmpty())
    }
    
    @Test
    fun `deleteSchedule should remove schedule`() {
        val repository = RoutineRepository()
        
        val schedule = repository.createSchedule("Test Schedule")
        assertEquals(1, repository.getSchedules().size)
        
        val deleted = repository.deleteSchedule(schedule.id)
        assertTrue(deleted)
        assertTrue(repository.getSchedules().isEmpty())
    }
    
    @Test
    fun `getRoutinesInSchedule should return all routines in schedule`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        val routine1 = exerciseRepo.generateBalancedRoutine(30)
        val routine2 = exerciseRepo.generateBalancedRoutine(45)
        val savedRoutine1 = repository.saveRoutine("Routine 1", routine1)
        val savedRoutine2 = repository.saveRoutine("Routine 2", routine2)
        
        val schedule = repository.createSchedule("Test Schedule")
        repository.addRoutineToSchedule(schedule.id, savedRoutine1.id)
        repository.addRoutineToSchedule(schedule.id, savedRoutine2.id)
        
        val routinesInSchedule = repository.getRoutinesInSchedule(schedule.id)
        assertEquals(2, routinesInSchedule.size)
    }
    
    @Test
    fun `deleting saved routine should remove it from schedules`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        val routine = exerciseRepo.generateBalancedRoutine(45)
        val savedRoutine = repository.saveRoutine("Test Routine", routine)
        val schedule = repository.createSchedule("Test Schedule")
        
        repository.addRoutineToSchedule(schedule.id, savedRoutine.id)
        assertEquals(1, repository.getSchedule(schedule.id)!!.routineIds.size)
        
        repository.deleteSavedRoutine(savedRoutine.id)
        
        val updatedSchedule = repository.getSchedule(schedule.id)
        assertTrue(updatedSchedule!!.routineIds.isEmpty())
    }
    
    @Test
    fun `createPracticeSchedulePlan with daysPerWeek should create correct number of routines`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        // Create a 14-day plan with 5 days per week (should create 10 routines: 5 for week 1, 5 for week 2)
        val startDate = System.currentTimeMillis()
        val endDate = startDate + (13 * 24 * 60 * 60 * 1000L) // 14 days (2 weeks)
        
        val plan = repository.createPracticeSchedulePlan(
            name = "Test Plan",
            startDate = startDate,
            endDate = endDate,
            instrument = InstrumentType.GUITAR,
            targetDurationMinutes = 45,
            difficulty = DifficultyLevel.INTERMEDIATE,
            daysPerWeek = 5,
            exerciseRepository = exerciseRepo
        )
        
        assertNotNull(plan)
        assertEquals("Test Plan", plan.name)
        assertEquals(5, plan.daysPerWeek)
        // Should have routines for 5 days per week over 2 weeks, but actual count depends on start day
        assertTrue(plan.scheduleEntries.size <= 10)
        assertTrue(plan.scheduleEntries.size >= 8) // At least 8 routines
    }
    
    @Test
    fun `createPracticeSchedulePlan with 7 daysPerWeek should create routine for every day`() {
        val repository = RoutineRepository()
        val exerciseRepo = ExerciseRepository()
        
        // Create a 7-day plan with 7 days per week (should create 7 routines)
        val startDate = System.currentTimeMillis()
        val endDate = startDate + (6 * 24 * 60 * 60 * 1000L) // 7 days
        
        val plan = repository.createPracticeSchedulePlan(
            name = "Daily Practice",
            startDate = startDate,
            endDate = endDate,
            instrument = InstrumentType.PIANO,
            targetDurationMinutes = 30,
            difficulty = DifficultyLevel.BEGINNER,
            daysPerWeek = 7,
            exerciseRepository = exerciseRepo
        )
        
        assertNotNull(plan)
        assertEquals(7, plan.daysPerWeek)
        assertEquals(7, plan.scheduleEntries.size)
    }
}
