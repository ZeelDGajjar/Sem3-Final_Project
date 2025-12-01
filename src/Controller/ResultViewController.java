package Controller;


import java.net.URL;
import Model.Planet;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    private Pane rootPane;
    @FXML
    private Label warningMessageLabel;  

    @FXML
    public Button btnTryAgain;     
    
    @FXML
    private Label failureReasonLabel; 
    
    @FXML
    private Label levelReachedLabel;  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setResults();
       btnTryAgain.setOnAction(e -> {
           try {
                Parent pane = FXMLLoader.load(getClass().getResource("/View/GameView.fxml"));
                rootPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                System.getLogger(MenuViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
       });
    }
    
    /**
     * @param warningMessage  A short warning or conclusion message (e.g., "You were zombied!")
     * @param failureReason   Detailed explanation for failure (e.g., "You ran out of oxygen")
     * @param levelReached    The level number the player reached before losing
     */
    public void setResult(String warningMessage, String failureReason, int levelReached) {
        warningMessageLabel.setText(warningMessage != null ? warningMessage : "No message");
        failureReasonLabel.setText(failureReason != null ? failureReason : "No reason provided");
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
        Stage stage = (Stage) btnTryAgain.getScene().getWindow();
        stage.close();
    }
}
