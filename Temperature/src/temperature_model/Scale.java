package temperature_model;

public interface Scale {
    double convertFromCelsius(double temperature);

    double convertInCelsius(double temperature);
}