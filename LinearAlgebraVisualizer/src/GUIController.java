import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * {@code GUIController} handles interactions and logic of {@code GUI}.
 */

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
        JPopupMenu menu = new JPopupMenu();
        JMenuItem resetScaleItem = new JMenuItem("Reset scale");
        menu.add(resetScaleItem);

        //TODO: add all listeners

        components.addVectorButton().addActionListener(_ -> {
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

            Matrix2D interpolated = Matrix2D.interpolate(matrixManager.getSpaceBasis(), matrixManager.getTarget(), t);
            matrixManager.setInterpolated(interpolated);
            gui.repaint();
        });

        components.sumVectorsButton().addActionListener(_ -> {
            if (vectorManager.getDrawingMode() != DrawingMode.SUM) {
                if (vectorManager.getVectorAmount() == 0) {
                    JOptionPane.showMessageDialog(gui, "Input vectors first!");
                    return;
                }
                vectorManager.setDrawingMode(DrawingMode.SUM);
                gui.repaint();
            }
        });

        gui.addMouseWheelListener(e -> {
            final int MAX_SCALE = 30000000;
            if (e.getWheelRotation() < 0) {
                if (gui.getScale() > MAX_SCALE) {
                    return;
                }

                int newScale = (int) (gui.getScale() * 1.1);
                gui.setScale(newScale);
                gui.repaint();
            } else if (e.getWheelRotation() > 0 && gui.getScale() > 10) {
                int newScale = (int) (gui.getScale() / 1.1);
                gui.setScale(newScale);
                gui.repaint();
            }
        });

        components.setTransformationButton().addActionListener(_ -> {
            try {
                double inputA = Double.parseDouble(components.transformationFieldA().getText());
                double inputB = Double.parseDouble(components.transformationFieldB().getText());
                double inputC = Double.parseDouble(components.transformationFieldC().getText());
                double inputD = Double.parseDouble(components.transformationFieldD().getText());

                Matrix2D target = new Matrix2D(inputA,inputB,inputC,inputD);
                matrixManager.setTarget(target);
                matrixManager.setInterpolated(matrixManager.getSpaceBasis());
                components.transformationSlider().setValue(0);

                gui.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gui,"Invalid Matrix!");
            }
        });

        components.hideDetBox().addActionListener(_ -> {
            boolean isHideDet = components.hideDetBox().isSelected();
            matrixManager.setHideDet(isHideDet);
            gui.repaint();
        });

        components.setBasisButton().addActionListener(_ -> {
            try {
                double inputA = Double.parseDouble(components.basisFieldA().getText());
                double inputB = Double.parseDouble(components.basisFieldB().getText());
                double inputC = Double.parseDouble(components.basisFieldC().getText());
                double inputD = Double.parseDouble(components.basisFieldD().getText());

                Matrix2D newBasis = new Matrix2D(inputA, inputB, inputC, inputD);

                if (newBasis.getDeterminant() == 0) {
                    JOptionPane.showMessageDialog(gui,"Basis must have Dimension 2 !");
                } else {
                    matrixManager.setSpaceBasis(newBasis);
                    components.transformationSlider().setValue(1);
                    components.transformationSlider().setValue(0);
                    gui.repaint();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gui, "Invalid Input!");
            }
        });

        components.hideBasisVecBox().addActionListener(_ -> {
            boolean isHideBasis = components.hideBasisVecBox().isSelected();
            matrixManager.setHideBasisVectors(isHideBasis);
            gui.repaint();
        });

        components.showEigenvectorsBox().addActionListener(_ -> {
            boolean isShowEigenvectors = components.showEigenvectorsBox().isSelected();
            matrixManager.setShowEigenvectors(isShowEigenvectors);
            gui.repaint();
        });

        resetScaleItem.addActionListener(_ -> {
            gui.setScale(50);
            gui.repaint();
        });

        gui.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });



        gui.setVectorManager(vectorManager);
        gui.setMatrixManager(matrixManager);
    }


}

