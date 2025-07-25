import java.awt.*;

/**
 * {@code CoordinateGrid} draws a static 2d coordinate-grid with {@code java.awt}
 */
public class CoordinateGrid implements Drawable {

    public CoordinateGrid() {
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        drawGrid(g, windowWidth, windowHeight, scale);
        drawOrigin(g, windowWidth, windowHeight);
    }

    public void drawGrid(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        g.setColor(Color.gray);
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;
        int startX = originX % scale;
        int startY = originY % scale;

        for (int gridX = startX; gridX <= windowWidth; gridX += scale) {
            g.drawLine(gridX, 0, gridX, windowHeight);
        }

        for (int gridY = startY; gridY <= windowHeight; gridY += scale) {
            g.drawLine(0, gridY, windowWidth, gridY);
        }
    }

    public void drawOrigin(Graphics2D g, int windowWidth, int windowHeight) {
        g.setColor(Color.white);
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        g.drawLine(originX, 0, originX, windowHeight);
        g.drawLine(0, originY, windowWidth, originY);
    }
}
