public class Matrix2D {

    private double a,b,c,d;
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
}
