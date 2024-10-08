package ru.aston.springtask2.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.aston.springtask2.model.weather.WeatherData;

@FeignClient(value = "weather-service", url = "http://api.weatherapi.com/v1", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface WeatherClient {
    @GetMapping(value = "/current.json")
    WeatherData getWeather(@RequestParam("key") String apiKey,
                           @RequestParam("q") String coordinates,
                           @RequestParam("aqi") String aqi);
}
