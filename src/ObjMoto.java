import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjMoto implements DrawingObj {

    // Definir atributs
    public double x = 0;
    public double y = 75;
    public Image img = new Image("./imgs/moto.png");

    // Animar
    public void run(Canvas cnv, double fps) {
        // Animar la posicio
        if (x > (cnv.getWidth() + 25)) {
            x = -25;
        } else {
            x = x + 50.0 / fps;
        }
    }

    // Dibuixar
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x, y, -25, 25);
    }

    // Canviar la posició Y
    public void posicionaY() {
        if (y == 75) {
            y = 50;
        } else {
            y = 75;
        }
    }
}
