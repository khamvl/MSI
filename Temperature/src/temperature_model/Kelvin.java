package temperature_model;

public class Kelvin implements Scale {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273;
    }

    @Override
    public double convertInCelsius(double temperature) {
        return temperature - 273;
    }

    @Override
    public String toString() {
        return "Kelvin scale";
    }
}