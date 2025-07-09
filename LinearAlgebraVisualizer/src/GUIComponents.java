import javax.swing.*;

public record GUIComponents (
        //TODO: Add all GUI components with an action
        JButton showVectorButton,
        JTextField vectorXField,
        JTextField vectorYField,
        JButton vectorColorButton,
        JPanel colorPreview,
        JButton removeLastVectorButton,
        JSlider transformationSlider,
        JButton sumVectorsButton,
        JTextField matrixFieldA,
        JTextField matrixFieldB,
        JTextField matrixFieldC,
        JTextField matrixFieldD,
        JButton setMatrixButton,
        JCheckBox hideDetBox
) {}
