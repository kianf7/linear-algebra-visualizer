public class Vector2D{
    private double x;
    private double y;

    Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getX() {return x;}
    public double getY() {return y;}

    public static Vector2D vectorAdd(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.getX()+ v2.getX(), v1.getY() + v2.getY());
    }

    public static boolean isNull(final Vector2D vector) {
        return vector.getX() == 0 && vector.getY() == 0;
    }

}
