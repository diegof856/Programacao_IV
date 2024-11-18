package com.programacao_III.Previsao_Tempo.dtos.climateDTO;

// O 'record' 'ClimateTemperatureResponseDTO' é usado para encapsular os dados de temperatura
// em três escalas diferentes: Celsius, Fahrenheit e Kelvin.
// Ele simplifica a criação de uma classe imutável com apenas três campos,
// e os métodos necessários são gerados automaticamente (como construtor, getters, equals, hashCode e toString).
public record ClimateTemperatureResponseDTO(
        // Campo 'Temperatura_Celsius' do tipo 'String':
        // Representa a temperatura medida na escala Celsius, geralmente usada no sistema métrico.
        String Temperatura_Celsius,

        // Campo 'Temperatura_Fahrenheit' do tipo 'String':
        // Representa a temperatura medida na escala Fahrenheit, frequentemente usada nos Estados Unidos.
        String Temperatura_Fahrenheit,

        // Campo 'Kelvin' do tipo 'String':
        // Representa a temperatura medida na escala Kelvin, usada em contextos científicos e físicos.
        String Kelvin) {

    // Este 'record' não possui métodos ou lógica adicional.
    // Os métodos 'getter', o construtor, e os métodos 'equals', 'hashCode' e 'toString'
    // são gerados automaticamente com base nos campos declarados.
}
