package com.programacao_III.Previsao_Tempo.dtos.climateDTO;

import com.programacao_III.Previsao_Tempo.models.winds.WindToday;

// O 'record' 'ClimateWindResponseDTO' é utilizado para encapsular os dados relacionados às condições do vento.
// Este 'record' é uma forma simplificada de criar uma classe imutável que contém apenas um campo.
public record ClimateWindResponseDTO(
        // Campo 'Condicoes_do_vento' do tipo 'WindToday':
        // Representa as condições atuais do vento, como velocidade e direção.
        WindToday Condicoes_do_vento) {

    // Este 'record' não possui lógica adicional ou métodos personalizados.
    // Ele é usado para transportar informações sobre as condições do vento de forma simples e direta.
}
