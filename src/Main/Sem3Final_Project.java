/*
 * Github: https://github.com/ZeelDGajjar/Sem3-Final_Project.git
 */
package Main;

import Model.GameState;
import Model.GameModel;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Application class for the game
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
    public void start(Stage primaryStage) throws IOException {
        
        //initialize the core game data models 
        gameState = new GameState();
        gameModel  = new GameModel(gameState); 
        
        //load and display the main scene ( menu)
        
        try {
        // Load the first scene (menu screen) from the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/zombied/view/menu.fxml")); //*** need to create it 
 
        // Create a new Scene using the FXML layout
        Scene menuScene = new Scene(loader.load());

        // Set the title of the window
        primaryStage.setTitle("Zombied: Space Physics Game");
   
        // Set the menu scene on the stage
        primaryStage.setScene(menuScene);

        // Show the stage (make the window appear)
        primaryStage.show();

        System.out.println("Menu scene loaded successfully.");

       } catch (Exception e) {
        // If something goes wrong, print the error details
        e.printStackTrace();
        System.out.println("Error loading menu.fxml. Check the file path.");
    }
        
        
       primaryStage.setTitle("Zombied: Space Physics Game");
       primaryStage.show();
    }
    
     /**
     * Switches the current scene to another FXML view.
     * Example usage: Sem3Final_Project.switchScene("/zombied/view/game.fxml");
     */    
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
