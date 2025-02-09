package com.programacao_III.Previsao_Tempo.models.hourdawnnightfall;

import com.fasterxml.jackson.annotation.JsonProperty;


public class HourDawnNightfall {

    @JsonProperty("Nascer_Do_Sol")
    private String sunrise;

    @JsonProperty("Por_Do_Sol")
    private String sunset;

    public HourDawnNightfall() {
    }

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
