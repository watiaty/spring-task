package ru.aston.springtask2.service.impl;

import org.springframework.stereotype.Service;
import ru.aston.springtask2.model.recommendation.HumidityRecommendation;
import ru.aston.springtask2.model.recommendation.RainRecommendation;
import ru.aston.springtask2.model.recommendation.TemperatureRecommendation;
import ru.aston.springtask2.model.recommendation.VisibilityRecommendation;
import ru.aston.springtask2.model.recommendation.WindRecommendation;
import ru.aston.springtask2.model.weather.WeatherData;
import ru.aston.springtask2.service.WeatherRecommendationService;

@Service
public class WeatherRecommendationServiceImpl implements WeatherRecommendationService {
    private final TemperatureRecommendation temperatureRecommendation;
    private final HumidityRecommendation humidityRecommendation;
    private final WindRecommendation windRecommendation;
    private final VisibilityRecommendation visibilityRecommendation;
    private final RainRecommendation rainRecommendation;

    public WeatherRecommendationServiceImpl() {
        this.temperatureRecommendation = new TemperatureRecommendation();
        this.humidityRecommendation = new HumidityRecommendation();
        this.windRecommendation = new WindRecommendation();
        this.visibilityRecommendation = new VisibilityRecommendation();
        this.rainRecommendation = new RainRecommendation();
    }

    /**
     * Получает рекомендации на оснавании погоды
     *
     * @param weatherData Характеристики погоды
     * @return Возвращает строку с рекомендациями
     */
    public String getRecommendation(WeatherData weatherData) {
        StringBuilder recommendations = new StringBuilder();
        recommendations.append(temperatureRecommendation.getRecommendation(weatherData));
        recommendations.append(humidityRecommendation.getRecommendation(weatherData));
        recommendations.append(windRecommendation.getRecommendation(weatherData));
        recommendations.append(visibilityRecommendation.getRecommendation(weatherData));
        recommendations.append(rainRecommendation.getRecommendation(weatherData));

        return recommendations.toString();
    }
}

