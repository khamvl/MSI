package temperature_view;

import temperature_model.Model;
import temperature_model.Scale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GuiView implements View {
    private JTextField inputTextField;
    private JTextField outputTextField;
    private JComboBox<Scale> inputScaleComboBox;
    private JComboBox<Scale> outputScaleComboBox;
    private JButton converterButton;

    @Override
    public void start(Model converter, ActionListener actionListener) {
        SwingUtilities.invokeLater(() -> {
            inputTextField = new JTextField(12);
            outputTextField = new JTextField(12);

            inputScaleComboBox = new JComboBox<>(converter.scales());
            outputScaleComboBox = new JComboBox<>(converter.scales());

            inputTextField.setHorizontalAlignment(JTextField.CENTER);

            outputTextField.setHorizontalAlignment(JTextField.CENTER);
            outputTextField.setEditable(false);

            JFrame window = new JFrame("Temperature converter");
            window.setSize(450, 200);
            window.setMinimumSize(new Dimension(450, 200));
            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocationRelativeTo(null);

            window.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            constraints.ipady = 3;
            constraints.weightx = 0.01;

            constraints.gridx = 0;
            constraints.gridy = 0;
            window.add(new JLabel("Input temperature"), constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            window.add(inputTextField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            window.add(inputScaleComboBox, constraints);

            converterButton = new JButton("convert");
            converterButton.addActionListener(actionListener);
            constraints.gridx = 2;
            constraints.gridy = 1;
            window.add(converterButton, constraints);

            constraints.gridx = 4;
            constraints.gridy = 0;
            window.add(new JLabel("Output temperature"), constraints);

            constraints.gridx = 4;
            constraints.gridy = 1;
            window.add(outputTextField, constraints);

            constraints.gridx = 4;
            constraints.gridy = 2;
            window.add(outputScaleComboBox, constraints);
        });
    }

    @Override
    public Scale getInputScale() {
        return (Scale) inputScaleComboBox.getSelectedItem();
    }

    @Override
    public Scale getOutputScale() {
        return (Scale) outputScaleComboBox.getSelectedItem();
    }

    @Override
    public double getInputTemperature() {
        return Double.parseDouble(inputTextField.getText());
    }

    @Override
    public void setOutputTemperature(double temperatureString) {
        outputTextField.setText(String.valueOf(temperatureString));
    }

    @Override
    public void showErrorMessage() {
        JOptionPane.showMessageDialog(
                converterButton,
                "The temperature value must be a number!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}