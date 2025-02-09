package com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO;

import com.programacao_III.Previsao_Tempo.models.climates.ClimateLastTwentyFourHours;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherLastTwentyFourHours;

import java.util.List;

public record WeatherLastTwentyFourHoursResponseDTO(Integer Quantidade_De_Previsoes, List<WeatherLastTwentyFourHours> condicoes_ultimas_vinte_quatro_horas) {
}
