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

        for (int x = 0; x <= windowWidth; x+= gridSpacing) {
            g.drawLine(x, 0, x, windowHeight);
        }

        for (int y = 0; y <= windowHeight; y+= gridSpacing) {
            g.drawLine(0, y, windowWidth, y);
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
