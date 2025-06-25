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

    /* TEST
    public static void main(String[] args) {
        Vector2D v1 = new Vector2D(1.0, 2.0);
        Vector2D v2 = new Vector2D(3.0, 4.0);

        Vector2D v3 = Vector2D.vectorAdd(v1, v2);
        System.out.println("x: " + v3.getX() + " y: " + v3.getY());

    }
    */

}
