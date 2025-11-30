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
    static void initJFX() {
        // Initializes JavaFX environment
        new JFXPanel();
    }

    @BeforeEach
    void setUp() {
        controller = new ResultController();

        controller.warningMessageLabel = new Label();
        controller.FailureReasonLabel = new Label();
        controller.levelReachedLabel = new Label();
        controller.TryAgainBtn = new Button("Try Again");
    }

    // TEST setResult()
    @Test
    void testSetResult_NullValues() {
        controller.setResult(null, null, 2);

        assertEquals("No message", controller.warningMessageLabel.getText());
        assertEquals("No reason provided", controller.FailureReasonLabel.getText());
        assertEquals("Level 2", controller.levelReachedLabel.getText());
    }
    
    @Test
    void testSetResult_NormalValues() {
        controller.setResult("Warning!", "Bad move", 5);

        assertEquals("Warning!", controller.warningMessageLabel.getText());
        assertEquals("Bad move", controller.FailureReasonLabel.getText());
        assertEquals("Level 5", controller.levelReachedLabel.getText());
    }

    // TEST handleTryAgain()

    @Test
    void testHandleTryAgain_ClosesWindow() {
        // Create a stage to simulate the window
        Stage testStage = new Stage();
        Scene scene = new Scene(controller.TryAgainBtn);
        testStage.setScene(scene);
        testStage.show();

        assertTrue(testStage.isShowing(), "Stage should be showing before clicking");

        // Assign button to controller so method can find the window
        controller.TryAgainBtn = (Button) scene.getRoot();

        // Fire event
        controller.handleTryAgain(new ActionEvent());

        // After request to close window
        assertFalse(testStage.isShowing(), "Stage should be closed after clicking Try Again");
    }
}
