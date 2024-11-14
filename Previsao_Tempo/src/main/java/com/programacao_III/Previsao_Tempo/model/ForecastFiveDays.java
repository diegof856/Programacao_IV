package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class ForecastFiveDays {
    // O campo 'dateTime' é usado para armazenar a data e hora no formato de texto.
    // Ele está marcado com 'WRITE_ONLY', o que significa que será ignorado durante a serialização
    // (não aparecerá no JSON de saída), mas será usado durante a desserialização do JSON.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"dt_txt"})
    private String dateTime;

    // O campo 'dateForecast' contém a data da previsão, que será exibida em formato de string.
    @JsonProperty("Data")
    private String dateForecast;

    // O campo 'hourForecast' contém a hora da previsão.
    @JsonProperty("Hora")
    private String hourForecast;

    // O campo 'partDay' armazena a fase do dia (manhã, tarde, noite).
    // Ele é mapeado para o JSON com o alias 'sys'.
    @JsonProperty("Fase Do Dia")
    @JsonAlias({"sys"})
    private PartDay partDay;

    // O campo 'climate' contém informações sobre o clima, como temperatura, umidade, etc.
    @JsonProperty("Clima")
    @JsonAlias({"main"})
    private Climate climate;

    // O campo 'weatherConditions' armazena as condições meteorológicas esperadas, como chuva, neve, etc.
    @JsonProperty("Condição Do Clima Esperada")
    @JsonAlias({"weather"})
    private List<WeatherConditions> weatherConditions;

    // O campo 'wind' armazena as condições do vento, como velocidade e direção.
    @JsonProperty("Condição Do Vento")
    @JsonAlias({"wind"})
    private Wind wind;

    // O campo 'precipitation' armazena o valor da precipitação (em milímetros).
    @JsonProperty("Precipitação")
    @JsonAlias({"pop"})
    private String precipitation;

    // O campo 'dateHourForecast' armazena a data e hora da previsão como um objeto LocalDateTime.
    // Ele é marcado como 'WRITE_ONLY' para ser usado apenas internamente e não será serializado.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime dateHourForecast;
    @JsonProperty("Quantidade De Chuva Prevista Neste Período De Tempo")
    @JsonAlias({"rain"})
    private RainThreeHour rain;

    public ForecastFiveDays(){}

    public ForecastFiveDays(PartDay partDay, String dateTime, Climate climate, List<WeatherConditions> weatherConditions, Wind wind, String precipitation, RainThreeHour rain) {
        this.partDay = partDay;
        this.dateTime = dateTime;
        this.climate = climate;
        this.weatherConditions = weatherConditions;
        this.wind = wind;
        this.precipitation = precipitation;
        this.rain = rain;
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
        return precipitation;
    }

    public PartDay getPartDay() {
        return partDay;
    }

    public RainThreeHour getRain() {
        return rain;
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

}
