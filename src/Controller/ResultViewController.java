package Controller;


import java.net.URL;
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
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Controller for the result screen.
 * Displays outcome of the game (score, zombied status, and explanation)
 * and allows the user to return to the main menu.
 * @author Vedika
 */
public class ResultViewController implements Initializable {
    @FXML
    private Label warningMessageLabel;  

    @FXML
    public Button TryAgainBtn;     
    
    @FXML
    private Label FailureReasonLabel; 
    
    @FXML
    private Label levelReachedLabel;  
    
    @FXML
    private ImageView backgroundImageView;
    
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
       // Load background image
        try {
            Image bgImage = new Image(getClass().getResourceAsStream("/Images/ResultBackground.png"));
            backgroundImageView.setImage(bgImage);
        } catch (Exception e) {
            System.err.println("Failed to load background image");
            e.printStackTrace();
        }
    }
}
