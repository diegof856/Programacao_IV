package com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherConditions;

import java.util.List;


public class WeatherConditionsRequestDTO {


    @JsonAlias("weather")
    private List<WeatherConditions> weatherConditions;


    public WeatherConditionsRequestDTO() {}


    public WeatherConditionsRequestDTO(List<WeatherConditions> weatherConditions) {
        this.weatherConditions = weatherConditions;
    }


    public List<WeatherConditions> getWeatherConditions() {
        return weatherConditions;
    }
}
