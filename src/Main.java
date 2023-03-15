import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
 
public class Main extends Application {

    public static Ctrl0 ctrl0;
    public static Futures futures = new Futures();

    public static void main(String[] args) {

        // Iniciar app JavaFX   
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        final int windowWidth = 500;
        final int windowHeight = 400;

        UtilsViews.stage = stage;
        UtilsViews.parentContainer.setStyle("-fx-font: 14 arial;");
        UtilsViews.addView(getClass(), "View0", "./assets/view0.fxml");
        ctrl0 = (Ctrl0) UtilsViews.getController("View0");

        Scene scene = new Scene(UtilsViews.parentContainer);
        scene.addEventFilter(KeyEvent.ANY, keyEvent -> { ctrl0.keyEvent(keyEvent); });

        stage.setScene(scene);
        stage.onCloseRequestProperty(); // Call close method when closing window
        stage.setTitle("Exemples Canvas Futurs");
        stage.setMinWidth(windowWidth);
        stage.setMinHeight(windowHeight);
        stage.show();

        // Start drawing loop
        ctrl0.startDrawing();

        // Add icon only if not Mac
        if (!System.getProperty("os.name").contains("Mac")) {
            Image icon = new Image("file:./assets/icon.png");
            stage.getIcons().add(icon);
        }

        futures.start();
    }

    @Override
    public void stop() {
        // Aturar el bucle de dibuix
        ctrl0.stopDrawing();

        // Esperar que acabin els futurs
        futures.stop();

        // Acabar l'aplicació
        System.out.println("Acabar");
    }
}