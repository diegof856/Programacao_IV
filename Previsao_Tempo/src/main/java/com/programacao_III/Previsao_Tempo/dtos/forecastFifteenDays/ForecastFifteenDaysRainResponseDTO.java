package com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFifteenDays;

import java.util.List;

// A classe 'ForecastFifteenDaysRainResponseDTO' é um 'record' em Java.
// Ela é usada para encapsular uma lista de previsões de chuva para os próximos 15 dias.
public record ForecastFifteenDaysRainResponseDTO(List<ForecastFifteenDays> Previsoes_De_Chuva) {
    // O campo 'Previsoes_De_Chuva' contém uma lista de objetos do tipo 'ForecastFifteenDays',
    // que representam as previsões de chuva para cada dia dentro de um período de 15 dias.

    // Por ser um 'record', esta classe é imutável e já possui métodos como 'getter',
    // 'toString', 'equals' e 'hashCode' gerados automaticamente pelo compilador.

    // Principais usos deste 'record':
    // - Transferir dados relacionados às previsões de chuva em uma API ou serviço.
    // - Garantir que o objeto é somente leitura após a sua criação, promovendo imutabilidade.
}
