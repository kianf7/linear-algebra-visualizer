import java.awt.*;
import java.util.ArrayList;

public class VectorManager implements Drawable{
    private final ArrayList<DrawableVector> inputVectors = new ArrayList<DrawableVector>();
    private final GUI gui;
    private int vectorAmount = 0;
    private int mode;

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
    // mode for differentiating between different drawing options
    public void setMode(int mode) {
        this.mode = mode;
    }
    public int getMode() {
        return mode;
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        for(DrawableVector vector : inputVectors) {
            vector.draw(g, windowWidth, windowHeight, scale);
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

        for (DrawableVector v : inputVectors) {
            if (inputVectors.size() == 1) {
                v.setOriginX(originX);
                v.setOriginY(originY);
                v.draw(g, windowWidth, windowHeight, scale);
                return;
            }
            v.setOriginX(originX);
            v.setOriginY(originY);
            v.draw(g, windowWidth, windowHeight, scale);

            originX = v.calculateEndX(v, scale);
            originY = v.calculateEndY(v, scale);

            sumX += v.getX();
            sumY += v.getY();
        }
        DrawableVector sum = new DrawableVector(sumX,sumY,Color.blue,"sum");
        sum.setOriginX(windowWidth / 2);
        sum.setOriginY(windowHeight / 2);
        sum.draw(g,windowWidth,windowHeight,scale);
    }
}
