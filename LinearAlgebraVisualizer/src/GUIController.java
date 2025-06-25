import javax.swing.*;
import java.awt.*;

public class GUIController {
    //TODO: add objects for all elements that have a listener
    private final GUI gui;
    private final JButton showVectorButton;
    private final JTextField vectorXField;
    private final JTextField vectorYField;
    private final VectorManager vectorManager;

    public GUIController(GUI gui, JButton showVectorButton, JTextField vectorXField, JTextField vectorYField) {
        this.gui = gui;
        this.showVectorButton = showVectorButton;
        this.vectorXField = vectorXField;
        this.vectorYField = vectorYField;

        this.vectorManager = new VectorManager(gui);
        initializeListeners();
    }


    public void initializeListeners() {
        //TODO: add all elements that have a listener

        showVectorButton.addActionListener(e -> {
            try {
                double inputX = Double.parseDouble(vectorXField.getText());
                double inputY = Double.parseDouble(vectorYField.getText());

                DrawableVector inputVector = new DrawableVector(inputX, inputY, Color.green);
                vectorManager.addVector(inputVector);
                gui.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(gui, "Invalid Input!");
            }
        });



        gui.setVectorManager(vectorManager);

    }


}

