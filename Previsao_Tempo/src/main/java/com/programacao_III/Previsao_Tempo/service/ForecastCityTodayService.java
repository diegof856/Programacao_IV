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

    public WeatherConditionsResponseDTO getCondition(String nameCity) {
        try {
            WeatherConditionsRequestDTO requestDTO = this.makeWeatherRequestDTO(nameCity);
            return new WeatherConditionsResponseDTO(requestDTO.getWeatherConditions().getFirst());
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    public ClimateTemperatureResponseDTO getTemperature(String nameCity) {
        try {
            ClimateRequestDTO requestDTO = this.makeRequestDTO(nameCity);
            requestDTO.getClimateToday().setNameCity(requestDTO.getNameCity());
            return new ClimateTemperatureResponseDTO(
                    requestDTO.getClimateToday().getTemperature(),
                    this.transformCelsiusToFahrenheit(requestDTO.getClimateToday().getTemperature().replaceAll("[^\\d.-]", "").trim()),
                    this.transformCelsiusToKelvin(requestDTO.getClimateToday().getTemperature().replaceAll("[^\\d.-]", "").trim())
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    public ClimateResponseDTO getClimate(String nameCity) {
        try {
            ClimateRequestDTO requestDTO = this.makeRequestDTO(nameCity);
            WeatherConditionsRequestDTO weatherRequestDTO = this.makeWeatherRequestDTO(nameCity);
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
            throw new CityNotFoundException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    public ClimateWindResponseDTO getWind(String nameCity) {
        try {
            ClimateRequestDTO requestDTO = this.data.sendRequest(this.makeUrlTodayAndCoord(nameCity), HttpMethod.GET, null, ClimateRequestDTO.class, new HttpHeaders()).getBody();
            return new ClimateWindResponseDTO(requestDTO.getWindToday());
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    public WeatherLastTwentyFourHoursResponseDTO ForecastLastTwentyFourHours(String nameCity, Pageable pageable) {
        try {
            WeatherLastTwentyFourHoursRequestDTO requestDTO = this.makeLastTwentyFourHoursRequest(nameCity);

            requestDTO.getWeatherLastTwentyFourHours().forEach(weatherLastTwentyFourHours -> {
                weatherLastTwentyFourHours.getClimateLastTwentyFourHours().setData(this.transformTimesTampDateHour(weatherLastTwentyFourHours.getDt()));
            });

            return new WeatherLastTwentyFourHoursResponseDTO(
                    requestDTO.getQuantityForecast(),
                    this.paginateLastTwentyFourHoursForecasts(requestDTO.getWeatherLastTwentyFourHours(), pageable)
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    private WeatherLastTwentyFourHoursRequestDTO makeLastTwentyFourHoursRequest(String nameCity) {
        return this.data.sendRequest(this.makeUrlLastTwentyFourHoursRequest(nameCity), HttpMethod.GET, null, WeatherLastTwentyFourHoursRequestDTO.class, new HttpHeaders()).getBody();
    }
    private WeatherConditionsRequestDTO makeWeatherRequestDTO(String nameCity) {
        return this.data.sendRequest(this.makeUrlTodayAndCoord(nameCity), HttpMethod.GET, null, WeatherConditionsRequestDTO.class, new HttpHeaders()).getBody();
    }

    private ClimateRequestDTO makeRequestDTO(String nameCity) {
        return this.data.sendRequest(this.makeUrlTodayAndCoord(nameCity), HttpMethod.GET, null, ClimateRequestDTO.class, new HttpHeaders()).getBody();
    }
    private String makeUrlLastTwentyFourHoursRequest(String nameCity) {
        return API_URL_FORECAST_LADTTWENTYFOURHOURS + this.getCoord(nameCity).getLat() + API_LON + this.getCoord(nameCity).getLon() + API_APPID + API_KEY + API_LANG;
    }
    private String transformCelsiusToFahrenheit(String tempCelsius) {
        return String.format("%.2f", (Double.parseDouble(tempCelsius) * 1.8) + 32) + " °F";
    }

    private String transformCelsiusToKelvin(String tempCelsius) {
        return String.format("%.2f", Double.parseDouble(tempCelsius) + 273.15) + " K";
    }

    private List<WeatherLastTwentyFourHours> paginateLastTwentyFourHoursForecasts(
            List<WeatherLastTwentyFourHours> forecasts, Pageable pageable) {
        int start = (int) pageable.getOffset();  // Calcula o índice inicial
        int end = Math.min((start + pageable.getPageSize()), forecasts.size());  // Calcula o índice final
        return forecasts.subList(start, end);
    }
}
