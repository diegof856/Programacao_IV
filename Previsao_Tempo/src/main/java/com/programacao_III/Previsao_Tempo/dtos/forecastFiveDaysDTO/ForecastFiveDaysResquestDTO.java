package com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.programacao_III.Previsao_Tempo.models.Cities.CityInfo;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFiveDays;

import java.util.List;

public class ForecastFiveDaysResquestDTO {

    // A anotação @JsonAlias mapeia os nomes alternativos que podem aparecer no JSON para o campo 'quantityforecast'.
    // O nome 'cnt' no JSON será mapeado para o campo 'quantityforecast'.
    @JsonAlias({"cnt"})
    private Integer quantityforecast;

    // A anotação @JsonAlias mapeia o nome alternativo 'list' no JSON para o campo 'list'.
    @JsonAlias("list")
    private List<ForecastFiveDays> list;

    // A anotação @JsonAlias mapeia o nome alternativo 'city' no JSON para o campo 'city'.
    @JsonAlias("city")
    private CityInfo city;

    // Construtor padrão sem parâmetros, necessário para a serialização e deserialização do JSON.
    // Este construtor é usado pelo Jackson para criar uma instância da classe a partir de um JSON.
    public ForecastFiveDaysResquestDTO() {}

    // Construtor com parâmetros para inicializar os campos 'quantityforecast', 'list' e 'city'.
    // Este construtor é útil para criar um objeto completo e preenchido com os dados necessários.
    public ForecastFiveDaysResquestDTO(Integer quantityforecast, List<ForecastFiveDays> list, CityInfo city) {
        this.quantityforecast = quantityforecast;
        this.list = list;
        this.city = city;
    }

    // Método getter para acessar o valor de 'quantityforecast', que representa a quantidade de previsões solicitadas.
    public Integer getQuantityforecast() {
        return quantityforecast;
    }

    // Método setter para modificar o valor de 'quantityforecast'. Este método é utilizado para definir o número de previsões.
    public void setQuantityforecast(Integer quantityforecast) {
        this.quantityforecast = quantityforecast;
    }

    // Método getter para acessar a lista de previsões, que contém as previsões climáticas para os próximos dias.
    public List<ForecastFiveDays> getList() {
        return list;
    }

    // Método getter para acessar as informações da cidade, que representam os dados da cidade solicitada para as previsões.
    public CityInfo getCity() {
        return city;
    }
}
