import javax.swing.*;
import java.awt.*;

public class GUIController {
    private final GUI gui;
    private final VectorManager vectorManager;
    private final GUIComponents components;
    private final MatrixManager matrixManager;
    private final Matrix2D testTransformation = new Matrix2D(-5,3,-2,5); //This will later be input over gui

    public GUIController(GUI gui, GUIComponents components) {
        this.gui = gui;
        this.components = components;
        this.vectorManager = new VectorManager();
        this.matrixManager = new MatrixManager();
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

                if (vectorManager.getDrawingMode() != 0) {
                    vectorManager.setDrawingMode(0);
                    gui.repaint();
                }

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
            vectorManager.setDrawingMode(0);
            gui.repaint();
        });

        components.transformationSlider().addChangeListener(e -> {
            int value = components.transformationSlider().getValue();
            double t = value / 1000.0;

            Matrix2D interpolated = Matrix2D.interpolate(Matrix2D.identity(), testTransformation, t);
            matrixManager.setTransformation(interpolated);
            gui.repaint();
        });

        components.sumVectorsButton().addActionListener(e -> {
            if (vectorManager.getDrawingMode() != 1) {
                vectorManager.setDrawingMode(1);
                gui.repaint();
            }
        });

        gui.setVectorManager(vectorManager);
        gui.setMatrixManager(matrixManager);
    }


}

