import java.awt.*;
import java.awt.geom.AffineTransform;

public class DrawableVector extends Vector2D implements Drawable {
    private Color color;
    private String name;
    public int originX;
    public int originY;

    public DrawableVector(double x, double y, Color color, String name) {
        super(x,y);
        this.color = color;
        this.name = name;
    }

    public void setColor(Color color) {this.color = color;}
    public Color getColor() {return color;}

    public void setOriginX(int originX) {this.originX = originX;}
    public void setOriginY(int originY) {this.originY = originY;}

    public int getOriginX() {return originX;}
    public int getOriginY() {return originY;}

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        g.setColor(color);

        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        int endX = (int) (originX + scale * getX());
        int endY = (int) (originY - scale * getY());

        drawLine(g, originX, originY, endX, endY, color);
        drawArrowhead(g, originX, originY, endX, endY, color);
        g.setColor(Color.white);
        g.drawString(name, endX, endY);
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

    public int calculateEndX(DrawableVector v, int scale) {
        return (int) (originX + v.getX() * scale);}

    public int calculateEndY(DrawableVector v, int scale) {
        return (int) (originY - v.getY() * scale);}

    public void drawVectorSum(Graphics2D g, DrawableVector v1, DrawableVector v2, int windowWidth, int windowHeight, int scale) {
        // sets origin of v2 based of ending point of v1
        originX = calculateEndX(v1, scale);
        originY = calculateEndY(v1,scale);

        // calculates ending point of v2
        int endX_v2 = calculateEndX(v2, scale);
        int endY_v2 = calculateEndY(v2, scale);

        drawLine(g, originX, originY, endX_v2, endY_v2, v2.color);
        drawArrowhead(g, originX, originY, endX_v2, endY_v2, v2.color);

        // setting origin on ending point of v2
        setOriginX(endX_v2);
        setOriginY(endY_v2);

        // draws sum of v1, v2
        //drawLine(g, originX, originY, endX_v2, endY_v2, Color.BLUE);
        //drawArrowhead(g, originX, originY, endX_v2, endY_v2, Color.BLUE);
    }
}
