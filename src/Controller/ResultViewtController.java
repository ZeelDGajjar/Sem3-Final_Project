/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Planet;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Controller for the result screen.
 * Displays outcome of the game (score, zombied status, and explanation)
 * and allows the user to return to the main menu.
 */
public class ResultViewtController implements Initializable {
    @FXML
     public Label warningMessageLabel;  //Display a warning or game-over title message  

    @FXML
    public Button TryAgainBtn;     
    
    @FXML
     public Label FailureReasonLabel; // Shows reason why player failed / lost
    
    @FXML
    public  Label levelReachedLabel;  // Shows level player reached before failing

     /**
     * @param warningMessage  A short warning or conclusion message (e.g., "You were zombied!")
     * @param failureReason   Detailed explanation for failure (e.g., "You ran out of oxygen")
     * @param levelReached    The level number the player reached before losing
     */
    public void setResult(String warningMessage, String failureReason, int levelReached) {
        warningMessageLabel.setText(warningMessage != null ? warningMessage : "No message");
        FailureReasonLabel.setText(failureReason != null ? failureReason : "No reason provided");
        levelReachedLabel.setText("Level " + levelReached);
    }
    
    /**
     * Handles the Try Again button click.
     * Closes the result window, allowing the caller to restart the game
     * or return to the main menu.
     *
     * @param event The ActionEvent triggered by clicking the button
     */
    @FXML
    public void handleTryAgain(ActionEvent event) {
        // Close the result window
        Stage stage = (Stage) TryAgainBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      String[] imageFiles = {
       "Sun.png", "Mercury.png", "Venus.png", "Earth.jpg", "Moon.png",
       "Mars.png", "Jupiter.png", "Saturn.png", "Uranus.png", "Neptune.png"
    };
    
    Image[] images = new Image[imageFiles.length];

    for (int i = 0; i < imageFiles.length; i++) {
        try {
            images[i] = new Image(getClass().getResourceAsStream("/Images/" + imageFiles[i]));
            if (images[i] == null) {
                System.err.println("Image not found: " + imageFiles[i]);
            }
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imageFiles[i]);
            e.printStackTrace();
        }
        
     }
    }
}

     
     

