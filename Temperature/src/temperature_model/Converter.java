package temperature_model;

public record Converter(Scale[] scales) implements Model {
    @Override
    public double getConvertedTemperature(Scale inputScale, Scale outputScale, double inputTemperature) {
        double temperatureInCelsius = inputScale.convertInCelsius(inputTemperature);

        return outputScale.convertFromCelsius(temperatureInCelsius);
    }
}