# Schedule Display and Days Per Week Improvements

## Overview
This document describes the improvements made to address space concerns with automatic schedules and add flexibility for weekly practice planning.

## Changes Made

### 1. Today's Routine Display with Day Navigation

**Problem**: When automatic schedules were created, all routines were shown on the main page, taking up too much space.

**Solution**: 
- Added a new "Today's Routine" section on the home screen
- Only shows the routine for the currently selected day (defaults to today)
- Includes navigation controls to browse different days:
  - Left arrow: Navigate to previous day
  - Right arrow: Navigate to next day
  - "Go to Today" button: Jump back to today's date
- Shows routine details with options to:
  - View full routine details
  - Start practice session directly
  - Mark routine as completed

**Implementation**:
- New `TodaysRoutineSection` composable in `HomeScreen.kt`
- Date navigation state management in `PracticeViewModel`
- Helper methods: `navigateToPreviousDay()`, `navigateToNextDay()`, `navigateToToday()`

### 2. Days Per Week Option for Schedule Plans

**Problem**: Users needed the ability to create schedules with specific practice days per week (e.g., practice 5 days a week instead of every day).

**Solution**:
- Added optional "Days Per Week" slider (1-7 days) when creating schedule plans
- Schedule generation logic distributes routines evenly throughout each week
- Shows days/week badge in schedule plan display when less than 7 days

**Implementation**:
- New `daysPerWeek` field in `PracticeSchedulePlan` model (defaults to 7)
- Updated `createPracticeSchedulePlan()` in `RoutineRepository` to respect weekly limits
- Routines are created for the first N days of each week (Monday-based weeks)
- UI slider in `CreateSchedulePlanDialog` for selecting days per week

## User Experience

### Creating a Schedule with Days Per Week
1. Open Auto Schedule Planner
2. Click "Create New Schedule Plan"
3. Enter plan details:
   - Plan name
   - Number of days (total duration)
   - **Days per week** (new: 1-7 slider)
   - Instrument, difficulty, and daily duration
4. System generates routines respecting the weekly limit

### Viewing Today's Routine
1. Return to home screen
2. See "Today's Routine" section prominently displayed
3. Use navigation arrows to browse different days
4. Click "Start" to begin practice or "View Details" to see full routine
5. Mark routine as completed when finished

## Benefits

1. **Reduced Clutter**: Home screen only shows one routine at a time
2. **Better Navigation**: Easy to browse past and future scheduled routines
3. **Flexibility**: Can create schedules for 1-7 days per week
4. **Rest Days**: Built-in support for scheduling rest days
5. **Progress Tracking**: Clear indication of completed vs. pending routines

## Technical Details

### Date Handling
- Uses Java Calendar for date arithmetic
- Compares dates by year and day-of-year (ignoring time)
- Week starts on Monday for days-per-week calculations

### State Management
- `currentViewingDate` in ViewModel tracks which day is being viewed
- Automatically loads today's date on app start
- Persists navigation state during app session

### Week-based Distribution
When `daysPerWeek < 7`, routines are created for the first N days of each calendar week (Monday through Sunday):
- 5 days/week = Monday through Friday
- 3 days/week = Monday, Tuesday, Wednesday
- etc.

This provides a consistent, predictable schedule that automatically includes weekend rest days when appropriate.
