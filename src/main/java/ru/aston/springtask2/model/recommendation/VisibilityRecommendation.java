package ru.aston.springtask2.model.recommendation;

import ru.aston.springtask2.model.weather.WeatherData;

public class VisibilityRecommendation implements RecommendationStrategy {
    @Override
    public String getRecommendation(WeatherData weatherData) {
        Double visKm = weatherData.getCurrent().getVisKm();
        if (visKm != null && visKm < 1) {
            return "Низкая видимость, акуратнее на дороге\n";
        }
        return "";
    }
}
