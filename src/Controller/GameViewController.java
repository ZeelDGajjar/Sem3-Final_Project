/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Planet;
import Model.Projectile;
import Model.Trajectory;
import Model.Vector2;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

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
    private Circle uranusPath;
    @FXML
    private Circle neptunePath;
    @FXML
    private Circle venusPath;
    @FXML
    private Circle mercuryPath;
    @FXML
    private Circle earthPath;
    @FXML
    private Circle moonPath;
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
    private Circle Uranus;
    @FXML
    private Circle Neptune;
    @FXML
    private Circle Moon;
    @FXML
    private Pane rocket;
    @FXML
    private Pane fire;
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
    
    int[] radius = {696340, 2440, 6052, 6371, 1737, 3390, 69911, 58232, 25559, 24764};
    double[] planetMasses = {2E30, 3.30E23, 4.87E24, 5.97E24, 7.35E22, 6.42E23, 1.90E27, 5.68E26, 8.68E25, 1.02E26};
    double rocketMass = 2970000;
    
    double speed;
    double angle;
    ArrayList<Planet> planets = new ArrayList<Planet>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Circle> circlePlanets = List.of(Sun, Mercury, Venus, Earth, Moon, Mars, Jupiter, Saturn, Uranus, Neptune);
        List<Circle> circlePlanetPaths = List.of(mercuryPath, venusPath, earthPath, moonPath, marsPath, jupiterPath, saturnPath, uranusPath, neptunePath);
        
        Image[] images = new Image[]{
            new Image(getClass().getResourceAsStream("/Images/Sun.png")),
            new Image(getClass().getResourceAsStream("/Images/Mercury.png")),
            new Image(getClass().getResourceAsStream("/Images/Venus.png")),
            new Image(getClass().getResourceAsStream("/Images/Earth.jpg")),
            new Image(getClass().getResourceAsStream("/Images/Moon.png")),
            new Image(getClass().getResourceAsStream("/Images/Mars.png")),
            new Image(getClass().getResourceAsStream("/Images/Jupiter.png")),
            new Image(getClass().getResourceAsStream("/Images/Saturn.png")),
            new Image(getClass().getResourceAsStream("/Images/Uranus.png")),
            new Image(getClass().getResourceAsStream("/Images/Neptune.png")),
        };
        
        for (int i = 0; i < 10; i++) {
            circlePlanets.get(i).setFill(new ImagePattern(images[i]));
            planets.add(new Planet(circlePlanets.get(i).getId(), circlePlanets.get(i).getCenterX(), circlePlanets.get(i).getCenterY(), radius[i], planetMasses[i]));
        }
        
        fire.setOpacity(0);
        
        // Buttons
        directionInput.valueProperty().addListener((observable, oldVal, newVal) -> {
           double dx = rocket.getLayoutX() - Earth.getCenterX();
            double dy = rocket.getLayoutY() - Earth.getCenterY();
            
            angle = Math.toDegrees(Math.atan2(dy, dx));
            
            rocket.setRotate(angle + newVal.doubleValue());
        });
        
        speedInput.alignmentProperty().addListener((observable, oldVal, newVal) -> {
            speed = Double.parseDouble(newVal.toString());
        });
        
        btnLaunch.setOnAction(e -> {
            Vector2 initialPosition = new Vector2(rocket.getLayoutX(), rocket.getLayoutY());
            
            Projectile proj = new Projectile(rocketMass, initialPosition, speed, angle);
            proj.calculateTrajectory(planets);
        });
    }
}
