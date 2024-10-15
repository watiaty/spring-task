package ru.aston.springtask2.model.recommendation;

import ru.aston.springtask2.model.weather.WeatherData;

public class WindRecommendation implements RecommendationStrategy {
    @Override
    public String getRecommendation(WeatherData weatherData) {
        Double windKph = weatherData.getCurrent().getWindKph();
        if (windKph != null && windKph > 20) {
            return "Сильный ветер, будьте аккуратнее\n";
        }
        return "";
    }
}
