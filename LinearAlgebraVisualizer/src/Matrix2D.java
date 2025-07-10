public class Matrix2D {

    private final double a,b,c,d;
    // M = a, b
    //     c, d
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

    public double determinant() {
        return a * d - b * c;
    }

    public boolean isZero() {
        return a == 0 && b == 0 && c == 0 && d == 0;
    }
}
