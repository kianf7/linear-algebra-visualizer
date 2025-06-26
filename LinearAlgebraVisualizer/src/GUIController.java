import javax.swing.*;
import java.awt.*;

public class GUIController {
    //TODO: add objects for all elements that have a listener
    private final GUI gui;
    private final VectorManager vectorManager;
    private final Matrix2D transformation;
    private final GUIComponents components;

    public GUIController(GUI gui, GUIComponents components, Matrix2D transformation) {
        this.gui = gui;
        this.transformation = transformation;
        this.vectorManager = new VectorManager(gui);
        this.components = components;
        initializeListeners();
    }


    public void initializeListeners() {
        //TODO: add all elements that have a listener

        components.showVectorButton().addActionListener(e -> {
            try {
                double inputX = Double.parseDouble(components.vectorXField().getText());
                double inputY = Double.parseDouble(components.vectorYField().getText());
                components.vectorXField().setText("");
                components.vectorYField().setText("");
                String name = "v" +vectorManager.getVectorAmount();
                DrawableVector inputVector = new DrawableVector(inputX, inputY,Color.white,name);
                vectorManager.addVector(inputVector);
                gui.repaint();
            } catch (NumberFormatException ex) {
                components.vectorXField().setText("");
                components.vectorYField().setText("");
                JOptionPane.showMessageDialog(gui, "Invalid Input!");
            }
        });

        components.vectorColorButton().addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(gui, "Choose color", Color.white);
            if (chosen != null) {
                components.colorPreview().setBackground(chosen);
                vectorManager.setCurrentColor(chosen);
            }

        });

        components.removeLastVectorButton().addActionListener(e -> {
            if (vectorManager.removeVector() == -1) {
                JOptionPane.showMessageDialog(gui,"No vector to remove!");
            }
        });

        components.transformationSlider().addChangeListener(e -> {
            int value = components.transformationSlider().getValue();
            double t = value / 100.0;

            Matrix2D interpolated = Matrix2D.interpolate(Matrix2D.identity(), transformation, t);
            gui.setTransformation(interpolated);
        });



        gui.setVectorManager(vectorManager);

    }


}

