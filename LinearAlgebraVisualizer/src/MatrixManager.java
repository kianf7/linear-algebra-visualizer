import java.awt.*;

/**
 * {@code MatrixManager} manages input matrices and provides {@code draw} method for determinant and eigenvectors.
 */

public class MatrixManager implements Transformable {
    private Matrix2D spaceBasis = Matrix2D.identity();
    private Matrix2D interpolated = spaceBasis;
    private Matrix2D target = spaceBasis;
    private boolean hideDet;
    private boolean hideBasisVectors;
    private boolean showEigenvectors;
    private final int MAX_GRID_LINES = 30;
    private final Color detBlue = new Color(128, 128, 255);
    private final Color detFill = new Color(100,100,255,100);
    private final Color detFillNeg = new Color(200,50,255,90);
    private final Color orange = new Color(255,193,129);

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

    public void setSpaceBasis(Matrix2D spaceBasis) {
        this.spaceBasis = spaceBasis;
    }

    public Matrix2D getSpaceBasis() {
        return spaceBasis;
    }

    public void setHideBasisVectors(boolean hideBasisVectors) {
        this.hideBasisVectors = hideBasisVectors;
    }

    public void setShowEigenvectors(boolean showEigenvectors) {
        this.showEigenvectors = showEigenvectors;
    }

    @Override
    public void draw(Graphics2D g, int windowWidth, int windowHeight, int scale, Matrix2D transformation) {
        if (showEigenvectors) {
            drawEigenvectors(g, windowWidth, windowHeight, scale, MAX_GRID_LINES);
        }

        if (!hideDet) {
            drawDeterminant(g, windowWidth, windowHeight, scale);
        }

        if (!hideBasisVectors) {
            drawBasisVectors(g, windowWidth, windowHeight, scale);
        }
    }

    public void drawDeterminant(Graphics2D g, int windowWidth, int windowHeight, int scale) {
        if (interpolated.getDeterminant() == 0) {
            return;
        }

        Color determinantFillColor;
        if (interpolated.getDeterminant() > 0) {
            determinantFillColor = detFill;
        } else {
            determinantFillColor = detFillNeg;
        }

        int originX = windowWidth / 2;
        int originY = windowHeight / 2;

        Vector2D i = interpolated.transformVector(new Vector2D(1,0));
        Vector2D j = interpolated.transformVector(new Vector2D(0,1));

        TransformableVector iDraw = new TransformableVector(1,0, detBlue,"");
        TransformableVector jDraw = new TransformableVector(0,1, detBlue,"");

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
        double determinant = interpolated.getDeterminant();
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

    private int maxStepsInsideGrid(Vector2D v, int gridLines) {
        double vx = Math.abs(v.getX());
        double vy = Math.abs(v.getY());

        int stepsX = vx < 1e-12 ? gridLines : (int) Math.floor(gridLines / vx);
        int stepsY = vy < 1e-12 ? gridLines : (int) Math.floor(gridLines / vy);

        return Math.min(stepsX, stepsY);
    }

    public void drawEigenvectors(Graphics2D g, int windowWidth, int windowHeight, int scale, int gridLines) {
        Vector2D eigenVector1;
        Vector2D eigenVector2;
        Vector2D eigenVector1Negate;
        Vector2D eigenVector2Negate;
        double[] eigenValues = target.getEigenvalues();

        if (target.isEqual(Matrix2D.zero()) || interpolated.isEqual(Matrix2D.identity()) || target.isEqual(new Matrix2D(-1,0,0,-1))) {
            eigenVector1 = new Vector2D(1,0);
            eigenVector2 = new Vector2D(0,1);
        } else {
            eigenVector1 = target.getEigenvector(eigenValues[0]);
            eigenVector2= target.getEigenvector(eigenValues[1]);
        }

        eigenVector1Negate = eigenVector1.negate();
        eigenVector2Negate = eigenVector2.negate();

        TransformableVector eigenTransform1 = new TransformableVector(eigenVector1.getX(),eigenVector1.getY(),orange, "");
        TransformableVector eigenTransform1Negate = new TransformableVector(eigenVector1Negate.getX(), eigenVector1Negate.getY(),orange,"1");
        drawChainedTogether(g, windowWidth, windowHeight, scale, eigenTransform1, gridLines);
        drawChainedTogether(g, windowWidth, windowHeight, scale, eigenTransform1Negate, gridLines);

        if (eigenValues[0] != eigenValues[1] || interpolated.isEqual(Matrix2D.identity()) || target.isEqual(Matrix2D.zero()) || target.isEqual(new Matrix2D(-1,0,0,-1))) {
            TransformableVector eigenTransform2 = new TransformableVector(eigenVector2.getX(),eigenVector2.getY(),orange, "");
            TransformableVector eigenTransform2Negate = new TransformableVector(eigenVector2Negate.getX(), eigenVector2Negate.getY(),orange,"");
            drawChainedTogether(g, windowWidth, windowHeight , scale, eigenTransform2, gridLines);
            drawChainedTogether(g, windowWidth , windowHeight, scale, eigenTransform2Negate, gridLines);
        }
    }

    public void drawChainedTogether(Graphics2D g, int windowWidth, int windowHeight, int scale, TransformableVector v, int gridLines) {
        if (interpolated.getDeterminant() == 0) {
            return;
        }

        int amount = maxStepsInsideGrid(v, gridLines);

        for (int i = 0; i <= amount; i++) {
            TransformableVector scaled = new TransformableVector(
                    v.getX() * i,
                    v.getY() * i,
                    v.getColor(),
                    ""
            );
            scaled.setDrawingThickness(new BasicStroke(1));
            scaled.draw(g, windowWidth, windowHeight, scale, interpolated);
        }
    }
}
