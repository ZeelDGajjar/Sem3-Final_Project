/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Model.GameState;

/**
 *
 * @author Vedika
 */
public class GameModel {
    private GameState gameState;
    
    // Constructor that receives the GameState
    public GameModel(GameState gameState) {
        this.gameState = gameState;
    }

    // Example placeholder methods
    public void startLevel() {
        System.out.println("Level started.");
    }

    public GameState getGameState() {
        return gameState;
    }
}

