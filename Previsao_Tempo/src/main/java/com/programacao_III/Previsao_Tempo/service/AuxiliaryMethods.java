package com.programacao_III.Previsao_Tempo.service;

import com.programacao_III.Previsao_Tempo.dtos.coordDTO.CoordRequestDTO;
import com.programacao_III.Previsao_Tempo.models.coord.Coord;
import com.programacao_III.Previsao_Tempo.utils.GetData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class AuxiliaryMethods {
    // Instanciação do RestTemplate para fazer requisições HTTP
    protected RestTemplate rest = new RestTemplate();
    // Instanciação do utilitário GetData para facilitar a execução de requisições HTTP
    protected GetData data = new GetData(rest);

    // Injeção das variáveis de ambiente com as configurações da API
    @Value("${api.key}") // Injeta a chave da API do OpenWeatherMap
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

    // Método responsável por obter as coordenadas geográficas de uma cidade.
    // Faz uma requisição à API do OpenWeatherMap para obter as coordenadas da cidade.
    protected Coord getCoord(String nameCity) {
        // Faz a requisição HTTP para obter os dados da coordenada da cidade
        CoordRequestDTO request = this.data.sendRequest(this.makeUrlTodayAndCoord(nameCity),
                        HttpMethod.GET, null, CoordRequestDTO.class, new HttpHeaders())
                .getBody();

        // Retorna as coordenadas obtidas na resposta da API
        return request.getCoord();
    }

    // Método para montar a URL para obter as coordenadas da cidade usando o nome da cidade.
    protected String makeUrlTodayAndCoord(String nameCity) {
        // Concatena a URL base com o nome da cidade e os parâmetros necessários para a requisição
        return  API_URL + nameCity + API_APPID + API_KEY + API_LANG;
    }

    // Método para transformar um timestamp (segundos desde a época Unix) em uma hora no formato "HH:mm:ss"
    protected String transformTimesTampHour(Long timesTamp) {
        // Cria um formatador para formatar a hora no padrão "HH:mm:ss"
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Converte o timestamp em um LocalDateTime e formata para a hora
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timesTamp), ZoneId.systemDefault()).format(timeFormatter);
    }

    // Método para transformar um timestamp (segundos desde a época Unix) em uma data e hora no formato "yyyy-MM-dd HH:mm:ss"
    protected String transformTimesTampDateHour(Long timesTamp) {
        // Cria um formatador para formatar a data e hora no padrão "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Converte o timestamp em um LocalDateTime e formata para a data e hora
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timesTamp), ZoneId.systemDefault()).format(timeFormatter);
    }

    // Converte uma string de data no formato "yyyy-MM-dd HH:mm:ss" para um objeto LocalDateTime
    protected LocalDateTime transformDate(String dateTime) {
        // Cria um formatador para converter a string para LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Converte a string para LocalDateTime
        return LocalDateTime.parse(dateTime, formatter).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    // Método para formatar números de maneira mais atraente, por exemplo, para populações
    protected String transformFormatMoreAttractive(Double numberPopulation) {
        // Cria um formatador para números com a localidade do Brasil (pt-BR)
        NumberFormat formatAttractive = NumberFormat.getInstance(new Locale("pt", "BR"));

        // Retorna o número formatado
        return formatAttractive.format(numberPopulation);
    }

    // Extrai somente a hora (sem a data) de um objeto LocalDateTime
    protected String pickUpOnlyTime(LocalDateTime dateTime) {
        // Cria um formatador para formatar apenas a hora no padrão "HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Formata e retorna apenas a hora
        return dateTime.toLocalTime().format(formatter);
    }

    // Extrai somente a data (sem a hora) de um objeto LocalDateTime
    protected String pickUpOnlyDate(LocalDateTime dateTime) {
        // Cria um formatador para formatar apenas a data no padrão "dd-MM-yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Formata e retorna apenas a data
        return dateTime.toLocalDate().format(formatter);
    }
}
