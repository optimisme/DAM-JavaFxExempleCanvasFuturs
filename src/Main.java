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

    private Futures futures = new Futures();
    private Drawing drawing = new Drawing();
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
    public void start(Stage primaryStage) {

        int windowHeight = 350;
        int windowWidth = 300;

        // Construir interficie
        VBox root = buildInterface(primaryStage);

        // Definir escena
        Scene  scene = new Scene(root);
        scene.addEventFilter(KeyEvent.ANY, keyEvent -> { keyEvent(keyEvent); });

        // Iniciar finestra d'app
        primaryStage.setTitle("Carretera");
        primaryStage.onCloseRequestProperty();
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(true);
        primaryStage.setHeight(windowHeight);
        primaryStage.setWidth(windowWidth);
        primaryStage.setMinHeight(windowHeight);
        primaryStage.setMinWidth(windowWidth);
        primaryStage.heightProperty().addListener((observable, oldValue, newvalue) -> {
            double titleHeight = primaryStage.getHeight() - scene.getHeight();
            double rootHeight = primaryStage.getHeight() - titleHeight;
            root.setPrefHeight(rootHeight);
        });

        // Definir icona d'app
        Image icon = new Image("file:./imgs/icon.png");
        primaryStage.getIcons().add(icon);

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