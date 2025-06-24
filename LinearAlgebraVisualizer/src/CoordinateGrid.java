import java.awt.*;

public class CoordinateGrid implements Drawable{
    private final int gridSpacing;
    private final int windowWidth;
    private final int windowHeight;


    public CoordinateGrid(int scale, int windowWidth, int windowHeight) {
        gridSpacing = scale;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    @Override
    public void draw(Graphics2D g) {
        drawGrid(g);
        drawOrigin(g);
    }

    public void drawGrid(Graphics2D g) {
        g.setColor(Color.gray);
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;
        int startX = originX % gridSpacing;
        int startY = originY % gridSpacing;

        for (int gridX = startX; gridX <= windowWidth; gridX += gridSpacing) {
            g.drawLine(gridX, 0, gridX, windowHeight);
        }

        for (int gridY = startY; gridY <= windowHeight; gridY += gridSpacing) {
            g.drawLine(0, gridY, windowWidth, gridY);
        }
    }

    public void drawOrigin(Graphics2D g) {
        g.setColor(Color.white);
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        g.drawLine(originX, 0, originX, windowHeight);
        g.drawLine(0, originY, windowWidth, originY);
    }
}
