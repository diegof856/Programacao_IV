package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dtos.climateDTO.ClimateRequestDTO;
import com.programacao_III.Previsao_Tempo.dtos.climateDTO.ClimateResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.climateDTO.ClimateTemperatureResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.climateDTO.ClimateWindResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO.WeatherConditionsRequestDTO;
import com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO.WeatherConditionsResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO.WeatherLastTwentyFourHoursRequestDTO;
import com.programacao_III.Previsao_Tempo.dtos.weatherConditionsDTO.WeatherLastTwentyFourHoursResponseDTO;
import com.programacao_III.Previsao_Tempo.exceptions.CityNotFoundException;
import com.programacao_III.Previsao_Tempo.exceptions.InternalServerErrorException;
import com.programacao_III.Previsao_Tempo.models.hourdawnnightfall.HourDawnNightfall;
import com.programacao_III.Previsao_Tempo.models.weatherconditions.WeatherLastTwentyFourHours;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ForecastCityTodayService extends AuxiliaryMethods {

    // Método para obter as condições climáticas atuais de uma cidade
    public WeatherConditionsResponseDTO getCondition(String nameCity) {
        try {
            // Faz a requisição à API para obter as condições climáticas atuais
            WeatherConditionsRequestDTO requestDTO = this.makeWeatherRequestDTO(nameCity);
            // Retorna a primeira condição climática da lista
            return new WeatherConditionsResponseDTO(requestDTO.getWeatherConditions().getFirst());
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();  // Caso a cidade não seja encontrada
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();  // Caso ocorra um erro interno
        }
    }

    // Método para obter a temperatura atual de uma cidade
    public ClimateTemperatureResponseDTO getTemperature(String nameCity) {
        try {
            // Faz a requisição à API para obter a temperatura atual
            ClimateRequestDTO requestDTO = this.makeRequestDTO(nameCity);

            // Retorna a temperatura em Celsius, Fahrenheit e Kelvin
            requestDTO.getClimateToday().setNameCity(requestDTO.getNameCity());
            return new ClimateTemperatureResponseDTO(
                    requestDTO.getClimateToday().getTemperature(),
                    this.transformCelsiusToFahrenheit(requestDTO.getClimateToday().getTemperature().replaceAll("[^\\d.-]", "").trim()),
                    this.transformCelsiusToKelvin(requestDTO.getClimateToday().getTemperature().replaceAll("[^\\d.-]", "").trim())
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();  // Caso a cidade não seja encontrada
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();  // Caso ocorra um erro interno
        }
    }

    // Método para obter as informações climáticas detalhadas de uma cidade
    public ClimateResponseDTO getClimate(String nameCity) {
        try {
            // Faz a requisição à API para obter o clima detalhado da cidade
            ClimateRequestDTO requestDTO = this.makeRequestDTO(nameCity);
            WeatherConditionsRequestDTO weatherRequestDTO = this.makeWeatherRequestDTO(nameCity);
            // Retorna as condições climáticas detalhadas
            requestDTO.getClimateToday().setNameCity(requestDTO.getNameCity());
            return new ClimateResponseDTO(
                    requestDTO.getClimateToday(),
                    new HourDawnNightfall(
                            this.transformTimesTampHour(requestDTO.getCityInfo().getSunrise()),
                            this.transformTimesTampHour(requestDTO.getCityInfo().getSunset())
                    ),
                    requestDTO.getWindToday(),
                    weatherRequestDTO.getWeatherConditions().getFirst()
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();  // Caso a cidade não seja encontrada
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();  // Caso ocorra um erro interno
        }
    }

    // Método para obter informações sobre o vento na cidade
    public ClimateWindResponseDTO getWind(String nameCity) {
        try {
            // Faz a requisição à API para obter informações sobre o vento
            ClimateRequestDTO requestDTO = this.data.sendRequest(this.makeUrlTodayAndCoord(nameCity), HttpMethod.GET, null, ClimateRequestDTO.class, new HttpHeaders()).getBody();
            return new ClimateWindResponseDTO(requestDTO.getWindToday());  // Retorna as informações sobre o vento
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();  // Caso a cidade não seja encontrada
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();  // Caso ocorra um erro interno
        }
    }

    // Método para obter as previsões climáticas das últimas 24 horas para a cidade com paginação
    public WeatherLastTwentyFourHoursResponseDTO ForecastLastTwentyFourHours(String nameCity, Pageable pageable) {
        try {
            // Faz a requisição à API para obter as previsões das últimas 24 horas
            WeatherLastTwentyFourHoursRequestDTO requestDTO = this.makeLastTwentyFourHoursRequest(nameCity);

            // Formata a data e hora das previsões
            requestDTO.getWeatherLastTwentyFourHours().forEach(weatherLastTwentyFourHours -> {
                weatherLastTwentyFourHours.getClimateLastTwentyFourHours().setData(this.transformTimesTampDateHour(weatherLastTwentyFourHours.getDt()));
            });

            // Retorna as previsões paginadas
            return new WeatherLastTwentyFourHoursResponseDTO(
                    requestDTO.getQuantityForecast(),
                    this.paginateLastTwentyFourHoursForecasts(requestDTO.getWeatherLastTwentyFourHours(), pageable)
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();  // Caso a cidade não seja encontrada
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();  // Caso ocorra um erro interno
        }
    }

    // Faz a requisição para obter as previsões das últimas 24 horas
    private WeatherLastTwentyFourHoursRequestDTO makeLastTwentyFourHoursRequest(String nameCity) {
        return this.data.sendRequest(this.makeUrlLastTwentyFourHoursRequest(nameCity), HttpMethod.GET, null, WeatherLastTwentyFourHoursRequestDTO.class, new HttpHeaders()).getBody();
    }

    // Faz a requisição para obter as condições climáticas para a cidade
    private WeatherConditionsRequestDTO makeWeatherRequestDTO(String nameCity) {
        return this.data.sendRequest(this.makeUrlTodayAndCoord(nameCity), HttpMethod.GET, null, WeatherConditionsRequestDTO.class, new HttpHeaders()).getBody();
    }

    // Faz a requisição para obter o clima detalhado para a cidade
    private ClimateRequestDTO makeRequestDTO(String nameCity) {
        return this.data.sendRequest(this.makeUrlTodayAndCoord(nameCity), HttpMethod.GET, null, ClimateRequestDTO.class, new HttpHeaders()).getBody();
    }

    // Método que cria a URL para obter as previsões das últimas 24 horas
    private String makeUrlLastTwentyFourHoursRequest(String nameCity) {
        return API_URL_FORECAST_LADTTWENTYFOURHOURS + this.getCoord(nameCity).getLat() + API_LON + this.getCoord(nameCity).getLon() + API_APPID + API_KEY + API_LANG;
    }

    // Converte a temperatura de Celsius para Fahrenheit
    private String transformCelsiusToFahrenheit(String tempCelsius) {
        return String.format("%.2f", (Double.parseDouble(tempCelsius) * 1.8) + 32) + " °F";
    }

    // Converte a temperatura de Celsius para Kelvin
    private String transformCelsiusToKelvin(String tempCelsius) {
        return String.format("%.2f", Double.parseDouble(tempCelsius) + 273.15) + " K";
    }

    // Método de paginação para previsões das últimas 24 horas
    private List<WeatherLastTwentyFourHours> paginateLastTwentyFourHoursForecasts(
            List<WeatherLastTwentyFourHours> forecasts, Pageable pageable) {
        int start = (int) pageable.getOffset();  // Calcula o índice inicial
        int end = Math.min((start + pageable.getPageSize()), forecasts.size());  // Calcula o índice final

        // Retorna a sublista de previsões para a página atual
        return forecasts.subList(start, end);
    }
}
