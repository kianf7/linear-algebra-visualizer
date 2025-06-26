import javax.swing.*;
import java.awt.*;

public class GUIController {
    //TODO: add objects for all elements that have a listener
    private final GUI gui;
    private final JButton showVectorButton;
    private final JButton SumUpButton;
    private final JTextField vectorXField;
    private final JTextField vectorYField;
    private final VectorManager vectorManager;


    public GUIController(GUI gui, JButton showVectorButton, JButton sumUpButton, JTextField vectorXField, JTextField vectorYField) {
        this.gui = gui;
        this.showVectorButton = showVectorButton;
        this.SumUpButton = sumUpButton;
        this.vectorXField = vectorXField;
        this.vectorYField = vectorYField;

        this.vectorManager = new VectorManager(gui);
        initializeListeners();
    }


    public void initializeListeners() {
        //TODO: add all elements that have a listener

        showVectorButton.addActionListener(e -> {
            // if mode is switched clear vectors
            if (vectorManager.getMode() != 0) {
                vectorManager.setMode(0);
                vectorManager.clearVectors();
                gui.repaint();
            }
            try {
                double inputX = Double.parseDouble(vectorXField.getText());
                double inputY = Double.parseDouble(vectorYField.getText());
                vectorXField.setText("");
                vectorYField.setText("");
                String name = "v" +vectorManager.getVectorAmount();
                DrawableVector inputVector = new DrawableVector(inputX, inputY, Color.green,name);
                inputVector.setOriginX(gui.getWidth()/2);
                inputVector.setOriginY(gui.getHeight()/2);
                vectorManager.addVector(inputVector);
                gui.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gui, "Invalid Input!");
            }
        });
        SumUpButton.addActionListener(e -> {
            // if mode is switched clear vectors
            if (vectorManager.getMode() != 1) {
                vectorManager.setMode(1);
                vectorManager.clearVectors();
                gui.repaint();
            }
            try {
                double inputX = Double.parseDouble(vectorXField.getText());
                double inputY = Double.parseDouble(vectorYField.getText());
                vectorXField.setText("");
                vectorYField.setText("");
                String name = "v" +vectorManager.getVectorAmount();
                DrawableVector inputVector = new DrawableVector(inputX, inputY, Color.green,name);
                vectorManager.addVector(inputVector);
                gui.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gui, "Invalid Input!");
            }
        });

        gui.setVectorManager(vectorManager);

    }


}

