package com.programacao_III.Previsao_Tempo.dtos.climateDTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.cities.CityInfo;
import com.programacao_III.Previsao_Tempo.models.climates.ClimateToday;
import com.programacao_III.Previsao_Tempo.models.hourdawnnightfall.HourDawnNightfall;
import com.programacao_III.Previsao_Tempo.models.winds.WindToday;

public class ClimateRequestDTO {

    @JsonProperty("main")
    private ClimateToday climate;

    @JsonAlias({"wind"})
    private WindToday windToday;

    @JsonAlias({"sys"})
    private CityInfo cityInfo;

    private HourDawnNightfall hourDawnNightfall;

    @JsonAlias({"name"})
    private String nameCity;

    public ClimateRequestDTO() {
    }

    public ClimateRequestDTO(ClimateToday climate, WindToday windToday, CityInfo cityInfo) {
        this.windToday = windToday;
        this.climate = climate;
        this.cityInfo = cityInfo;
    }

    public ClimateToday getClimateToday() {
        return climate;
    }

    public String getNameCity() {
        return nameCity;
    }

    public WindToday getWindToday() {
        return windToday;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public HourDawnNightfall getHourDawnNightfall() {
        return hourDawnNightfall;
    }
}
