package ru.aston.springtask2.model.recommendation;

import ru.aston.springtask2.model.weather.WeatherData;

public class RainRecommendation implements RecommendationStrategy {
    @Override
    public String getRecommendation(WeatherData weatherData) {
        Double precipMm = weatherData.getCurrent().getPrecipMm();
        if (precipMm != null && precipMm > 0) {
            return "Идет дождь, возьмите зонтик\n";
        }
        return "";
    }
}
