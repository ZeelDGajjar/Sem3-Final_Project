/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import Model.Planet;
import javafx.geometry.Point2D;

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
    private ImageView image;
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
    @FXML
    private Pane rocket;
    
    List<Circle> circlePlanets = List.of(Sun, Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranius, Neptune);
    double[] radius = {696340, 2440, 6052, 6371, 3390, 69911, 58232, 25559, 24764};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Adding images in the circles
        for (int i = 0; i < circlePlanets.length; i++) {
            String path = "/Images/" + circlePlanets.get(i) + ".png";
            Image planetImg = new Image(getClass().getResourceAsStream(path));
            circlePlanets.get(i).setFill(new ImagePattern(planetImg));
            
            Point2D position;
            position = new Point2D(circlePlanets.get(i).centerXProperty(), circlePlanets.get(i).centerYProperty());
            Planet planet = new Planet(planet, radius[i], position);
        }
        
    }    
    
    @FXML
    private void ResetBtnClicked(ActionEvent event) {
        speedInput.setText("");
        directionInput.setValue(0);
        
        Image img = new Image(getClass().getResourceAsStream("/Images/Earth.png"));
        Earth.setFill(new ImagePattern(img));

    }

    @FXML
    private void DirectionDragDropped(DragEvent event) {
    }

    @FXML
    private void LaunchBtnClicked(ActionEvent event) {
    }
}