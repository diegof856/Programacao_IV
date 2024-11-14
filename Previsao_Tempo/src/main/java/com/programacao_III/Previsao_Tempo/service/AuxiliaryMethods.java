package com.programacao_III.Previsao_Tempo.service;


import com.programacao_III.Previsao_Tempo.model.Coord;
import com.programacao_III.Previsao_Tempo.model.ForecastFiveDays;
import com.programacao_III.Previsao_Tempo.model.ForecastFourDays;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AuxiliaryMethods {


    // Método responsável por obter as coordenadas geográficas de uma cidade.
    protected abstract Coord getCoord(String nameCity);

    // Atualiza as previsões com a data e hora formatadas
    protected void editLastDataFourDayRequestDTO(ForecastFourDays forecastFourDays, String nameCity) {
        forecastFourDays.getClimate().setNameCity(nameCity);
        forecastFourDays.setDateHourForecast(this.transformDate(forecastFourDays.getDateTime()));
        forecastFourDays.setDateForecast(this.pickUpOnlyDate(forecastFourDays.getDateHourForecast()));
        forecastFourDays.setHourForecast(this.pickUpOnlyTime(forecastFourDays.getDateHourForecast()));
    }
    protected void editLastDataFiveDayRequestDTO(ForecastFiveDays forecastFiveDays, String nameCity){
        forecastFiveDays.getClimate().setNameCity(nameCity);
        forecastFiveDays.setDateHourForecast(this.transformDate(forecastFiveDays.getDateTime()));
        forecastFiveDays.setDateForecast(this.pickUpOnlyDate(forecastFiveDays.getDateHourForecast()));
        forecastFiveDays.setHourForecast(this.pickUpOnlyTime(forecastFiveDays.getDateHourForecast()));
    }
    // Converte o timestamp recebido da API para um objeto LocalDateTime
    private LocalDateTime transformDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    // Extrai somente a hora (sem a data) do objeto LocalDateTime
    private String pickUpOnlyTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dateTime.toLocalTime().format(formatter);
    }

    // Extrai somente a data (sem a hora) do objeto LocalDateTime
    private String pickUpOnlyDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateTime.toLocalDate().format(formatter);
    }

    // Aplica a paginação nas previsões
    private List<ForecastFourDays> paginateFourDaysForecasts(Set<ForecastFourDays> forecasts, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), forecasts.size());
        return new ArrayList<>(forecasts).subList(start, end); // Retorna a sublista de previsões
    }
    // Aplica a paginação nas previsões
    private List<ForecastFiveDays> paginateFiveDaysForecasts(Set<ForecastFiveDays> forecasts, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), forecasts.size());
        return new ArrayList<>(forecasts).subList(start, end); // Retorna a sublista de previsões
    }
    // Agrupa as previsões por data, e dentro de cada data, agrupa por hora
    protected Map<String, Set<ForecastFourDays>> makeMap(List<ForecastFourDays> requestList) {
        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> forecast.getDateForecast(), // Chave de agrupamento por data
                        () -> new TreeMap<String, Set<ForecastFourDays>>(), // Tipo do mapa (TreeMap para ordenação)
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFourDays -> forecastFourDays.getHourForecast())))) // Ordena por hora
                );
    }

    // Agrupa as previsões paginadas por data
    protected Map<String, List<ForecastFourDays>> makeFourDaysPaginatedMap(List<ForecastFourDays> requestList, Pageable pageable) {
        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsão para o dia " + forecast.getDateForecast(), // Agrupamento por data
                        () -> new TreeMap<String, List<ForecastFourDays>>(),
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFourDays -> forecastFourDays.getHourForecast()))),
                                forecasts -> paginateFourDaysForecasts(forecasts, pageable)
                        )
                ));
    }
    // Agrupa as previsões paginadas por data
    protected Map<String, List<ForecastFiveDays>> makeFiveDaysPaginatedMap(List<ForecastFiveDays> requestList, Pageable pageable) {

        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsão para o dia " + forecast.getDateForecast(), // Agrupamento por data
                        () -> new TreeMap<String, List<ForecastFiveDays>>(),
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFiveDays -> forecastFiveDays.getHourForecast()))),
                                forecasts -> paginateFiveDaysForecasts(forecasts, pageable)
                        )
                ));
    }
}
