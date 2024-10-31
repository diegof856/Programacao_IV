package com.programacao_III.Precisao_Tempo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Precisao_Tempo.model.WeatherConditions;

import java.util.List;

public class WeatherConditionsRequestDTO {
    @JsonProperty("weather")
    private List<WeatherConditions> weatherConditions;

    public WeatherConditionsRequestDTO(){}

    public WeatherConditionsRequestDTO(List<WeatherConditions> weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public List<WeatherConditions> getWeatherConditions() {
        return weatherConditions;
    }
}
