package com.programacao_III.Previsao_Tempo.models.winds;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WindToday {

    @JsonProperty("Velocidade_Do_Vento")
    @JsonAlias({"speed"})
    private String speed;

    @JsonProperty("Direcao_Do_Vento")
    @JsonAlias({"deg"})
    private String windDirection;

    public WindToday(){}


    public WindToday(String windDirection, String speed) {
        this.windDirection = windDirection;
        this.speed = speed;
    }


    public String getSpeed() {
        return speed + "m/s";
    }

    public String getWindDirection() {
        return windDirection + "°";
    }
}
