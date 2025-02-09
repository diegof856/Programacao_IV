package com.programacao_III.Previsao_Tempo.models.climates;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ClimateLastTwentyFourHours {


    @JsonProperty("Data")
    private String data;

    @JsonProperty("Temperatura")
    @JsonAlias({"temp"})
    private String temperature;

    @JsonProperty("Sensacao_Termica")
    @JsonAlias({"feels_like"})
    private String feels_like;


    @JsonProperty("Temperatura_Minima")
    @JsonAlias({"temp_min"})
    private String temp_min;

    @JsonProperty("Temperatura_Maxima")
    @JsonAlias({"temp_max"})
    private String temp_max;


    @JsonProperty("Pressao_Atmosferica")
    @JsonAlias({"pressure"})
    private String pressure;

    @JsonProperty("Umidade_Relativa_Do_Ar")
    @JsonAlias({"humidity"})
    private String humidity;


    public ClimateLastTwentyFourHours(String pressure, String temp_max, String temp_min, String feels_like, String temperature, String humidity) {
        this.pressure = pressure;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.feels_like = feels_like;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getHumidity() {
        return humidity + "%";
    }

    public String getPressure() {
        return pressure + "hpa";
    }

    public String getTemp_max() {
        return temp_max + "ºC";
    }

    public String getFeels_like() {
        return feels_like + "ºC";
    }

    public String getTemperature() {
        return temperature + "ºC";
    }

    public String getTemp_min() {
        return temp_min + "ºC";
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
