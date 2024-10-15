package ru.aston.springtask2.model.recommendation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.aston.springtask2.model.weather.WeatherData;
import ru.aston.springtask2.util.DataUtils;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class VisibilityRecommendationTest {
    private final VisibilityRecommendation visibilityRecommendation = new VisibilityRecommendation();

    private static Stream<Arguments> visibilityDataProvider() {
        return Stream.of(
                Arguments.of(0.5, "Низкая видимость, акуратнее на дороге\n"),
                Arguments.of(5.0, "")
        );
    }

    @ParameterizedTest
    @MethodSource("visibilityDataProvider")
    @DisplayName("Test recommendation based on visibility")
    void givenVisibility_whenGetRecommendation_thenReturnsExpectedMessage(double visibility, String expectedMessage) {
        // given
        WeatherData weatherData = DataUtils.getWeatherData();
        weatherData.getCurrent().setVisKm(visibility);
        // when
        String recommendation = visibilityRecommendation.getRecommendation(weatherData);
        // then
        assertThat(recommendation).isEqualTo(expectedMessage);
    }
}
