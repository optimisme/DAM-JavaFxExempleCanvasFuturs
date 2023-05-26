import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DrawObjCarretera implements UtilsDrawObj {

    // Animar
    public void run(Canvas cnv, double fps) {
        // No cal animar res
    }

    // Dibuixar
    public void draw(GraphicsContext gc) {

        int canvasWidth = (int) gc.getCanvas().getWidth();

        // Fons carretera (rectangle negre)
        gc.setFill(Color.BLACK);
        gc.rect(0, 75, canvasWidth, 50);
        gc.fill();

        // Linies blanques
        gc.setStroke(Color.WHITE);
        gc.beginPath();
        gc.moveTo(0, 100);
        for (int cnt = 0; cnt < canvasWidth; cnt += 25) {
            gc.lineTo(cnt + 15, 100);
            gc.moveTo(cnt + 25, 100);
            gc.stroke();
        }

        // Fons carril bus i lletres BUS
        gc.setFill(Color.GREEN);
        gc.rect(0, 150, canvasWidth, 25);
        gc.fill();

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 20));
        gc.fillText("BUS", 10, 170);

        // Text explicatiu
        gc.setFill(Color.GRAY);
        gc.setFont(new Font("Arial", 12));
        gc.fillText("Tecles amunt i avall mouen el BUS\nBotons canvien Y de cotxe/moto\nProper destí del cotxe " + Main.ctrl0.ctrlCanvas.cotxe.destiX, 10, 200);
    }
}
