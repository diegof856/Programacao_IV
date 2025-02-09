package com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFourDays;

import java.util.List;
import java.util.Map;


public record ForecastFourDaysPageableDTO(Map<String, List<ForecastFourDays>> Previsoes_Pelos_Proximos_Quatro_Dias) {

}
