package ru.aston.springtask2.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Current {
    private Integer lastUpdatedEpoch;
    private String lastUpdated;
    @JsonProperty("temp_c")
    private Double tempC;
    private Double tempF;
    private Integer isDay;
    private Condition condition;
    private Double windMph;
    private Double windKph;
    private Integer windDegree;
    private String windDir;
    private Double pressureMb;
    private Double pressureIn;
    private Double precipMm;
    private Double precipIn;
    private Integer humidity;
    private Integer cloud;
    private Double feelslikeC;
    private Double feelslikeF;
    private Double visKm;
    private Double visMiles;
    private Double uv;
    private Double gustMph;
    private Double gustKph;
}

