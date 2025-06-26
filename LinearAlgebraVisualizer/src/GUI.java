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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        //TODO add everything that should be drawn
        TransformableGrid gridTest = new TransformableGrid(30);
        gridTest.draw(g2d, getWidth(), getHeight(), scale, new Matrix2D(1,2,-1,1));

        if (vectorManager != null) {
            vectorManager.draw(g2d, getWidth(), getHeight(), scale);
        }

    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Linear Algebra Visualizer");
        GUI testGui = new GUI(35);

        JTextField vectorXField = new JTextField(5);
        vectorXField.setBackground(Color.darkGray);
        vectorXField.setForeground(Color.white);
        JTextField vectorYField = new JTextField(5);
        vectorYField.setBackground(Color.darkGray);
        vectorYField.setForeground(Color.white);
        JButton showVectorButton = new JButton("Add vector");
        showVectorButton.setBackground(Color.darkGray);
        showVectorButton.setForeground(Color.white);
        JButton vectorColorButton = new JButton("Choose color");
        vectorColorButton.setBackground(Color.darkGray);
        vectorColorButton.setForeground(Color.white);
        JLabel labelX = new JLabel("x:");
        labelX.setForeground(Color.white);
        JLabel labelY = new JLabel("y:");
        labelY.setForeground(Color.white);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(30,30,30));
        inputPanel.add(labelX);
        inputPanel.add(vectorXField);
        inputPanel.add(labelY);
        inputPanel.add(vectorYField);
        inputPanel.add(showVectorButton);
        inputPanel.add(vectorColorButton);

        GUIController guiController = new GUIController(testGui,showVectorButton,vectorXField, vectorYField, vectorColorButton);
        frame.setLayout(new BorderLayout());
        frame.add(testGui, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
