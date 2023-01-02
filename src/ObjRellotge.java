import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class ObjRellotge implements DrawingObj {

    // Definir atributs
    private int hores = 18;
    private int minuts = 45;
    private int segons = 8;

    // Getters i setters
    public void     setHores(int hores)         { this.hores = hores; }
    public void     setMinuts(int minuts)       { this.minuts = minuts; }
    public void     setSegons(int segons)       { this.segons = segons; }

    // Animar
    public void run(Canvas cnv, double fps) { 
        // El rellotge calcula els canvis 
        // al procés paral·lel "getRunnableRellotge"
    }

    // Dibuixar el rellotge circular
    public void draw(GraphicsContext gc) {

        int canvasWidth = (int) gc.getCanvas().getWidth();

        int x = canvasWidth - 50;
        int y = 35;
        int diameter = 50;
        int radius = diameter / 2;
        int radiusHalf = radius / 2;
        double sub = Math.PI / 2;
        double radians = 0;

        gc.setFill(Color.ORANGE);
        gc.fillOval(x - radius, y - radius, diameter, diameter);

        // Dibuixar les hores
        radians = Math.toRadians(hores * 30) - sub;
        drawCircleLine(gc, Color.BLUE, 5, x, y, radiusHalf, radians);

        // Dibuixar els minuts
        radians = Math.toRadians(minuts * 6) - sub;
        drawCircleLine(gc, Color.BLACK, 2, x, y, radius, radians);

        // Dibuixar els segons
        radians = Math.toRadians(segons * 6) - sub;
        drawCircleLine(gc, Color.RED, 1, x, y, radius, radians);
    }

    private void drawCircleLine (GraphicsContext gc, Color color, int size, int x, int y, int radius, double radians) {
        gc.setStroke(color);
        gc.setLineWidth(size);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.beginPath();
        gc.moveTo(x, y);
        gc.lineTo(x + radius * Math.cos(radians), y + radius * Math.sin(radians));
        gc.stroke();
        gc.setLineCap(StrokeLineCap.SQUARE);
    }
}
