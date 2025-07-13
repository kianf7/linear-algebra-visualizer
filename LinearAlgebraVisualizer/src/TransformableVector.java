import java.awt.*;

public class TransformableVector extends DrawableVector implements Transformable {

    public TransformableVector(double x, double y, Color color, String name) {
        super(x, y, color, name);
    }

    public boolean transformationIsZero(Graphics2D g, Matrix2D transformation, int originX, int originY) {
        if (transformation.isZero()) {
            g.fillOval(originX - 4, originY - 4, 8, 8);
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.setColor(Color.white);
            g.drawString(getName(), originX + 5, originY - 5);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale, Matrix2D transformation) {
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        Stroke oldStroke = prepare(g, originX, originY);
        if (oldStroke == null) return;

        if (transformationIsZero(g, transformation, originX, originY)) {
            return;
        }

        Vector2D transformedVector = transformation.transformVector(this);
        int transformedEndX = calculateEndX(originX, transformedVector.getX(), scale);
        int transformedEndY = calculateEndY(originY, transformedVector.getY(), scale);

        if (transformedEndX == originX && transformedEndY == originY) {
            g.fillOval(originX - 4, originY - 4, 8, 8);
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.setColor(getColor());
            g.drawString(getName(), originX + 5, originY - 5);
            return;
        }


        drawLine(g, originX, originY, transformedEndX,transformedEndY, getColor(), getDrawingThickness());
        drawArrowhead(g, originX, originY, transformedEndX, transformedEndY, getColor());

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.white);
        g.drawString(getName(), transformedEndX + 5, transformedEndY - 5);

        g.setStroke(oldStroke);

    }

    public static int calculateEndX (int originX, double transformedX, int scale) {
        return (int) (originX + transformedX * scale);
    }

    public static int calculateEndY (int originY, double transformedY, int scale) {
        return (int) (originY - transformedY * scale);
    }

    public void drawUnlockedFromOrigin (Graphics2D g, int newOriginX, int newOriginY, int scale, Matrix2D transformation, boolean hasHead) {
        Stroke oldStroke = g.getStroke();
        if (isZero()) {
            g.setColor(getColor());
            g.fillOval(newOriginX - 4, newOriginY - 4, 8, 8);
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.setColor(Color.white);
            g.drawString(getName(), newOriginX + 5, newOriginY - 5);
            return;
        }

        Vector2D transformedVector = transformation.transformVector(this);
        int transformedEndX = (int) (newOriginX + transformedVector.getX() * scale);
        int transformedEndY = (int) (newOriginY - transformedVector.getY() * scale);

        drawLine(g, newOriginX, newOriginY, transformedEndX,transformedEndY, getColor(), getDrawingThickness());
        if (hasHead) {
            drawArrowhead(g, newOriginX, newOriginY, transformedEndX, transformedEndY, getColor());
        }

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.white);
        g.drawString(getName() ,transformedEndX - 10, transformedEndY + 5);

        g.setStroke(oldStroke);
    }
}
