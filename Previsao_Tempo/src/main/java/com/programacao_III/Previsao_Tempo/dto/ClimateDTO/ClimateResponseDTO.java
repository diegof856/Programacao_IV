package com.programacao_III.Previsao_Tempo.dto.ClimateDTO;

import com.programacao_III.Previsao_Tempo.model.Climate;

// A classe 'ClimateResponseDTO' é um 'record' em Java, que é uma forma simplificada de declarar uma classe imutável
// com apenas atributos finais e métodos 'getter' gerados automaticamente.
public record ClimateResponseDTO(Climate Clima_Atual) {
    // Este 'record' contém um único campo 'Clima_Atual' do tipo 'Climate',
    // que representa as informações climáticas atuais. O nome do campo é 'Clima_Atual'.
}
