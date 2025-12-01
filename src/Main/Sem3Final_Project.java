/*
 * Github: https://github.com/ZeelDGajjar/Sem3-Final_Project.git
 */
package Main;

import Model.GameModel;
import Model.GameState;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Application class for the Zombied game
 * This class launches the JavaFx application, loads the menu screen
 * and manages shared game state and scene switching
 * @author Vedika
 */
public class Sem3Final_Project extends Application {

    private static Stage primaryStage; 

    private static GameModel gameModel; 
    private static GameState gameState; 

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            System.out.println(getClass().getResource("/View/MenuView.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("/View/MenuView.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setTitle("Zombied Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {  
            System.getLogger(Sem3Final_Project.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
