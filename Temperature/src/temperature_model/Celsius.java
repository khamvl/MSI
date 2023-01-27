package temperature_model;

public class Celsius implements Scale {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertInCelsius(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "Celsius scale";
    }
}