package com.programacao_III.Previsao_Tempo.dtos.climateDTO;

import com.programacao_III.Previsao_Tempo.models.climates.ClimateToday;
import com.programacao_III.Previsao_Tempo.models.hourdawnnightfall.HourDawnNightfall;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherConditions;
import com.programacao_III.Previsao_Tempo.models.winds.WindToday;

public record ClimateResponseDTO(ClimateToday Clima_Atual, HourDawnNightfall Hora_Por_Do_Sol_Amanhecer, WindToday Condicoes_do_vento, WeatherConditions Condicao_Do_Clima) {

}
