# Zombied

## Project Description

**Zombied** is an educational space-themed game that helps players develop **physics intuition**.
Players explore how objects move in a multi-planetary system by setting **speed** and **direction** to navigate through space with realistic acceleration effects.

## How to Run the Project

### In NetBeans

1. Install **Java JDK 17+**
2. Install **JavaFX 21+** and note the path to the `lib` folder
3. Open NetBeans and select **File → Open Project**, then choose the project folder
4. Right-click the project → **Properties → Libraries → Add JAR/Folder**, and add the JavaFX `lib` folder
5. Go to **Run → Set Project Configuration → Customize** and add VM options:

   ```
   --module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml
   ```

   *(replace "path/to/javafx/lib" with your JavaFX path)*
6. Right-click the project → **Run** or press **F6**
7. Click **Start** in the main menu, input speed and direction, and observe the object’s motion

## Overview

In **Zombied**, players interact with a **multi-planetary system**, learning how forces and motion affect trajectories.
The game emphasizes understanding **acceleration, velocity, and motion dynamics** in an intuitive, visual way.

* **Goal:** Experiment with speed and direction to see how objects behave in space
* **Learning Focus:** Physics intuition through observation of motion in a multi-planet system
* **Theme:** Minimalist space visuals focusing on motion dynamics


## Features

* **Main Menu:** Instructions, **Start**, and **Exit** buttons
* **Interactive Input:** Players enter speed and direction for the object
* **Physics Simulation:** Motion influenced by multiple planetary accelerations
* **Immediate Feedback:** Observe trajectory changes based on inputs
* **Learning Focus:** Understand how objects respond to forces in space


## Teamwork Summary

Each member also worked on their model’s test units.

**Team Member 1 (Zeel Gajjar):**

* MainView and GameView and their controllers
* Models: PhysicsUtil, Vector2, Projectile, and Trajectory

**Team Member 2 (Vedika Jain):**

* ResultView and its Controller
* Models: CollisionUtil, GameModel, GameState, and Planet


## Educational Value

**Zombied** helps players:

* Develop an intuitive understanding of motion and acceleration
* Observe cause-and-effect relationships in a multi-planetary system
* Experiment safely to explore physics concepts visually
* Strengthen analytical thinking and experimentation skills


## Technologies Used

* **Language:** Java 17+
* **Framework:** JavaFX 21+ (for GUI, animation, and interactivity)
* **Concepts:** Object-Oriented Programming, Event Handling, Physics Simulation


### 🌌 “Learn. Observe. Understand.”

*Build your physics intuition one trajectory at a time.*
