import java.awt.*;
import java.util.ArrayList;

public class VectorManager implements Drawable{
    private final ArrayList<DrawableVector> inputVectors = new ArrayList<DrawableVector>();
    private int vectorAmount = 0;
    private Color currentColor = Color.white;

    public VectorManager() {
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
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        for(DrawableVector vector : inputVectors) {
            vector.draw(g, windowWidth, windowHeight, scale);
        }
    }
}
