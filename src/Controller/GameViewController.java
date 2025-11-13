/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author zeelg
 */
public class GameViewController implements Initializable {

    @FXML
    private Text zomombieLevel;
    @FXML
    private Text scorePts;
    @FXML
    private Text targetPlanet;
    @FXML
    private Circle marsPath;
    @FXML
    private Circle jupiterPath;
    @FXML
    private Circle saturnPath;
    @FXML
    private Circle uraniusPath;
    @FXML
    private Circle neptunePath;
    @FXML
    private Circle venusPath;
    @FXML
    private Circle earthPath;
    @FXML
    private Circle moonPath;
    @FXML
    private Circle mercuryPath;
    @FXML
    private Circle Earth;
    @FXML
    private Circle Sun;
    @FXML
    private Circle Venus;
    @FXML
    private Circle Mercury;
    @FXML
    private Circle Mars;
    @FXML
    private Circle Jupiter;
    @FXML
    private Circle Saturn;
    @FXML
    private Circle Uranius;
    @FXML
    private Circle Neptune;
    @FXML
    private Circle Moon;
    @FXML
    private Button btnReset;
    @FXML
    private ImageView audioInput;
    @FXML
    private Text timeLeftToLaunch;
    @FXML
    private TextField speedInput;
    @FXML
    private Slider directionInput;
    @FXML
    private Button btnLaunch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(getClass().getResource("/Images/Earth.png").toExternalForm());
        Earth.setFill(new ImagePattern(image));
    }    

    @FXML
    private void ResetBtnClicked(ActionEvent event) {
    }

    @FXML
    private void LaunchBtnClicked(ActionEvent event) {
    }
    
}
