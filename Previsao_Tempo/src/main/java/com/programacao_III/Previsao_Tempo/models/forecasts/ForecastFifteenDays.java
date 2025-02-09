package com.programacao_III.Previsao_Tempo.models.forecasts;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.feelslike.FeelsLike;
import com.programacao_III.Previsao_Tempo.models.hourdawnnightfall.HourDawnNightfall;
import com.programacao_III.Previsao_Tempo.models.temperature.Temperature;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherConditions;

import java.util.List;

public class ForecastFifteenDays {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"dt"})
    private Long dt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"sunrise"})
    private Long sunrise;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"sunset"})
    private Long sunset;

    @JsonProperty("Data")
    private String day;

    @JsonProperty("Nascer_E_Por_Do_Sol")
    private HourDawnNightfall hourDawnNightfall;


    @JsonProperty("Pressao_Atmosferica")
    @JsonAlias({"pressure"})
    private String pressure;

    @JsonProperty("Temperatura")
    @JsonAlias({"temp"})
    private Temperature temperature;


    @JsonProperty("Sensacao_Termica")
    @JsonAlias({"feels_like"})
    private FeelsLike feelsLike;


    @JsonProperty("Umidade_Relativa_Do_Ar")
    @JsonAlias({"humidity"})
    private String humidity;

    @JsonProperty("Condicao_Do_Clima_Esperada")
    @JsonAlias({"weather"})
    private List<WeatherConditions> weatherConditionsList;

    @JsonProperty("Velocidade_Do_Vento")
    @JsonAlias({"speed"})
    private String speed;

    @JsonProperty("Direcao_Do_Vento")
    @JsonAlias({"deg"})
    private String deg;


    @JsonProperty("Rajadas_De_Vento")
    @JsonAlias({"gust"})
    private String gust;


    @JsonProperty("Cobertura_De_Nuvens_No_Ceu")
    @JsonAlias("clouds")
    private String clouds;

    @JsonProperty("Volume_De_Chuva_Previsto")
    @JsonAlias({"rain"})
    private String rain;

    public ForecastFifteenDays(){}

    public ForecastFifteenDays(Long sunrise, Long sunset, Long dt, String pressure, Temperature temperature, FeelsLike feelsLike, String humidity, List<WeatherConditions> weatherConditionsList, String speed, String deg, String gust, String clouds, String rain) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.dt = dt;
        this.pressure = pressure;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.weatherConditionsList = weatherConditionsList;
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
        this.clouds = clouds;
        this.rain = rain;
    }


    public Long getDt() {
        return dt;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public HourDawnNightfall getHourDawnNightfall() {
        return hourDawnNightfall;
    }

    public void setHourDawnNightfall(HourDawnNightfall hourDawnNightfall) {
        this.hourDawnNightfall = hourDawnNightfall;
    }

    public String getPressure() {
        return pressure + "hpa";
    }

    public String getHumidity() {
        return humidity + "%";
    }

    public List<WeatherConditions> getWeatherConditionsList() {
        return weatherConditionsList;
    }

    public String getSpeed() {
        return speed + "m/s";
    }

    public String getDeg() {
        return deg + "°";
    }

    public String getGust() {
        return gust + "m/s";
    }

    public String getClouds() {
        return clouds + "%";
    }

    public String getRain() {
        return rain + "mm";
    }
}
