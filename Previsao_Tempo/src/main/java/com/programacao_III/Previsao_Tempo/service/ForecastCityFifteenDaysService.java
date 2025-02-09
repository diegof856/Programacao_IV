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


    public ForecastFifteenDaysResponseDTO getForecastFifteenDays(String nameCity, Pageable pageable) {
        try {
            ForecastFifteenDaysRequestDTO requestDTO = this.makeFifteenDaysForecastRequestDTO(nameCity);
            this.editLastDataFifteenDayRequesDTO(requestDTO);

            return new ForecastFifteenDaysResponseDTO(
                    requestDTO.getQuantityInquiry(),
                    requestDTO.getCityInfo(),
                    this.paginateFifteenDaysForecasts(requestDTO.getForecastFifteenDaysList(), pageable)
            );
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (IllegalArgumentException e) {
            throw new PagesEndException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    public ForecastFifteenDaysResponseDTO getAllForecastFifteenDays(String nameCity, Integer quantityForecast, Pageable pageable) {
        try {
            if (quantityForecast > 5) {
                ForecastFifteenDaysRequestDTO requestDTO = this.makeFifteenDayForecastRequestWithDTOQuantity(nameCity, quantityForecast);
                this.editLastDataFifteenDayRequesDTO(requestDTO);
                return new ForecastFifteenDaysResponseDTO(
                        requestDTO.getQuantityInquiry(),
                        requestDTO.getCityInfo(),
                        this.paginateFifteenDaysForecasts(requestDTO.getForecastFifteenDaysList(), pageable)
                );
            } else {
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

    public ForecastFifteenDaysRainResponseDTO getListRainsForecastFifteenDays(String nameCity, Pageable pageable, Integer quantityForecast) {
        try {
            ForecastFifteenDaysRequestDTO requestDTO = this.makeFifteenDayForecastRequestWithDTOQuantity(nameCity, quantityForecast);
            this.editLastDataFifteenDayRequesDTO(requestDTO);
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

    private List<ForecastFifteenDays> makeListRain(List<ForecastFifteenDays> forecastFifteenDaysList) {
        return forecastFifteenDaysList.stream().filter(forecastFifteenDays ->
                forecastFifteenDays.getWeatherConditionsList().stream()
                        .anyMatch(weatherConditions -> weatherConditions.getDescription().contains("chuva"))
        ).collect(Collectors.toList());
    }

    private void editLastDataFifteenDayRequesDTO(ForecastFifteenDaysRequestDTO forecastFifteenDaysRequestDTO) {
        forecastFifteenDaysRequestDTO.getCityInfo().setQuantityPopulation(this.transformFormatMoreAttractive(forecastFifteenDaysRequestDTO.getCityInfo().getPopulation()));
        forecastFifteenDaysRequestDTO.getForecastFifteenDaysList().forEach(forecastFifteenDays -> {
            forecastFifteenDays.setDay(this.transformTimesTampDate(forecastFifteenDays.getDt()));
            forecastFifteenDays.setHourDawnNightfall(new HourDawnNightfall(
                    this.transformTimesTampHour(forecastFifteenDays.getSunrise()),
                    this.transformTimesTampHour(forecastFifteenDays.getSunset())
            ));
        });
    }

    private ForecastFifteenDaysRequestDTO makeFifteenDaysForecastRequestDTO(String nameCity) {
        return this.data.sendRequest(this.makeURL(nameCity), HttpMethod.GET, null, ForecastFifteenDaysRequestDTO.class, new HttpHeaders()).getBody();
    }

    private String makeURL(String nameCity) {
        return API_URL_FORECAST_FIFTEEN_DAYS + this.getCoord(nameCity).getLat() + API_LON + this.getCoord(nameCity).getLon() + API_APPID + API_KEY + API_LANG;
    }

    private ForecastFifteenDaysRequestDTO makeFifteenDayForecastRequestWithDTOQuantity(String nameCity, Integer quantityForecast) {
        return this.data.sendRequest(this.makeURLQuantity(nameCity, quantityForecast), HttpMethod.GET, null, ForecastFifteenDaysRequestDTO.class, new HttpHeaders()).getBody();
    }

    private String makeURLQuantity(String nameCity, Integer quantityDays) {
        return API_URL_FORECAST_FIFTEEN_DAYS + this.getCoord(nameCity).getLat() + API_LON + this.getCoord(nameCity).getLon() + API_CNT + quantityDays + API_APPID + API_KEY + API_LANG;
    }

    private List<ForecastFifteenDays> paginateFifteenDaysForecasts(List<ForecastFifteenDays> forecasts, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), forecasts.size());
        return forecasts.subList(start, end);
    }

    private String transformTimesTampDate(Long timesTamp) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timesTamp), ZoneId.systemDefault()).format(timeFormatter);
    }
}
