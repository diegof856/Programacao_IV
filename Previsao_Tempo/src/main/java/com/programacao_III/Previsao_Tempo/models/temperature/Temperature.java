package com.programacao_III.Previsao_Tempo.models.temperature;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Temperature {


    @JsonProperty("Temperatura_Durante_O_Dia")
    @JsonAlias("day")
    private String day;


    @JsonProperty("Temperatura_Minima")
    @JsonAlias("min")
    private String min;


    @JsonProperty("Temperatura_Maxima")
    @JsonAlias("max")
    private String max;

    @JsonProperty("Temperatura_Durante_A_Noite")
    @JsonAlias("night")
    private String night;


    @JsonProperty("Temperatura_Registrada_Do_Entardecer")
    @JsonAlias("eve")
    private String eve;


    @JsonProperty("Temperatura_Registrada_Durante_A_Manhã")
    @JsonAlias("morn")
    private String morn;


    public Temperature() {
    }


    public Temperature(String day, String morn, String eve, String max, String min, String night) {
        this.day = day;
        this.morn = morn;
        this.eve = eve;
        this.max = max;
        this.min = min;
        this.night = night;
    }


    public String getDay() {
        return day + "ºC";
    }


    public String getMin() {
        return min + "ºC";
    }


    public String getNight() {
        return night + "ºC";
    }


    public String getMax() {
        return max + "ºC";
    }


    public String getEve() {
        return eve + "ºC";
    }


    public String getMorn() {
        return morn + "ºC";
    }
}
