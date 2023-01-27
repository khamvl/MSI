package temperature_model;

public class Fahrenheit implements Scale {
    @Override
    public double convertFromCelsius(double temperature) {
        return (9.0 / 5) * temperature + 32;
    }

    @Override
    public double convertInCelsius(double temperature) {
        return (5.0 / 9) * (temperature - 32);
    }

    @Override
    public String toString() {
        return "Fahrenheit scale";
    }
}