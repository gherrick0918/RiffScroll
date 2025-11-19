# RiffScroll Features Documentation

## Core Features

### 1. Dynamic Routine Generation

**Purpose**: Generate balanced, varied practice routines automatically

**How it works**:
- User selects target duration (15-90 minutes via slider)
- Algorithm ensures at least one exercise from each category:
  - Techniques (mechanical skills)
  - Creativity (musical expression)
  - Songs (practical application)
- Randomly selects additional exercises to fill time
- Avoids duplicate exercises in a single routine
- Assigns difficulty level (1-3) to routine

**Benefits**:
- Eliminates decision fatigue
- Ensures well-rounded practice
- Prevents getting stuck in comfort zone
- Saves time planning practice sessions

### 2. Exercise Library

**13 Professionally-Designed Exercises**

#### Technique Exercises (5)
1. **Chromatic Scale Practice** (5 min, 60 BPM)
   - Build finger strength and dexterity
   - Focus on one finger per fret
   - Progressive tempo increase

2. **Alternate Picking Drill** (5 min, 80 BPM)
   - Master down-up picking pattern
   - Improve speed and precision
   - Relaxed wrist technique

3. **String Skipping Exercise** (5 min, 70 BPM)
   - Develop accuracy and coordination
   - Practice controlled string jumps
   - Build fretboard navigation

4. **Hammer-On & Pull-Off Practice** (5 min, 60 BPM)
   - Build legato technique
   - Focus on even volume
   - Combine ascending/descending patterns

5. **Bending Accuracy** (5 min, no metronome)
   - Master string bending
   - Pitch accuracy training
   - Pre-bends and releases

#### Creativity Exercises (4)
1. **Improvisation in Pentatonic** (10 min, 90 BPM)
   - Free musical exploration
   - Fretboard knowledge
   - Phrasing development

2. **Melodic Composition** (10 min, no metronome)
   - Create original melodies
   - Develop musical ideas
   - Recording practice ideas

3. **Rhythm Creation** (8 min, 100 BPM)
   - Develop unique patterns
   - Syncopation practice
   - Muting techniques

4. **Modal Exploration** (10 min, no metronome)
   - Explore different modes
   - Understand mode flavors
   - Modal improvisation

#### Song Exercises (4)
1. **Song Section Practice** (15 min)
   - Focus on challenging parts
   - Break down complex sections
   - Slow practice to full tempo

2. **Full Song Play-Through** (10 min)
   - Complete performance practice
   - Consistency building
   - Self-evaluation through recording

3. **Song Memorization** (10 min)
   - Play without sheet music
   - Internalize song structure
   - Smooth transitions

4. **Cover Song Learning** (15 min)
   - Learn by ear
   - Match tone and feel
   - Play along with recording

### 3. Timer System

**Real-Time Practice Tracking**

Features:
- Counts up from 00:00 in MM:SS format
- Displays target duration for current exercise
- Visual progress bar showing completion percentage
- Continues running throughout practice
- Pauses when user pauses session

Benefits:
- Stay focused on current task
- Know when to move to next exercise
- Track total practice time
- Build discipline and routine

### 4. Metronome Integration

**Adjustable Tempo Control**

Features:
- Range: 40-240 BPM
- Toggle on/off during practice
- Fine adjustment with slider
- Quick +/- 5 BPM buttons
- Automatically set to exercise's suggested BPM
- Visual indicator when active

Use Cases:
- Technical exercises requiring steady tempo
- Building speed gradually
- Rhythm practice
- Timing accuracy training

*Note: Current implementation provides the UI and state management. Audio playback would be implemented in a future update.*

### 5. RPG Progression System

**Level Up Your Guitar Skills**

Mechanics:
- **Experience Points (XP)**:
  - Earn 2 XP per minute of practice
  - Displayed as current/max (e.g., "50/100")
  - Visual progress bar

- **Levels**:
  - Start at Level 1
  - Level up when XP reaches threshold
  - XP requirement increases 50% per level
  - Level 1→2: 100 XP (50 minutes)
  - Level 2→3: 150 XP (75 minutes)
  - Level 3→4: 225 XP (112.5 minutes)

- **Statistics**:
  - Total practice minutes
  - Completed routines count
  - Current level
  - XP progress

Benefits:
- Gamifies practice for motivation
- Tracks long-term progress
- Provides sense of achievement
- Encourages consistency

### 6. Exercise Instructions

**Clear, Step-by-Step Guidance**

Features:
- Numbered instruction list for each exercise
- Displayed prominently during practice
- Easy to follow while playing
- Professional technique tips
- Progressive difficulty suggestions

Example (Alternate Picking):
1. Use strict down-up picking pattern
2. Practice on a single string first
3. Keep wrist relaxed and movements small
4. Gradually increase speed

Benefits:
- Learn proper technique
- Understand exercise goals
- Avoid bad habits
- Practice with purpose

### 7. Responsive Design

**Works on All Devices**

Supported Configurations:
- **Phones (Portrait)**:
  - Single column layout
  - Optimized for one-handed use
  - Full screen practice view

- **Phones (Landscape)**:
  - Horizontal optimization
  - Efficient space usage
  - Comfortable viewing angle

- **Tablets (Portrait)**:
  - Larger touch targets
  - Increased font sizes
  - More visual breathing room

- **Tablets (Landscape)**:
  - Two-column layout
  - Side-by-side information
  - Desktop-like experience

Technical Implementation:
- Uses Jetpack Compose adaptive layouts
- `configChanges` in manifest handles rotation
- State preservation during configuration changes
- Responsive padding and spacing

### 8. Practice Session Management

**Full Control Over Your Practice**

Session Controls:
- **Start**: Begin routine with first exercise
- **Pause**: Temporarily stop timer and metronome
- **Resume**: Continue from where you left off
- **Next**: Skip to next exercise
- **End**: Stop session early

Session State:
- Tracks current exercise index
- Maintains elapsed time
- Preserves metronome settings
- Shows completion progress

Benefits:
- Flexibility during practice
- Handle interruptions gracefully
- Skip exercises if needed
- Complete control

### 9. Visual Exercise Categorization

**Quick Identification System**

Category Badges:
- **⚡ Technique** (Red badge)
  - Mechanical skill development
  - Precision and speed
  
- **🎨 Creative** (Gold badge)
  - Musical expression
  - Improvisation and composition
  
- **🎵 Song** (Green badge)
  - Practical application
  - Performance preparation

Additional Badges:
- **Duration** (Blue badge): "5 min", "10 min"
- **Tempo** (Blue badge): "♩ 80 BPM"
- **Level** (Gold badge): "Lv 1", "Lv 2"

Benefits:
- Instant visual recognition
- Understand routine balance
- Color-coded organization
- Professional appearance

### 10. Old School RPG Theme

**Immersive Retro Gaming Aesthetic**

Visual Elements:
- Dark fantasy color palette
- Bordered card designs
- Gradient progress bars
- Badge/tag system
- Level and XP mechanics
- Achievement-style feedback

Design Inspiration:
- Classic 8-bit/16-bit RPGs
- Pixel art aesthetic (modern interpretation)
- Quest and adventure themes
- Character progression systems

Benefits:
- Memorable and unique
- Makes practice more engaging
- Appeals to gamer guitarists
- Stands out from typical music apps

## User Experience Flow

### First Time User

1. **Open App** → See Level 1, 0 XP progress
2. **Adjust Duration** → Use slider (default 45 min)
3. **Generate Routine** → View 6 varied exercises
4. **Start Practice** → Timer begins, instructions shown
5. **Complete Exercise** → XP gained, progress updated
6. **Finish Routine** → Level progress, stats updated

### Returning User

1. **Open App** → See accumulated progress
2. **Quick Generate** → Routine in one tap
3. **Start Practice** → Resume familiar workflow
4. **Track Progress** → Watch level and XP grow

## Technical Specifications

### Performance
- Smooth 60 FPS animations
- Instant routine generation (<100ms)
- Efficient state management
- Low battery consumption

### Compatibility
- Minimum: Android 7.0 (API 24)
- Target: Android 14 (API 34)
- Supports all screen sizes
- Portrait and landscape modes

### Storage
- Lightweight APK (~5-10 MB)
- No external storage required
- Future: Local database for persistence

## Future Enhancement Possibilities

### Short Term
- Audio metronome sound playback
- Vibration feedback for metronome
- Save/load user progress
- Practice history log

### Medium Term
- Custom exercise creation
- Routine templates
- Practice scheduling
- Statistics and charts

### Long Term
- Backing track integration
- Video tutorials
- Social features
- Cloud sync
- Achievement system

## Conclusion

RiffScroll provides a complete, thoughtfully designed solution for guitar practice. The combination of dynamic routine generation, comprehensive exercise library, RPG progression, and responsive design creates an engaging and effective practice tool for guitarists of all levels.
