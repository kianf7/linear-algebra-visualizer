import java.awt.*;

/**
 * {@code Drawable} is an interface for objects which can be drawn in 2d with {@code java.awt}.
 */

public interface Drawable {
    void draw(Graphics2D g, int windowWidth, int windowHeight, int scale);
}
