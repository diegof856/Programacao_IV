package com.programacao_III.Previsao_Tempo.dtos.ClimateDTO;

import com.programacao_III.Previsao_Tempo.model.*;

// A classe 'ClimateResponseDTO' é um 'record' em Java, que é uma forma simplificada de declarar uma classe imutável
// com apenas atributos finais e métodos 'getter' gerados automaticamente.
public record ClimateResponseDTO(Climate Clima_Atual, HourDawnNightfall Hora_Por_Do_Sol_Amanhecer, WindToday Condicoes_do_vento,
                                 WeatherConditions Condicao_Do_Clima) {
    // Este 'record' contém um único campo 'Clima_Atual' do tipo 'Climate',
    // que representa as informações climáticas atuais. O nome do campo é 'Clima_Atual'.
}
