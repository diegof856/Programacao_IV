package com.programacao_III.Previsao_Tempo.interfaces;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface IPaginatedMapMethods<T> {
    Map<String, List<T>> makePaginatedMap(List<? extends T> requestList, Pageable pageable);

}
