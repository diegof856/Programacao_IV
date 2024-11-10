package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

// A classe CityInfo representa informações sobre uma cidade, como o nome da cidade,
// a quantidade de pessoas (população), a hora do nascer e pôr do sol. Ela é usada para
// encapsular esses dados que podem ser retornados em uma resposta de uma API ou utilizados
// em algum processamento interno.
public class CityInfo {

    // O campo 'population' representa a quantidade de pessoas na cidade.
    // A anotação @JsonProperty é usada para mapear o nome do campo no JSON para o nome
    // do atributo Java, no caso "Quantidade De Pessoas".
    // A anotação @JsonAlias é usada para mapear o campo 'population' que pode ser chamado de 'population'
    // no JSON (não sendo obrigatório).
    @JsonProperty("Quantidade De Pessoas")
    @JsonAlias({"population"})
    private Long population;

    // O campo 'cityName' representa o nome da cidade.
    // A anotação @JsonAlias é usada para mapear o campo 'name' do JSON para 'cityName' em Java.
    @JsonAlias({"name"})
    private String cityName;

    // O campo 'sunrise' representa a hora do nascer do sol.
    // A anotação @JsonProperty é usada para mapear o campo 'sunrise' do JSON para o nome do atributo 'sunrise' em Java.
    @JsonProperty("Hora que o sol nasçe")
    @JsonAlias({"sunrise"})
    private Long sunrise;

    // O campo 'sunset' representa a hora do pôr do sol.
    // A anotação @JsonProperty é usada para mapear o campo 'sunset' do JSON para o nome do atributo 'sunset' em Java.
    @JsonProperty("Hora que o sol se poe")
    @JsonAlias({"sunset"})
    private Long sunset;

    // Construtor padrão necessário para a deserialização do JSON
    public CityInfo() {}

    // Construtor parametrizado para inicializar os campos da classe com valores fornecidos
    public CityInfo(Long population, Long sunset, Long sunrise, String cityName) {
        this.population = population;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.cityName = cityName;
    }

    // Métodos getters para acessar os valores dos campos. Eles são usados quando a classe
    // é manipulada em outras partes do código.
    public Long getPopulation() {
        return population;
    }

    public String getCityName() {
        return cityName;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }
}
