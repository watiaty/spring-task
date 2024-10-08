package ru.aston.springtask2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.aston.springtask2.client.WeatherClient;
import ru.aston.springtask2.model.weather.WeatherData;
import ru.aston.springtask2.service.WeatherRecommendationService;
import ru.aston.springtask2.service.WeatherService;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherRecommendationService weatherRecommendationServiceImpl;

    @Value("${weather.api-key}")
    private String apiKey;

    /**
     * Получает рекомендации по погоде
     *
     * @param coordinates Долгота и широта
     * @return Возвращает строку с рекомендациями
     */
    public String getRecommendationByCoordinates(String coordinates) {
        WeatherData weatherData = getWeatherDataByCoordinates(coordinates);
        return weatherRecommendationServiceImpl.getRecommendation(weatherData);
    }

    /**
     * Получает характеристики погоды в определенном местоположении
     *
     * @param coordinates Долгота и широта
     * @return Возвращает погоду
     */
    private WeatherData getWeatherDataByCoordinates(String coordinates) {
        String aqi = "yes";
        return weatherClient.getWeather(apiKey, coordinates, aqi);
    }
}
