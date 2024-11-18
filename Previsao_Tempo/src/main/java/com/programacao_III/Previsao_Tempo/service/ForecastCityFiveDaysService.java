package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO.AllForecastFiveDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO.ForecastFiveDaysPageAbleResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO.ForecastFiveDaysResquestDTO;
import com.programacao_III.Previsao_Tempo.exceptions.CityNotFoundException;
import com.programacao_III.Previsao_Tempo.exceptions.InternalServerErrorException;
import com.programacao_III.Previsao_Tempo.exceptions.PagesEndException;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFiveDays;
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
public class ForecastCityFiveDaysService extends AuxiliaryMethods {

    // Método para obter a previsão de 5 dias com paginação
    public ForecastFiveDaysPageAbleResponseDTO getForecastFiveDays(String nameCity, Pageable pageable){
        try{
            // Cria o DTO de requisição para os próximos 5 dias para a cidade
            ForecastFiveDaysResquestDTO resquestDTO = this.makeRequestDTO(nameCity);

            // Processa os dados da previsão
            this.editLastDataFiveDayRequestDTO(resquestDTO);

            // Agrupa as previsões paginadas por data
            Map<String, List<ForecastFiveDays>> forecastFiveDaysMap = this.makeFiveDaysPaginatedMap(resquestDTO.getList(), pageable);

            // Retorna as previsões agrupadas por data e paginadas
            return new ForecastFiveDaysPageAbleResponseDTO(forecastFiveDaysMap);
        }catch (HttpClientErrorException.NotFound e) {
            // Lança exceção se a cidade não for encontrada
            throw new CityNotFoundException();
        }catch (IllegalArgumentException e){
            // Lança exceção se a página solicitada não existir
            throw new PagesEndException();
        }catch (RuntimeException e){
            // Lança exceção para erro interno do servidor
            throw new InternalServerErrorException();
        }
    }

    // Método para obter todas as previsões dos próximos 5 dias
    public AllForecastFiveDaysResponseDTO getAllForecastFiveDays(String nameCity){
        try{
            // Cria o DTO de requisição para os próximos 5 dias
            ForecastFiveDaysResquestDTO resquestDTO = this.makeRequestDTO(nameCity);

            // Processa os dados da previsão
            this.editLastDataFiveDayRequestDTO(resquestDTO);

            // Agrupa todas as previsões por data
            Map<String, Set<ForecastFiveDays>> mapAllForescastsFiveDays = this.makeFiveDaysMap(resquestDTO.getList());

            // Retorna as previsões agrupadas
            return new AllForecastFiveDaysResponseDTO(resquestDTO.getQuantityforecast(), mapAllForescastsFiveDays);
        } catch (HttpClientErrorException.NotFound e) {
            // Lança exceção se a cidade não for encontrada
            throw new CityNotFoundException();
        }catch (IllegalArgumentException e){
            // Lança exceção se ocorrer erro relacionado à página
            throw new PagesEndException();
        }catch (RuntimeException e){
            // Lança exceção para erro interno do servidor
            throw new InternalServerErrorException();
        }
    }

    // Método para criar o DTO de requisição para os próximos 5 dias de uma cidade
    private ForecastFiveDaysResquestDTO makeRequestDTO(String nameCity){
        return this.data.sendRequest(this.makeUrl(nameCity), HttpMethod.GET, null, ForecastFiveDaysResquestDTO.class, new HttpHeaders()).getBody();
    }

    // Método para gerar a URL para a requisição de previsões de 5 dias
    private String makeUrl(String nameCity){
        return API_URL_FORECAST_FIVE_DAYS + this.getCoord(nameCity).getLat() + API_LON + this.getCoord(nameCity).getLon() + API_APPID + API_KEY + API_LANG;
    }

    // Agrupa as previsões de 5 dias por data e, dentro de cada data, agrupa por hora
    private Map<String, Set<ForecastFiveDays>> makeFiveDaysMap(List<ForecastFiveDays> requestList) {
        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsão para o dia "+ forecast.getDateForecast(), // Agrupamento por data
                        () -> new TreeMap<String, Set<ForecastFiveDays>>(), // Usando TreeMap para garantir a ordenação
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFiveDays -> forecastFiveDays.getHourForecast())))) // Ordena as previsões por hora
                );
    }

    // Método para processar os dados de previsão (hora do nascer e pôr do sol, data, etc.)
    private void editLastDataFiveDayRequestDTO(ForecastFiveDaysResquestDTO resquestFiveDaysDTO){
        resquestFiveDaysDTO.getList().forEach(forecastFiveDays -> {
            forecastFiveDays.setCity(resquestFiveDaysDTO.getCity());
            forecastFiveDays.getCity().setSunriseHour(this.transformTimesTampHour(forecastFiveDays.getCity().getSunrise())); // Converte a hora do nascer do sol
            forecastFiveDays.getCity().setSunsetHour(this.transformTimesTampHour(forecastFiveDays.getCity().getSunset())); // Converte a hora do pôr do sol
            forecastFiveDays.getCity().setQuantityPopulation(this.transformFormatMoreAttractive(forecastFiveDays.getCity().getPopulation())); // Converte a população para formato mais legível
            forecastFiveDays.setDateHourForecast(this.transformDate(forecastFiveDays.getDateTime())); // Converte o timestamp para data e hora
            forecastFiveDays.setDateForecast(this.pickUpOnlyDate(forecastFiveDays.getDateHourForecast())); // Extrai apenas a data
            forecastFiveDays.setHourForecast(this.pickUpOnlyTime(forecastFiveDays.getDateHourForecast())); // Extrai apenas a hora
        });
    }

    // Agrupa as previsões paginadas por data
    private Map<String, List<ForecastFiveDays>> makeFiveDaysPaginatedMap(List<ForecastFiveDays> requestList, Pageable pageable) {
        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsão para o dia " + forecast.getDateForecast(), // Agrupamento por data
                        () -> new TreeMap<String, List<ForecastFiveDays>>(), // Usando TreeMap para garantir ordenação
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFiveDays -> forecastFiveDays.getHourForecast()))), // Ordenação das previsões por hora
                                forecasts -> paginateFiveDaysForecasts(forecasts, pageable) // Paginação das previsões
                        )
                ));
    }

    // Método que aplica a paginação nas previsões
    private List<ForecastFiveDays> paginateFiveDaysForecasts(Set<ForecastFiveDays> forecasts, Pageable pageable) {
        int start = (int) pageable.getOffset(); // Posição inicial da página
        int end = Math.min((start + pageable.getPageSize()), forecasts.size()); // Limita o tamanho da página com base na quantidade de previsões

        return new ArrayList<>(forecasts).subList(start, end); // Retorna a sublista de previsões conforme a paginação
    }
}
