package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO.ForecastFourDaysPageableDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO.ForecastFourDaysRequestDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFourDaysDTO.AllForecastFourDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.exceptions.CityNotFoundException;
import com.programacao_III.Previsao_Tempo.exceptions.InternalServerErrorException;
import com.programacao_III.Previsao_Tempo.exceptions.PagesEndException;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFourDays;
import com.programacao_III.Previsao_Tempo.utils.GetData;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ForecastCityFourDaysService extends AuxiliaryMethods {

    // Método principal para retornar as previsões para os próximos 4 dias em formato paginado
    public ForecastFourDaysPageableDTO getForecast(String nameCity, Pageable pageable) {
        try {
            // Faz a requisição à API para obter as previsões para os próximos 4 dias
            ForecastFourDaysRequestDTO requestDTO = this.makeRequestDTO(nameCity);

            this.editLastDataFourDayRequestDTO(requestDTO); // Atualiza a data e hora das previsões

            // Agrupa as previsões paginadas por data e retorna as previsões paginadas
            Map<String, List<ForecastFourDays>> forecastFourDaysMap = this.makeFourDaysPaginatedMap(requestDTO.getList(), pageable);
            return new ForecastFourDaysPageableDTO(forecastFourDaysMap); // Retorna a resposta paginada

        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException(); // Lança exceção caso a cidade não seja encontrada
        } catch (IllegalArgumentException e) {
            throw new PagesEndException(); // Lança exceção caso as páginas sejam inválidas
        } catch (RuntimeException e) {
            throw new InternalServerErrorException(); // Lança exceção em caso de erro interno
        }
    }

    // Método para retornar todas as previsões para os próximos 4 dias sem paginação
    public AllForecastFourDaysResponseDTO getAllForecastFourDays(String nameCity) {
        try {
            // Faz a requisição à API para obter as previsões para os próximos 4 dias
            ForecastFourDaysRequestDTO requestDTO = this.makeRequestDTO(nameCity);
            this.editLastDataFourDayRequestDTO(requestDTO); // Atualiza as previsões com data e hora formatadas

            // Agrupa as previsões por data e hora, retornando a resposta no formato adequado
            Map<String, Set<ForecastFourDays>> forecastFourDaysMap = this.makeFourDayMap(requestDTO.getList());
            return new AllForecastFourDaysResponseDTO(requestDTO.getQuantityforecast(), forecastFourDaysMap); // Retorna todas as previsões agrupadas
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException(); // Lança exceção caso a cidade não seja encontrada
        } catch (RuntimeException e) {
            throw new InternalServerErrorException(); // Lança exceção em caso de erro interno
        }
    }

    // Método para atualizar as previsões com a data e hora formatadas
    private void editLastDataFourDayRequestDTO(ForecastFourDaysRequestDTO requestFourDayDTO) {
        // Para cada previsão, são feitas alterações nos campos relacionados à cidade e horários
        requestFourDayDTO.getList().forEach(forecastFourDays -> {
            forecastFourDays.setCity(requestFourDayDTO.getCity()); // Define a cidade associada à previsão
            forecastFourDays.getCity().setSunriseHour(this.transformTimesTampHour(forecastFourDays.getCity().getSunrise())); // Define a hora do nascer do sol
            forecastFourDays.getCity().setSunsetHour(this.transformTimesTampHour(forecastFourDays.getCity().getSunset())); // Define a hora do pôr do sol
            forecastFourDays.getCity().setQuantityPopulation(this.transformFormatMoreAttractive(forecastFourDays.getCity().getPopulation())); // Formata a população
            forecastFourDays.setDateHourForecast(this.transformDate(forecastFourDays.getDateTime())); // Transforma a data e hora do timestamp
            forecastFourDays.setDateForecast(this.pickUpOnlyDate(forecastFourDays.getDateHourForecast())); // Pega apenas a data
            forecastFourDays.setHourForecast(this.pickUpOnlyTime(forecastFourDays.getDateHourForecast())); // Pega apenas a hora
        });
    }

    // Método para agrupar as previsões por data e hora, retornando um mapa com as previsões ordenadas
    private Map<String, Set<ForecastFourDays>> makeFourDayMap(List<ForecastFourDays> requestList) {
        // Agrupa as previsões por data e, dentro de cada data, agrupa por hora
        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsao_Para_O_Dia " + forecast.getDateForecast(), // Agrupamento por data
                        () -> new TreeMap<String, Set<ForecastFourDays>>(), // Tipo de mapa (TreeMap para ordenação)
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFourDays -> forecastFourDays.getHourForecast())))) // Ordenação por hora
                );
    }

    // Método para agrupar as previsões paginadas por data e hora, com a aplicação da paginação
    private Map<String, List<ForecastFourDays>> makeFourDaysPaginatedMap(List<ForecastFourDays> requestList, Pageable pageable) {
        // Agrupa as previsões por data e, dentro de cada data, agrupa por hora e aplica a paginação
        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsao_Para_O_Dia " + forecast.getDateForecast(), // Agrupamento por data
                        () -> new TreeMap<String, List<ForecastFourDays>>(),
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFourDays -> forecastFourDays.getHourForecast()))), // Ordenação por hora
                                forecasts -> paginateFourDaysForecasts(forecasts, pageable) // Aplica a paginação
                        )
                ));
    }

    // Método para aplicar a paginação nas previsões
    private List<ForecastFourDays> paginateFourDaysForecasts(Set<ForecastFourDays> forecasts, Pageable pageable) {
        // Calcula o início e o fim da sublista de previsões com base na página solicitada
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), forecasts.size());
        return new ArrayList<>(forecasts).subList(start, end); // Retorna a sublista de previsões
    }

    // Método para fazer a requisição à API e obter os dados da previsão para os próximos 4 dias
    private ForecastFourDaysRequestDTO makeRequestDTO(String nameCity) {
        return this.data.sendRequest(this.makeUrl(nameCity), HttpMethod.GET, null, ForecastFourDaysRequestDTO.class, new HttpHeaders()).getBody();
    }

    // Método para construir a URL para a requisição à API usando o nome da cidade
    private String makeUrl(String nameCity) {
        return API_URL_FORECAST_FOUR_DAYS + this.getCoord(nameCity).getLat() + API_LON + this.getCoord(nameCity).getLon() + API_APPID + API_KEY + API_LANG;
    }
}