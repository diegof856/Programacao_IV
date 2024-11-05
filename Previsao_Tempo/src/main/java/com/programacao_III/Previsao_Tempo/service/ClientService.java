package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dto.ClimateDTO.ClimateRequestDTO;
import com.programacao_III.Previsao_Tempo.dto.ClimateDTO.ClimateResponseDTO;
import com.programacao_III.Previsao_Tempo.dto.CoordDTO.CoordRequestDTO;
import com.programacao_III.Previsao_Tempo.dto.CoordDTO.CoordResponseDTO;
import com.programacao_III.Previsao_Tempo.dto.WeatherConditionsDTO.WeatherConditionsRequestDTO;
import com.programacao_III.Previsao_Tempo.dto.WeatherConditionsDTO.WeatherConditionsResponseDTO;
import com.programacao_III.Previsao_Tempo.utils.GetData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {
    @Value("${api.key}")
    private String API_KEY;

    public CoordResponseDTO getCoord(String nameCity){
        RestTemplate restTemplate = new RestTemplate();

        GetData dataCoord = new GetData(restTemplate);

      CoordRequestDTO request =  dataCoord.sendRequest("https://api.openweathermap.org/data/2.5/weather?q="
                        + nameCity
                        + "&appid="+API_KEY+"&lang=pt_br&units=metric"
                ,HttpMethod.GET
                , null
                , CoordRequestDTO.class
                ,new HttpHeaders()).getBody();

        return new CoordResponseDTO(request.getCoord());

    }

    public WeatherConditionsResponseDTO getCondition(String nameCity){

        RestTemplate rest = new RestTemplate();
        GetData data = new GetData(rest);
      WeatherConditionsRequestDTO requestDTO =  data.sendRequest("https://api.openweathermap.org/data/2.5/weather?q="
                        + nameCity
                        + "&appid="+API_KEY+"&lang=pt_br&units=metric"
                ,HttpMethod.GET
                , null
                , WeatherConditionsRequestDTO.class
                ,new HttpHeaders()).getBody();

        return new WeatherConditionsResponseDTO(requestDTO.getWeatherConditions()
                .stream()
                .map(weatherConditions -> weatherConditions.getDescription())
                .findFirst()
                .orElse("Descricao disponivel"));

    }

    public ClimateResponseDTO getTemperature(String nameCity){
        RestTemplate rest = new RestTemplate();
        GetData data = new GetData(rest);

        ClimateRequestDTO requestDTO = data.sendRequest("https://api.openweathermap.org/data/2.5/weather?q="
                        + nameCity
                        + "&appid="+API_KEY+"&lang=pt_br&units=metric"
                ,HttpMethod.GET
                , null
                , ClimateRequestDTO.class
                ,new HttpHeaders()).getBody();
        return new ClimateResponseDTO(requestDTO.getClimate().getTemperature()+"ºC"
                ,requestDTO.getClimate().getHumidity()+"ºC"
                ,requestDTO.getClimate().getFeels_like()+"ºC"
                ,requestDTO.getClimate().getPressure()+" hPa"
                ,requestDTO.getClimate().getTemp_min()+"ºC"
                ,requestDTO.getClimate().getTemp_max()+"ºC"
                ,requestDTO.getClimate().getSea_level()+" hPa"
                ,requestDTO.getClimate().getGrnd_level()+" hPa"
                ,nameCity);
    }
}
