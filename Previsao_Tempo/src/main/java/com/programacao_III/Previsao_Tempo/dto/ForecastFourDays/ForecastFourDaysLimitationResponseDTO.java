package com.programacao_III.Previsao_Tempo.dto.ForecastFourDays;

import com.programacao_III.Previsao_Tempo.model.ForecastFourDays;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

// O 'record' 'ForecastFourDaysLimitationResponseDTO' é utilizado para representar uma resposta com a previsão do tempo
// para os próximos quatro dias, limitada por algum critério de paginação.
public record ForecastFourDaysLimitationResponseDTO(Map<String, List<ForecastFourDays>> Paginacao) {
    // O 'record' contém um único campo 'Paginacao', que é um 'Map' (mapa) onde:
    // - A chave do mapa é uma 'String', provavelmente representando algum critério de paginação (como número da página).
    // - O valor associado à chave é uma lista de objetos 'ForecastFourDays', que contém a previsão do tempo para quatro dias.
    // Este campo permite que a resposta da previsão seja paginada.
}
