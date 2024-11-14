package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dtos.ClimateDTO.ClimateRequestDTO;
import com.programacao_III.Previsao_Tempo.dtos.ClimateDTO.ClimateResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.ClimateDTO.ClimateTemperatureResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.ClimateDTO.ClimateWindResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.WeatherConditionsDTO.WeatherConditionsRequestDTO;
import com.programacao_III.Previsao_Tempo.dtos.WeatherConditionsDTO.WeatherConditionsResponseDTO;
import com.programacao_III.Previsao_Tempo.exceptions.CityNotFoundException;
import com.programacao_III.Previsao_Tempo.exceptions.InternalServerErrorException;
import com.programacao_III.Previsao_Tempo.model.HourDawnNightfall;
import com.programacao_III.Previsao_Tempo.utils.GetData;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class ForecastCityTodayService {

    @Value("${api.key}") // Injeta a chave da API do OpenWeatherMap
    private String API_KEY;
    @Value("${api.urlWeather}")
    private String API_URL;
    @Value("${api.urlAppid}")
    private String API_APPID;
    @Value("${api.urlLang}")
    private String API_LANG;
    // Método para obter as condições climáticas atuais de uma cidade
    public WeatherConditionsResponseDTO getCondition(String nameCity) {
        RestTemplate rest = new RestTemplate();
        GetData data = new GetData(rest);

        // Faz a requisição à API para obter as condições climáticas atuais
       try {
           WeatherConditionsRequestDTO requestDTO = this.makeWeatherRequestDTO(nameCity,data);
           return new WeatherConditionsResponseDTO(requestDTO.getWeatherConditions().getFirst()); // Retorna a primeira condição climática da lista

       }catch (HttpClientErrorException.NotFound e){
           throw new CityNotFoundException();
       }catch (RuntimeException e){
           throw new InternalServerErrorException();
       }

    }

    // Método para obter a temperatura atual de uma cidade
    public ClimateTemperatureResponseDTO getTemperature(String nameCity) {
        RestTemplate rest = new RestTemplate();
        GetData data = new GetData(rest);

        try{
            // Faz a requisição à API para obter a temperatura atual
            ClimateRequestDTO requestDTO = this.makeRequestDTO(nameCity,data);

            // Retorna a temperatura em Celsius, Fahrenheit e Kelvin
            requestDTO.getClimate().setNameCity(requestDTO.getNameCity());
            return new ClimateTemperatureResponseDTO(
                    requestDTO.getClimate().getTemperature(),
                    this.transformCelsiusToFahrenheit(requestDTO.getClimate().getTemperature().replaceAll("[^\\d.-]","").trim()),
                    this.transformCelsiusToKelvin(requestDTO.getClimate().getTemperature().replaceAll("[^\\d.-]","").trim())
            );
        } catch (HttpClientErrorException.NotFound e){
            throw new CityNotFoundException();
        }catch (RuntimeException e){
            throw new InternalServerErrorException();
        }

    }
    // Método para obter as informações climáticas detalhadas de uma cidade
    public ClimateResponseDTO getClimate(String nameCity) {
        RestTemplate rest = new RestTemplate();
        GetData data = new GetData(rest);
    try{

        // Faz a requisição à API para obter o clima detalhado da cidade
        ClimateRequestDTO requestDTO = this.makeRequestDTO(nameCity,data);
        WeatherConditionsRequestDTO weatherRequestDTO = this.makeWeatherRequestDTO(nameCity,data);
        // Retorna as condições climáticas detalhadas
        requestDTO.getClimate().setNameCity(requestDTO.getNameCity());
        return new ClimateResponseDTO(requestDTO.getClimate()
                ,new HourDawnNightfall(
                        this.transformTimesTamp(requestDTO.getCityInfo().getSunrise())
                        ,this.transformTimesTamp(requestDTO.getCityInfo().getSunset())
                    )
                ,requestDTO.getWindToday()
                ,weatherRequestDTO.getWeatherConditions().getFirst());
    }catch (HttpClientErrorException.NotFound e){
        throw new CityNotFoundException();
    }catch (RuntimeException e){
        throw new InternalServerErrorException();
    }

    }
    public ClimateWindResponseDTO getWind(String nameCity){

        RestTemplate rest = new RestTemplate();
        GetData data = new GetData(rest);

        // Faz a requisição à API para obter o clima detalhado da cidade
        ClimateRequestDTO requestDTO = data.sendRequest(API_URL + nameCity + API_APPID + API_KEY + API_LANG,
                        HttpMethod.GET, null, ClimateRequestDTO.class, new HttpHeaders())
                .getBody();

        return  new ClimateWindResponseDTO(requestDTO.getWindToday());
    }
    private WeatherConditionsRequestDTO makeWeatherRequestDTO(String nameCity,GetData data){
        return data.sendRequest(
                        API_URL + nameCity + API_APPID + API_KEY + API_LANG,
                        HttpMethod.GET, null, WeatherConditionsRequestDTO.class, new HttpHeaders())
                .getBody();
    }
    private ClimateRequestDTO makeRequestDTO(String nameCity, GetData data){
        return data.sendRequest(API_URL + nameCity + API_APPID + API_KEY + API_LANG,
                        HttpMethod.GET, null, ClimateRequestDTO.class, new HttpHeaders())
                .getBody();
    }

    // Converte a temperatura de Celsius para Fahrenheit
    private String transformCelsiusToFahrenheit(String tempCelsius) {
        return String.format("%.2f", (Double.parseDouble(tempCelsius) * 1.8) + 32) + " °F";
    }

    // Converte a temperatura de Celsius para Kelvin
    private String transformCelsiusToKelvin(String tempCelsius) {
        return String.format("%.2f", Double.parseDouble(tempCelsius) + 273.15) + " K";
    }


    private String transformTimesTamp(Long timesTamp){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timesTamp),ZoneId.systemDefault()).format(timeFormatter);
    }
}