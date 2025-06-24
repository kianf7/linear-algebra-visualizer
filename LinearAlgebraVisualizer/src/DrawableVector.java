import java.awt.*;

public class DrawableVector extends Vector2D implements Drawable {
    private Color color;

    public DrawableVector(double x, double y, Color color) {
        super(x,y);
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        // TODO: FILL
    }
}
