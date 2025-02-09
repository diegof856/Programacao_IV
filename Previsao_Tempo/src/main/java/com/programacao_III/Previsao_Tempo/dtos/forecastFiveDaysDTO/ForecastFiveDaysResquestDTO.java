package com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.programacao_III.Previsao_Tempo.models.cities.CityInfo;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFiveDays;

import java.util.List;

public class ForecastFiveDaysResquestDTO {


    @JsonAlias({"cnt"})
    private Integer quantityforecast;

    @JsonAlias("list")
    private List<ForecastFiveDays> list;

    @JsonAlias("city")
    private CityInfo city;

    public ForecastFiveDaysResquestDTO() {}

    public ForecastFiveDaysResquestDTO(Integer quantityforecast, List<ForecastFiveDays> list, CityInfo city) {
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

    public List<ForecastFiveDays> getList() {
        return list;
    }

    public CityInfo getCity() {
        return city;
    }
}
