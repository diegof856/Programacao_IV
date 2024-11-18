package com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO;

import com.programacao_III.Previsao_Tempo.models.climates.ClimateLastTwentyFourHours;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherLastTwentyFourHours;

import java.util.List;

// A classe 'WeatherLastTwentyFourHoursResponseDTO' é um 'record' que serve como objeto de resposta
// para retornar as condições climáticas das últimas 24 horas em uma resposta HTTP.
// Ela contém a quantidade de previsões e uma lista de objetos que representam as condições do clima.
public record WeatherLastTwentyFourHoursResponseDTO(

        // 'Quantidade_De_Previsoes': Este campo representa a quantidade de previsões retornadas na resposta.
        // Ele é um valor do tipo Integer.
        Integer Quantidade_De_Previsoes,

        // 'condicoes_ultimas_vinte_quatro_horas': Este campo é uma lista de objetos 'WeatherLastTwentyFourHours',
        // que contém as condições climáticas observadas nas últimas 24 horas. Cada objeto dessa lista representa uma
        // previsão do clima para uma hora específica dentro do período de 24 horas.
        List<WeatherLastTwentyFourHours> condicoes_ultimas_vinte_quatro_horas
) {
    // O 'record' automaticamente cria um construtor, métodos de acesso (getters),
    // e outros métodos como 'equals()', 'hashCode()', e 'toString()'.
    //
    // O uso de um 'record' aqui é adequado porque ele representa uma estrutura de dados imutável e
    // facilita a transferência de dados entre as camadas da aplicação, sendo um DTO (Data Transfer Object).
}
