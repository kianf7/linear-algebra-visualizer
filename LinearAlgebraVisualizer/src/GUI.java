import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class GUI extends JPanel {
    private int scale;
    private VectorManager vectorManager;
    private MatrixManager matrixManager;
    TransformableGrid gridTest = new TransformableGrid(30);

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

    public void setMatrixManager(MatrixManager matrixManager) {
        this.matrixManager = matrixManager;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        //TODO add everything that should be drawn
        gridTest.draw(g2d, getWidth(), getHeight(), scale, matrixManager.getTransformation());

        //TODO: Maybe add switch case for different modes
        if (vectorManager.getDrawingMode() == 0) {
            vectorManager.draw(g2d, getWidth(), getHeight(), scale);
        }
        else if (vectorManager.getDrawingMode() == 1) {
            vectorManager.drawSum(g2d, getWidth(), getHeight(), scale);
        }



    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Linear Algebra Visualizer");
        GUI testGui = new GUI(35);

        JTextField vectorXField = new JTextField(3);
        JTextField vectorYField = new JTextField(3);
        JButton showVectorButton = new JButton("Add vector");
        JButton vectorColorButton = new JButton("Choose color");
        JLabel labelX = new JLabel("x:");
        JLabel labelY = new JLabel("y:");
        JLabel colorLabel = new JLabel("Current Color:");
        JPanel colorPreview = new JPanel();
        colorPreview.setPreferredSize(new Dimension(20, 20));
        colorPreview.setBackground(Color.WHITE);
        colorPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JButton removeLastVectorButton = new JButton("Remove last vector");
        JButton sumVectorsButton = new JButton("Sum Vectors");


        JPanel inputPanel = new JPanel();
        inputPanel.add(labelX);
        inputPanel.add(vectorXField);
        inputPanel.add(labelY);
        inputPanel.add(vectorYField);
        inputPanel.add(showVectorButton);
        inputPanel.add(sumVectorsButton);
        inputPanel.add(removeLastVectorButton);
        inputPanel.add(vectorColorButton);
        inputPanel.add(colorLabel);
        inputPanel.add(colorPreview);

        JSlider transformationSlider = new JSlider(0,1000,0);
        transformationSlider.setMajorTickSpacing(20);
        transformationSlider.setMinorTickSpacing(5);

        JPanel inputPanel2 = new JPanel();
        inputPanel2.add(new JLabel("Transformation:"));
        inputPanel2.add(transformationSlider);

        //TODO: Add all GUI components with an action to class GUIComponents and here
        GUIComponents components = new GUIComponents(showVectorButton, vectorXField, vectorYField, vectorColorButton, colorPreview, removeLastVectorButton, transformationSlider, sumVectorsButton);

        new GUIController(testGui,components);
        frame.setLayout(new BorderLayout());
        frame.add(testGui, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(inputPanel2, BorderLayout.SOUTH);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
