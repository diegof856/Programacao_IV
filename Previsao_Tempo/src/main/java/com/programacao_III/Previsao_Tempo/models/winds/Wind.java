package com.programacao_III.Previsao_Tempo.models.winds;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {

    @JsonProperty("Velocidade_Do_Vento")
    @JsonAlias({"speed"})
    private String speed;


    @JsonProperty("Direcao_Do_Vento")
    @JsonAlias({"deg"})
    private String windDirection;

    @JsonProperty("Rajadas_De_Vento")
    @JsonAlias({"gust"})
    private String gustsWind;

    public Wind(){}

    public Wind(String speed, String gustsWind, String windDirection) {
        this.speed = speed;
        this.gustsWind = gustsWind;
        this.windDirection = windDirection;
    }

    public String getSpeed() {
        return speed + "m/s";
    }


    public String getWindDirection() {
        return windDirection + "º";
    }


    public String getGustsWind() {
        return gustsWind + "m/s";
    }
}
