import java.awt.*;

public class TransformableGrid extends CoordinateGrid implements Transformable{
    private final int MAX_GRID_LINES;
    private final Color NICE_BLUE = new Color(156,156,255);

    public TransformableGrid(int MAX_GRID_LINES) {
        this.MAX_GRID_LINES = MAX_GRID_LINES;
    }

    public int getMAX_GRID_LINES() {
        return MAX_GRID_LINES;
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale, Matrix2D transformation) {
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;
        Color oldColor = g.getColor();
        Stroke oldStroke = g.getStroke();
        g.setStroke(new BasicStroke(1.5F));

        drawBackground(g, windowWidth, windowHeight, scale);
        drawHorizontalLines(g, originX, originY, scale, transformation);
        drawVerticalLines(g, originX, originY, scale, transformation);

        g.setStroke(oldStroke);
        g.setColor(oldColor);

    }

    public void drawVerticalLines(Graphics2D g, int originX, int originY, int scale, Matrix2D transformation) {
        for (int currentX = -MAX_GRID_LINES; currentX <= MAX_GRID_LINES; currentX++) {
            if (currentX == 0) {
                g.setColor(Color.white);
            } else {
                g.setColor(NICE_BLUE);
            }
            Vector2D top = new Vector2D(currentX, MAX_GRID_LINES);
            Vector2D bottom = new Vector2D(currentX, -MAX_GRID_LINES);
            Vector2D transformedTop = transformation.transformVector(top);
            Vector2D transformedBottom = transformation.transformVector(bottom);
            drawTransformedLine(g,transformedBottom,transformedTop,originX,originY,scale);
        }
    }

    public void drawHorizontalLines(Graphics2D g, int originX, int originY, int scale, Matrix2D transformation) {
        for(int currentY = -MAX_GRID_LINES; currentY <= MAX_GRID_LINES; currentY += 1) {
            if (currentY == 0) {
                g.setColor(Color.white);
            } else {
                g.setColor(NICE_BLUE);
            }
            Vector2D right = new Vector2D(MAX_GRID_LINES, currentY);
            Vector2D left = new Vector2D(-MAX_GRID_LINES, currentY);
            Vector2D transformedLeft = transformation.transformVector(left);
            Vector2D transformedRight = transformation.transformVector(right);
            drawTransformedLine(g,transformedLeft,transformedRight,originX,originY,scale);
        }
    }

    public void drawTransformedLine(Graphics2D g, Vector2D a, Vector2D b, int originX, int originY, int scale) {
        int x1 = originX + (int) (a.getX() * scale);
        int y1 = originY - (int) (a.getY() * scale);
        int x2 = originX + (int) (b.getX() * scale);
        int y2 = originY - (int) (b.getY() * scale);

        g.drawLine(x1,y1,x2,y2);
    }

    public void drawBackground(Graphics g, int windowWidth, int windowHeight, int scale) {
        Color oldColor = g.getColor();
        g.setColor(Color.darkGray);
        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        int startX = originX % scale;
        int startY = originY % scale;

        for (int x = startX; x <= windowWidth; x += scale) {
            g.drawLine(x, 0, x, windowHeight);
        }
        for (int y = startY; y <= windowHeight; y += scale) {
            g.drawLine(0, y, windowWidth, y);
        }

        g.setColor(oldColor);
    }
}
