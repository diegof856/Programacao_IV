package com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays;

import com.programacao_III.Previsao_Tempo.models.cities.CityInfoFifteenDaysForecast;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFifteenDays;

import java.util.List;



public record ForecastFifteenDaysResponseDTO(Integer Quantidade_de_dias_previstos, CityInfoFifteenDaysForecast Informacoes_cidade, List<ForecastFifteenDays> Previsoes) {
}
