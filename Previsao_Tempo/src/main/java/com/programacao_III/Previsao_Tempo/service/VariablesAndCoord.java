package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dtos.coordDTO.CoordRequestDTO;
import com.programacao_III.Previsao_Tempo.models.coord.Coord;
import com.programacao_III.Previsao_Tempo.utils.GetData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public abstract class VariablesAndCoord {

    protected RestTemplate rest = new RestTemplate();
    protected GetData data = new GetData(rest);

    @Value("${api.key}")
    protected String API_KEY;

    @Value("${api.urlWeather}")
    protected String API_URL;

    @Value("${api.urlAppid}")
    protected String API_APPID;

    @Value("${api.urlLang}")
    protected String API_LANG;

    @Value("${api.urlForecastFourDays}")
    protected String API_URL_FORECAST_FOUR_DAYS;

    @Value("${api.urlForecastFiveDays}")
    protected String API_URL_FORECAST_FIVE_DAYS;

    @Value("${api.urlLon}")
    protected String API_LON;

    @Value("${api.urlForecastFifteenDays}")
    protected String API_URL_FORECAST_FIFTEEN_DAYS;

    @Value("${api.urlCnt}")
    protected String API_CNT;

    @Value("${api.urlForecastLastTwentyFourHours}")
    protected String API_URL_FORECAST_LADTTWENTYFOURHOURS;


    protected Coord getCoord(String nameCity) {

        CoordRequestDTO request = this.data.sendRequest(this.makeUrlTodayAndCoord(nameCity),
                        HttpMethod.GET, null, CoordRequestDTO.class, new HttpHeaders())
                .getBody();
        return request.getCoord();
    }

    protected String makeUrlTodayAndCoord(String nameCity) {
        return  API_URL + nameCity + API_APPID + API_KEY + API_LANG;
    }

}
