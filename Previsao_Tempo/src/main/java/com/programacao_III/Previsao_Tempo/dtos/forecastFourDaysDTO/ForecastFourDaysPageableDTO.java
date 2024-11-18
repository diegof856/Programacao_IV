package com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFourDays;

import java.util.List;
import java.util.Map;

// O 'record' 'ForecastFourDaysPageableDTO' é utilizado para representar uma resposta com a previsão do tempo
// para os próximos quatro dias, limitada por algum critério de paginação.
public record ForecastFourDaysPageableDTO(Map<String, List<ForecastFourDays>> Previsoes_Pelos_Proximos_Quatro_Dias) {

    // O 'record' contém um único campo 'Previsoes_Pelos_Proximos_Quatro_Dias', que é um 'Map' (mapa) onde:
    // - A chave do mapa é uma 'String', provavelmente representando algum critério de paginação, como número da página (ex: "page1", "page2", etc.).
    // - O valor associado à chave é uma lista de objetos 'ForecastFourDays', que contém as previsões do tempo para os próximos quatro dias.
    //    Cada item da lista é uma previsão do tempo, do tipo 'ForecastFourDays', que contém detalhes sobre o clima para um período específico.
    // Este campo permite que a resposta de previsões seja organizada de forma paginada.
    // Ou seja, ao invés de retornar todas as previsões de uma vez, a resposta pode ser segmentada em várias páginas, facilitando o processamento e a leitura.
}
