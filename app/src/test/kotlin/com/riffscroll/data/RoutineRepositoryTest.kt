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
}
