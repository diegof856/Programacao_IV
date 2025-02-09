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

    public ForecastFourDaysPageableDTO getForecast(String nameCity, Pageable pageable) {
        try {

            ForecastFourDaysRequestDTO requestDTO = this.makeRequestDTO(nameCity);

            this.editLastDataFourDayRequestDTO(requestDTO);

            Map<String, List<ForecastFourDays>> forecastFourDaysMap = this.makeFourDaysPaginatedMap(requestDTO.getList(), pageable);
            return new ForecastFourDaysPageableDTO(forecastFourDaysMap);

        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (IllegalArgumentException e) {
            throw new PagesEndException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    public AllForecastFourDaysResponseDTO getAllForecastFourDays(String nameCity) {
        try {
            ForecastFourDaysRequestDTO requestDTO = this.makeRequestDTO(nameCity);
            this.editLastDataFourDayRequestDTO(requestDTO);

            Map<String, Set<ForecastFourDays>> forecastFourDaysMap = this.makeFourDayMap(requestDTO.getList());
            return new AllForecastFourDaysResponseDTO(requestDTO.getQuantityforecast(), forecastFourDaysMap);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    private void editLastDataFourDayRequestDTO(ForecastFourDaysRequestDTO requestFourDayDTO) {
        requestFourDayDTO.getList().forEach(forecastFourDays -> {
            forecastFourDays.setCity(requestFourDayDTO.getCity());
            forecastFourDays.getCity().setSunriseHour(this.transformTimesTampHour(forecastFourDays.getCity().getSunrise()));
            forecastFourDays.getCity().setSunsetHour(this.transformTimesTampHour(forecastFourDays.getCity().getSunset()));
            forecastFourDays.getCity().setQuantityPopulation(this.transformFormatMoreAttractive(forecastFourDays.getCity().getPopulation()));
            forecastFourDays.setDateHourForecast(this.transformDate(forecastFourDays.getDateTime()));
            forecastFourDays.setDateForecast(this.pickUpOnlyDate(forecastFourDays.getDateHourForecast()));
            forecastFourDays.setHourForecast(this.pickUpOnlyTime(forecastFourDays.getDateHourForecast()));
        });
    }


    private Map<String, Set<ForecastFourDays>> makeFourDayMap(List<ForecastFourDays> requestList) {

        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsao_Para_O_Dia " + forecast.getDateForecast(),
                        () -> new TreeMap<String, Set<ForecastFourDays>>(),
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFourDays -> forecastFourDays.getHourForecast()))))
                );
    }


    private Map<String, List<ForecastFourDays>> makeFourDaysPaginatedMap(List<ForecastFourDays> requestList, Pageable pageable) {

        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsao_Para_O_Dia " + forecast.getDateForecast(),
                        () -> new TreeMap<String, List<ForecastFourDays>>(),
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFourDays -> forecastFourDays.getHourForecast()))),
                                forecasts -> paginateFourDaysForecasts(forecasts, pageable)
                        )
                ));
    }

    private List<ForecastFourDays> paginateFourDaysForecasts(Set<ForecastFourDays> forecasts, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), forecasts.size());
        return new ArrayList<>(forecasts).subList(start, end);
    }

    private ForecastFourDaysRequestDTO makeRequestDTO(String nameCity) {
        return this.data.sendRequest(this.makeUrl(nameCity), HttpMethod.GET, null, ForecastFourDaysRequestDTO.class, new HttpHeaders()).getBody();
    }

    private String makeUrl(String nameCity) {
        return API_URL_FORECAST_FOUR_DAYS + this.getCoord(nameCity).getLat() + API_LON + this.getCoord(nameCity).getLon() + API_APPID + API_KEY + API_LANG;
    }
}