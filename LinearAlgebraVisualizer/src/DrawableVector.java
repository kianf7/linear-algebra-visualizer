import java.awt.*;
import java.awt.geom.AffineTransform;

public class DrawableVector extends Vector2D implements Drawable {
    private Color color;
    private String name;

    public DrawableVector(double x, double y, Color color, String name) {
        super(x,y);
        this.color = color;
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;
        Stroke oldStroke = g.getStroke();

        int endX = calculateEndX(originX, scale);
        int endY = calculateEndY(originY, scale);
        drawLine(g, originX, originY, endX, endY, color);
        drawArrowhead(g, originX, originY, endX, endY, color);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.white);
        g.drawString(name, endX + 5, endY - 5);

        g.setStroke(oldStroke);
    }

    public void drawUnlockedFromOrigin(Graphics2D g, int newOriginX, int newOriginY, int scale) {
        Stroke oldStroke = g.getStroke();
        int endX = calculateEndX(newOriginX, scale);
        int endY = calculateEndY(newOriginY, scale);

        drawLine(g, newOriginX, newOriginY, endX,endY, color);
        drawArrowhead(g, newOriginX, newOriginY, endX, endY, color);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.white);
        g.drawString(name, endX - 10, endY + 5);

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

    public int calculateEndX( int originX, int scale) {
        return (int) (originX + getX() * scale);
    }

    public int calculateEndY(int originY, int scale) {
        return (int) (originY - getY() * scale);
    }
}
