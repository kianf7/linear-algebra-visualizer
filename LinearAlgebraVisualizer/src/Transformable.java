import java.awt.*;

public interface Transformable {
    void draw(Graphics2D g, int windowWidth, int windowHeight, int scale, Matrix2D transformation);
}
