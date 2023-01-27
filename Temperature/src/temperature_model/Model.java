package temperature_model;

public interface Model {
    Scale[] scales();

    double getConvertedTemperature(Scale inputScale, Scale outputScale, double inputTemperature);
}