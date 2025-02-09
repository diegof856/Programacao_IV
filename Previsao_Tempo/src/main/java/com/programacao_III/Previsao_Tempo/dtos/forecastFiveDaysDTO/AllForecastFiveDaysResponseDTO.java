package com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFiveDays;

import java.util.Map;
import java.util.Set;


public record AllForecastFiveDaysResponseDTO(Integer Quantidades_de_Previsoes, Map<String, Set<ForecastFiveDays>> Previsoes) {
}
