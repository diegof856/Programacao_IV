package com.programacao_III.Previsao_Tempo.models.climates;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Climate {

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

    @JsonProperty("Pressao_Atmosferica_Ao_Nivel_Do_Mar")
    @JsonAlias({"sea_level"})
    private String sea_level;


    @JsonProperty("Pressao_Atmosferica_Ao_Nivel_Da_Terra")
    @JsonAlias({"grnd_level"})
    private String grnd_level;

    public Climate() {};


    public Climate(String temperature, String feels_like, String temp_min, String temp_max, String grnd_level, String sea_level, String humidity, String pressure) {
        this.temperature = temperature;
        this.feels_like = feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.grnd_level = grnd_level;
        this.sea_level = sea_level;
        this.humidity = humidity;
        this.pressure = pressure;
    }




    public String getTemperature() {
        return temperature + "ºC";
    }


    public String getFeels_like() {
        return feels_like + "ºC";
    }


    public String getTemp_min() {
        return temp_min + "ºC";
    }


    public String getTemp_max() {
        return temp_max + "ºC";
    }


    public String getPressure() {
        return pressure + "hpa";
    }


    public String getHumidity() {
        return humidity + "%";
    }


    public String getSea_level() {
        return sea_level + "hpa";
    }


    public String getGrnd_level() {
        return grnd_level + "hpa";
    }
}
