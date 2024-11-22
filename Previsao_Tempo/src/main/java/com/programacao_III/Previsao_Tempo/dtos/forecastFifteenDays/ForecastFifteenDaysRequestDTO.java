package com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.cities.CityInfoFifteenDaysForecast;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFifteenDays;

import java.util.List;

// A classe 'ForecastFifteenDaysRequestDTO' é um DTO (Data Transfer Object) utilizado para
// transportar as informações de uma previsão de clima de 15 dias entre camadas da aplicação.
// Ela encapsula dados sobre a cidade, as previsões de clima e a quantidade de informações a serem consultadas.
public class ForecastFifteenDaysRequestDTO {

    // A anotação @JsonProperty define como o campo será representado no JSON.
    // O campo 'Informações sobre a cidade' será mapeado para o campo 'cityInfo'.
    @JsonProperty("Informações sobre a cidade")
    // A anotação @JsonAlias permite mapear um campo com diferentes nomes no JSON. Aqui, 'city' é um nome alternativo.
    @JsonAlias("city")
    private CityInfoFifteenDaysForecast cityInfo;  // Representa as informações sobre a cidade.

    // A anotação @JsonAlias mapeia o nome alternativo 'cnt' no JSON para o campo 'quantityInquiry'.
    @JsonAlias("cnt")
    private Integer quantityInquiry;  // Representa a quantidade de informações de previsão a serem consultadas.

    // A anotação @JsonProperty mapeia o nome 'list' do JSON para o campo 'forecastFifteenDaysList'.
    @JsonProperty("list")
    private List<ForecastFifteenDays> forecastFifteenDaysList;  // Lista das previsões do clima para os próximos 15 dias.

    // Construtor padrão sem parâmetros, necessário para a serialização e deserialização do JSON.
    public ForecastFifteenDaysRequestDTO() {}

    // Construtor com parâmetros, inicializando todos os campos da classe.
    public ForecastFifteenDaysRequestDTO(CityInfoFifteenDaysForecast cityInfo, List<ForecastFifteenDays> forecastFifteenDaysList, Integer quantityInquiry) {
        this.cityInfo = cityInfo;
        this.forecastFifteenDaysList = forecastFifteenDaysList;
        this.quantityInquiry = quantityInquiry;
    }

    // Método getter para acessar a lista de previsões de clima.
    public List<ForecastFifteenDays> getForecastFifteenDaysList() {
        return forecastFifteenDaysList;
    }

    // Método getter para acessar as informações da cidade.
    public CityInfoFifteenDaysForecast getCityInfo() {
        return cityInfo;
    }

    // Método getter para acessar a quantidade de previsões solicitadas.
    public Integer getQuantityInquiry() {
        return quantityInquiry;
    }
}
