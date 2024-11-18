package com.programacao_III.Previsao_Tempo.models.forecasts;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.Cities.CityInfo;
import com.programacao_III.Previsao_Tempo.models.climates.Climate;
import com.programacao_III.Previsao_Tempo.models.partday.PartDay;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherConditions;
import com.programacao_III.Previsao_Tempo.models.winds.Wind;

import java.time.LocalDateTime;
import java.util.List;

public class ForecastFiveDays {

    // 'dateTime' é a data e hora de previsão no formato texto, usada apenas para desserialização.
    // Não será incluída na resposta JSON devido à anotação 'WRITE_ONLY'.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"dt_txt"})
    private String dateTime;

    // 'dateForecast' é a data da previsão formatada como string.
    // Será incluída no JSON de resposta com a chave "Data".
    @JsonProperty("Data")
    private String dateForecast;

    // 'hourForecast' é a hora da previsão em formato de string.
    // Será incluída no JSON de resposta com a chave "Hora".
    @JsonProperty("Hora")
    private String hourForecast;

    // 'partDay' armazena a fase do dia (manhã, tarde, noite) e será mapeado com o alias 'sys'.
    // A fase do dia será incluída no JSON de resposta com a chave "Fase Do Dia".
    @JsonProperty("Fase Do Dia")
    @JsonAlias({"sys"})
    private PartDay partDay;

    // 'climate' armazena informações sobre o clima, como temperatura, umidade, etc.
    // Será incluído no JSON de resposta com a chave "Clima".
    @JsonProperty("Clima")
    @JsonAlias({"main"})
    private Climate climate;

    // 'weatherConditions' armazena as condições meteorológicas, como chuva ou neve.
    // Será incluído no JSON de resposta com a chave "Condição Do Clima Esperada".
    @JsonProperty("Condição Do Clima Esperada")
    @JsonAlias({"weather"})
    private List<WeatherConditions> weatherConditions;

    // 'wind' contém as condições do vento (velocidade e direção).
    // Será incluído no JSON de resposta com a chave "Condição Do Vento".
    @JsonProperty("Condição Do Vento")
    @JsonAlias({"wind"})
    private Wind wind;

    // 'precipitation' armazena o valor da precipitação (em milímetros) e será incluído no JSON com a chave "Precipitação".
    @JsonProperty("Precipitação")
    @JsonAlias({"pop"})
    private String precipitation;

    // 'dateHourForecast' é a data e hora da previsão como objeto LocalDateTime.
    // Marcado com 'WRITE_ONLY', é usado internamente, mas não aparece no JSON de resposta.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime dateHourForecast;

    // 'city' contém informações sobre a cidade para a qual a previsão é fornecida.
    @JsonProperty("Informações da cidade")
    private CityInfo city;

    // Construtor padrão sem parâmetros.
    public ForecastFiveDays(){}

    // Construtor com parâmetros para inicializar a classe com dados específicos.
    public ForecastFiveDays(PartDay partDay, String dateTime, Climate climate, List<WeatherConditions> weatherConditions, Wind wind, String precipitation) {
        this.partDay = partDay;
        this.dateTime = dateTime;
        this.climate = climate;
        this.weatherConditions = weatherConditions;
        this.wind = wind;
        this.precipitation = precipitation;
    }

    // Métodos getters e setters para acessar e modificar os campos.

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
        return precipitation+"mm"; // Adiciona a unidade "mm" à precipitação.
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
