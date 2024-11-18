package com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.programacao_III.Previsao_Tempo.models.Cities.CityInfo;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFourDays;

import java.util.List;

public class ForecastFourDaysRequestDTO {

    // A anotação @JsonAlias mapeia os nomes alternativos que podem aparecer no JSON para o campo 'quantityforecast'.
    // O nome 'cnt' no JSON será mapeado para o campo 'quantityforecast'.
    @JsonAlias({"cnt"})
    private Integer quantityforecast;

    // A anotação @JsonAlias mapeia o nome alternativo 'list' no JSON para o campo 'list'.
    @JsonAlias("list")
    private List<ForecastFourDays> list;

    // A anotação @JsonAlias mapeia o nome alternativo 'city' no JSON para o campo 'city'.
    @JsonAlias("city")
    private CityInfo city;

    // Construtor padrão sem parâmetros, necessário para a serialização e deserialização do JSON
    public ForecastFourDaysRequestDTO() {}

    // Construtor com parâmetros para inicializar os campos 'quantityforecast', 'list' e 'city'.
    public ForecastFourDaysRequestDTO(Integer quantityforecast, List<ForecastFourDays> list, CityInfo city) {
        this.quantityforecast = quantityforecast;
        this.list = list;
        this.city = city;
    }

    // Método getter para acessar o valor de 'quantityforecast'.
    public Integer getQuantityforecast() {
        return quantityforecast;
    }

    // Método setter para modificar o valor de 'quantityforecast'.
    public void setQuantityforecast(Integer quantityforecast) {
        this.quantityforecast = quantityforecast;
    }

    // Método getter para acessar a lista de previsões.
    public List<ForecastFourDays> getList() {
        return list;
    }

    // Método getter para acessar as informações da cidade.
    public CityInfo getCity() {
        return city;
    }
}
