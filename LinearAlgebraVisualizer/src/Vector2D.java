
public class Vector2D {
    private final double x,y;

    Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {return x;}
    public double getY() {return y;}

    public boolean isZero() {
        return getX() == 0 && getY() == 0;
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D normalize() {
        double length = getLength();
        if (length == 0) {
            return new Vector2D(0,0);
        }
        return new Vector2D(x / length, y / length);
    }

    public Vector2D negate() {
        return new Vector2D(-x,-y);
    }

}
