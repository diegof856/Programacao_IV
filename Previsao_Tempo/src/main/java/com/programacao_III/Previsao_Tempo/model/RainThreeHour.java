package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RainThreeHour {
    @JsonProperty("Quantidade de chuva nas últimas três horas")
    @JsonAlias({"3h"})
    private String quantityRainThreeHour;

    public RainThreeHour(){}

    public RainThreeHour(String quantityRainThreeHour) {
        this.quantityRainThreeHour = quantityRainThreeHour;
    }

    public String getQuantityRainThreeHour() {
        return quantityRainThreeHour +" m/s";
    }

    public void setQuantityRainThreeHour(String quantityRainThreeHour) {
        this.quantityRainThreeHour = quantityRainThreeHour;
    }
}
