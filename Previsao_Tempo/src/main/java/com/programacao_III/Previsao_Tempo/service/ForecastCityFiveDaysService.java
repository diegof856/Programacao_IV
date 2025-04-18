package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO.AllForecastFiveDaysResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO.ForecastFiveDaysPageAbleResponseDTO;
import com.programacao_III.Previsao_Tempo.dtos.forecastFiveDaysDTO.ForecastFiveDaysResquestDTO;
import com.programacao_III.Previsao_Tempo.exceptions.CityNotFoundException;
import com.programacao_III.Previsao_Tempo.exceptions.InternalServerErrorException;
import com.programacao_III.Previsao_Tempo.exceptions.PagesEndException;
import com.programacao_III.Previsao_Tempo.interfaces.IFormatMoreAttractive;
import com.programacao_III.Previsao_Tempo.interfaces.IGetDateOrHour;
import com.programacao_III.Previsao_Tempo.interfaces.IPaginatedMapMethods;
import com.programacao_III.Previsao_Tempo.interfaces.ITransformDateOrHour;
import com.programacao_III.Previsao_Tempo.models.forecasts.ForecastFiveDays;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ForecastCityFiveDaysService extends VariablesAndCoord implements IGetDateOrHour, IFormatMoreAttractive, ITransformDateOrHour, IPaginatedMapMethods<ForecastFiveDays> {

    public ForecastFiveDaysPageAbleResponseDTO getForecastFiveDays(String nameCity, Pageable pageable) {
        try {
            ForecastFiveDaysResquestDTO resquestDTO = this.makeRequestDTO(nameCity);
            this.editLastDataFiveDayRequestDTO(resquestDTO);
            Map<String, List<ForecastFiveDays>> forecastFiveDaysMap = this.makePaginatedMap(resquestDTO.getList(), pageable);
            return new ForecastFiveDaysPageAbleResponseDTO(forecastFiveDaysMap);
        } catch (HttpClientErrorException.NotFound e) {

            throw new CityNotFoundException();
        } catch (IllegalArgumentException e) {
            throw new PagesEndException();
        } catch (RuntimeException e) {

            throw new InternalServerErrorException();
        }
    }

    public AllForecastFiveDaysResponseDTO getAllForecastFiveDays(String nameCity) {
        try {
            ForecastFiveDaysResquestDTO resquestDTO = this.makeRequestDTO(nameCity);
            this.editLastDataFiveDayRequestDTO(resquestDTO);

            Map<String, Set<ForecastFiveDays>> mapAllForescastsFiveDays = this.makeFiveDaysMap(resquestDTO.getList());
            return new AllForecastFiveDaysResponseDTO(resquestDTO.getQuantityforecast(), mapAllForescastsFiveDays);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException();
        } catch (IllegalArgumentException e) {
            throw new PagesEndException();
        } catch (RuntimeException e) {
            throw new InternalServerErrorException();
        }
    }

    private ForecastFiveDaysResquestDTO makeRequestDTO(String nameCity) {
        return this.data.sendRequest(this.makeUrl(nameCity), HttpMethod.GET, null, ForecastFiveDaysResquestDTO.class, new HttpHeaders()).getBody();
    }

    private String makeUrl(String nameCity) {
        return API_URL_FORECAST_FIVE_DAYS + this.getCoord(nameCity).getLat() + API_LON + this.getCoord(nameCity).getLon() + API_APPID + API_KEY + API_LANG;
    }

    @Override
    public Map<String, List<ForecastFiveDays>> makePaginatedMap(List<? extends ForecastFiveDays> requestList, Pageable pageable) {
        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsao_Para_O_Dia " + forecast.getDateForecast(),
                        () -> new TreeMap<String, List<ForecastFiveDays>>(),
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFiveDays -> forecastFiveDays.getHourForecast()))),
                                forecasts -> paginateFiveDaysForecasts(forecasts, pageable)
                        )
                ));
    }

    private Map<String, Set<ForecastFiveDays>> makeFiveDaysMap(List<ForecastFiveDays> requestList) {
        return requestList.stream()
                .collect(Collectors.groupingBy(
                        forecast -> "Previsao_Para_O_Dia " + forecast.getDateForecast(),
                                () ->new TreeMap<String, Set<ForecastFiveDays>>(),
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(forecastFiveDays -> forecastFiveDays.getHourForecast()))))
                );
    }

    private void editLastDataFiveDayRequestDTO(ForecastFiveDaysResquestDTO resquestFiveDaysDTO) {
        resquestFiveDaysDTO.getList().forEach(forecastFiveDays -> {
            forecastFiveDays.setCity(resquestFiveDaysDTO.getCity());
            forecastFiveDays.getCity().setSunriseHour(this.transformTimesTampHour(forecastFiveDays.getCity().getSunrise()));
            forecastFiveDays.getCity().setSunsetHour(this.transformTimesTampHour(forecastFiveDays.getCity().getSunset()));
            forecastFiveDays.getCity().setQuantityPopulation(this.transformFormatMoreAttractive(forecastFiveDays.getCity().getPopulation()));
            forecastFiveDays.setDateHourForecast(this.transformDate(forecastFiveDays.getDateTime()));
            forecastFiveDays.setDateForecast(this.pickUpOnlyDate(forecastFiveDays.getDateHourForecast()));
            forecastFiveDays.setHourForecast(this.pickUpOnlyTime(forecastFiveDays.getDateHourForecast()));
        });
    }
    private List<ForecastFiveDays> paginateFiveDaysForecasts(Set<? extends ForecastFiveDays> forecasts, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), forecasts.size());
        List<ForecastFiveDays> paginatedForecasts = new ArrayList<>(forecasts);
        return paginatedForecasts.subList(start, end);
    }


}
