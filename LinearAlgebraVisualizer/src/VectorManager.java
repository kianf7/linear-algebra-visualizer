import java.awt.*;
import java.util.ArrayList;

//TODO: Maybe make generic <T extends DrawableVector>
public class VectorManager implements Transformable {
    private final ArrayList<DrawableVector> inputVectors = new ArrayList<>();
    private final Color STANDARD_COLOR = Color.white;
    private Color currentColor = STANDARD_COLOR;
    private DrawingMode drawingMode;

    public VectorManager() {
    }

    public void setDrawingMode(DrawingMode drawingMode) {
        this.drawingMode = drawingMode;
    }

    public DrawingMode getDrawingMode() {
        return drawingMode;
    }

    public int getVectorAmount() {
        return inputVectors.size();
    }

    public void setCurrentColor(Color newColor) {
        currentColor = newColor;
    }
    public void addVector(DrawableVector newVector) {
        newVector.setColor(currentColor);
        inputVectors.add(newVector);
    }

    public int removeVector() {
        if (inputVectors.isEmpty()) {
            return -1;
        } else {
            inputVectors.removeLast();
            return 0;
        }
    }
    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale, Matrix2D transformation) {
        for(DrawableVector vector : inputVectors) {
            if (vector instanceof TransformableVector transformableVector) {
                transformableVector.draw(g, windowWidth, windowHeight, scale, transformation);
            } else {
                vector.draw(g, windowWidth, windowHeight, scale);
            }
        }
    }

    public void drawSum(Graphics2D g, int windowWidth, int windowHeight, int scale, Matrix2D transformation) {
        if (inputVectors.isEmpty()) return;

        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        double currentX = 0;
        double currentY = 0;

        int newOriginX = originX;
        int newOriginY = originY;

        for (DrawableVector inputVector : inputVectors) {
            TransformableVector v = (TransformableVector) inputVector;

            double nextX = currentX + v.getX();
            double nextY = currentY + v.getY();

            v.drawUnlockedFromOrigin(g, newOriginX, newOriginY, scale, transformation);

            currentX = nextX;
            currentY = nextY;

            Vector2D transformed = transformation.transformVector(new Vector2D(currentX, currentY));
            newOriginX = (int) (originX + transformed.getX() * scale);
            newOriginY = (int) (originY - transformed.getY() * scale);
        }

        TransformableVector sum = new TransformableVector(currentX, currentY, Color.BLUE, "sum");
        sum.draw(g, windowWidth, windowHeight, scale, transformation);
    }

}
