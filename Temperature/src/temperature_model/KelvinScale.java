package temperature_model;

public class KelvinScale implements Scale {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertInCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public String toString() {
        return "Kelvin scale";
    }
}