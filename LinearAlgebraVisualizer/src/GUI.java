import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
    private int scale;

    GUI(int scale) {
        setBackground(Color.BLACK);
        this.scale = scale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //TODO add everything that should be drawn
        CoordinateGrid gridTest = new CoordinateGrid(scale, getWidth(), getHeight());
        gridTest.draw(g2d);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Linear Algebra Visualizer");
        GUI testGui = new GUI(35);
        frame.add(testGui);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
