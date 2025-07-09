import javax.swing.*;
import java.awt.*;

public class GUIController {
    private final GUI gui;
    private final VectorManager vectorManager;
    private final GUIComponents components;
    private final MatrixManager matrixManager;

    public GUIController(GUI gui, GUIComponents components) {
        this.gui = gui;
        this.components = components;
        this.vectorManager = new VectorManager();
        this.matrixManager = new MatrixManager();
        initializeListeners();
    }


    public void initializeListeners() {
        //TODO: add all elements that have a listener

        components.showVectorButton().addActionListener(_ -> {
            try {
                if (vectorManager.getDrawingMode() != DrawingMode.NORMAL) {
                    vectorManager.setDrawingMode(DrawingMode.NORMAL);
                }

                double inputX = Double.parseDouble(components.vectorXField().getText());
                double inputY = Double.parseDouble(components.vectorYField().getText());
                components.vectorXField().setText("");
                components.vectorYField().setText("");

                String name = "v" +vectorManager.getVectorAmount();
                //TODO: Maybe add mode/button to change between static & transformable vector
                TransformableVector inputVector = new TransformableVector(inputX, inputY,Color.white,name);
                vectorManager.addVector(inputVector);
                gui.repaint();
            } catch (NumberFormatException ex) {
                components.vectorXField().setText("");
                components.vectorYField().setText("");
                JOptionPane.showMessageDialog(gui, "Invalid Input!");
            }
        });

        components.vectorColorButton().addActionListener(_ -> {
            Color chosen = JColorChooser.showDialog(gui, "Choose color", Color.white);
            if (chosen != null) {
                components.colorPreview().setBackground(chosen);
                vectorManager.setCurrentColor(chosen);
            }

        });

        components.removeLastVectorButton().addActionListener(_ -> {
            if (vectorManager.removeVector() == -1) {
                JOptionPane.showMessageDialog(gui,"No vector to remove!");
            }
            vectorManager.setDrawingMode(DrawingMode.NORMAL);
            gui.repaint();
        });

        components.transformationSlider().addChangeListener(_ -> {
            int value = components.transformationSlider().getValue();
            double t = value / 1000.0;

            Matrix2D interpolated = Matrix2D.interpolate(Matrix2D.identity(), matrixManager.getTarget(), t);
            matrixManager.setInterpolated(interpolated);
            gui.repaint();
        });

        components.sumVectorsButton().addActionListener(_ -> {
            if (vectorManager.getDrawingMode() != DrawingMode.SUM) {
                vectorManager.setDrawingMode(DrawingMode.SUM);
                gui.repaint();
            }
        });

        gui.addMouseWheelListener(e -> {
            if (e.getWheelRotation() < 0) {
                int newScale = (int) (gui.getScale() * 1.1);
                gui.setScale(newScale);
                gui.repaint();
            } else if (e.getWheelRotation() > 0 && gui.getScale() > 10) {
                int newScale = (int) (gui.getScale() / 1.1);
                gui.setScale(newScale);
                gui.repaint();
            }
        });

        components.setMatrixButton().addActionListener(e -> {
            try {
                double inputA = Double.parseDouble(components.matrixFieldA().getText());
                double inputB = Double.parseDouble(components.matrixFieldB().getText());
                double inputC = Double.parseDouble(components.matrixFieldC().getText());
                double inputD = Double.parseDouble(components.matrixFieldD().getText());

                Matrix2D target = new Matrix2D(inputA,inputB,inputC,inputD);
                matrixManager.setTarget(target);
                matrixManager.setInterpolated(Matrix2D.identity());
                components.transformationSlider().setValue(0);

                gui.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gui,"Invalid Matrix!");
            }
        });

        components.hideDetBox().addActionListener(e -> {
            boolean hideDet = components.hideDetBox().isSelected();
            matrixManager.setHideDet(hideDet);
            gui.repaint();
        });

        gui.setVectorManager(vectorManager);
        gui.setMatrixManager(matrixManager);
    }


}

