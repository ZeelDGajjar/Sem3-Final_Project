/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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


}
