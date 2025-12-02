package Controller;

<<<<<<< HEAD
=======
import Model.CollisionUtil;
import Model.GameModel;
import Model.GameState;
import Model.Planet;
import Model.Projectile;
import Model.Vector2;
import java.io.IOException;
>>>>>>> 34e517bcfae844cb6d7b9f3b2c14267c01c61383
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
<<<<<<< HEAD
import javafx.event.ActionEvent;
=======
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
>>>>>>> 34e517bcfae844cb6d7b9f3b2c14267c01c61383
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
<<<<<<< HEAD

=======
import javafx.geometry.Bounds;
import javafx.scene.Parent;
>>>>>>> 34e517bcfae844cb6d7b9f3b2c14267c01c61383
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
import Model.Planet;
import Model.Vector2;

/**
 * Controller for game view.
 * Updated so the rocket uses a simple arcade projectile motion in UI coordinates (pixels),
 * moves in the direction it's pointed, stops when it reaches a planet, and advances
 * the "zombied level" over time (1 -> 2 -> max).
 */
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
    private ImageView rocket;
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
<<<<<<< HEAD
    @FXML
   // private Pane rocket;
    
    List<Circle> circlePlanets = List.of(Sun, Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranius, Neptune);
    double[] radius = {696340, 2440, 6052, 6371, 3390, 69911, 58232, 25559, 24764};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
            Image planetImg = new Image(getClass().getResourceAsStream(path));
            circlePlanets.get(i).setFill(new ImagePattern(planetImg));
            
            Vector2 position;
            position = new Vector2(circlePlanets.get(i).centerXProperty(), circlePlanets.get(i).centerYProperty());
            Planet planet = new Planet(planet, radius[i], position);
        }
    }
    
    @FXML
    private void ResetBtnClicked(ActionEvent event) {
=======

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

    private Timeline flightTimeline;

    private Timeline zombiedLevelTimeline;

    private static final double GRAVITY_PIXELS = 300.0;
    private static final double STEP_DT = 0.02; 
    private static final double SPEED_UI_SCALE = 1.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupPlanets();
        setupControls();

        gameModel.setPlanets(planets);
        gameModel.startLevel();
        updateZomobieUI(); 

        startZombiedLevelCycle(12); 

        startCountdownTimer(100);
    }

    /**
     * Sets up images of planets and keeps planet positions in UI coordinates (pixels).
     *
     * Important: we store planet centers and radii in the Planet model in UI units so collision checks
     * are straightforward (no model/UI scale conversions).
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

        planets.clear();
        for (int i = 0; i < circlePlanets.size(); i++) {
            Circle c = circlePlanets.get(i);
            c.setFill(new ImagePattern(images[i]));

            Bounds b = c.getBoundsInParent();
            double uiCenterX = b.getMinX() + b.getWidth() / 2.0;
            double uiCenterY = b.getMinY() + b.getHeight() / 2.0;

            double radiusPixels = c.getRadius();

            planets.add(new Planet(planetIds.get(i), uiCenterX, uiCenterY, (int) radiusPixels, planetMasses[i]));
        }

        fire.setOpacity(0);
    }

    /**
     * Sets up UI controls and listeners.
     */
    private void setupControls() {
        directionInput.valueProperty().addListener((obs, oldVal, newVal) -> {
            angle = newVal.doubleValue();
            rocket.setRotate(angle);
        });

        speedInput.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                speed = Double.parseDouble(newVal);
            } catch (NumberFormatException e) {
                speed = 0;
            }
        });

        btnLaunch.setOnAction(e -> launchRocket());
        btnReset.setOnAction(e -> resetGame());
        audioInput.setOnMouseClicked(e -> toggleAudio());
    }

    /**
     * Manages audio toggle.
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
     * Resets the visible rocket and timers.
     */
    private void resetGame() {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("/View/GameView.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            System.getLogger(MenuViewController.class.getName()).log(java.lang.System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    /**
     * Stops the transition
    */
    private void stopFlight() {
        if (flightTimeline != null) {
            flightTimeline.stop();
            flightTimeline = null;
        }
    }
    
    /**
     * Updates the score
     */
    private void updateScore() {
        scorePts.setText(String.valueOf(gameState.getAttempts()));
    }

    /**
     * Launches the rocket. Uses simple arcade projectile physics in UI coordinates (pixels).
     * The rocket will stop as soon as it intersects any planet (based on circle radius).
     */
    private void launchRocket() {
        stopFlight();

        gameState.addAttempts();
        updateScore();

        Bounds rb = rocket.getBoundsInParent();
        double uiStartX = rb.getMinX() + rb.getWidth() / 2.0;
        double uiStartY = rb.getMinY() + rb.getHeight() / 2.0;

        double speedPx = speed * SPEED_UI_SCALE;

        double angleDeg = rocket.getRotate();
        double angleRad = Math.toRadians(angleDeg);

        double vx = speedPx * Math.cos(angleRad);          
        double vy = -speedPx * Math.sin(angleRad);         

        final double[] posX = {uiStartX};
        final double[] posY = {uiStartY};
        final double[] velX = {vx};
        final double[] velY = {vy};

        Pane parentPane = (Pane) rocket.getParent();

        flightTimeline = new Timeline(new KeyFrame(Duration.seconds(STEP_DT), ev -> {
            velY[0] += GRAVITY_PIXELS * STEP_DT; // gravity pulls down (positive y)
            posX[0] += velX[0] * STEP_DT;
            posY[0] += velY[0] * STEP_DT;

            double rocketCenterOffsetX = rocket.getBoundsInParent().getWidth() / 2.0;
            double rocketCenterOffsetY = rocket.getBoundsInParent().getHeight() / 2.0;
            rocket.setLayoutX(posX[0] - rocketCenterOffsetX);
            rocket.setLayoutY(posY[0] - rocketCenterOffsetY);

            Projectile tempProj = new Projectile(rocketMass, new Vector2(posX[0], posY[0]), 0, 0);
            tempProj.setPosition(new Vector2(posX[0], posY[0]));

            Planet collided = CollisionUtil.checkAnyCollsion(tempProj, planets);
            if (collided != null) {
                stopFlight();

                double dx = posX[0] - collided.getX();
                double dy = posY[0] - collided.getY();
                double dist = Math.sqrt(dx * dx + dy * dy);
                double targetDist = collided.getRadius() + Math.max(rocket.getBoundsInParent().getWidth(), rocket.getBoundsInParent().getHeight()) / 2.0;

                double snapX, snapY;
                if (dist == 0) {
                    snapX = collided.getX();
                    snapY = collided.getY();
                } else {
                    snapX = collided.getX() + dx / dist * targetDist;
                    snapY = collided.getY() + dy / dist * targetDist;
                }

                rocket.setLayoutX(snapX - rocketCenterOffsetX);
                rocket.setLayoutY(snapY - rocketCenterOffsetY);

                gameState.updateScore(100);
                updateScore();
                if (countdownTimeline != null) countdownTimeline.stop();
            }

            if (posX[0] < -1000 || posX[0] > parentPane.getWidth() + 1000 || posY[0] < -1000 || posY[0] > parentPane.getHeight() + 1000) {
                stopFlight();
                redirectToMissedFXML();
            }
        }));
        flightTimeline.setCycleCount(Timeline.INDEFINITE);
        flightTimeline.play();
    }

    /**
     * Start a repeating timeline that advances zombied level every intervalSeconds.
     * Sequence: start at current level (likely 1), after interval -> advanceLevel() (becomes 2),
     * after interval -> advanceLevel() again (could reach final -> gameModel will set zombied).
     */
    private void startZombiedLevelCycle(int intervalSeconds) {
        if (zombiedLevelTimeline != null) zombiedLevelTimeline.stop();

        zombiedLevelTimeline = new Timeline(new KeyFrame(Duration.seconds(intervalSeconds), ev -> {
            if (gameModel.isZombied()) {
                if (zomombieLevel != null) zomombieLevel.setText("MAX");
                zombiedLevelTimeline.stop();
                return;
            }

            gameModel.advanceLevel();

            updateZomobieUI();

            if (gameModel.isZombied()) {
                if (zomombieLevel != null) zomombieLevel.setText("MAX");
                zombiedLevelTimeline.stop();
            }
        }));
        zombiedLevelTimeline.setCycleCount(Timeline.INDEFINITE);
        zombiedLevelTimeline.play();
    }

    /**
     * Updates zomobieLevel Text and targetPlanet Text from model.
     */
    private void updateZomobieUI() {
        if (zomombieLevel != null) {
            zomombieLevel.setText(String.valueOf(gameModel.getCurrentLevel()));
        }
        if (targetPlanet != null) {
            Planet t = gameModel.getTargetPlanet();
            if (t != null) targetPlanet.setText(t.getName());
        }
    }

    /**
     * The time counter of the game
     *
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
            System.getLogger(MenuViewController.class.getName()).log(java.lang.System.Logger.Level.ERROR, (String) null, ex);
        }
>>>>>>> 34e517bcfae844cb6d7b9f3b2c14267c01c61383
    }

    @FXML
    private void DirectionDragDropped(DragEvent event) {
        
    }

    @FXML
    private void LaunchBtnClicked(ActionEvent event) {
    }
}