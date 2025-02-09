package com.programacao_III.Previsao_Tempo.interfaces;

import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFourDays;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface PaginatedMapMethods<T> {
    Map<String, List<T>> makePaginatedMap(List<? extends T> requestList, Pageable pageable);

}
