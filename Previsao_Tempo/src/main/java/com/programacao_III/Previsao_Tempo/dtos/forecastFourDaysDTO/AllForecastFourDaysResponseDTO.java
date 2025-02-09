package com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFourDays;

import java.util.Map;
import java.util.Set;


public record AllForecastFourDaysResponseDTO(Integer Quantidades_de_Previsoes, Map<String, Set<ForecastFourDays>> Previsoes) {

}
