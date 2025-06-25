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
        int endX= (int) (originX + scale * getX());
        int endY= (int) (originY - scale * getY());
        g.setStroke(new BasicStroke(3));
        g.drawLine(originX, originY, endX, endY);
    }
    public static void drawVectorSum(Graphics2D g, DrawableVector v1, DrawableVector v2, int windowWidth, int windowHeight, int scale) {

        // draw v1 starting from origin
        int originX_v1 = windowWidth / 2;
        int originY_v1 = windowHeight / 2;

        int endX_v1 = (int) (originX_v1 + scale * v1.getX());
        int endY_v1 = (int) (originY_v1 - scale * v1.getY());

        g.setColor(v1.color);
        g.setStroke(new BasicStroke(3));
        g.drawLine(originX_v1, originY_v1, endX_v1, endY_v1);

        // set v2 origin based on ending point of v1

        int endX_v2 = (int) (endX_v1 + scale * v2.getX());
        int endY_v2 = (int) (endY_v1 - scale * v2.getY());

        g.setColor(v2.color);
        g.setStroke(new BasicStroke(3));
        g.drawLine(endX_v1, endY_v1, endX_v2, endY_v2);


    }


}
