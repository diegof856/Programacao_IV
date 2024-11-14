package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dtos.CoordDTO.CoordRequestDTO;
import com.programacao_III.Previsao_Tempo.dtos.ForecastFourDaysDTO.ForecastFourDaysPageableDTO;
import com.programacao_III.Previsao_Tempo.dtos.ForecastFourDaysDTO.ForecastFourDaysRequestDTO;
import com.programacao_III.Previsao_Tempo.dtos.ForecastFourDaysDTO.ForecastFourDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.exceptions.CityNotFoundException;
import com.programacao_III.Previsao_Tempo.exceptions.InternalServerErrorException;
import com.programacao_III.Previsao_Tempo.model.Coord;
import com.programacao_III.Previsao_Tempo.model.ForecastFourDays;
import com.programacao_III.Previsao_Tempo.utils.GetData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ForecastCityFourDaysService extends AuxiliaryMethods {
    @Value("${api.key}") // Injeta a chave da API do OpenWeatherMap
    private String API_KEY;
    @Value("${api.urlWeather}")
    private String API_URL;
    @Value("${api.urlAppid}")
    private String API_APPID;
    @Value("${api.urlLang}")
    private String API_LANG;
    @Value("${api.urlForecastFourDays}")
    private String API_URL_FORECAST_FOUR_DAYS;
    @Value("${api.urlLon}")
    private String URL_LON;

    // Retorna uma versão paginada das previsões para os próximos 4 dias
    public ForecastFourDaysPageableDTO getForecast(String nameCity, Pageable pageable) {
        RestTemplate restTemplate = new RestTemplate();
        GetData data = new GetData(restTemplate);

           try{
               // Faz a requisição à API para obter as previsões para os próximos 4 dias
               ForecastFourDaysRequestDTO requestDTO = this.makeRequestDTO(nameCity,data);

               // Atualiza as previsões com os dados da cidade e transforma os dados
               requestDTO.getList().forEach(forecastFourDays -> {
                   this.editLastDataFourDayRequestDTO(forecastFourDays, requestDTO.getCity().getCityName());
               });

               // Agrupa as previsões paginadas por data
               Map<String, List<ForecastFourDays>> forecastFourDaysMap = this.makeFourDaysPaginatedMap(requestDTO.getList(), pageable);
               return new ForecastFourDaysPageableDTO(forecastFourDaysMap);

           }catch (HttpClientErrorException.NotFound e){
               throw new CityNotFoundException();
           }catch (RuntimeException e){
               throw new InternalServerErrorException();
           }

    }
    // Método que retorna as previsões do tempo para os próximos quatro dias
    public ForecastFourDaysResponseDTO getAllForecastFourDays(String nameCity) {
        RestTemplate restTemplate = new RestTemplate();
        GetData data = new GetData(restTemplate);

       try{
           // Faz uma requisição à API para obter as previsões para os próximos 4 dias
           ForecastFourDaysRequestDTO requestDTO = this.makeRequestDTO(nameCity,data);

           // Atualiza as previsões com a cidade e transforma os dados
           requestDTO.getList().forEach(forecastFourDays -> {
               this.editLastDataFourDayRequestDTO(forecastFourDays, requestDTO.getCity().getCityName());
           });

           // Agrupa as previsões por data e hora, retornando a resposta no formato adequado
           Map<String, Set<ForecastFourDays>> forecastFourDaysMap = this.makeMap(requestDTO.getList());
           return new ForecastFourDaysResponseDTO(requestDTO.getQuantityforecast(), forecastFourDaysMap);
       } catch (HttpClientErrorException.NotFound e) {
           throw new CityNotFoundException();
       }catch (RuntimeException e){
           throw new InternalServerErrorException();
       }
    }
    private ForecastFourDaysRequestDTO makeRequestDTO(String nameCity, GetData data){
        return data.sendRequest(API_URL_FORECAST_FOUR_DAYS
                                + this.getCoord(nameCity).getLat()
                                + URL_LON
                                + this.getCoord(nameCity).getLon()
                                + API_APPID
                                + API_KEY
                                + API_LANG,
                        HttpMethod.GET, null, ForecastFourDaysRequestDTO.class, new HttpHeaders())
                .getBody();
    }

    @Override
    protected Coord getCoord(String nameCity) {
        RestTemplate restTemplate = new RestTemplate();
        GetData dataCoord = new GetData(restTemplate);

        // Faz uma requisição à API do OpenWeatherMap para obter as coordenadas da cidade
        CoordRequestDTO request = dataCoord.sendRequest(API_URL + nameCity + API_APPID + API_KEY + API_LANG,
                        HttpMethod.GET, null, CoordRequestDTO.class, new HttpHeaders())
                .getBody();

        return request.getCoord(); // Retorna as coordenadas obtidas na resposta
    }
}
