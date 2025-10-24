/*
 * Github: https://github.com/ZeelDGajjar/Sem3-Final_Project.git
 */
package sem3.final_project;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main Application class for the Zombied game
 * This class launches the JavaFx application, loads the menu screen
 * and manages shared game state and scene switching
 * @author Vedika
 */
public class Sem3Final_Project extends Application {
    
    //Main game window 
    private static Stage primaryStage; 
    
    //Shared models used accross the controllers 
    private static GameModel gameModel; 
    private static GameState gameState; 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        //initialize the core game data models 
        gameState = new GameState();
       
        
        
        
        
        
       primaryStage.show();
    }
    
    public static GameModel getGameModel() {
        return gameModel; 
    }
    
    public static GameState getGameState() {
        return gameState;    
    }
}
