package temperature_view;

import temperature_model.Model;
import temperature_model.Scale;

import java.awt.event.ActionListener;

public interface View {
    void start(Model converter, ActionListener actionListener);

    Scale getInputScale();

    Scale getOutputScale();

    double getInputTemperature();

    void setOutputTemperature(double temperatureString);

    void showErrorMessage();
}