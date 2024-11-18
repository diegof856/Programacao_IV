package com.programacao_III.Previsao_Tempo.dtos.climateDTO;

import com.programacao_III.Previsao_Tempo.models.climates.ClimateToday;
import com.programacao_III.Previsao_Tempo.models.hourdawnnightfall.HourDawnNightfall;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherConditions;
import com.programacao_III.Previsao_Tempo.models.winds.WindToday;

// A classe 'ClimateResponseDTO' é declarada como um 'record' em Java.
// Um 'record' é uma forma concisa de definir uma classe imutável com apenas campos finais.
// Ele gera automaticamente um construtor, métodos 'getter', além de 'equals', 'hashCode' e 'toString'.
public record ClimateResponseDTO(
        // Campo 'Clima_Atual' do tipo 'ClimateToday', representa as informações climáticas atuais.
        ClimateToday Clima_Atual,

        // Campo 'Hora_Por_Do_Sol_Amanhecer' do tipo 'HourDawnNightfall',
        // armazena informações sobre o horário do nascer e do pôr do sol.
        HourDawnNightfall Hora_Por_Do_Sol_Amanhecer,

        // Campo 'Condicoes_do_vento' do tipo 'WindToday',
        // contém informações sobre as condições do vento, como velocidade e direção.
        WindToday Condicoes_do_vento,

        // Campo 'Condicao_Do_Clima' do tipo 'WeatherConditions',
        // representa as condições climáticas atuais, como chuva, neve ou céu limpo.
        WeatherConditions Condicao_Do_Clima) {

    // Este 'record' não possui métodos adicionais ou lógica além dos campos declarados.
    // O construtor padrão e os métodos 'getter' para cada campo são gerados automaticamente.
}
