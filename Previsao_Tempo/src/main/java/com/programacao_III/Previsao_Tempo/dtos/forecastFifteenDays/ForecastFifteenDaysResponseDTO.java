package com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays;

import com.programacao_III.Previsao_Tempo.models.cities.CityInfoFifteenDaysForecast;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFifteenDays;

import java.util.List;

// A classe 'ForecastFifteenDaysResponseDTO' é um 'record' em Java, que representa uma resposta contendo
// as previsões climáticas para os próximos 15 dias. Ela armazena as informações sobre a cidade,
// o número de dias previstos e uma lista das previsões de clima.

public record ForecastFifteenDaysResponseDTO(
        Integer Quantidade_de_dias_previstos,  // Representa a quantidade de dias para os quais a previsão está disponível.
        CityInfoFifteenDaysForecast Informacoes_cidade,  // Contém as informações detalhadas sobre a cidade.
        List<ForecastFifteenDays> Previsoes  // Lista de previsões climáticas para os próximos 15 dias.
) {
    // Este 'record' é uma forma simplificada de criar um objeto imutável que contém os seguintes dados:
    // - 'Quantidade_de_dias_previstos': Um valor inteiro indicando quantos dias de previsão são fornecidos.
    // - 'Informacoes_cidade': Um objeto da classe 'CityInfoFifteenDaysForecast' contendo detalhes da cidade.
    // - 'Previsoes': Uma lista de objetos 'ForecastFifteenDays', que contém as previsões de clima detalhadas
    //   para os próximos dias.
}
