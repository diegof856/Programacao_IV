package com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFiveDays;

import java.util.Map;
import java.util.Set;

// A classe 'AllForecastFiveDaysResponseDTO' é um 'record' que representa uma resposta contendo as previsões
// climáticas para um período de cinco dias. Ela armazena a quantidade de previsões e um mapa de previsões por
// data, onde a chave é a data da previsão e o valor é um conjunto de previsões para esse dia específico.

public record AllForecastFiveDaysResponseDTO(
        Integer Quantidades_de_Previsoes,  // Representa o número total de previsões de clima fornecidas para o período.
        Map<String, Set<ForecastFiveDays>> Previsoes  // Um mapa que relaciona datas (como String) com um conjunto de previsões para cada data.
) {
    // Este 'record' contém:
    // - 'Quantidades_de_Previsoes': Um valor inteiro indicando o número total de previsões para o período de cinco dias.
    // - 'Previsoes': Um mapa onde cada chave é uma data no formato de String (ex: "2024-11-20") e o valor é um conjunto
    //   de previsões climáticas para esse dia, representadas pela classe 'ForecastFiveDays'.
}
