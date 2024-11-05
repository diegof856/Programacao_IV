package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Climate {
    @JsonProperty("temp")
    private String temperature;
    @JsonProperty("feels_like")
    private String feels_like;
    @JsonProperty("temp_min")
    private String temp_min;
    @JsonProperty("temp_max")
    private String temp_max;
    @JsonProperty("pressure")
    private String pressure;
    @JsonProperty("humidity")
    private String humidity;
    @JsonProperty("sea_level")
    private String sea_level;
    @JsonProperty("grnd_level")
    private String grnd_level;

    public Climate(){};

    public Climate(String temperature, String grnd_level, String sea_level, String humidity, String pressure, String temp_max, String temp_min, String feels_like) {
        this.temperature = temperature;
        this.grnd_level = grnd_level;
        this.sea_level = sea_level;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.feels_like = feels_like;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getFeels_like() {
        return feels_like;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getSea_level() {
        return sea_level;
    }

    public String getGrnd_level() {
        return grnd_level;
    }
}
