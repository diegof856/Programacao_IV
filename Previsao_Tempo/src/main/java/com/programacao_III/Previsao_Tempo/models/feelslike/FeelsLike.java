package com.programacao_III.Previsao_Tempo.models.feelslike;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FeelsLike {
    @JsonProperty("Sensacao_Termica_Durante_O_Dia")
    @JsonAlias("day")
    private String day;

    @JsonProperty("Sensacao_Termica_Durante_A_Noite")
    @JsonAlias("night")
    private String night;

    @JsonProperty("Sensacao_Termica_Durante_O_Entardecer")
    @JsonAlias("eve")
    private String eve;

    @JsonProperty("Sensacao_Termica_Durante_A_Manhã")
    @JsonAlias("morn")
    private String morn;

    public FeelsLike() {
    }

    public FeelsLike(String day, String night, String eve, String morn) {
        this.day = day;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    public String getDay() {
        return day + "°C";
    }

    public String getMorn() {
        return morn + "°C";
    }

    public String getEve() {
        return eve + "°C";
    }

    public String getNight() {
        return night + "°C";
    }
}
