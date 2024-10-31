package com.programacao_III.Precisao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Climate {
    @JsonProperty("temp")
    private Double temperature;
    @JsonProperty("feels_like")
    private Double feels_like;
    @JsonProperty("temp_min")
    private Double temp_min;
    @JsonProperty("temp_max")
    private Double temp_max;
    @JsonProperty("pressure")
    private Integer pressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("sea_level")
    private Integer sea_level;
    @JsonProperty("grnd_level")
    private Integer grnd_level;

    public Climate(){};

    public Climate(Integer grnd_level, Integer sea_level, Integer humidity, Integer pressure, Double temp_max, Double temp_min, Double feels_like, Double temperature) {
        this.grnd_level = grnd_level;
        this.sea_level = sea_level;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.feels_like = feels_like;
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Integer getGrnd_level() {
        return grnd_level;
    }

    public Integer getSea_level() {
        return sea_level;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public Double getFeels_like() {
        return feels_like;
    }

    @Override
    public String toString() {
        return "{ " +
                "Umidade Relativa Do Ar = " + this.humidity + "%"
                + ", Temperatura = " + this.temperature + "ºC"
                +", Sensação térmica = "+this.feels_like+"ºC "+
                '}';
    }
}
