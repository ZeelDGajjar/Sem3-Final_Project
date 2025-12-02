package Controller;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller for the result screen.
 * Displays outcome of the game (score, zombied status, and explanation)
 * and allows the user to return to the main menu.
 */
public class ResultViewController implements Initializable {

    @FXML
    private Pane rootPane;

    @FXML
    private Label warningMessageLabel;

    @FXML
    private Label failureReasonLabel;

    @FXML
    private Label levelReachedLabel;

    @FXML
    private Button btnTryAgain;

    @FXML
    private ImageView backgroundImageView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Load background image
        try {
            Image bgImage = new Image(
                getClass().getResourceAsStream("/Images/ResultBackground.png")
            );
            backgroundImageView.setImage(bgImage);
        } catch (Exception e) {
            System.err.println("Failed to load background image");
            e.printStackTrace();
        }

        // Try again button reloads GameView
        btnTryAgain.setOnAction(e -> {
            try {
                Parent pane = FXMLLoader.load(getClass().getResource("/View/GameView.fxml"));
                rootPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Sets the game result text fields.
     */
    public void setResult(String warningMessage, String failureReason, int levelReached) {
        warningMessageLabel.setText(warningMessage != null ? warningMessage : "No message");
        failureReasonLabel.setText(failureReason != null ? failureReason : "No reason provided");
        levelReachedLabel.setText("Level " + levelReached);
    }

    /**
     * Closes the result window (if used as popup).
     */
    @FXML
    public void handleTryAgain(ActionEvent event) {
        Stage stage = (Stage) btnTryAgain.getScene().getWindow();
        stage.close();
    }
}
