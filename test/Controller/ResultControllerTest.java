package Controller;

import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ResultControllerTest {

    public ResultController controller;

    @BeforeAll
    static void setupJFX() {
        new JFXPanel(); // Initializes JavaFX
    }

    @BeforeEach
    void init() {
        controller = new ResultController();
        controller.warningMessageLabel = new Label();
        controller.FailureReasonLabel = new Label();
        controller.levelReachedLabel = new Label();
        controller.TryAgainBtn = new Button();
    }


    @Test
    void testSetResult() {
        controller.setResult("Game Over", "You were hit", 5);

        assertEquals("Game Over", controller.warningMessageLabel.getText());
        assertEquals("You were hit", controller.FailureReasonLabel.getText());
        assertEquals("Level 5", controller.levelReachedLabel.getText());
    }

    @Test
    void testSetResultWithNulls() {
        controller.setResult(null, null, 2);

        assertEquals("No message", controller.warningMessageLabel.getText());
        assertEquals("No reason provided", controller.FailureReasonLabel.getText());
        assertEquals("Level 2", controller.levelReachedLabel.getText());
    }

}
