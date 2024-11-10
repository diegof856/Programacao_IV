package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dto.ClimateDTO.ClimateRequestDTO;
import com.programacao_III.Previsao_Tempo.dto.ClimateDTO.ClimateResponseDTO;
import com.programacao_III.Previsao_Tempo.dto.ClimateDTO.TemperatureResponseDTO;
import com.programacao_III.Previsao_Tempo.dto.CoordDTO.CoordRequestDTO;
import com.programacao_III.Previsao_Tempo.dto.ForecastFourDays.ForecastFourDaysRequestDTO;
import com.programacao_III.Previsao_Tempo.dto.ForecastFourDays.ForecastFourDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.dto.ForecastFourDays.ForecastFourDaysLimitationResponseDTO;
import com.programacao_III.Previsao_Tempo.dto.WeatherConditionsDTO.WeatherConditionsRequestDTO;
import com.programacao_III.Previsao_Tempo.dto.WeatherConditionsDTO.WeatherConditionsResponseDTO;
import com.programacao_III.Previsao_Tempo.model.Coord;
import com.programacao_III.Previsao_Tempo.model.ForecastFourDays;
import com.programacao_III.Previsao_Tempo.utils.GetData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Value("${api.key}") // Injeta a chave da API do OpenWeatherMap
    private String API_KEY;

    // Método responsável por obter as coordenadas geográficas de uma cidade.
    private Coord getCoord(String nameCity) {
        RestTemplate restTemplate = new RestTemplate();
        GetData dataCoord = new GetData(restTemplate);

        // Faz uma requisição à API do OpenWeatherMap para obter as coordenadas da cidade
        CoordRequestDTO request = dataCoord.sendRequest(
                        "https://api.openweathermap.org/data/2.5/weather?q=" + nameCity
                                + "&appid=" + API_KEY + "&lang=pt_br&units=metric",
                        HttpMethod.GET, null, CoordRequestDTO.class, new HttpHeaders())
                .getBody();

        return request.getCoord(); // Retorna as coordenadas obtidas na resposta
    }

    // Método que retorna as previsões do tempo para os próximos quatro dias
    public ForecastFourDaysResponseDTO getForecastFourDays(String nameCity) {
        RestTemplate restTemplate = new RestTemplate();
        GetData dataCoord = new GetData(restTemplate);

        // Faz uma requisição à API para obter as previsões para os próximos 4 dias
        ForecastFourDaysRequestDTO requestDTO = dataCoord.sendRequest(
                        "https://pro.openweathermap.org/data/2.5/forecast/hourly?lat="
                                + this.getCoord(nameCity).getLat() + "&lon="
                                + this.getCoord(nameCity).getLon() + "&appid="
                                + API_KEY + "&lang=pt_br&units=metric",
                        HttpMethod.GET, null, ForecastFourDaysRequestDTO.class, new HttpHeaders())
                .getBody();

        // Atualiza as previsões com a cidade e transforma os dados
        requestDTO.getList().forEach(forecastFourDays -> {
            this.editLastDataRequestDTO(forecastFourDays, requestDTO.getCity().getCityName());
        });

        // Agrupa as previsões por data e hora, retornando a resposta no formato adequado
        Map<String, Set<ForecastFourDays>> forecastFourDaysMap = this.makeMap(requestDTO.getList());
        return new ForecastFourDaysResponseDTO(requestDTO.getQuantityforecast(), forecastFourDaysMap);
    }

    // Agrupa as previsões por data, e dentro de cada data, agrupa por hora
    private Map<String, Set<ForecastFourDays>> makeMap(List<ForecastFourDays> requestList) {
        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> forecast.getDateForecast(), // Chave de agrupamento por data
                        () -> new TreeMap<String, Set<ForecastFourDays>>(), // Tipo do mapa (TreeMap para ordenação)
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFourDays -> forecastFourDays.getHourForecast())))) // Ordena por hora
                );
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

    // Retorna uma versão paginada das previsões para os próximos 4 dias
    public ForecastFourDaysLimitationResponseDTO getPaginationForecast(String nameCity, Pageable pageable) {
        RestTemplate restTemplate = new RestTemplate();
        GetData dataCoord = new GetData(restTemplate);

        // Faz a requisição à API para obter as previsões para os próximos 4 dias
        ForecastFourDaysRequestDTO requestDTO = dataCoord.sendRequest(
                        "https://pro.openweathermap.org/data/2.5/forecast/hourly?lat="
                                + this.getCoord(nameCity).getLat() + "&lon="
                                + this.getCoord(nameCity).getLon() + "&appid="
                                + API_KEY + "&lang=pt_br&units=metric",
                        HttpMethod.GET, null, ForecastFourDaysRequestDTO.class, new HttpHeaders())
                .getBody();

        // Atualiza as previsões com os dados da cidade e transforma os dados
        requestDTO.getList().forEach(forecastFourDays -> {
            this.editLastDataRequestDTO(forecastFourDays, requestDTO.getCity().getCityName());
        });

        // Agrupa as previsões paginadas por data
        Map<String, List<ForecastFourDays>> forecastFourDaysMap = this.makePaginatedMap(requestDTO.getList(), pageable);
        return new ForecastFourDaysLimitationResponseDTO(forecastFourDaysMap);
    }

    // Agrupa as previsões paginadas por data
    private Map<String, List<ForecastFourDays>> makePaginatedMap(List<ForecastFourDays> requestList, Pageable pageable) {
        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsão para o dia " + forecast.getDateForecast(), // Agrupamento por data
                        () -> new TreeMap<String, List<ForecastFourDays>>(),
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFourDays -> forecastFourDays.getHourForecast()))),
                                forecasts -> paginateForecasts(forecasts, pageable)
                        )
                ));
    }

    // Aplica a paginação nas previsões
    private List<ForecastFourDays> paginateForecasts(Set<ForecastFourDays> forecasts, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), forecasts.size());
        return new ArrayList<>(forecasts).subList(start, end); // Retorna a sublista de previsões
    }

    // Atualiza as previsões com a data e hora formatadas
    private void editLastDataRequestDTO(ForecastFourDays forecastFourDays, String nameCity) {
        forecastFourDays.getClimate().setNameCity(nameCity);
        forecastFourDays.setDateHourForecast(this.transformDate(forecastFourDays.getDateTime()));
        forecastFourDays.setDateForecast(this.pickUpOnlyDate(forecastFourDays.getDateHourForecast()));
        forecastFourDays.setHourForecast(this.pickUpOnlyTime(forecastFourDays.getDateHourForecast()));
    }

    // Método para obter as condições climáticas atuais de uma cidade
    public WeatherConditionsResponseDTO getCondition(String nameCity) {
        RestTemplate rest = new RestTemplate();
        GetData data = new GetData(rest);

        // Faz a requisição à API para obter as condições climáticas atuais
        WeatherConditionsRequestDTO requestDTO = data.sendRequest(
                        "https://api.openweathermap.org/data/2.5/weather?q="
                                + nameCity + "&appid=" + API_KEY + "&lang=pt_br&units=metric",
                        HttpMethod.GET, null, WeatherConditionsRequestDTO.class, new HttpHeaders())
                .getBody();

        return new WeatherConditionsResponseDTO(requestDTO.getWeatherConditions().getFirst()); // Retorna a primeira condição climática da lista
    }

    // Método para obter a temperatura atual de uma cidade
    public TemperatureResponseDTO getTemperature(String nameCity) {
        RestTemplate rest = new RestTemplate();
        GetData data = new GetData(rest);

        // Faz a requisição à API para obter a temperatura atual
        ClimateRequestDTO requestDTO = data.sendRequest(
                        "https://api.openweathermap.org/data/2.5/weather?q="
                                + nameCity + "&appid=" + API_KEY + "&lang=pt_br&units=metric",
                        HttpMethod.GET, null, ClimateRequestDTO.class, new HttpHeaders())
                .getBody();

        // Retorna a temperatura em Celsius, Fahrenheit e Kelvin
        requestDTO.getClimate().setNameCity(requestDTO.getNameCity());
        return new TemperatureResponseDTO(
                requestDTO.getClimate().getTemperature(),
                this.transformCelsiusToFahrenheit(requestDTO.getClimate().getTemperature().replaceAll("[^\\d.-]","").trim()),
                this.transformCelsiusToKelvin(requestDTO.getClimate().getTemperature().replaceAll("[^\\d.-]","").trim())
        );
    }

    // Converte a temperatura de Celsius para Fahrenheit
    private String transformCelsiusToFahrenheit(String tempCelsius) {
        return String.format("%.2f", (Double.parseDouble(tempCelsius) * 1.8) + 32) + " °F";
    }

    // Converte a temperatura de Celsius para Kelvin
    private String transformCelsiusToKelvin(String tempCelsius) {
        return String.format("%.2f", Double.parseDouble(tempCelsius) + 273.15) + " K";
    }

    // Método para obter as informações climáticas detalhadas de uma cidade
    public ClimateResponseDTO getClimate(String nameCity) {
        RestTemplate rest = new RestTemplate();
        GetData data = new GetData(rest);

        // Faz a requisição à API para obter o clima detalhado da cidade
        ClimateRequestDTO requestDTO = data.sendRequest(
                        "https://api.openweathermap.org/data/2.5/weather?q="
                                + nameCity + "&appid=" + API_KEY + "&lang=pt_br&units=metric",
                        HttpMethod.GET, null, ClimateRequestDTO.class, new HttpHeaders())
                .getBody();

        // Retorna as condições climáticas detalhadas
        requestDTO.getClimate().setNameCity(requestDTO.getNameCity());
        return new ClimateResponseDTO(requestDTO.getClimate());
    }
}