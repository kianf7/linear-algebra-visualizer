import java.awt.*;

/**
 * {@code Transformable} is an interface for drawable objects which can be transformed by matrices.
 */

public interface Transformable {
    void draw(Graphics2D g, int windowWidth, int windowHeight, int scale, Matrix2D transformation);
}
