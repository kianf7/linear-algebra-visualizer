import javax.swing.*;

/**
 * {@code GUIComponents} contains all interactive GUI components.
 * @param addVectorButton
 * @param vectorXField
 * @param vectorYField
 * @param vectorColorButton
 * @param colorPreview
 * @param removeLastVectorButton
 * @param transformationSlider
 * @param sumVectorsButton
 * @param transformationFieldA
 * @param transformationFieldB
 * @param transformationFieldC
 * @param transformationFieldD
 * @param setTransformationButton
 * @param hideDetBox
 * @param basisFieldA
 * @param basisFieldB
 * @param basisFieldC
 * @param basisFieldD
 * @param setBasisButton
 * @param hideBasisVecBox
 * @param showEigenvectorsBox
 */

public record GUIComponents (
        //TODO: Add all GUI components with an action
        JButton addVectorButton,
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
        JCheckBox hideBasisVecBox,
        JCheckBox showEigenvectorsBox
) {}
