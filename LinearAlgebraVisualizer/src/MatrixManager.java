import java.awt.*;

public class MatrixManager {
    private Matrix2D spaceBasis = Matrix2D.identity();
    private Matrix2D interpolated = spaceBasis;
    private Matrix2D target = spaceBasis;
    private boolean hideDet;
    private boolean hideBasisVectors;
    private final Color niceBlue = new Color(128, 128, 255);
    private final Color detFill = new Color(100,100,255,100);
    private final Color detFillNeg = new Color(200,50,255,90);

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

    public void setSpaceBasis(Matrix2D spaceBasis) {
        this.spaceBasis = spaceBasis;
    }

    public Matrix2D getSpaceBasis() {
        return spaceBasis;
    }

    public void setHideBasisVectors(boolean hideBasisVectors) {
        this.hideBasisVectors = hideBasisVectors;
    }

    public boolean getHideBasisVectors() {
        return hideBasisVectors;
    }

    public void drawDeterminant(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        if (interpolated.determinant() == 0) {
            return;
        }

        Color determinantFillColor;
        if (interpolated.determinant() > 0) {
            determinantFillColor = detFill;
        } else {
            determinantFillColor = detFillNeg;
        }

        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        Vector2D i = interpolated.transformVector(new Vector2D(1,0));
        Vector2D j = interpolated.transformVector(new Vector2D(0,1));


        TransformableVector iDraw = new TransformableVector(1,0,niceBlue,"");
        TransformableVector jDraw = new TransformableVector(0,1,niceBlue,"");

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


        g.setColor(determinantFillColor);
        g.fill(parallelogram);

        g.setColor(Color.LIGHT_GRAY);
        double determinant = interpolated.determinant();
        g.setFont(new Font("Arial", Font.BOLD, 15));
        String formattedDeterminant = String.format("%.2f", determinant);

        g.drawString("Det: " + formattedDeterminant, endPointX + 1, endPointY - 5);
    }

    public void drawBasisVectors(Graphics2D g, int windowWidth, int windowHeight, int scale) {

        TransformableVector iDraw = new TransformableVector(1,0,Color.green,"");
        TransformableVector jDraw = new TransformableVector(0,1,Color.red,"");

        iDraw.draw(g,windowWidth,windowHeight,scale,interpolated);
        jDraw.draw(g,windowWidth,windowHeight,scale,interpolated);
    }
}
