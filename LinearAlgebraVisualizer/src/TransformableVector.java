import java.awt.*;

public class TransformableVector extends DrawableVector implements Transformable {

    public TransformableVector(double x, double y, Color color, String name) {
        super(x, y, color, name);
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale, Matrix2D transformation) {
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        Stroke oldStroke = prepare(g, windowWidth, windowHeight);
        if (oldStroke == null) return;

        Vector2D transformedVector = transformation.transformVector(this);
        int transformedEndX = calculateEndX(originX, transformedVector.getX(), scale);
        int transformedEndY = calculateEndY(originY, transformedVector.getY(), scale);


        drawLine(g, originX, originY, transformedEndX,transformedEndY, getColor());
        drawArrowhead(g, originX, originY, transformedEndX, transformedEndY, getColor());

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.setColor(Color.white);
        g.drawString(getName(), transformedEndX + 5, transformedEndY - 5);

        g.setStroke(oldStroke);

    }

    public int calculateEndX (int originX, double transformedX, int scale) {
        return (int) (originX + transformedX * scale);
    }

    public int calculateEndY (int originY, double transformedY, int scale) {
        return (int) (originY - transformedY * scale);
    }

    @Override
    public void drawUnlockedFromOrigin (Graphics2D g, int newOriginX, int newOriginY, int scale) {
        //TODO
    }
}
