package Controller;

import Model.CollisionUtil;
import Model.GameModel;
import Model.GameState;
import Model.Planet;
import Model.Projectile;
import Model.Trajectory;
import Model.Vector2;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameViewController implements Initializable {
    
    @FXML
    private Pane rootPane;
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

    private double speed;
    private double angle;
    private double rocketMass = 2.97e6;
    private int[] radius = {696340, 2440, 6052, 6371, 1737, 3390, 69911, 58232, 25559, 24764};
    private double[] planetMasses = {2E30, 3.30E23, 4.87E24, 5.97E24, 7.35E22, 6.42E23, 1.90E27, 5.68E26, 8.68E25, 1.02E26};
    private ArrayList<Planet> planets = new ArrayList<>();

    private MediaPlayer mediaPlayer;
    private boolean playMusic = true;
    private Timeline countdownTimeline;

    private Image imgPlay, imgMute;
    private GameState gameState = new GameState();
    private GameModel gameModel = new GameModel(gameState);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupPlanets();
        setupControls();
        startCountdownTimer(100); 
    }

    /**
     * Sets up images of planets and other things
     */
    private void setupPlanets() {
        List<Circle> circlePlanets = List.of(Sun, Mercury, Venus, Earth, Moon, Mars, Jupiter, Saturn, Uranus, Neptune);
        List<String> planetIds = List.of("Sun", "Mercury", "Venus", "Earth", "Moon", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");

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
            new Image(getClass().getResourceAsStream("/Images/Neptune.png"))
        };

        imgPlay = new Image("/Images/volume.jpg");
        imgMute = new Image("/Images/volumeMute.png");
        audioInput.setImage(imgMute);

        for (int i = 0; i < circlePlanets.size(); i++) {
            circlePlanets.get(i).setFill(new ImagePattern(images[i]));
            planets.add(new Planet(planetIds.get(i), circlePlanets.get(i).getCenterX(), circlePlanets.get(i).getCenterY(), radius[i], planetMasses[i]));
        }

        fire.setOpacity(0);
    }

    /**
     * Sets uo controls of the buttons and inputs
     */
    private void setupControls() {
        directionInput.valueProperty().addListener((obs, oldVal, newVal) -> {
            angle = newVal.doubleValue();
            rocket.setRotate(angle);
        });

        speedInput.textProperty().addListener((obs, oldVal, newVal) -> {
            try { speed = Double.parseDouble(newVal); } 
            catch (NumberFormatException e) { speed = 0; }
        });

        btnLaunch.setOnAction(e -> launchRocket());
        btnReset.setOnAction(e -> resetGame());
        audioInput.setOnMouseClicked(e -> toggleAudio());
    }

    /**
     * Manages the audio stop and play button
     */
    private void toggleAudio() {
        if (playMusic) {
            Media media = new Media(getClass().getResource("/Music/spaceMusic.mp3").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
            audioInput.setImage(imgPlay);
            playMusic = false;
        } else {
            audioInput.setImage(imgMute);
            if (mediaPlayer != null) mediaPlayer.stop();
            playMusic = true;
        }
    }

    /**
     * Resets game
     */
    private void resetGame() {
        rocket.setLayoutX(400);
        rocket.setLayoutY(300);
        rocket.setRotate(0);
        gameState.resetAttempts();
        updateScore();
        if (countdownTimeline != null) countdownTimeline.stop();
        startCountdownTimer(10);
    }

    /**
     * Updates score
     */
    private void updateScore() {
        scorePts.setText(String.valueOf(gameState.getAttempts()));
    }

    /**
     * Manages the launching of the rocket
     */
    private void launchRocket() {
        gameState.addAttempts();
        updateScore();

        double scale = 1E-5;
        double offsetX = 400, offsetY = 300;
        Vector2 initialPosition = new Vector2((rocket.getLayoutX() - offsetX) / scale, (rocket.getLayoutY() - offsetY) / scale);
        Projectile proj = new Projectile(rocketMass, initialPosition, speed, angle);
        List<Point2D> trajectoryPoints = proj.calculateTrajectory(planets);

        animateWithTimeline(rocket, trajectoryPoints, 5, scale, offsetX, offsetY, proj);
    }

    /**
     * Animates the launching of the rocket
     * @param node the given node
     * @param points the given points of path
     * @param totalTimeSeconds the given animation time
     * @param scale the given scale number to scale down the real data of planets
     * @param offsetX the given offset double of the rocket in x direction
     * @param offsetY the given offset double of the rocket in y direction
     * @param proj the projectile follow
     */
    private void animateWithTimeline(Node node, List<Point2D> points, double totalTimeSeconds, double scale, double offsetX, double offsetY, Projectile proj) {
        Timeline timeline = new Timeline();
        double dt = totalTimeSeconds / points.size();
        boolean[] hitPlanet = {false};
        Pane parentPane = (Pane) node.getParent();

        for (int i = 0; i < points.size(); i++) {
            Point2D p = points.get(i);
            double paneX = p.getX() * scale + offsetX;
            double paneY = p.getY() * scale + offsetY;

            KeyFrame kf = new KeyFrame(Duration.seconds(i * dt), e -> {
                node.setLayoutX(paneX);
                node.setLayoutY(paneY);

                Planet collided = CollisionUtil.checkAnyCollsion(proj, planets);
                if (collided != null && !hitPlanet[0]) {
                    hitPlanet[0] = true;
                    gameState.updateScore(100);
                    updateScore();
                    if (countdownTimeline != null) countdownTimeline.stop();
                }

                if (paneX < 0 || paneX > parentPane.getWidth() || paneY < 0 || paneY > parentPane.getHeight()) {
                    redirectToMissedFXML();
                }
            });
            timeline.getKeyFrames().add(kf);
        }

        timeline.setOnFinished(e -> {
            if (!hitPlanet[0]) redirectToMissedFXML();
        });

        node.toFront();
        node.setOpacity(1);
        timeline.setCycleCount(1);
        timeline.play();
    }

    /**
     * The time counter of the game
     * @param seconds the given seconds to work with 
     */
    private void startCountdownTimer(int seconds) {
        if (countdownTimeline != null) countdownTimeline.stop();
        timeLeftToLaunch.setText(String.valueOf(seconds));

        countdownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            int current = Integer.parseInt(timeLeftToLaunch.getText());
            if (current > 0) {
                timeLeftToLaunch.setText(String.valueOf(current - 1));
            } else {
                countdownTimeline.stop();
                redirectToMissedFXML();
            }
        }));
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
        countdownTimeline.play();
    }
    
    /**
     * Manages the result view
     */
    private void redirectToMissedFXML() {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("/View/ResultView.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.getLogger(MenuViewController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
