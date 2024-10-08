package ru.aston.springtask2.model.recommendation;

import ru.aston.springtask2.model.weather.WeatherData;

public class HumidityRecommendation implements RecommendationStrategy {
    @Override
    public String getRecommendation(WeatherData weatherData) {
        Integer humidity = weatherData.getCurrent().getHumidity();
        if (humidity > 70) {
            return "Высокая влажность\n";
        } else if (humidity < 30) {
            return "Низкая влажность\n";
        }
        return "";
    }
}
