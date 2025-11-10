/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Core game logic model : 
 * Handles level progression, physics simulation , and timer logic
 * Coordinates with GameState to track player progress 
 * Notifies observers (views/ controllers) when the state changes
 * @author Vedika
 */
       public class GameModel {
       private int currentLevel; 
       private final int maxLevel = 5;  //make final and set a fix value 
       private double difficultyFactor; //scales the difficulty , for example by increasing gravity, reducing accuracy
       private boolean isZombied; 
       
       //time 
       private long levelStartTime; 
       private long levelTimeLimit = 30 ; //seconds allowed per level 
       
       //simulation objects
       private Projectile projectile; //the launched object
       private List<Planet> planets; 
       private Planet targetPlanet;  //represents win condition, calculated flight path of the projectile
       private Trajectory lastTrajectory; //most recent launch result (motion)
       
       //observer 
       private List<GameObserver> observers; //controllers or views that react whenever something changes
       
       private GameState gameState; //tracks score, attempts etc..
    
        // Constructor that receives the GameState
        public GameModel(GameState gameState) {
            this.gameState = gameState;
            this.currentLevel = 1; 
            this.difficultyFactor = 1.0;
            this.observers = new ArrayList<>();
        }

        /** Start a level by updating timers and dynamic difficulty */
        public void startLevel() {
            System.out.println("Level " + currentLevel +  "started.");
             this.levelStartTime = System.currentTimeMillis();
            this.difficultyFactor = 1.0 + (currentLevel - 1) * 0.5; //+0.5 per level
            this.isZombied = false; 
            gameState.setCurrentLevel(currentLevel);
        }

        /**
         * advance to next level or ends game if last level reached
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
            notifyObservers();
        }

        public void resetLevel() {
            projectile = null; 
            lastTrajectory = null; 
            startLevel();
        }
        
         /**
          * returns true if the current level is the last one
          */
        public boolean isFinalLevel() {
            return currentLevel == maxLevel; 
        }

        //Time logic 

        /**
         * Starts or restarts the level timer 
         */ 
        public void startLevelTimer() {
            this.levelStartTime = System.currentTimeMillis();
        }

        /**
         * return remaining seconds for this level 
         */
        public long getRemainingLevelTime() {
            long elapsSec = (System.currentTimeMillis() - levelStartTime) / 1000; 
            long remaining = levelTimeLimit - elapsSec; 
            if (remaining <= 0) {  //no time left 
                gameState.setZombied(true);
                isZombied = true; 
                return 0; //timer expired 
            }
            notifyTimerUpdate(remaining);
            return remaining; 
        }

        /**
         * checks if level time is up
         */
        public boolean isLevelTimeUp() {
            return getRemainingLevelTime() <= 0; 
        }

         //Phyiscs simulation 
         /**
          * Simulates the projectile launch
          * Updates trajectory, detects collision,  and adjusts gameState
          * @param speed
          * @param angleDegrees
          * @return 
          */
        public Trajectory simulateLaunch(double speed, double angleDegrees) {

            if (targetPlanet == null ) { //must have a targetPlanet
                System.err.println("No target planet set. Cannot simuulate launch");
                return null; 
            }
            gameState.addAttempts();
            projectile = new Projectile(speed, angleDegrees);

            Trajectory trajectory = PhysicsUtil.calculateTrajectoryPoint(projectile, difficultyFactor);
            this.lastTrajectory = trajectory;

            boolean hit = CollisionUtil.checkCollision(trajectory, targetPlanet);

            if (hit) {
                handleSuccessfulHit();
                trajectory.setFailureReason(null);
            } else {
                handleMiss(trajectory);
            }
            notifyObservers();
            return trajectory;
        }

        //success and failures handling 

        //Determines and returns failure reason text for UI 
        public String checkFailure(Trajectory traj) {
            if (traj == null) {
                return "No trajectory.";
            }
            
            if (gameState.isZombied()) {
                return "Game over!";
            }
            
            if (traj.getFailureReason() != null) {
                return traj.getFailureReason().toString();
            }
            
            return "Success!";
        } 

        //** Updates GameState depending on whether the player succeeded
        public void updateGameState(boolean success) {
            if (success) {
            int pointsEarned = (int) (100 * difficultyFactor);
                gameState.updateScore(pointsEarned);
            } else {
                gameState.updateScore(-10); // penalty for failure
            }
            notifyObservers();
        }

          /**
         * Called when rocket reaches target planet
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
         * Called when rocket fails: track failure reason and game over if planets/zombies eat you *
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

         //observers methods 
        /** //no duplicate observers
         * observer pattern : register observer
         * @param observer the view or controller that reacts when a change happens
         */
        public void addObserver(GameObserver observer) {
            if (!observers.contains(observer)) { //checks wheter the list already contains the observer
            observers.add(observer); //if not present, then it adds it to the list 
        }
        }

        /**
         * notify all observers that the game state has changed
         */
        public void notifyObservers() {
           if (observers == null || observers.isEmpty()) {
               return;
           }
           for(GameObserver obs : observers) {
               obs.onGameStateChanged(gameState); 
           }
        }
        
        /**
        * Notifies all registered observers about the remaining level time.
        * This method is called internally by the GameModel whenever the countdown changes.
        *
        * @param remaining the number of seconds remaining in the current level

        */
        private void notifyTimerUpdate(long remaining) {
           for (GameObserver obs : observers ) {
               obs.onTimerUpdate(remaining);
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
        
         /**
         * Checks whether the current level's time has run out.
         *
         * @return true if the level timer has reached zero, false otherwise
         */
        public boolean isTimeUp() {
            return getRemainingLevelTime() <= 0;

        }


        //getter and setters 
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

         public void setPlanets(List<Planet> planets) {
            this.planets = planets;
            updateTargetPlanet();
        }

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