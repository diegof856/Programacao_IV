package com.programacao_III.Previsao_Tempo.dtos.climateDTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.Cities.CityInfo;
import com.programacao_III.Previsao_Tempo.models.climates.ClimateToday;
import com.programacao_III.Previsao_Tempo.models.hourdawnnightfall.HourDawnNightfall;
import com.programacao_III.Previsao_Tempo.models.winds.WindToday;

// Classe DTO (Data Transfer Object) usada para receber dados de uma API externa ou requisição.
public class ClimateRequestDTO {

    // A anotação @JsonProperty indica que o campo "main" no JSON será mapeado para a variável 'climate'.
    @JsonProperty("main")
    private ClimateToday climate;

    // A anotação @JsonAlias é usada para indicar que o campo "wind" no JSON será mapeado para 'windToday'.
    @JsonAlias({"wind"})
    private WindToday windToday;

    // A anotação @JsonAlias mapeia o campo "sys" no JSON para a variável 'cityInfo'.
    @JsonAlias({"sys"})
    private CityInfo cityInfo;

    // Campo que representa informações de amanhecer, entardecer e anoitecer, sem mapeamento direto do JSON.
    private HourDawnNightfall hourDawnNightfall;

    // O campo "name" no JSON será mapeado para a variável 'nameCity', que armazena o nome da cidade.
    @JsonAlias({"name"})
    private String nameCity;

    // Construtor padrão sem parâmetros, necessário para algumas bibliotecas de serialização/deserialização.
    public ClimateRequestDTO() {}

    // Construtor que permite inicializar os campos 'climate', 'windToday' e 'cityInfo' ao criar o objeto.
    public ClimateRequestDTO(ClimateToday climate, WindToday windToday, CityInfo cityInfo) {
        this.windToday = windToday; // Inicializa o campo 'windToday'.
        this.climate = climate; // Inicializa o campo 'climate'.
        this.cityInfo = cityInfo; // Inicializa o campo 'cityInfo'.
    }

    // Método getter para acessar o objeto 'climate', que contém as informações climáticas principais.
    public ClimateToday getClimateToday() {
        return climate;
    }

    // Método getter para acessar o nome da cidade, armazenado em 'nameCity'.
    public String getNameCity() {
        return nameCity;
    }

    // Método getter para acessar as informações do vento atual, armazenadas em 'windToday'.
    public WindToday getWindToday() {
        return windToday;
    }

    // Método getter para acessar as informações da cidade, armazenadas em 'cityInfo'.
    public CityInfo getCityInfo() {
        return cityInfo;
    }

    // Método getter para acessar informações sobre amanhecer, entardecer e anoitecer, armazenadas em 'hourDawnNightfall'.
    public HourDawnNightfall getHourDawnNightfall() {
        return hourDawnNightfall;
    }
}
