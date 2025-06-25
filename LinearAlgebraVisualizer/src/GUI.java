import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
    private int scale;

    GUI(int scale) {
        setBackground(Color.BLACK);
        this.scale = scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
    public int getScale() {
        return scale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //TODO add everything that should be drawn
        CoordinateGrid gridTest = new CoordinateGrid();
        gridTest.draw(g2d, getWidth(), getHeight(), scale);

        //DrawableVector test = new DrawableVector(1.5,0, Color.yellow);
        //test.draw(g2d, getWidth(), getHeight(), scale);

        //DrawableVector test2 = new DrawableVector(2,4, Color.green);
        //test2.draw(g2d, getWidth(), getHeight(), scale);

        DrawableVector.drawVectorSum(g2d, new DrawableVector(1,2, Color.red), new DrawableVector(1, -2,Color.green),getWidth(),getHeight(), scale);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Linear Algebra Visualizer");
        GUI testGui = new GUI(60);
        frame.add(testGui);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
