package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Core game logic model :
 * Handles level progression, physics simulation , and timer logic
 * Coordinates with GameState to track player progress
 * Notifies observers (views/ controllers) when the state changes
 * @author Vedika
 */
public class GameModel {
   private int currentLevel; 
   private final int maxLevel = 5;  
   private double difficultyFactor; //ex. increase gravity, decrease accuracy
   private boolean isZombied; 

   //time 
   private long levelStartTime; 
   private long levelTimeLimit = 30 ; 

   //simulation objects
   private Projectile projectile; 
   private List<Planet> planets; 
   private Planet targetPlanet;  //represents win condition, calculated flight path of the projectile
   private Trajectory lastTrajectory; //most recent launch result (motion)

   private GameState gameState; 

    /**
     * Creates a new GameModel using the provided gameState
     * @param gameState 
     */
    public GameModel(GameState gameState) {
        this.gameState = gameState;
        this.currentLevel = 1; 
        this.difficultyFactor = 1.0;
    }

    /**
    * Starts the current level 
    * resets timers, update the difficulty scaling, and refreshes level state
    * GameState is updated to correspond with the active level
    */
    public void startLevel() {
        System.out.println("Level " + currentLevel +  "started.");
         this.levelStartTime = System.currentTimeMillis();
        this.difficultyFactor = 1.0 + (currentLevel - 1) * 0.5; //+0.5 per level
        this.isZombied = false; 
        gameState.setCurrentLevel(currentLevel);
    }

    /**
     * Advance the game to the next level
     * if the final level has been reached, the game ends and zombied state is triggered
     * Otherwise, target planet updates and the next level begins 
     */
    public void advanceLevel() {
        if (currentLevel < maxLevel) {
            currentLevel++; 
            updateTargetPlanet();
            startLevel();
        }
        else {
            isZombied = true; //game finised 
            gameState.setZombied(true);
        }
    }

    /**
     * resets the currentLevel by clearing trajectory and projectile data
     * then restarting the level
     */
    public void resetLevel() {
        projectile = null; 
        lastTrajectory = null; 
        startLevel();
    }

    /**
     * Indicates whether the current level is the final level 
     * @return true if this is the final level, false otherwise
     */
    public boolean isFinalLevel() {
        return currentLevel == maxLevel; 
    }

    //Time logic 

    /**
     * Starts or restarts the level countdown timer 
     */
    public void startLevelTimer() {
        this.levelStartTime = System.currentTimeMillis();
    }

    /**
     * Computes and returns the number of seconds remaining in the current level
     * if the time runs out, the game enters zombied state
     * @return remaining seconds (0 if already expired)
     */
    public long getRemainingLevelTime() {
        long elapsSec = (System.currentTimeMillis() - levelStartTime) / 1000; 
        long remaining = levelTimeLimit - elapsSec; 
        if (remaining <= 0) {  //no time left 
            gameState.setZombied(true);
            isZombied = true; 
            return 0; //timer expired 
        }
        return remaining; 
    }

    /**
     * Checks whether the current level's timer has expired.
     * @return true if no time is left, false otherwise
     */
    public boolean isLevelTimeUp() {
        return getRemainingLevelTime() <= 0; 
    }

     /**
     * Checks whether the current level's time has run out.
     * @return true if the level timer has reached the end, false otherwise
     */
    public boolean isTimeUp() {
        return isLevelTimeUp();
    }

     //Phyiscs simulation 
     /**
      * Simulates the projectile launch with the given speed and angle
      * Generate trajectory data, detects collision,updates gameState and notifies observers
      * @param speed initial speed of the projectile
      * @param angleDegrees launch angle in degrees
      * @return object representing the simulation result or null if the simulation could not run
      */
    public Trajectory simulateLaunch(double speed, double angleDegrees) {
        // preserve backward compatibility: default initial position at origin (0,0)
        return simulateLaunch(speed, angleDegrees, new Vector2(0, 0));
    }

    /**
     * Simulates a launch starting at a provided initial position (useful to start from the rocket's on-screen coordinates).
     *
     * @param speed initial speed
     * @param angleDegrees initial angle in degrees
     * @param initialPos initial position in the same coordinate space used by planets (e.g., scene coordinates)
     * @return Trajectory of Point2D representing the flight path (may set failureReason)
     */
    public Trajectory simulateLaunch(double speed, double angleDegrees, Vector2 initialPos) {

        if (targetPlanet == null ) { //must have a targetPlanet
            System.err.println("No target planet set. Cannot simuulate launch");
            return null; 
        }
        gameState.addAttempts();

        // Use the provided initial position to create the projectile
        projectile = new Projectile(speed, angleDegrees, 1.0, initialPos);

        List<Vector2> trajPoints = new ArrayList<>();
        double dt = 0.1;
        double maxTime = 30;

        for (double t = 0; t <= maxTime; t += dt) {
            // record current projectile position
            trajPoints.add(new Vector2(projectile.getPosition().x, projectile.getPosition().y));

            // compute acceleration due to all planets
            Vector2 acceleration = PhysicsUtil.computeGravity(projectile.getMass(), projectile.getPosition(), planets);

            // integrate velocity and position (simple Euler integration)
            projectile.setVelocity(projectile.getVelocity().add(acceleration.scale(dt)));
            projectile.setPosition(projectile.getPosition().add(projectile.getVelocity().scale(dt)));

            // stop if out of bounds (use a reasonable viewport size; view should translate if different)
            if (CollisionUtil.isOutOfBounds(projectile, 2000, 2000)) break; 
        }

        List<javafx.geometry.Point2D> trajPoints2D = new ArrayList<>();
        for (Vector2 v : trajPoints) {
            trajPoints2D.add(new javafx.geometry.Point2D(v.x, v.y));
        }

        lastTrajectory = new Trajectory(trajPoints2D);

        Planet hitPlanet = CollisionUtil.checkAnyCollsion(projectile, planets);

        if (hitPlanet != null && hitPlanet.equals(targetPlanet)) {
            handleSuccessfulHit();
            lastTrajectory.setFailureReason(null);
            lastTrajectory.setSuccess();
        } else {
            handleMiss(lastTrajectory);
        }

        return lastTrajectory;
    }

    //success and failures handling 

    /**
     * Determines and returns the appropriate failure message
     * based on given trajectory and game status
     * @param traj the trajectory to evaluate
     * @return failure/ success message
     */
    public String checkFailure(Trajectory traj) {
        if (traj == null) {
            return "No trajectory.";
        }

        if (gameState.isZombied()) {
            return "Game over!";
        }

        if (traj.getFailureReason() != null) {
            return traj.getFailureReason();
        }

        return "Success!";
    } 

    /**
     * Updates the player's score depending on success or failure 
     * @param success true if the launch hit the target, false otherwise
     */
    public void updateGameState(boolean success) {
        if (success) {
        int pointsEarned = (int) (100 * difficultyFactor);
            gameState.updateScore(pointsEarned);
        } else {
            gameState.updateScore(-10); // penalty for failure
        }
    }

    /**
    * Called when the projectile successfully collides with the target planet.
    * Awards score and advances to the next level, or ends the game if final level completed.
    */
    private void handleSuccessfulHit() {
        int pointsEarned = (int) (100 * difficultyFactor);
        gameState.updateScore(pointsEarned);

        // Unlock next level if possible
        if (isFinalLevel()) { 
            System.out.println("All levels complete!");
            isZombied = true; 
            gameState.setZombied(true);
        } else {
            advanceLevel();
        }
    }

    /**
    * Called when the projectile misses the target.
    * Determines failure reason (timeout or missed shot), applies score penalty,
    * and stores the message in the trajectory.
    *
    * @param trajectory the generated trajectory for this launch
    */
    private void handleMiss(Trajectory trajectory) {

        if (isLevelTimeUp()) {
            gameState.setZombied(true);
            trajectory.setFailureReason("Timeout: Zombie planets caught you!");
        } else {
            gameState.updateScore(-10);
            trajectory.setFailureReason("Missed the target planet!");
        }
    }

     //GAME RESET AND PLANET MANAGEMENT 

       /** Resets the entire game back to level 1. */
    public void resetGame() {
        currentLevel = 1;
        difficultyFactor = 1.0;
        isZombied = false;
        gameState.resetAll();
        updateTargetPlanet();
        startLevel();
    }    

    //method to udpate the target planet when the level changes
    private void updateTargetPlanet() {
    if (planets != null && currentLevel - 1 < planets.size()) {
        targetPlanet = planets.get(currentLevel - 1); //follow the same index, if level is N, then target planet should be at index N-1 of the arraylist 
       }
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public boolean isZombied() {
        return isZombied; 
    }

    public double getDifficultyFactor() {
        return difficultyFactor;
    }

    public Planet getTargetPlanet() {
        return targetPlanet;
    }

    public Trajectory getLastTrajectory() {
        return lastTrajectory;
    }

    public long getLevelTimeLimit() {
        return levelTimeLimit;
    }

    /**
    * Sets the list of planets used for each level and updates the current target planet.
    *
    * @param planets list of planets assigned to sequential levels
    */
     public void setPlanets(List<Planet> planets) {
        this.planets = planets;
        updateTargetPlanet();
    }

    /**
    * Sets the level's time limit.
    * @param limitSeconds the number of seconds each level should last
    */
    public void setLevelTimeLimit(long limitSeconds) {
        this.levelTimeLimit = limitSeconds;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public void setLastTrajectory(Trajectory trajectory) {
        this.lastTrajectory = trajectory;
    }
}
