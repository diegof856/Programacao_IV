package com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO;

import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherConditions;

// A classe 'WeatherConditionsResponseDTO' é um 'record' que serve como um objeto de resposta
// para retornar as condições climáticas de uma cidade ou região em uma resposta HTTP.
// Ela encapsula um objeto do tipo 'WeatherConditions' que contém os dados específicos sobre o clima.
public record WeatherConditionsResponseDTO(WeatherConditions Condicao_Do_Clima) {

    // O 'record' tem um único campo:
    // 1. 'Condicao_Do_Clima' - Um objeto do tipo 'WeatherConditions' que contém as informações detalhadas
    //    sobre a condição climática atual, como a descrição do clima (ex: "Céu claro", "Chuva leve"),
    //    que pode ser usada para mostrar ao usuário as condições meteorológicas no momento.
    //
    // A vantagem de usar um 'record' é que ele oferece uma implementação simplificada de um DTO
    // (Data Transfer Object) com um construtor, métodos de acesso (getters) e outros métodos
    // como 'equals()', 'hashCode()' e 'toString()' automaticamente gerados. Além disso, o 'record' é imutável
    // por padrão, garantindo que os dados sejam consistentes durante o ciclo de vida do objeto.
    //
    // Esse tipo de estrutura é utilizado especialmente quando se está lidando com dados que serão
    // transferidos entre as camadas da aplicação, como no caso de uma API que retorna as condições climáticas.
    //
    // No contexto da aplicação, este DTO será utilizado como parte das respostas para o usuário,
    // que consome os dados de clima fornecidos pela API do OpenWeatherMap.

}
