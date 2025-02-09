package com.programacao_III.Previsao_Tempo.models.weatherconditions;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherConditions {


    @JsonProperty("Condicao_Esperada")
    @JsonAlias({"description"})
    private String description;


    public WeatherConditions() {}


    public WeatherConditions(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}
