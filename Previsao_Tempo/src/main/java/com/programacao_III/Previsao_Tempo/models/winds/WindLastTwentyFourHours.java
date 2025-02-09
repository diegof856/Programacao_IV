package com.programacao_III.Previsao_Tempo.models.winds;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WindLastTwentyFourHours {
    @JsonProperty("Velocidade_Do_Vento")
    @JsonAlias({"speed"})
    private String speed;

    @JsonProperty("Direcao_Do_Vento")
    @JsonAlias({"deg"})
    private String windDirection;


    public WindLastTwentyFourHours(){}

    public WindLastTwentyFourHours(String speed, String windDirection) {
        this.speed = speed;
        this.windDirection = windDirection;
    }

    public String getSpeed() {
        return speed + "m/s";
    }


    public String getWindDirection() {
        return windDirection + "º";
    }

}
