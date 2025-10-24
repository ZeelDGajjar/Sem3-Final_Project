/*
 * Github: https://github.com/ZeelDGajjar/Sem3-Final_Project.git
 */
package sem3.final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

   //need to be able to swtich from different fxml 
    
    
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        //initialize the core game data models 
        gameState = new GameState();
        
       primaryStage.show();
    }
    
     /**
     * Switches the current scene to another FXML view.
     * Example usage: Sem3Final_Project.switchScene("/zombied/view/game.fxml");
     */
    public static void switchScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(Sem3Final_Project.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error switching to scene: " + fxmlPath);
        }
    }
    
    public static GameModel getGameModel() {
        return gameModel; 
    }
    
    public static GameState getGameState() {
        return gameState;    
    }
}
