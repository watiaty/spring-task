package ru.aston.springtask2.model.recommendation;

import ru.aston.springtask2.model.weather.WeatherData;

public class TemperatureRecommendation implements RecommendationStrategy {
    @Override
    public String getRecommendation(WeatherData weatherData) {
        Double tempC = weatherData.getCurrent().getTempC();
        if (tempC < 0) {
            return "На улице холодно, одевайтесь потеплее\n";
        } else if (tempC > 30) {
            return "На улице очень жарко, не забывайте пить воду\n";
        } else {
            return "На улице хорошая погода\n";
        }
    }
}
