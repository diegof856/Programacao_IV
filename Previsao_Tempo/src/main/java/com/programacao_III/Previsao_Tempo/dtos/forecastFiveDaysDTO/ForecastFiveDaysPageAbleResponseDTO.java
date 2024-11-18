package com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFiveDays;

import java.util.List;
import java.util.Map;

// A classe 'ForecastFiveDaysPageAbleResponseDTO' é um 'record' usado para representar uma resposta
// que contém previsões climáticas para os próximos cinco dias, organizadas de forma paginada.

public record ForecastFiveDaysPageAbleResponseDTO(
        Map<String, List<ForecastFiveDays>> Previsoes_Pelos_Proximos_Cinco_Dias) {

    // Este 'record' contém um único campo:
    // - 'Previsoes_Pelos_Proximos_Cinco_Dias': Um mapa (Map) que relaciona uma chave (String) com uma lista de previsões climáticas.
    // A chave pode ser, por exemplo, a data (em formato String), e o valor será uma lista de objetos 'ForecastFiveDays' que contêm as previsões climáticas para esse dia.
    // O uso de uma lista permite armazenar várias previsões para um mesmo dia, o que é útil para mostrar diferentes condições climáticas ao longo do dia.

}
