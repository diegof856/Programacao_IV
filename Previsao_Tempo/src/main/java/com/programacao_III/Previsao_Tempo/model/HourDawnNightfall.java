package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HourDawnNightfall {
    // O campo 'sunrise' representa a hora do nascer do sol.

    @JsonProperty("Nascer do sol")
    private String sunrise;

    // O campo 'sunset' representa a hora do pôr do sol.
    @JsonProperty("Pôr Do Sol")
    private String sunset;
    public HourDawnNightfall(){}

    public HourDawnNightfall(String sunrise, String sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
