import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class Ctrl0 implements Initializable {
    
    @FXML
    private Button buttonCar, buttonMoto, buttonThreads;

    @FXML
    private Canvas canvas;

    @FXML
    private HBox hbox;

    public Ctrl0Canvas ctrlCanvas = new Ctrl0Canvas();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Initialize canvas responsive size
        UtilsViews.parentContainer.heightProperty().addListener((observable, oldValue, newvalue) -> {
            updateCanvasSize();
        });
        UtilsViews.parentContainer.widthProperty().addListener((observable, oldValue, newvalue) -> {
            updateCanvasSize();
        });
    }

    public void drawingStart () {
        ctrlCanvas.start(canvas);
    }

    public void drawingStop () {
        ctrlCanvas.start(canvas);
    }

    public void updateCanvasSize () {
        // Start Canvas size
        canvas.setWidth(UtilsViews.parentContainer.getWidth());
        canvas.setHeight(UtilsViews.parentContainer.getHeight());
    }

    public void keyEvent (KeyEvent evt) {

        // Quan apretem "amunt" o "avall" el bus comença a moure's
        if (evt.getEventType() == KeyEvent.KEY_PRESSED) {
            if (evt.getCode() == KeyCode.UP) {
                ctrlCanvas.bus.direccio = "amunt";
            }
            if (evt.getCode() == KeyCode.DOWN) {
                ctrlCanvas.bus.direccio = "avall";
            }
        }

        // Quan deixem anar "amunt" o "avall" el bus para si la direcció coincideix
        if (evt.getEventType() == KeyEvent.KEY_RELEASED) {
            if (evt.getCode() == KeyCode.UP && ctrlCanvas.bus.direccio == "amunt") {
                ctrlCanvas.bus.direccio = "quiet";
            }
            if (evt.getCode() == KeyCode.DOWN && ctrlCanvas.bus.direccio == "avall") {
                ctrlCanvas.bus.direccio = "quiet";
            }
        }
    }

    @FXML
    public void actionCar () {
        ctrlCanvas.cotxe.posicionaY();
    }

    @FXML
    public void actionMoto () {
        ctrlCanvas.moto.posicionaY();
    }

    @FXML
    public void actionThreads () {
        Boolean paused = Main.futures.getPaused();
        if (paused) {
            buttonThreads.setText("Pause threads");
        } else {
            buttonThreads.setText("Run threads");
        }
        Main.futures.setPaused(!Main.futures.getPaused());
    }
}
