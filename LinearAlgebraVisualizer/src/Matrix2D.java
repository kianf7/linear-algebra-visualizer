/**
 * {@code Matrix2D} represents a 2x2 matrix and provides useful operations.
 */

public class Matrix2D {
    private final double a,b,c,d;
    public Matrix2D(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getC() {
        return c;
    }
    public double getD() {
        return d;
    }

    public Vector2D transformVector(Vector2D v) {
        double transformedX = a * v.getX() + b * v.getY();
        double transformedY = c * v.getX() + d * v.getY();
        return new Vector2D(transformedX, transformedY);
    }

    public static Matrix2D identity() {
        return new Matrix2D(1, 0, 0, 1);
    }

    public static Matrix2D zero() {
        return new Matrix2D(0,0,0,0);
    }

    //Works somehow
    public static Matrix2D interpolate(Matrix2D m1, Matrix2D m2, double t) {
        double a = m1.getA() * m2.getA() + m1.getB() * m2.getC();
        double b = m1.getA() * m2.getB() + m1.getB() * m2.getD();
        double c = m1.getC() * m2.getA() + m1.getD() * m2.getC();
        double d = m1.getC() * m2.getB() + m1.getD() * m2.getD();

        double interpolatedA = (1 - t) * m1.getA() + t * a;
        double interpolatedB = (1 - t) * m1.getB() + t * b;
        double interpolatedC = (1 - t) * m1.getC() + t * c;
        double interpolatedD = (1 - t) * m1.getD() + t * d;

        return new Matrix2D(interpolatedA, interpolatedB, interpolatedC, interpolatedD);
    }

    public boolean isZero() {
        return a == 0 && b == 0 && c == 0 && d == 0;
    }

    public boolean isEqual(Matrix2D other) {
        return a == other.a && b == other.b && c == other.c && d == other.d;
    }

    public double getDeterminant() {
        return a * d - b * c;
    }

    //Following is based on Mathe2-Skript

    //For 2x2 matrices you get the polynomial t^2 - (a + d)t + (ad - bc)
    public double[] getEigenvalues() {
        double bCoefficient = a + d;
        double cCoefficient = getDeterminant();

        if (bCoefficient * bCoefficient - 4 * cCoefficient < 0) {
            return null;
        }

        double sqrt = Math.sqrt(bCoefficient * bCoefficient - 4 * cCoefficient);

        double lambda1 = 0.5 * (bCoefficient + sqrt);
        double lambda2 = 0.5 * (bCoefficient - sqrt);

        return new double[] {lambda1, lambda2};
    }

    public Vector2D getEigenvector(double lambda) {
        // (A - lambda * I) * v = 0
        double m00 = a - lambda;
        double m01 = b;
        double m10 = c;
        double m11 = d - lambda;

        //Cant use 0 because of graphical bugs
        if (Math.abs(m00) > 1e-10 || Math.abs(m01) > 1e-10) {
            return new Vector2D(-m01, m00).normalize();
        } else if (Math.abs(m10) > 1e-10 || Math.abs(m11) > 1e-10) {
            return new Vector2D(-m11, m10).normalize();
        } else {
            return new Vector2D(1, 0);
        }
    }


}
