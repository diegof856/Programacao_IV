package com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.programacao_III.Previsao_Tempo.models.cities.CityInfo;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFourDays;

import java.util.List;

public class ForecastFourDaysRequestDTO {


    @JsonAlias({"cnt"})
    private Integer quantityforecast;

    @JsonAlias("list")
    private List<ForecastFourDays> list;

    @JsonAlias("city")
    private CityInfo city;

    public ForecastFourDaysRequestDTO() {}

    public ForecastFourDaysRequestDTO(Integer quantityforecast, List<ForecastFourDays> list, CityInfo city) {
        this.quantityforecast = quantityforecast;
        this.list = list;
        this.city = city;
    }

    public Integer getQuantityforecast() {
        return quantityforecast;
    }

    public void setQuantityforecast(Integer quantityforecast) {
        this.quantityforecast = quantityforecast;
    }

    public List<ForecastFourDays> getList() {
        return list;
    }

    public CityInfo getCity() {
        return city;
    }
}
