import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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

        Image icon = new Image("file:./assets/icon.png");
        stage.getIcons().add(icon);

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

    public VBox buildInterface(Stage primaryStage) {

        double hboxHeight = 50;

        // Definir la divisió vertical
        VBox vbox = new VBox(0);
        vbox.setAlignment(Pos.TOP_CENTER);

            // Definir l'area de dibuix
            Canvas canvas = new Canvas(100, 100);
            drawing.start(canvas);
            canvas.heightProperty().bind(vbox.heightProperty().subtract(hboxHeight));
            canvas.widthProperty().bind(vbox.widthProperty());
            
            // Definir l'espai horitzontal dels botons
            HBox hbox = new HBox(8);
            hbox.setAlignment(Pos.CENTER);
            hbox.setMinHeight(hboxHeight);
            hbox.setMaxHeight(hboxHeight);

                // Definir el boto
                Button buttonC = new Button();
                buttonC.setText("Cotxe Y");
                buttonC.setOnAction(actionEvent ->  {
                    cotxe.posicionaY(); 
                });

                // Definir el boto
                Button buttonM = new Button();
                buttonM.setText("Moto Y");
                buttonM.setOnAction(actionEvent ->  {
                    moto.posicionaY(); 
                });

                // Definir el boto
                Button buttonP = new Button();
                buttonP.setText("Pausa threads");
                buttonP.setOnAction(actionEvent ->  {
                    Boolean paused = futures.getPaused();
                    if (paused) {
                        buttonP.setText("Pausa threads");
                    } else {
                        buttonP.setText("Reprendre threads");
                    }
                    futures.setPaused(!futures.getPaused());
                });

            hbox.getChildren().addAll(buttonC, buttonM, buttonP);

        vbox.getChildren().addAll(canvas);
        vbox.getChildren().addAll(hbox);
        VBox.setVgrow(canvas, Priority.ALWAYS);
        VBox.setVgrow(hbox, Priority.NEVER);

        //vbox.setBackground(new Background(new BackgroundFill(Color.BLUE,null,null)));
        //hbox.setBackground(new Background(new BackgroundFill(Color.ORANGE,null,null)));

        return vbox;

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