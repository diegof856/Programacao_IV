package com.programacao_III.Previsao_Tempo.models.forecasts;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.feelslike.FeelsLike;
import com.programacao_III.Previsao_Tempo.models.hourdawnnightfall.HourDawnNightfall;
import com.programacao_III.Previsao_Tempo.models.temperature.Temperature;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherConditions;

import java.util.List;

// A classe ForecastFifteenDays representa a previsão do tempo para os próximos quinze dias,
// incluindo dados sobre temperatura, umidade, vento, pressão atmosférica e outras condições climáticas.
public class ForecastFifteenDays {

    // O campo 'dt' representa a data e hora da previsão (timestamp em segundos).
    // A anotação @JsonProperty especifica que esse campo não deve ser exposto em serialização,
    // e a anotação @JsonAlias permite que ele seja mapeado a partir do nome 'dt' no JSON.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"dt"})
    private Long dt;

    // O campo 'sunrise' representa o horário do nascer do sol (timestamp em segundos).
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"sunrise"})
    private Long sunrise;

    // O campo 'sunset' representa o horário do pôr do sol (timestamp em segundos).
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"sunset"})
    private Long sunset;

    // O campo 'day' contém a data de previsão (ex: "2024-11-18").
    // O valor será fornecido como string.
    @JsonProperty("Data")
    private String day;

    // O campo 'hourDawnNightfall' armazena as informações sobre o nascer e pôr do sol.
    // Essas informações são representadas pela classe HourDawnNightfall.
    @JsonProperty("Nascer_E_Por_Do_Sol")
    private HourDawnNightfall hourDawnNightfall;

    // O campo 'pressure' contém o valor da pressão atmosférica (em hPa).
    @JsonProperty("Pressao_Atmosferica")
    @JsonAlias({"pressure"})
    private String pressure;

    // O campo 'temperature' contém as informações sobre a temperatura do dia,
    // representadas pela classe Temperature.
    @JsonProperty("Temperatura")
    @JsonAlias({"temp"})
    private Temperature temperature;

    // O campo 'feelsLike' contém as informações sobre a sensação térmica,
    // representadas pela classe FeelsLike.
    @JsonProperty("Sensacao_Termica")
    @JsonAlias({"feels_like"})
    private FeelsLike feelsLike;

    // O campo 'humidity' contém o valor da umidade relativa do ar (em %).
    @JsonProperty("Umidade_Relativa_Do_Ar")
    @JsonAlias({"humidity"})
    private String humidity;

    // O campo 'weatherConditionsList' é uma lista de objetos da classe WeatherConditions,
    // que descrevem as condições climáticas esperadas para aquele dia (ex: céu nublado, chuva, etc).
    @JsonProperty("Condicao_Do_Clima_Esperada")
    @JsonAlias({"weather"})
    private List<WeatherConditions> weatherConditionsList;

    // O campo 'speed' representa a velocidade do vento (em metros por segundo).
    @JsonProperty("Velocidade_Do_Vento")
    @JsonAlias({"speed"})
    private String speed;

    // O campo 'deg' representa a direção do vento (em graus).
    @JsonProperty("Direcao_Do_Vento")
    @JsonAlias({"deg"})
    private String deg;

    // O campo 'gust' representa as rajadas de vento (em metros por segundo).
    @JsonProperty("Rajadas_De_Vento")
    @JsonAlias({"gust"})
    private String gust;

    // O campo 'clouds' contém a cobertura de nuvens no céu (em %).
    @JsonProperty("Cobertura_De_Nuvens_No_Ceu")
    @JsonAlias("clouds")
    private String clouds;

    // O campo 'rain' contém o volume de chuva previsto (em milímetros).
    @JsonProperty("Volume_De_Chuva_Previsto")
    @JsonAlias({"rain"})
    private String rain;

    // Construtor padrão necessário para a deserialização do JSON.
    public ForecastFifteenDays(){}

    // Construtor parametrizado para inicializar os campos com valores fornecidos.
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

    // Métodos 'getters' para acessar os valores das variáveis de instância.

    public Long getDt() {
        return dt;  // Retorna o timestamp da previsão (não exposto na serialização).
    }

    public Long getSunrise() {
        return sunrise;  // Retorna o horário do nascer do sol.
    }

    public Long getSunset() {
        return sunset;  // Retorna o horário do pôr do sol.
    }

    public String getDay() {
        return day;  // Retorna a data de previsão.
    }

    public void setDay(String day) {
        this.day = day;  // Define a data de previsão.
    }

    public HourDawnNightfall getHourDawnNightfall() {
        return hourDawnNightfall;  // Retorna as informações sobre o nascer e pôr do sol.
    }

    public void setHourDawnNightfall(HourDawnNightfall hourDawnNightfall) {
        this.hourDawnNightfall = hourDawnNightfall;  // Define as informações sobre o nascer e pôr do sol.
    }

    public String getPressure() {
        return pressure + "hpa";  // Retorna a pressão atmosférica com a unidade 'hpa'.
    }

    public String getHumidity() {
        return humidity + "%";  // Retorna a umidade relativa do ar com o símbolo '%'.
    }

    public List<WeatherConditions> getWeatherConditionsList() {
        return weatherConditionsList;  // Retorna a lista de condições climáticas.
    }

    public String getSpeed() {
        return speed + "m/s";  // Retorna a velocidade do vento com a unidade 'm/s'.
    }

    public String getDeg() {
        return deg + "°";  // Retorna a direção do vento com o símbolo de graus '°'.
    }

    public String getGust() {
        return gust + "m/s";  // Retorna as rajadas de vento com a unidade 'm/s'.
    }

    public String getClouds() {
        return clouds + "%";  // Retorna a cobertura de nuvens com o símbolo '%'.
    }

    public String getRain() {
        return rain + "mm";  // Retorna o volume de chuva previsto com a unidade 'mm'.
    }
}
