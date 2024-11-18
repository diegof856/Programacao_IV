package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays.ForecastFifteenDaysRainResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays.ForecastFifteenDaysRequestDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFifteenDays.ForecastFifteenDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.exceptions.CityNotFoundException;
import com.programacao_III.Previsao_Tempo.exceptions.ForecastFifteenDaysException;
import com.programacao_III.Previsao_Tempo.exceptions.InternalServerErrorException;
import com.programacao_III.Previsao_Tempo.exceptions.PagesEndException;
import com.programacao_III.Previsao_Tempo.models.hourdawnnightfall.HourDawnNightfall;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFifteenDays;
import com.programacao_III.Previsao_Tempo.utils.GetData;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ForecastCityFifteenDaysService extends AuxiliaryMethods {

    // Método que busca a previsão de 15 dias para uma cidade e retorna uma resposta paginada
    public ForecastFifteenDaysResponseDTO getForecastFifteenDays(String nameCity, Pageable pageable) {
        try {
            // Cria um DTO com a solicitação para a previsão de 15 dias
            ForecastFifteenDaysRequestDTO requestDTO = this.makeFifteenDaysForecastRequestDTO(nameCity);
            // Edita os dados da solicitação, como formatação de população e hora do amanhecer/anoitecer
            this.editLastDataFifteenDayRequesDTO(requestDTO);

            // Retorna uma resposta com a previsão paginada
            return new ForecastFifteenDaysResponseDTO(
                    requestDTO.getQuantityInquiry(),
                    requestDTO.getCityInfo(),
                    this.paginateFifteenDaysForecasts(requestDTO.getForecastFifteenDaysList(), pageable)
            );
        } catch (HttpClientErrorException.NotFound e) {
            // Lança exceção se a cidade não for encontrada
            throw new CityNotFoundException();
        } catch (IllegalArgumentException e) {
            // Lança exceção se a página solicitada não existir
            throw new PagesEndException();
        } catch (RuntimeException e) {
            // Lança exceção para erros gerais no servidor
            throw new InternalServerErrorException();
        }
    }

    // Método que retorna todas as previsões de 15 dias com base na quantidade solicitada e paginação
    public ForecastFifteenDaysResponseDTO getAllForecastFifteenDays(String nameCity, Integer quantityForecast, Pageable pageable) {
        try {
            // Verifica se a quantidade de previsões é maior que 5
            if (quantityForecast > 5) {
                // Faz a requisição para uma quantidade maior de previsões
                ForecastFifteenDaysRequestDTO requestDTO = this.makeFifteenDayForecastRequestWithDTOQuantity(nameCity, quantityForecast);
                this.editLastDataFifteenDayRequesDTO(requestDTO);
                return new ForecastFifteenDaysResponseDTO(
                        requestDTO.getQuantityInquiry(),
                        requestDTO.getCityInfo(),
                        this.paginateFifteenDaysForecasts(requestDTO.getForecastFifteenDaysList(), pageable)
                );
            } else {
                // Faz a requisição para uma quantidade menor de previsões
                ForecastFifteenDaysRequestDTO requestDTO = this.makeFifteenDayForecastRequestWithDTOQuantity(nameCity, quantityForecast);
                this.editLastDataFifteenDayRequesDTO(requestDTO);
                return new ForecastFifteenDaysResponseDTO(
                        requestDTO.getQuantityInquiry(),
                        requestDTO.getCityInfo(),
                        requestDTO.getForecastFifteenDaysList()
                );
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (HttpClientErrorException.BadRequest e) {
            throw new ForecastFifteenDaysException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    // Método que retorna uma lista das previsões de chuva para os 15 dias, com paginação
    public ForecastFifteenDaysRainResponseDTO getListRainsForecastFifteenDays(String nameCity, Pageable pageable, Integer quantityForecast) {
        try {
            // Faz a requisição para as previsões de chuva
            ForecastFifteenDaysRequestDTO requestDTO = this.makeFifteenDayForecastRequestWithDTOQuantity(nameCity, quantityForecast);
            this.editLastDataFifteenDayRequesDTO(requestDTO);
            // Filtra as previsões para incluir apenas as que mencionam "chuva"
            List<ForecastFifteenDays> listDaysRain = makeListRain(requestDTO.getForecastFifteenDaysList());

            return new ForecastFifteenDaysRainResponseDTO(this.paginateFifteenDaysForecasts(listDaysRain, pageable));
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (HttpClientErrorException.BadRequest e) {
            throw new ForecastFifteenDaysException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    // Filtra as previsões de chuva na lista de previsões de 15 dias
    private List<ForecastFifteenDays> makeListRain(List<ForecastFifteenDays> forecastFifteenDaysList) {
        return forecastFifteenDaysList.stream().filter(forecastFifteenDays ->
                forecastFifteenDays.getWeatherConditionsList().stream()
                        .anyMatch(weatherConditions -> weatherConditions.getDescription().contains("chuva"))
        ).collect(Collectors.toList());
    }

    // Método que edita os dados da solicitação de previsão de 15 dias
    private void editLastDataFifteenDayRequesDTO(ForecastFifteenDaysRequestDTO forecastFifteenDaysRequestDTO) {
        // Formata a população da cidade para um formato mais atraente
        forecastFifteenDaysRequestDTO.getCityInfo().setQuantityPopulation(this.transformFormatMoreAttractive(forecastFifteenDaysRequestDTO.getCityInfo().getPopulation()));
        // Para cada previsão de 15 dias, formata as datas e horas de amanhecer e anoitecer
        forecastFifteenDaysRequestDTO.getForecastFifteenDaysList().forEach(forecastFifteenDays -> {
            forecastFifteenDays.setDay(this.transformTimesTampDate(forecastFifteenDays.getDt()));
            forecastFifteenDays.setHourDawnNightfall(new HourDawnNightfall(
                    this.transformTimesTampHour(forecastFifteenDays.getSunrise()),
                    this.transformTimesTampHour(forecastFifteenDays.getSunset())
            ));
        });
    }

    // Faz uma requisição para obter as previsões de 15 dias com base no nome da cidade
    private ForecastFifteenDaysRequestDTO makeFifteenDaysForecastRequestDTO(String nameCity) {
        return this.data.sendRequest(this.makeURL(nameCity), HttpMethod.GET, null, ForecastFifteenDaysRequestDTO.class, new HttpHeaders()).getBody();
    }

    // Constrói a URL para fazer a requisição de previsões de 15 dias
    private String makeURL(String nameCity) {
        return API_URL_FORECAST_FIFTEEN_DAYS + this.getCoord(nameCity).getLat() + API_LON + this.getCoord(nameCity).getLon() + API_APPID + API_KEY + API_LANG;
    }

    // Faz uma requisição para obter previsões de 15 dias com uma quantidade específica de previsões
    private ForecastFifteenDaysRequestDTO makeFifteenDayForecastRequestWithDTOQuantity(String nameCity, Integer quantityForecast) {
        return this.data.sendRequest(this.makeURLQuantity(nameCity, quantityForecast), HttpMethod.GET, null, ForecastFifteenDaysRequestDTO.class, new HttpHeaders()).getBody();
    }

    // Constrói a URL para fazer a requisição de previsões de 15 dias com quantidade específica
    private String makeURLQuantity(String nameCity, Integer quantityDays) {
        return API_URL_FORECAST_FIFTEEN_DAYS + this.getCoord(nameCity).getLat() + API_LON + this.getCoord(nameCity).getLon() + API_CNT + quantityDays + API_APPID + API_KEY + API_LANG;
    }

    // Método que lida com a paginação das previsões de 15 dias, retornando apenas a parte relevante da lista
    private List<ForecastFifteenDays> paginateFifteenDaysForecasts(List<ForecastFifteenDays> forecasts, Pageable pageable) {
        int start = (int) pageable.getOffset(); // Posição inicial da página
        int end = Math.min(start + pageable.getPageSize(), forecasts.size()); // Limita o tamanho da lista
        return forecasts.subList(start, end); // Retorna a sublista de previsões
    }

    // Converte o timestamp recebido para uma data no formato "dd-MM-yyyy"
    private String transformTimesTampDate(Long timesTamp) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timesTamp), ZoneId.systemDefault()).format(timeFormatter);
    }
}
