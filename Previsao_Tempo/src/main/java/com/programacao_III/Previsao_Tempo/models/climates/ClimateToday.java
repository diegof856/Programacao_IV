package com.programacao_III.Previsao_Tempo.models.climates;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

// A classe ClimateToday representa as condições climáticas atuais de uma cidade,
// incluindo dados como temperatura, sensação térmica, pressão atmosférica, umidade,
// além da pressão atmosférica ao nível do mar e ao nível da terra. Esses dados são frequentemente
// fornecidos por APIs de previsões meteorológicas.
public class ClimateToday {

    // O campo 'temperature' armazena a temperatura atual da cidade.
    // A anotação @JsonProperty mapeia o campo JSON para o nome do atributo 'temperature' em Java.
    // A anotação @JsonAlias permite que o JSON utilize o nome 'temp' para o mesmo atributo.
    @JsonProperty("Temperatura")
    @JsonAlias({"temp"})
    private String temperature;

    // O campo 'feels_like' armazena a sensação térmica da cidade.
    // A anotação @JsonProperty mapeia o campo 'feels_like' no JSON para 'feels_like' em Java.
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

    // O campo 'pressure' representa a pressão atmosférica atual.
    @JsonProperty("Pressão Atmosférica")
    @JsonAlias({"pressure"})
    private String pressure;

    // O campo 'humidity' armazena a umidade relativa do ar na cidade.
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

    // O campo 'nameCity' armazena o nome da cidade.
    @JsonProperty("Cidade")
    private String nameCity;

    // Construtor padrão necessário para a deserialização do JSON.
    // O construtor sem parâmetros é necessário para que o Jackson possa criar um objeto dessa classe ao
    // deserializar um JSON que contenha os dados climáticos.
    public ClimateToday() {};

    // Construtor parametrizado para inicializar os campos com valores fornecidos.
    // Este construtor permite criar um objeto 'ClimateToday' com os dados climáticos de uma cidade.
    public ClimateToday(String temperature, String feels_like, String temp_min, String temp_max, String grnd_level, String nameCity, String sea_level, String humidity, String pressure) {
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

    // Método getter para obter a temperatura atual com o sufixo "ºC".
    public String getTemperature() {
        return temperature + "ºC";  // Retorna a temperatura com o sufixo "ºC"
    }

    // Método getter para obter a sensação térmica com o sufixo "ºC".
    public String getFeels_like() {
        return feels_like + "ºC";  // Retorna a sensação térmica com o sufixo "ºC"
    }

    // Método getter para obter a temperatura mínima com o sufixo "ºC".
    public String getTemp_min() {
        return temp_min + "ºC";  // Retorna a temperatura mínima com o sufixo "ºC"
    }

    // Método getter para obter a temperatura máxima com o sufixo "ºC".
    public String getTemp_max() {
        return temp_max + "ºC";  // Retorna a temperatura máxima com o sufixo "ºC"
    }

    // Método getter para obter a pressão atmosférica com o sufixo "hpa".
    public String getPressure() {
        return pressure + "hpa";  // Retorna a pressão atmosférica com o sufixo "hpa"
    }

    // Método getter para obter a umidade relativa do ar com o sufixo "%".
    public String getHumidity() {
        return humidity + "%";  // Retorna a umidade com o sufixo "%"
    }

    // Método getter para obter a pressão atmosférica ao nível do mar com o sufixo "hpa".
    public String getSea_level() {
        return sea_level + "hpa";  // Retorna a pressão ao nível do mar com o sufixo "hpa"
    }

    // Método getter para obter a pressão atmosférica ao nível da terra com o sufixo "hpa".
    public String getGrnd_level() {
        return grnd_level + "hpa";  // Retorna a pressão ao nível da terra com o sufixo "hpa"
    }

    // Método getter para obter o nome da cidade.
    public String getNameCity() {
        return nameCity;  // Retorna o nome da cidade
    }

    // Método setter para modificar o nome da cidade.
    // Esse setter pode ser utilizado caso seja necessário alterar o nome da cidade após a criação do objeto.
    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
