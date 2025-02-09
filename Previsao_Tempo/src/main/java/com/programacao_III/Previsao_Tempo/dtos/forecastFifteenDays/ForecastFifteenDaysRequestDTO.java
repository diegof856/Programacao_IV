package com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.cities.CityInfoFifteenDaysForecast;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFifteenDays;

import java.util.List;

public class ForecastFifteenDaysRequestDTO {


    @JsonProperty("Informações sobre a cidade")
    @JsonAlias("city")
    private CityInfoFifteenDaysForecast cityInfo;

    @JsonAlias("cnt")
    private Integer quantityInquiry;

    @JsonProperty("list")
    private List<ForecastFifteenDays> forecastFifteenDaysList;

    public ForecastFifteenDaysRequestDTO() {}


    public ForecastFifteenDaysRequestDTO(CityInfoFifteenDaysForecast cityInfo, List<ForecastFifteenDays> forecastFifteenDaysList, Integer quantityInquiry) {
        this.cityInfo = cityInfo;
        this.forecastFifteenDaysList = forecastFifteenDaysList;
        this.quantityInquiry = quantityInquiry;
    }

    public List<ForecastFifteenDays> getForecastFifteenDaysList() {
        return forecastFifteenDaysList;
    }

    public CityInfoFifteenDaysForecast getCityInfo() {
        return cityInfo;
    }

    public Integer getQuantityInquiry() {
        return quantityInquiry;
    }
}
