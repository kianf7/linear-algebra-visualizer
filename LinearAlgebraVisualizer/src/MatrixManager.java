import java.awt.*;

public class MatrixManager {
    private Matrix2D interpolated = Matrix2D.identity();
    private Matrix2D target = Matrix2D.identity();
    private boolean hideDet;

    public MatrixManager() {
    }

    public void setInterpolated(Matrix2D interpolated) {
        this.interpolated = interpolated;
    }

    public Matrix2D getInterpolated() {
        return interpolated;
    }

    public void setTarget(Matrix2D target) {
        this.target = target;
    }

    public Matrix2D getTarget() {
        return target;
    }

    public void setHideDet(boolean hideDet) {
        this.hideDet = hideDet;
    }

    public boolean getHideDet() {
        return hideDet;
    }

    public void drawDeterminant(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        if (interpolated.determinant() == 0) {
            return;
        }

        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        Vector2D i = interpolated.transformVector(new Vector2D(1,0));
        Vector2D j = interpolated.transformVector(new Vector2D(0,1));


        TransformableVector iDraw = new TransformableVector(1,0,Color.yellow,"");
        TransformableVector jDraw = new TransformableVector(0,1,Color.yellow,"");

        iDraw.drawUnlockedFromOrigin(g,originX,originY,scale,interpolated,false);
        jDraw.drawUnlockedFromOrigin(g,originX,originY,scale,interpolated,false);

        int I_nextStartingX = TransformableVector.calculateEndX(originX, j.getX(), scale);
        int I_nextStartingY = TransformableVector.calculateEndY(originY, j.getY(), scale);

        int J_nextStartingX = TransformableVector.calculateEndX(originX,i.getX(),scale);
        int J_nextStartingY = TransformableVector.calculateEndY(originY, i.getY(), scale);

        iDraw.drawUnlockedFromOrigin(g, I_nextStartingX, I_nextStartingY,scale,interpolated, false);
        jDraw.drawUnlockedFromOrigin(g,J_nextStartingX,J_nextStartingY,scale,interpolated,false);

        int endPointX = TransformableVector.calculateEndX(originX, i.getX() + j.getX(), scale);
        int endPointY = TransformableVector.calculateEndY(originY, i.getY() + j.getY(), scale);

        Polygon parallelogram = new Polygon();
        parallelogram.addPoint(originX,originY);
        parallelogram.addPoint(I_nextStartingX, I_nextStartingY);
        parallelogram.addPoint(endPointX,endPointY);
        parallelogram.addPoint(J_nextStartingX,J_nextStartingY);

        Color transparentYellow = new Color(255, 255, 0, 80);
        g.setColor(transparentYellow);
        g.fill(parallelogram);

        g.setColor(Color.yellow);
        double determinant = interpolated.determinant();
        String formattedDeterminant = String.format("%.2f", determinant);

        g.drawString("Det: " + formattedDeterminant, endPointX , endPointY -5);

        iDraw.setColor(Color.green);
        jDraw.setColor(Color.red);

        iDraw.draw(g,windowWidth,windowHeight,scale,interpolated);
        jDraw.draw(g,windowWidth,windowHeight,scale,interpolated);
    }
}
