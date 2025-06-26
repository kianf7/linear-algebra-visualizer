import javax.swing.*;
import java.awt.*;

public class GUIController {
    //TODO: add objects for all elements that have a listener
    private final GUI gui;
    private final JButton showVectorButton;
    private final JTextField vectorXField;
    private final JTextField vectorYField;
    private final VectorManager vectorManager;
    private final JButton vectorColorButton;
    private final JPanel colorPreview;
    private final JButton removeLastVectorButton;
    private final JSlider transformationSlider;
    private final Matrix2D transformation;

    public GUIController(GUI gui, JButton showVectorButton, JTextField vectorXField, JTextField vectorYField, JButton vectorColorButton, JPanel colorPreview, JButton removeLastVectorButton, JSlider transformationSlider, Matrix2D transformation) {
        this.gui = gui;
        this.showVectorButton = showVectorButton;
        this.vectorXField = vectorXField;
        this.vectorYField = vectorYField;
        this.vectorColorButton = vectorColorButton;
        this.colorPreview = colorPreview;
        this.removeLastVectorButton = removeLastVectorButton;
        this.transformationSlider = transformationSlider;
        this.transformation = transformation;

        this.vectorManager = new VectorManager(gui);
        initializeListeners();
    }


    public void initializeListeners() {
        //TODO: add all elements that have a listener

        showVectorButton.addActionListener(e -> {
            try {
                double inputX = Double.parseDouble(vectorXField.getText());
                double inputY = Double.parseDouble(vectorYField.getText());
                vectorXField.setText("");
                vectorYField.setText("");
                String name = "v" +vectorManager.getVectorAmount();
                DrawableVector inputVector = new DrawableVector(inputX, inputY,Color.white,name);
                vectorManager.addVector(inputVector);
                gui.repaint();
            } catch (NumberFormatException ex) {
                vectorXField.setText("");
                vectorYField.setText("");
                JOptionPane.showMessageDialog(gui, "Invalid Input!");
            }
        });

        vectorColorButton.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(gui, "Choose color", Color.white);
            if (chosen != null) {
                colorPreview.setBackground(chosen);
                vectorManager.setCurrentColor(chosen);
            }

        });

        removeLastVectorButton.addActionListener(e -> {
            if (vectorManager.removeVector() == -1) {
                JOptionPane.showMessageDialog(gui,"No vector to remove!");
            }
        });

        transformationSlider.addChangeListener(e -> {
            int value = transformationSlider.getValue();
            double t = value / 100.0;

            Matrix2D interpolated = Matrix2D.interpolate(Matrix2D.identity(), transformation, t);
            gui.setTransformation(interpolated);
        });



        gui.setVectorManager(vectorManager);

    }


}

