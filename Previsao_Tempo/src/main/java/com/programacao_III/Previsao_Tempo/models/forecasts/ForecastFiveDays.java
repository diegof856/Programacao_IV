package com.programacao_III.Previsao_Tempo.models.forecasts;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.cities.CityInfo;
import com.programacao_III.Previsao_Tempo.models.climates.Climate;
import com.programacao_III.Previsao_Tempo.models.partday.PartDay;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherConditions;
import com.programacao_III.Previsao_Tempo.models.winds.Wind;

import java.time.LocalDateTime;
import java.util.List;

public class ForecastFiveDays {


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"dt_txt"})
    private String dateTime;


    @JsonProperty("Data")
    private String dateForecast;


    @JsonProperty("Hora")
    private String hourForecast;


    @JsonProperty("Fase_Do_Dia")
    @JsonAlias({"sys"})
    private PartDay partDay;


    @JsonProperty("Clima")
    @JsonAlias({"main"})
    private Climate climate;

    @JsonProperty("Condicao_Do_Clima_Esperada")
    @JsonAlias({"weather"})
    private List<WeatherConditions> weatherConditions;

    @JsonProperty("Condicao_Do_Vento")
    @JsonAlias({"wind"})
    private Wind wind;

    @JsonProperty("Precipitacao")
    @JsonAlias({"pop"})
    private String precipitation;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime dateHourForecast;


    @JsonProperty("Informacoes_Da_Cidade")
    private CityInfo city;


    public ForecastFiveDays(){}

    public ForecastFiveDays(PartDay partDay, String dateTime, Climate climate, List<WeatherConditions> weatherConditions, Wind wind, String precipitation) {
        this.partDay = partDay;
        this.dateTime = dateTime;
        this.climate = climate;
        this.weatherConditions = weatherConditions;
        this.wind = wind;
        this.precipitation = precipitation;
    }


    public Wind getWind() {
        return wind;
    }

    public List<WeatherConditions> getWeatherConditions() {
        return weatherConditions;
    }

    public Climate getClimate() {
        return climate;
    }

    public String getPrecipitation() {
        return precipitation+"mm";
    }

    public PartDay getPartDay() {
        return partDay;
    }

    public String getDateTime() {
        return dateTime;
    }

    public LocalDateTime getDateHourForecast() {
        return dateHourForecast;
    }

    public String getHourForecast() {
        return hourForecast;
    }

    public void setHourForecast(String hourForecast) {
        this.hourForecast = hourForecast;
    }

    public void setDateHourForecast(LocalDateTime dateHourForecast) {
        this.dateHourForecast = dateHourForecast;
    }

    public String getDateForecast() {
        return dateForecast;
    }

    public void setDateForecast(String dateForecast) {
        this.dateForecast = dateForecast;
    }

    public CityInfo getCity() {
        return city;
    }

    public void setCity(CityInfo city) {
        this.city = city;
    }
}
