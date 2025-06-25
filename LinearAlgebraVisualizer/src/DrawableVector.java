import java.awt.*;

public class DrawableVector extends Vector2D implements Drawable {
    private Color color;

    public DrawableVector(double x, double y, Color color) {
        super(x,y);
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;
        g.setColor(color);
        int endX= (int) (originX + scale * (int) getX());
        int endY= (int) (originY - scale * getY());
        g.drawLine(originX, originY, endX, endY);

    }
}
