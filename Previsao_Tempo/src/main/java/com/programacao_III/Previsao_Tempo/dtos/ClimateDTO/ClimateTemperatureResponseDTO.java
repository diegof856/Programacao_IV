package com.programacao_III.Previsao_Tempo.dtos.ClimateDTO;

// O 'record' 'TemperatureResponseDTO' é utilizado para representar os dados de temperatura em diferentes escalas:
// Celsius, Fahrenheit e Kelvin.
public record ClimateTemperatureResponseDTO(String Temperatura_Celsius, String Temperatura_Fahrenheit, String Kelvin) {
    // Este 'record' contém três campos:
    // - 'Temperatura_Celsius': Representa a temperatura em graus Celsius.
    // - 'Temperatura_Fahrenheit': Representa a temperatura em graus Fahrenheit.
    // - 'Kelvin': Representa a temperatura na escala Kelvin.
}
