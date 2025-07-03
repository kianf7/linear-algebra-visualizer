import java.awt.*;
import java.util.ArrayList;

public class VectorManager implements Transformable{
    private final ArrayList<DrawableVector> inputVectors = new ArrayList<DrawableVector>();
    private int vectorAmount = 0;
    private Color currentColor = Color.white;
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
        return vectorAmount;
    }

    public void setCurrentColor(Color newColor) {
        currentColor = newColor;
    }
    public void addVector(DrawableVector newVector) {
        newVector.setColor(currentColor);
        inputVectors.add(newVector);
        vectorAmount += 1;
    }

    public int removeVector() {
        if (inputVectors.isEmpty()) {
            return -1;
        } else {
            inputVectors.removeLast();
            vectorAmount -= 1;
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

    public void drawSum(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        double sumX = 0;
        double sumY = 0;
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        if (inputVectors.isEmpty()) {
            return;
        }
        int startX = originX;
        int startY = originY;
        for (DrawableVector v : inputVectors) {
            if (inputVectors.size() == 1) {
                v.draw(g, windowWidth, windowHeight, scale);
                return;
            }
            v.drawUnlockedFromOrigin(g, startX, startY, scale);

            startX = v.calculateEndX(startX, scale);
            startY = v.calculateEndY(startY,scale);

            sumX += v.getX();
            sumY += v.getY();
        }
        DrawableVector sum = new DrawableVector(sumX,sumY,Color.blue,"sum");
        sum.draw(g,windowWidth,windowHeight,scale);
    }
}
