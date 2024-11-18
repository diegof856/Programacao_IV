package com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.programacao_III.Previsao_Tempo.models.climates.Climate;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherLastTwentyFourHours;

import java.util.List;

// A classe 'WeatherLastTwentyFourHoursRequestDTO' é usada para representar a solicitação
// que contém as condições climáticas das últimas 24 horas para uma cidade ou região.
// Essa classe é parte de um processo de deserialização de dados recebidos por uma API
// ou para enviar esses dados em uma solicitação HTTP.
public class WeatherLastTwentyFourHoursRequestDTO {

    // A anotação @JsonAlias mapeia o nome alternativo 'cnt' no JSON para o campo 'quantityForecast'.
    // Caso o nome 'cnt' apareça no JSON, ele será mapeado para 'quantityForecast'.
    @JsonAlias({"cnt"})
    private Integer quantityForecast;

    // A anotação @JsonAlias mapeia o nome alternativo 'list' no JSON para o campo 'weatherLastTwentyFourHours'.
    // Caso o nome 'list' apareça no JSON, ele será mapeado para 'weatherLastTwentyFourHours'.
    @JsonAlias({"list"})
    private List<WeatherLastTwentyFourHours> weatherLastTwentyFourHours;

    // Construtor padrão (necessário para a deserialização do JSON).
    public WeatherLastTwentyFourHoursRequestDTO(){}

    // Construtor com parâmetros para inicializar os campos 'quantityForecast' e 'weatherLastTwentyFourHours'.
    public WeatherLastTwentyFourHoursRequestDTO(Integer quantityForecast, List<WeatherLastTwentyFourHours> weatherLastTwentyFourHours) {
        this.quantityForecast = quantityForecast;
        this.weatherLastTwentyFourHours = weatherLastTwentyFourHours;
    }

    // Método getter para acessar o valor de 'quantityForecast'.
    public Integer getQuantityForecast() {
        return quantityForecast;
    }

    // Método getter para acessar a lista de previsões climáticas das últimas 24 horas.
    public List<WeatherLastTwentyFourHours> getWeatherLastTwentyFourHours() {
        return weatherLastTwentyFourHours;
    }
}
