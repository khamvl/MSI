package temperature_main;

import temperature_model.*;
import temperature_view.GuiView;
import temperature_view.View;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = {
                new CelsiusScale(),
                new FahrenheitScale(),
                new KelvinScale()
        };

        Model converter = new Converter(scales);
        View guiView = new GuiView();

        guiView.start(converter, e -> {
            try {
                guiView.setOutputTemperature(converter.convertTemperature(
                        guiView.getInputScale(),
                        guiView.getOutputScale(),
                        guiView.getInputTemperature()
                ));
            } catch (NumberFormatException exception) {
                guiView.showErrorMessage();
            }
        });
    }
}