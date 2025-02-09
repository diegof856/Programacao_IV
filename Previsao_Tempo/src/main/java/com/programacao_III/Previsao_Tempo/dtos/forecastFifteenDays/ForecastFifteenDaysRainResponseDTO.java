package com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFifteenDays;

import java.util.List;

public record ForecastFifteenDaysRainResponseDTO(List<ForecastFifteenDays> Previsoes_De_Chuva) {
}
