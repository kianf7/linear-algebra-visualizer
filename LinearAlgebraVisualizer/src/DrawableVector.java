import java.awt.*;

public class DrawableVector extends Vector2D implements Drawable {
    private int scale;
    private Color color;

    public DrawableVector(double x, double y, int scale, Color color) {
        super(x,y);
        this.scale = scale;
        this.color = color;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight) {
        // TODO: FILL
    }
}
