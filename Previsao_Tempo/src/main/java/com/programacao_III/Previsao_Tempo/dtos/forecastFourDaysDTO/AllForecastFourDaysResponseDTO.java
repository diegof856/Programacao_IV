package com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFourDays;

import java.util.Map;
import java.util.Set;

// O 'record' 'AllForecastFourDaysResponseDTO' é usado para representar a resposta que contém a previsão do tempo
// para os próximos quatro dias, organizada em um formato específico.
public record AllForecastFourDaysResponseDTO(Integer Quantidades_de_Previsoes, Map<String, Set<ForecastFourDays>> Previsoes) {

    // O 'record' tem dois campos:
    // 1. 'Quantidades_de_Previsoes' - Um inteiro que indica o número de previsões retornadas.
    //    Este campo armazena a quantidade total de previsões de tempo que foram geradas para os próximos quatro dias.

    // 2. 'Previsoes' - Um mapa (Map) que organiza as previsões em um conjunto (Set) para cada chave.
    //    - A chave do mapa é uma 'String', provavelmente representando algum critério, como o dia da previsão (ex: "segunda-feira", "terça-feira").
    //    - O valor é um 'Set' de objetos 'ForecastFourDays', que contém as previsões do tempo para os próximos quatro dias.
    //    O uso de 'Set' em vez de 'List' garante que não haja duplicatas nas previsões para o mesmo critério. Ou seja, se existirem múltiplas previsões para um determinado critério, elas serão ignoradas para evitar repetição.
}
