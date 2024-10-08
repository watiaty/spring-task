package ru.aston.springtask2.model.recommendation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.aston.springtask2.model.weather.WeatherData;
import ru.aston.springtask2.util.DataUtils;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RainRecommendationTest {
    private final RainRecommendation rainRecommendation = new RainRecommendation();

    private static Stream<Arguments> rainDataProvider() {
        return Stream.of(
                Arguments.of(10.0, "Идет дождь, возьмите зонтик\n"),
                Arguments.of(0.0, ""),
                Arguments.of(null, "")
        );
    }

    @ParameterizedTest
    @MethodSource("rainDataProvider")
    @DisplayName("Test recommendation based on rain")
    void givenPrecipitation_whenGetRecommendation_thenReturnsExpectedMessage(Double precipMm, String expectedMessage) {
        // given
        WeatherData weatherData = DataUtils.getWeatherData();
        weatherData.getCurrent().setPrecipMm(precipMm);
        // when
        String recommendation = rainRecommendation.getRecommendation(weatherData);
        // then
        assertThat(recommendation).isEqualTo(expectedMessage);
    }
}
