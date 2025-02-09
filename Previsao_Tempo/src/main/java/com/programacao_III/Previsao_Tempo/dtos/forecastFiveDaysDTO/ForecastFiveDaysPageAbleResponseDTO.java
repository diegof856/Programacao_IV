package com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFiveDays;

import java.util.List;
import java.util.Map;


public record ForecastFiveDaysPageAbleResponseDTO(Map<String, List<ForecastFiveDays>> Previsoes_Pelos_Proximos_Cinco_Dias) {
}
