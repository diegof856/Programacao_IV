package com.programacao_III.Previsao_Tempo.models.climates;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

// A classe ClimateLastTwentyFourHours representa as condições climáticas de uma cidade nas últimas 24 horas,
// incluindo dados como temperatura, sensação térmica, pressão atmosférica e umidade. Esses dados são frequentemente
// retornados em APIs de previsões meteorológicas.
public class ClimateLastTwentyFourHours {

    // O campo 'data' armazena a data e hora das condições climáticas.
    // A anotação @JsonProperty mapeia o campo JSON para o atributo 'data'.
    @JsonProperty("Data")
    private String data;

    // O campo 'temperature' representa a temperatura atual da cidade.
    // A anotação @JsonProperty mapeia o campo no JSON para o nome do atributo 'temperature' em Java.
    // A anotação @JsonAlias permite que o JSON utilize o nome 'temp' para o mesmo atributo.
    @JsonProperty("Temperatura")
    @JsonAlias({"temp"})
    private String temperature;

    // O campo 'feels_like' representa a sensação térmica da cidade.
    // A anotação @JsonProperty mapeia o campo 'feels_like' no JSON para 'feels_like' em Java.
    @JsonProperty("Sensacao_Termica")
    @JsonAlias({"feels_like"})
    private String feels_like;

    // O campo 'temp_min' representa a temperatura mínima registrada na cidade.
    // O campo 'temp_max' representa a temperatura máxima registrada.
    @JsonProperty("Temperatura_Minima")
    @JsonAlias({"temp_min"})
    private String temp_min;

    @JsonProperty("Temperatura_Maxima")
    @JsonAlias({"temp_max"})
    private String temp_max;

    // O campo 'pressure' representa a pressão atmosférica.
    @JsonProperty("Pressao_Atmosferica")
    @JsonAlias({"pressure"})
    private String pressure;

    // O campo 'humidity' representa a umidade relativa do ar na cidade.
    @JsonProperty("Umidade_Relativa_Do_Ar")
    @JsonAlias({"humidity"})
    private String humidity;

    // Construtor parametrizado que inicializa os campos com valores fornecidos.
    // Esse construtor permite criar um objeto 'ClimateLastTwentyFourHours' com dados específicos de temperatura,
    // sensação térmica, pressão e umidade.
    public ClimateLastTwentyFourHours(String pressure, String temp_max, String temp_min, String feels_like, String temperature, String humidity) {
        this.pressure = pressure;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.feels_like = feels_like;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // Método getter para obter a umidade, retornando o valor com o sufixo '%'.
    public String getHumidity() {
        return humidity + "%";
    }

    // Método getter para obter a pressão atmosférica, retornando o valor com o sufixo 'hpa'.
    public String getPressure() {
        return pressure + "hpa";
    }

    // Método getter para obter a temperatura máxima, retornando o valor com o sufixo 'ºC'.
    public String getTemp_max() {
        return temp_max + "ºC";
    }

    // Método getter para obter a sensação térmica, retornando o valor com o sufixo 'ºC'.
    public String getFeels_like() {
        return feels_like + "ºC";
    }

    // Método getter para obter a temperatura, retornando o valor com o sufixo 'ºC'.
    public String getTemperature() {
        return temperature + "ºC";
    }

    // Método getter para obter a temperatura mínima, retornando o valor com o sufixo 'ºC'.
    public String getTemp_min() {
        return temp_min + "ºC";
    }

    // Método getter para obter a data e hora das condições climáticas.
    public String getData() {
        return data;
    }

    // Método setter para definir o valor de 'data'.
    public void setData(String data) {
        this.data = data;
    }
}
