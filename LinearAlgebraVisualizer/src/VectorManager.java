import java.awt.*;
import java.util.ArrayList;

public class VectorManager implements Drawable{
    private final ArrayList<DrawableVector> inputVectors = new ArrayList<DrawableVector>();
    private final GUI gui;
    private int vectorAmount = 0;

    public VectorManager(GUI gui) {
        this.gui = gui;
    }
    public int getVectorAmount() {
        return vectorAmount;
    }
    public void addVector(DrawableVector newVector) {
        inputVectors.add(newVector);
        vectorAmount += 1;
        gui.repaint();
    }

    public int removeVector() {
        if (inputVectors.isEmpty()) {
            return -1;
        } else {
            inputVectors.removeLast();
            vectorAmount -= 1;
            gui.repaint();
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
