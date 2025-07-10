
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

}
