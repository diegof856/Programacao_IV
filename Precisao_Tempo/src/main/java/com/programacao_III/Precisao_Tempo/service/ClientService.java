package com.programacao_III.Precisao_Tempo.service;

import com.programacao_III.Precisao_Tempo.dto.*;
import com.programacao_III.Precisao_Tempo.utils.GetData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {


    public CoordResponseDTO getCoord(String nameCity){
        RestTemplate restTemplate = new RestTemplate();

        GetData dataCoord = new GetData(restTemplate);

      CoordRequestDTO request =  dataCoord.sendRequest("https://api.openweathermap.org/data/2.5/weather?q="
                        + nameCity
                        + "&appid=Key&lang=pt_br&units=metric"
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
                        + "&appid=Key&lang=pt_br&units=metric"
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
                        + "&appid=Key&lang=pt_br&units=metric"
                ,HttpMethod.GET
                , null
                , ClimateRequestDTO.class
                ,new HttpHeaders()).getBody();
        return new ClimateResponseDTO(requestDTO.getClimate().toString(),nameCity);
    }
}
