package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

// A classe Climate representa as condições climáticas de uma cidade, incluindo dados como temperatura,
// sensação térmica, pressão atmosférica, umidade, e o nome da cidade. Esses dados são frequentemente
// retornados em APIs de previsões meteorológicas.
public class Climate {

    // O campo 'temperature' representa a temperatura atual da cidade.
    // A anotação @JsonProperty é usada para mapear o campo no JSON para o nome do atributo 'temperature' em Java.
    // A anotação @JsonAlias permite que o JSON utilize o nome 'temp' para o mesmo atributo.
    @JsonProperty("Temperatura")
    @JsonAlias({"temp"})
    private String temperature;

    // O campo 'feels_like' representa a sensação térmica da cidade.
    // A anotação @JsonProperty é usada para mapear o campo 'feels_like' no JSON para 'feels_like' em Java.
    @JsonProperty("Sensação Térmica")
    @JsonAlias({"feels_like"})
    private String feels_like;

    // O campo 'temp_min' representa a temperatura mínima registrada na cidade.
    // O campo 'temp_max' representa a temperatura máxima registrada.
    @JsonProperty("Temperatura Minima")
    @JsonAlias({"temp_min"})
    private String temp_min;

    @JsonProperty("Temperatura Máxima")
    @JsonAlias({"temp_max"})
    private String temp_max;

    // O campo 'pressure' representa a pressão atmosférica.
    @JsonProperty("Pressão Atmosférica")
    @JsonAlias({"pressure"})
    private String pressure;

    // O campo 'humidity' representa a umidade relativa do ar na cidade.
    @JsonProperty("Umidade Relativa Do Ar")
    @JsonAlias({"humidity"})
    private String humidity;

    // O campo 'sea_level' representa a pressão atmosférica ao nível do mar.
    @JsonProperty("Pressão Atmosférica ao Nível Do Mar")
    @JsonAlias({"sea_level"})
    private String sea_level;

    // O campo 'grnd_level' representa a pressão atmosférica ao nível da terra.
    @JsonProperty("PressãoAtmosférica ao Nível Da Terra")
    @JsonAlias({"grnd_level"})
    private String grnd_level;

    // O campo 'nameCity' representa o nome da cidade.
    @JsonProperty("Cidade")
    @JsonAlias({"name"})
    private String nameCity;

    // Construtor padrão necessário para a deserialização do JSON
    public Climate() {};

    // Construtor parametrizado para inicializar os campos com valores fornecidos
    public Climate(String temperature, String feels_like, String temp_min, String temp_max, String grnd_level, String nameCity, String sea_level, String humidity, String pressure) {
        this.temperature = temperature;
        this.feels_like = feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.grnd_level = grnd_level;
        this.nameCity = nameCity;
        this.sea_level = sea_level;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    // Métodos 'getters' que retornam as informações climáticas com sufixos adequados.

    public String getTemperature() {
        return temperature + " ºC";  // Retorna a temperatura com o sufixo "ºC"
    }

    public String getFeels_like() {
        return feels_like + " ºC";  // Retorna a sensação térmica com o sufixo "ºC"
    }

    public String getTemp_min() {
        return temp_min + " ºC";  // Retorna a temperatura mínima com o sufixo "ºC"
    }

    public String getTemp_max() {
        return temp_max + " ºC";  // Retorna a temperatura máxima com o sufixo "ºC"
    }

    public String getPressure() {
        return pressure + " hpa";  // Retorna a pressão atmosférica com o sufixo "hpa"
    }

    public String getHumidity() {
        return humidity + " %";  // Retorna a umidade com o sufixo "%"
    }

    public String getSea_level() {
        return sea_level + " hpa";  // Retorna a pressão ao nível do mar com o sufixo "hpa"
    }

    public String getGrnd_level() {
        return grnd_level + " hpa";  // Retorna a pressão ao nível da terra com o sufixo "hpa"
    }

    public String getNameCity() {
        return nameCity;  // Retorna o nome da cidade
    }

    // Setter para o campo 'nameCity' (caso seja necessário modificar o nome da cidade)
    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
