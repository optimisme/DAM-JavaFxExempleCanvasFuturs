import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DrawObjCotxe implements UtilsDrawObj {

    // Definir atributs
    public double x = 0;
    public double y = 100;
    public double destiX = 0;
    public Image img = new Image("./assets/cotxe.png");

    // Animar la posició del cotxe cap al destí
    public void run(Canvas cnv, double fps) {
        if (x < destiX) {
            x = x + 100.0 / fps;
        }
        if (x > destiX) {
            x = x - 100.0 / fps;
        }
    }

    // Dibuixar
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x, y, 25, 25);
    }

    // Canviar la posició Y del cotxe
    public void posicionaY() {
        if (y == 100) {
            y = 118;
        } else {
            y = 100;
        }
    }
}
