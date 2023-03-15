import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DrawObjBus implements UtilsDrawObj {

    // Definir atributs
    public String direccio = "quiet";
    public double x = 0;
    public double y = 150;
    public Image img = new Image("./assets/bus.png");

    // Animar
    public void run(Canvas cnv, double fps) {
        // Animar la posicio X
        if (x < -25) {
            x = (int) cnv.getWidth();
        } else {
            x = x - 100.0 / fps;
        }
        // Animar la posicio Y
        if (direccio == "amunt" && y > 0) {
            y = y - 25 / fps;
        }
        if (direccio == "avall" && y < 200) {
            y = y + 25 / fps;
        }
        if (y < 0) { y = 0; }
        if (y > 200) { y = 200; }
    }

    // Dibuixar
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x, y, 25, 25);
    }
}
