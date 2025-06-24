import java.awt.*;

public class CoordinateGrid implements Drawable{
    private int scale;


    public CoordinateGrid(int scale) {
        this.scale = scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight) {
        drawGrid(g, windowWidth, windowHeight);
        drawOrigin(g, windowWidth, windowHeight);
    }

    public void drawGrid(Graphics2D g, int windowWidth, int windowHeight) {
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
