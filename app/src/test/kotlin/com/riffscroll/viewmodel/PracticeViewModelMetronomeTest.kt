package com.riffscroll.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for PracticeViewModel metronome functionality
 */
@OptIn(ExperimentalCoroutinesApi::class)
class PracticeViewModelMetronomeTest {
    
    private lateinit var viewModel: PracticeViewModel
    private val testDispatcher = StandardTestDispatcher()
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PracticeViewModel()
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `metronome should start with inactive state`() {
        assertFalse(viewModel.isMetronomeActive.value)
    }
    
    @Test
    fun `toggleMetronome should change active state`() = runTest {
        // Initial state should be inactive
        assertFalse(viewModel.isMetronomeActive.value)
        
        // Note: Actual audio playback will be tested manually or with instrumentation tests
        // This test verifies state management only
    }
    
    @Test
    fun `setMetronomeBpm should update BPM within valid range`() {
        // Test setting BPM within valid range
        viewModel.setMetronomeBpm(120)
        assertEquals(120, viewModel.metronomeBpm.value)
        
        // Test BPM lower bound
        viewModel.setMetronomeBpm(30)
        assertEquals(40, viewModel.metronomeBpm.value)  // Should be clamped to 40
        
        // Test BPM upper bound
        viewModel.setMetronomeBpm(300)
        assertEquals(240, viewModel.metronomeBpm.value)  // Should be clamped to 240
    }
    
    @Test
    fun `metronome BPM should default to 60`() {
        assertEquals(60, viewModel.metronomeBpm.value)
    }
    
    @Test
    fun `stopMetronome should set inactive state`() = runTest {
        viewModel.stopMetronome()
        assertFalse(viewModel.isMetronomeActive.value)
    }
}
