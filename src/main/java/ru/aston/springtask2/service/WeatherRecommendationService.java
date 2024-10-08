package ru.aston.springtask2.service;

import ru.aston.springtask2.model.weather.WeatherData;

public interface WeatherRecommendationService {
    String getRecommendation(WeatherData weatherData);
}
