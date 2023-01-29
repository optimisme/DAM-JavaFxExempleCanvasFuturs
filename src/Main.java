import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
 
public class Main extends Application {

    public static Futures futures = new Futures();
    public static Drawing drawing = new Drawing();
    public static ArrayList<DrawingObj> drawingList = new ArrayList<>();

    public static ObjCarretera carretera = new ObjCarretera();
    public static ObjCotxe cotxe = new ObjCotxe();
    public static ObjMoto moto = new ObjMoto();
    public static ObjBus bus = new ObjBus();
    public static ObjRellotge rellotge = new ObjRellotge();

    public static void main(String[] args) {
 
        // Crear objectes
        drawingList.add(carretera);
        drawingList.add(cotxe);
        drawingList.add(moto);
        drawingList.add(bus);
        drawingList.add(rellotge);

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

        Scene scene = new Scene(UtilsViews.parentContainer);
        scene.addEventFilter(KeyEvent.ANY, keyEvent -> { keyEvent(keyEvent); });

        stage.setScene(scene);
        stage.onCloseRequestProperty(); // Call close method when closing window
        stage.setTitle("Exemples Canvas Futurs");
        stage.setMinWidth(windowWidth);
        stage.setMinHeight(windowHeight);
        stage.show();

        // Image icon = new Image("file:./assets/icon.png");
        // stage.getIcons().add(icon);

        futures.start();
    }

    @Override
    public void stop() {
        // Aturar el bucle de dibuix
        drawing.stop(); 

        // Esperar que acabin els futurs
        futures.stop();

        // Acabar l'aplicació
        System.out.println("Acabar");
    }

    public void keyEvent (KeyEvent evt) {

        // Quan apretem "amunt" o "avall" el bus comença a moure's
        if (evt.getEventType() == KeyEvent.KEY_PRESSED) {
            if (evt.getCode() == KeyCode.UP) {
                bus.direccio = "amunt";
            }
            if (evt.getCode() == KeyCode.DOWN) {
                bus.direccio = "avall";
            }
        }

        // Quan deixem anar "amunt" o "avall" el bus para si la direcció coincideix
        if (evt.getEventType() == KeyEvent.KEY_RELEASED) {
            if (evt.getCode() == KeyCode.UP && bus.direccio == "amunt") {
                bus.direccio = "quiet";
            }
            if (evt.getCode() == KeyCode.DOWN && bus.direccio == "avall") {
                bus.direccio = "quiet";
            }
        }
    }
}