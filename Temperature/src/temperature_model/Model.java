package temperature_model;

public interface Model {
    Scale[] scales();

    double convertTemperature(Scale inputScale, Scale outputScale, double inputTemperature);
}