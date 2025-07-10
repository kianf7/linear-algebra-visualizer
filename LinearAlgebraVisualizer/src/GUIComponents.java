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
        JTextField transformationFieldA,
        JTextField transformationFieldB,
        JTextField transformationFieldC,
        JTextField transformationFieldD,
        JButton setTransformationButton,
        JCheckBox hideDetBox,
        JTextField basisFieldA,
        JTextField basisFieldB,
        JTextField basisFieldC,
        JTextField basisFieldD,
        JButton setBasisButton,
        JCheckBox hideBasisVecBox
) {}
