import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {
    private int scale;
    private VectorManager vectorManager;

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

    public void setVectorManager(VectorManager vectorManager) {
        this.vectorManager = vectorManager;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //TODO add everything that should be drawn
        CoordinateGrid gridTest = new CoordinateGrid();
        gridTest.draw(g2d, getWidth(), getHeight(), scale);

        if (vectorManager.getMode() == 0) {
            vectorManager.draw(g2d, getWidth(), getHeight(), scale);
        }
        else if (vectorManager.getMode() == 1) {
            vectorManager.drawSum(g2d, getWidth(), getHeight(), scale);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Linear Algebra Visualizer");
        GUI testGui = new GUI(35);

        JTextField vectorXField = new JTextField(5);
        JTextField vectorYField = new JTextField(5);
        JButton showVectorButton = new JButton("Add Vector");
        JButton SumUpButton = new JButton("Show Vector Sum");

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.gray);
        inputPanel.add(new JLabel("x:"));
        inputPanel.add(vectorXField);
        inputPanel.add(new JLabel("y:"));
        inputPanel.add(vectorYField);
        inputPanel.add(showVectorButton);
        inputPanel.add(SumUpButton);

        GUIController guiController = new GUIController(testGui,showVectorButton, SumUpButton, vectorXField, vectorYField);
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(testGui, BorderLayout.CENTER);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
