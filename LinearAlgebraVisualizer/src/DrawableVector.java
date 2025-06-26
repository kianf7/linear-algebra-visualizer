import java.awt.*;
import java.awt.geom.AffineTransform;

public class DrawableVector extends Vector2D implements Drawable {
    private Color color;

    public DrawableVector(double x, double y, Color color) {
        super(x,y);
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;
        g.setColor(color);
        int endX= (int) (originX + scale * getX());
        int endY= (int) (originY - scale * getY());
        drawLine(g, originX, originY, endX, endY, Color.red);
        drawArrowhead(g, originX, originY, endX, endY, Color.red);
    }

    private static void drawLine(Graphics2D g, int x1, int y1, int x2, int y2, Color color) {
        g.setColor(color);
        g.setStroke(new BasicStroke(2.5F));
        g.drawLine(x1, y1, x2, y2);
    }
    public static void drawArrowhead(Graphics2D g, int x1, int y1, int x2, int y2, Color color) {
        int arrowLength = 18;
        int arrowWidth = 12;

        Polygon arrowhead = new Polygon();
        arrowhead.addPoint(0,0);
        arrowhead.addPoint(-arrowWidth / 2, -arrowLength);
        arrowhead.addPoint(arrowWidth / 2, -arrowLength);

        double angle = Math.atan2(y2-y1, x2-x1);

        AffineTransform tx = new AffineTransform();
        tx.translate(x2,y2);
        tx.rotate(angle);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(x2,y2);
        g2d.rotate(angle - Math.PI/2);
        g2d.setColor(color);
        g2d.fill(arrowhead);
        g2d.dispose();
    }

    public static void drawVectorSum(Graphics2D g, DrawableVector v1, DrawableVector v2, int windowWidth, int windowHeight, int scale) {

        // draw v1 starting from origin
        int originX_v1 = windowWidth / 2;
        int originY_v1 = windowHeight / 2;

        int endX_v1 = (int) (originX_v1 + scale * v1.getX());
        int endY_v1 = (int) (originY_v1 - scale * v1.getY());

        drawLine(g, originX_v1, originY_v1, endX_v1, endY_v1, v1.color);
        drawArrowhead(g, originX_v1, originY_v1, endX_v1, endY_v1, v1.color);

        // set v2 origin based on ending point of v1
        int endX_v2 = (int) (endX_v1 + scale * v2.getX());
        int endY_v2 = (int) (endY_v1 - scale * v2.getY());

        drawLine(g, endX_v1, endY_v1, endX_v2, endY_v2, v2.color);
        drawArrowhead(g, endX_v1, endY_v1, endX_v2, endY_v2, v2.color);

        // draws sum of v1, v2
        drawLine(g, originX_v1, originY_v1, endX_v2, endY_v2, Color.BLUE);
        drawArrowhead(g, originX_v1, originY_v1, endX_v2, endY_v2, Color.BLUE);
    }
}
