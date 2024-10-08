package ru.aston.springtask2.model.recommendation;

import ru.aston.springtask2.model.weather.WeatherData;

public interface RecommendationStrategy {
    String getRecommendation(WeatherData weatherData);
}
