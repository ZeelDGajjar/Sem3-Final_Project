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
    private Label warningMessageLabel;    // Level reached

    @FXML
    private Button TryAgainBtn;       // Button to to try again
    
    @FXML
    private Label FailureReasonLabel; 
    
    @FXML
    private Label levelReachedLabel; 

     /**
     * Initialize the ResultController with outcome data.
     * Call this after loading the FXML.
     */
    public void setResult(String warningMessage, String failureReason, int levelReached) {
        warningMessageLabel.setText(warningMessage);
        FailureReasonLabel.setText(failureReason);
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
     
     

