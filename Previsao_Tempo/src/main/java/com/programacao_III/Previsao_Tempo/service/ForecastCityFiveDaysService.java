package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dtos.CoordDTO.CoordRequestDTO;
import com.programacao_III.Previsao_Tempo.dtos.ForecastFiveDaysDTO.ForecastFiveDaysPageAbleResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.ForecastFiveDaysDTO.ForecastFiveDaysResquestDTO;
import com.programacao_III.Previsao_Tempo.dtos.ForecastFourDaysDTO.ForecastFourDaysPageableDTO;
import com.programacao_III.Previsao_Tempo.dtos.ForecastFourDaysDTO.ForecastFourDaysRequestDTO;
import com.programacao_III.Previsao_Tempo.exceptions.CityNotFoundException;
import com.programacao_III.Previsao_Tempo.exceptions.InternalServerErrorException;
import com.programacao_III.Previsao_Tempo.model.Coord;
import com.programacao_III.Previsao_Tempo.model.ForecastFiveDays;
import com.programacao_III.Previsao_Tempo.model.ForecastFourDays;
import com.programacao_III.Previsao_Tempo.utils.GetData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
@Service
public class ForecastCityFiveDaysService extends AuxiliaryMethods {
    @Value("${api.key}") // Injeta a chave da API do OpenWeatherMap
    private String API_KEY;
    @Value("${api.urlWeather}")
    private String API_URL;
    @Value("${api.urlAppid}")
    private String API_APPID;
    @Value("${api.urlLang}")
    private String API_LANG;
    @Value("${api.urlForecastFiveDays}")
    private String API_URL_FORECAST_FIVE_DAYS;
    @Value("${api.urlLon}")
    private String URL_LON;
    public ForecastFiveDaysPageAbleResponseDTO getForecastFiveDays(String nameCity, Pageable pageable){
        RestTemplate restTemplate = new RestTemplate();
        GetData data = new GetData(restTemplate);
    try{
        ForecastFiveDaysResquestDTO resquestDTO = this.makeRequestDTO(nameCity,data);
        resquestDTO.getList().forEach(forecastFiveDays -> {

            this.editLastDataFiveDayRequestDTO(forecastFiveDays, resquestDTO.getCity().getCityName());
        });
        // Agrupa as previsões paginadas por data
        Map<String, List<ForecastFiveDays>> forecastFiveDaysMap = this.makeFiveDaysPaginatedMap(resquestDTO.getList(), pageable);
        return new ForecastFiveDaysPageAbleResponseDTO(forecastFiveDaysMap);
        }catch (HttpClientErrorException.NotFound e) {
        throw new CityNotFoundException();
    }catch (RuntimeException e){
        throw new InternalServerErrorException();
    }




    }
    private ForecastFiveDaysResquestDTO makeRequestDTO(String nameCity,GetData data){
        return data.sendRequest(API_URL_FORECAST_FIVE_DAYS
                                + this.getCoord(nameCity).getLat()
                                + URL_LON
                                + this.getCoord(nameCity).getLon()
                                + API_APPID
                                + API_KEY
                                + API_LANG,
                        HttpMethod.GET, null, ForecastFiveDaysResquestDTO.class, new HttpHeaders())
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
