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

    public void setA(double a) { this.a = a;}
    public void setB(double b) { this.b = b;}
    public void setC(double c) { this.c = c;}
    public void setD(double d) { this.d = d;}


    public Vector2D transformVector(Vector2D v) {
        double transformedX = a * v.getX() + b * v.getY();
        double transformedY = c * v.getX() + d * v.getY();
        return new Vector2D(transformedX, transformedY);
    }

    public static Matrix2D identity() {
        return new Matrix2D(1,0,0,1);
    }

    //TODO: Make not-static
    public static Matrix2D interpolate(Matrix2D in, Matrix2D out, double t) {
        double a = (1 - t) * in.getA() + t * out.getA();
        double b = (1 - t) * in.getB() + t * out.getB();
        double c = (1 - t) * in.getC() + t * out.getC();
        double d = (1 - t) * in.getD() + t * out.getD();

        return new Matrix2D(a,b,c,d);
    }

    public boolean isZero() {
        return a == 0 && b == 0 && c == 0 && d == 0;
    }
}
