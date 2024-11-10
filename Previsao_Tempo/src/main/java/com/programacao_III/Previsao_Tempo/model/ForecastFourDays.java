package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

// A classe ForecastFourDays representa a previsão do tempo para os próximos quatro dias,
// incluindo informações sobre a data, hora, clima, vento, precipitação e outras condições meteorológicas.
public class ForecastFourDays {

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

    // Construtor padrão
    public ForecastFourDays() {
    }

    // Construtor parametrizado para inicializar os campos com valores fornecidos
    public ForecastFourDays(String dateTime, PartDay partDay, Climate climate, List<WeatherConditions> weatherConditions, Wind wind, String precipitation) {
        this.dateTime = dateTime;
        this.partDay = partDay;
        this.climate = climate;
        this.weatherConditions = weatherConditions;
        this.wind = wind;
        this.precipitation = precipitation;
    }

    // Métodos getters para acessar os valores dos campos.

    public String getPrecipitation() {
        return precipitation + " mm";  // Retorna a precipitação em milímetros.
    }

    public Climate getClimate() {
        return climate;  // Retorna as informações sobre o clima.
    }

    public List<WeatherConditions> getWeatherConditions() {
        return weatherConditions;  // Retorna as condições climáticas esperadas (ex: chuva, neblina).
    }

    public Wind getWind() {
        return wind;  // Retorna as informações sobre o vento.
    }

    public PartDay getPartDay() {
        return partDay;  // Retorna a fase do dia (manhã, tarde, noite).
    }

    public String getDateTime() {
        return dateTime;  // Retorna a data e hora da previsão como texto.
    }

    public LocalDateTime getDateHourForecast() {
        return dateHourForecast;  // Retorna a data e hora da previsão como um objeto LocalDateTime.
    }

    // Método setter para definir a data e hora da previsão.
    public void setDateHourForecast(LocalDateTime dateHourForecast) {
        this.dateHourForecast = dateHourForecast;
    }

    // Getter e setter para 'hourForecast', que representa a hora da previsão.
    public String getHourForecast() {
        return hourForecast;  // Retorna a hora da previsão.
    }

    public void setHourForecast(String hourForecast) {
        this.hourForecast = hourForecast;  // Define a hora da previsão.
    }

    // Getter e setter para 'dateForecast', que representa a data da previsão.
    public String getDateForecast() {
        return dateForecast;  // Retorna a data da previsão.
    }

    public void setDateForecast(String dateForecast) {
        this.dateForecast = dateForecast;  // Define a data da previsão.
    }
}
