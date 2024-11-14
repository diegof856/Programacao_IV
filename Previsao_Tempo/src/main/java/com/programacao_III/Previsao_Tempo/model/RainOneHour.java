package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RainOneHour {
    @JsonProperty("Quantidade de chuva na última hora")
    @JsonAlias({"1h"})
    private String quantityRainOneHour;

    public RainOneHour(){}

    public RainOneHour(String quantityRainOneHour) {
        this.quantityRainOneHour = quantityRainOneHour;
    }

    public String getQuantityRainOneHour() {
        return quantityRainOneHour +" m/s";
    }

    public void setQuantityRainOneHour(String quantityRainOneHour) {
        this.quantityRainOneHour = quantityRainOneHour;
    }
}
