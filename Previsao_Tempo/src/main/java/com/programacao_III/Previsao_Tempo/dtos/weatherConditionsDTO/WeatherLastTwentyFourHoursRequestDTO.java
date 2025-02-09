package com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.programacao_III.Previsao_Tempo.models.climates.Climate;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherLastTwentyFourHours;

import java.util.List;


public class WeatherLastTwentyFourHoursRequestDTO {


    @JsonAlias({"cnt"})
    private Integer quantityForecast;


    @JsonAlias({"list"})
    private List<WeatherLastTwentyFourHours> weatherLastTwentyFourHours;


    public WeatherLastTwentyFourHoursRequestDTO(){}

    public WeatherLastTwentyFourHoursRequestDTO(Integer quantityForecast, List<WeatherLastTwentyFourHours> weatherLastTwentyFourHours) {
        this.quantityForecast = quantityForecast;
        this.weatherLastTwentyFourHours = weatherLastTwentyFourHours;
    }

    public Integer getQuantityForecast() {
        return quantityForecast;
    }

    public List<WeatherLastTwentyFourHours> getWeatherLastTwentyFourHours() {
        return weatherLastTwentyFourHours;
    }
}
