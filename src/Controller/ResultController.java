/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for the result screen.
 * Displays outcome of the game (score, zombied status, and explanation)
 * and allows the user to return to the main menu.
 */
public class ResultController {
    @FXML
    private Label warningMessageLabel; // Displays a warning or game-over title message
 
    @FXML
    private Button TryAgainBtn; // Button to restart or return to main menu
    
    @FXML
    private Label FailureReasonLabel; // Shows reason why player failed / lost
    
    @FXML
    private Label levelReachedLabel;  // Shows level player reached before failing

     /**
      * Initializes the result screen with game outcome details
      * This method must be called by the class loading the FXML
      * @param warningMessage A short warning or conclusion message (ex. "You were Zombied")
      * @param failureReason Detailed explanation for failure (ex. " ran out of food")
      * @param levelReached The level number the player reached before losing 
      */
    public void setResult(String warningMessage, String failureReason, int levelReached) {
        warningMessageLabel.setText(warningMessage != null ? warningMessage : "No message");
        FailureReasonLabel.setText(failureReason != null ? failureReason : "No reason provided");
        levelReachedLabel.setText("Level " + levelReached);
    }
    
    /**
     * Handle Try Again button click.
     * This will close the result window and return to the main menu or restart the game.
     */
    @FXML
    private void handleTryAgain(ActionEvent event) {
        // Close the result window
        Stage stage = (Stage) TryAgainBtn.getScene().getWindow();
        stage.close();
    }

}
     
     

