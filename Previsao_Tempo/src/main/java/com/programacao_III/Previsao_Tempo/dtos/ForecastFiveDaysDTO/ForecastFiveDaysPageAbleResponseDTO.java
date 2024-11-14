package com.programacao_III.Previsao_Tempo.dtos.ForecastFiveDaysDTO;

import com.programacao_III.Previsao_Tempo.model.ForecastFiveDays;
import com.programacao_III.Previsao_Tempo.model.ForecastFourDays;

import java.util.List;
import java.util.Map;

public record ForecastFiveDaysPageAbleResponseDTO(
        Map<String, List<ForecastFiveDays>> Previsoes_Pelos_Proximos_Cinco_Dias){
}
