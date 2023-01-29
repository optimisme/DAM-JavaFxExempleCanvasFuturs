import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Controller0 implements Initializable {
    
    @FXML
    private Button buttonCar, buttonMoto, buttonThreads;

    @FXML
    private Canvas canvas;

    @FXML
    private HBox hbox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Initialize canvas responsive size
        UtilsViews.stage.heightProperty().addListener((observable, oldValue, newvalue) -> {
            updateCanvasSize();
        });
        UtilsViews.stage.widthProperty().addListener((observable, oldValue, newvalue) -> {
            updateCanvasSize();
        });

        // Start drawing loop
        Main.drawing.start(canvas);
        updateCanvasSize();
    }

    public void updateCanvasSize () {
        // Start Canvas size
        canvas.setWidth(UtilsViews.parentContainer.getWidth());
        canvas.setHeight(UtilsViews.parentContainer.getHeight() - hbox.getHeight());
    }

    @FXML
    public void actionCar () {
        Main.cotxe.posicionaY();
    }

    @FXML
    public void actionMoto () {
        Main.moto.posicionaY();
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
