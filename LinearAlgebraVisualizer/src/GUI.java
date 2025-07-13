import javax.swing.*;
import java.awt.*;
import com.formdev.flatlaf.FlatDarkLaf;

public class GUI extends JPanel {
    private int scale;
    private VectorManager vectorManager;
    private MatrixManager matrixManager;
    TransformableGrid gridTest = new TransformableGrid(30);

    public GUI(int scale) {
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
        gridTest.draw(g2d, getWidth(), getHeight(), scale, matrixManager.getInterpolated());

        //TODO: Maybe add switch case for different modes
        if (vectorManager.getDrawingMode() == DrawingMode.NORMAL) {
            vectorManager.draw(g2d, getWidth(), getHeight(), scale, matrixManager.getInterpolated());
        }
        else if (vectorManager.getDrawingMode() == DrawingMode.SUM) {
            vectorManager.drawSum(g2d, getWidth(), getHeight(), scale, matrixManager.getInterpolated());
        }

        matrixManager.draw(g2d,getWidth(),getHeight(),scale, matrixManager.getInterpolated());

    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Linear Algebra Visualizer");
        GUI testGui = new GUI(50);

        JTextField vectorXField = new JTextField(3);
        JTextField vectorYField = new JTextField(3);
        JButton addVectorButton = new JButton("Add vector");
        JButton vectorColorButton = new JButton("Choose color");
        JLabel labelX = new JLabel("x:");
        JLabel labelY = new JLabel("y:");
        JLabel colorLabel = new JLabel("Current vector color:");
        JPanel colorPreview = new JPanel();
        colorPreview.setPreferredSize(new Dimension(20, 20));
        colorPreview.setBackground(Color.WHITE);
        colorPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JButton removeLastVectorButton = new JButton("Remove last");
        JButton sumVectorsButton = new JButton("Sum Vectors");
        sumVectorsButton.setToolTipText("Sums up all the vectors on the grid");
        addVectorButton.setToolTipText("Adds input vector to the grid");
        vectorXField.setToolTipText("Input x-Coordinate of new vector here");
        vectorYField.setToolTipText("Input y-Coordinate of new vector here");
        removeLastVectorButton.setToolTipText("Removes last input vector from grid");
        vectorColorButton.setToolTipText("Choose color for following vectors");


        JPanel inputPanel = new JPanel();
        inputPanel.add(labelX);
        inputPanel.add(vectorXField);
        inputPanel.add(labelY);
        inputPanel.add(vectorYField);
        inputPanel.add(addVectorButton);
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

        //Transformation-Matrix////////////////////////////////////////////////////////////////
        JTextField transformationFieldA = new JTextField(3);
        JTextField transformationFieldB = new JTextField(3);
        JTextField transformationFieldC = new JTextField(3);
        JTextField transformationFieldD = new JTextField(3);
        transformationFieldA.setText("1");
        transformationFieldB.setText("0");
        transformationFieldC.setText("0");
        transformationFieldD.setText("1");

        JPanel transformationInputGrid = new JPanel(new GridLayout(2,2,5,5));
        transformationInputGrid.setMaximumSize(new Dimension(200, 100));
        transformationInputGrid.setBorder(BorderFactory.createTitledBorder("Transformation"));
        transformationInputGrid.add(transformationFieldA);
        transformationInputGrid.add(transformationFieldB);
        transformationInputGrid.add(transformationFieldC);
        transformationInputGrid.add(transformationFieldD);
        transformationInputGrid.setToolTipText("Input a matrix to transform the grid");

        JButton setTransformationButton = new JButton("Set Transformation");
        //x////////////////////////////////////////////////////////////////////////////////////////

        //Space-Basis-Matrix///////////////////////////////////////////////////////////////////////
        JTextField basisFieldA = new JTextField(3);
        JTextField basisFieldB = new JTextField(3);
        JTextField basisFieldC = new JTextField(3);
        JTextField basisFieldD = new JTextField(3);
        basisFieldA.setText("1");
        basisFieldB.setText("0");
        basisFieldC.setText("0");
        basisFieldD.setText("1");

        JPanel basisGrid = new JPanel(new GridLayout(2,2,5,5));
        basisGrid.setMaximumSize(new Dimension(200,100));
        basisGrid.setBorder(BorderFactory.createTitledBorder("Space-Basis"));
        basisGrid.add(basisFieldA);
        basisGrid.add(basisFieldB);
        basisGrid.add(basisFieldC);
        basisGrid.add(basisFieldD);
        basisGrid.setToolTipText("Input a matrix as a basis for the grid");

        JButton setBasisButton = new JButton("Set Basis");



        JCheckBox hideDetBox = new JCheckBox();
        JLabel hideDetLabel = new JLabel("Hide Determinant:");
        JPanel hideDeterminant = new JPanel();
        hideDeterminant.setMaximumSize(new Dimension(200,30));
        hideDeterminant.add(hideDetLabel);
        hideDeterminant.add(hideDetBox);

        JCheckBox showEigenVectorsBox = new JCheckBox();
        JLabel showEigenVectorsLabel = new JLabel("Show Eigenvectors:");
        JPanel showEigenvectors = new JPanel();
        showEigenvectors.setMaximumSize((new Dimension(200,30)));
        showEigenvectors.add(showEigenVectorsLabel);
        showEigenvectors.add(showEigenVectorsBox);

        JCheckBox hideBasisVecBox = new JCheckBox();
        JLabel hideBasisVecLabel = new JLabel("Hide Basis-Vectors:");
        JPanel hideBasisVec = new JPanel();
        hideBasisVec.setMaximumSize(new Dimension(200,30));
        hideBasisVec.add(hideBasisVecLabel);
        hideBasisVec.add(hideBasisVecBox);

        transformationInputGrid.setAlignmentX(Component.CENTER_ALIGNMENT);
        setTransformationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hideDeterminant.setAlignmentX(Component.CENTER_ALIGNMENT);
        showEigenvectors.setAlignmentX(Component.CENTER_ALIGNMENT);
        basisGrid.setAlignmentX(Component.CENTER_ALIGNMENT);
        setBasisButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        hideBasisVec.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel matrixPanel = new JPanel();
        matrixPanel.setLayout(new BoxLayout(matrixPanel, BoxLayout.Y_AXIS));
        matrixPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        matrixPanel.add(transformationInputGrid);
        matrixPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        matrixPanel.add(setTransformationButton);
        matrixPanel.add(Box.createRigidArea(new Dimension(0,10)));
        matrixPanel.add(hideDeterminant);
        matrixPanel.add(Box.createRigidArea(new Dimension(0,10)));
        matrixPanel.add(showEigenvectors);
        matrixPanel.add(Box.createRigidArea(new Dimension(0,10)));
        matrixPanel.add(basisGrid);
        matrixPanel.add(Box.createRigidArea(new Dimension(0,10)));
        matrixPanel.add(setBasisButton);
        matrixPanel.add(hideBasisVec);



        //TODO: Add all GUI components with an action to class GUIComponents and here
        GUIComponents components = new GUIComponents(
                addVectorButton, vectorXField, vectorYField, vectorColorButton, colorPreview, removeLastVectorButton, transformationSlider, sumVectorsButton,
                transformationFieldA, transformationFieldB, transformationFieldC, transformationFieldD, setTransformationButton, hideDetBox,
                basisFieldA, basisFieldB, basisFieldC, basisFieldD, setBasisButton, hideBasisVecBox, showEigenVectorsBox

        );

        new GUIController(testGui,components);
        frame.setLayout(new BorderLayout());
        frame.add(testGui, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(inputPanel2, BorderLayout.SOUTH);
        frame.add(matrixPanel, BorderLayout.EAST);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
