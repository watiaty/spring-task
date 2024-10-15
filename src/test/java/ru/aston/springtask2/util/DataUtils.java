package ru.aston.springtask2.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.aston.springtask2.dtos.request.AddAttractionRequestDTO;
import ru.aston.springtask2.dtos.request.AddLocalityRequestDTO;
import ru.aston.springtask2.model.Attraction;
import ru.aston.springtask2.model.AttractionType;
import ru.aston.springtask2.model.Locality;
import ru.aston.springtask2.model.weather.Condition;
import ru.aston.springtask2.model.weather.Current;
import ru.aston.springtask2.model.weather.Location;
import ru.aston.springtask2.model.weather.WeatherData;

import java.util.ArrayList;
import java.util.Date;

public class DataUtils {
    public static Attraction getHomelParkTransient() {
        return Attraction.builder()
                .name("aaa")
                .creationDate(new Date())
                .type(AttractionType.PARK)
                .description("Park near river")
                .locality(getHomelLocalityTransient())
                .assistance(new ArrayList<>())
                .build();
    }

    public static Attraction getHomelParkPersisted() {
        return Attraction.builder()
                .id(1L)
                .name("aaa")
                .creationDate(new Date())
                .type(AttractionType.PARK)
                .description("Park near river")
                .locality(getHomelLocalityTransient())
                .assistance(new ArrayList<>())
                .build();
    }

    public static Attraction getHomelPark2Transient() {
        return Attraction.builder()
                .name("bbb")
                .creationDate(new Date())
                .type(AttractionType.PARK)
                .description("Park near river")
                .locality(getHomelLocalityTransient())
                .assistance(new ArrayList<>())
                .build();
    }

    public static Attraction getHomelPark2Persisted() {
        return Attraction.builder()
                .id(2L)
                .name("bbb")
                .creationDate(new Date())
                .type(AttractionType.PARK)
                .description("Park near river")
                .locality(getHomelLocalityTransient())
                .assistance(new ArrayList<>())
                .build();
    }

    public static Attraction getHomelMuseumTransient() {
        return Attraction.builder()
                .name("aaa")
                .creationDate(new Date())
                .type(AttractionType.MUSEUM)
                .description("Park near river")
                .locality(getHomelLocalityTransient())
                .assistance(new ArrayList<>())
                .build();
    }

    public static Locality getHomelLocalityTransient() {
        return Locality.builder()
                .name("Gomel")
                .region("Gomel state")
                .latitude(60.4)
                .longitude(76.2)
                .build();
    }

    public static Locality getHomelLocalityPersisted() {
        return Locality.builder()
                .id(1L)
                .name("Gomel")
                .region("Gomel state")
                .latitude(60.4)
                .longitude(76.2)
                .build();
    }

    public static AddLocalityRequestDTO getHomelLocalityDTOTransient() {
        return AddLocalityRequestDTO.builder()
                .name("Gomel")
                .region("Gomel state")
                .latitude(0d)
                .longitude(0d)
                .build();
    }

    public static AddAttractionRequestDTO getAttractionRequestDTOParkPersisted() {
        return AddAttractionRequestDTO.builder()
                .name("aaa")
                .creationDate(new Date())
                .localityId(1L)
                .type(AttractionType.PARK)
                .description("Park near river")
                .assistance(new ArrayList<>())
                .build();
    }

    public static Pageable getPageableWithSortByName() {
        return PageRequest.of(0, 1, Sort.by("name"));
    }

    public static Pageable getPageable() {
        return PageRequest.of(0, 1);
    }

    public static WeatherData getWeatherData() {
        return WeatherData.builder()
                .location(getLocation())
                .current(getCurrent())
                .build();
    }

    public static Location getLocation() {
        return Location.builder()
                .name("Porya")
                .region("Murmansk")
                .country("Russia")
                .lat(66.77d)
                .lon(33.63d)
                .tzId("Europe/Moscow")
                .localtimeEpoch(1720262756)
                .localtime("2024-07-06 13:45")
                .build();
    }

    public static Condition getCondition() {
        return Condition.builder()
                .text("Light rain")
                .icon("//cdn.weatherapi.com/weather/64x64/night/296.png")
                .code(1183)
                .build();
    }

    public static Current getCurrent() {
        return Current.builder()
                .lastUpdatedEpoch(1720262700)
                .lastUpdated("2024-07-06 13:45")
                .tempC(11.9)
                .tempF(53.5)
                .isDay(0)
                .condition(getCondition())
                .windMph(6.5)
                .windKph(10.4)
                .windDegree(289)
                .windDir("WNW")
                .pressureMb(998.0)
                .pressureIn(29.46)
                .precipMm(0.85)
                .precipIn(0.03)
                .humidity(98)
                .cloud(100)
                .feelslikeC(10.9)
                .feelslikeF(51.6)
                .visKm(9.0)
                .visMiles(5.0)
                .uv(1.0)
                .gustMph(12.3)
                .gustKph(19.9)
                .build();
    }
}
