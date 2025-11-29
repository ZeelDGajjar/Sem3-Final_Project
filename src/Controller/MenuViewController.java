package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author zeelg
 */
public class MenuViewController implements Initializable {

   @FXML
    private Pane rootPane;
    @FXML
    private Circle moon;
    @FXML
    private Circle earth;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnInstructions;
    @FXML
    private Button btnExit;
    @FXML
    private Label purposeText;
    @FXML
    private Label title;
    @FXML
    private Label instructionsText;
    
    private boolean show = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image[] images = new Image[]{
            new Image(getClass().getResourceAsStream("/Images/Earth.png")),
            new Image(getClass().getResourceAsStream("/Images/Moon.png"))
        };
        
        earth.setFill(new ImagePattern(images[0]));
        moon.setFill(new ImagePattern(images[1]));
        
        purposeText.setText("");
        title.setText("");
        instructionsText.setText("");
        
        btnStart.setOnAction(e -> {
            try {
                Parent pane = FXMLLoader.load(getClass().getResource("/View/GameView.fxml"));
                rootPane.getChildren().setAll(pane);
            } catch (IOException ex) {
                System.getLogger(MenuViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        
        btnInstructions.setOnAction(e -> {
            if (show) {
                purposeText.setText("Your Goal: To **escape** to other planets before you've been 'Zombied'");
                title.setText("How to play?");
                instructionsText.setText("Enter your rocket's **speed** and **direction** \n "
                        + "Press **Launch** to fire toward the target planet \n"
                        + "Get to the planet to score and level up \n "
                        + "If you miss, just see the feedback and get back to try again");
                show = false;
                return;
            }
            
            purposeText.setText("");
            title.setText("");
            instructionsText.setText("");
            show = true;
        });
        
        btnExit.setOnAction(e -> {
            exit();
        });
    }
}
