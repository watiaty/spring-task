package ru.aston.springtask2.model.recommendation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.aston.springtask2.model.weather.WeatherData;
import ru.aston.springtask2.util.DataUtils;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HumidityRecommendationTest {
    private final HumidityRecommendation humidityRecommendation = new HumidityRecommendation();

    private static Stream<Arguments> humidityDataProvider() {
        return Stream.of(
                Arguments.of(80, "Высокая влажность\n"),
                Arguments.of(20, "Низкая влажность\n"),
                Arguments.of(50, "")
        );
    }

    @ParameterizedTest
    @MethodSource("humidityDataProvider")
    @DisplayName("Test recommendation based on humidity")
    void givenHumidity_whenGetRecommendation_thenReturnsExpectedMessage(Integer humidity, String expectedMessage) {
        // given
        WeatherData weatherData = DataUtils.getWeatherData();
        weatherData.getCurrent().setHumidity(humidity);
        // when
        String recommendation = humidityRecommendation.getRecommendation(weatherData);
        // then
        assertThat(recommendation).isEqualTo(expectedMessage);
    }
}
