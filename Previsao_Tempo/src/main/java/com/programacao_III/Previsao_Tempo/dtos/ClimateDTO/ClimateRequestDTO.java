package com.programacao_III.Previsao_Tempo.dtos.ClimateDTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.model.*;

public class ClimateRequestDTO {

    // A anotação @JsonProperty é usada para mapear os nomes das propriedades no JSON para os nomes das variáveis da classe.
    // No caso, o nome "main" no JSON será mapeado para a variável 'climate'.
    @JsonProperty("main")
    private Climate climate;

    @JsonProperty("wind")
    private WindToday windToday;
    @JsonProperty("sys")
    private CityInfo cityInfo;
    private HourDawnNightfall hourDawnNightfall;
    // Mapeia o nome "name" do JSON para a variável 'nameCity', que representa o nome da cidade.
    @JsonProperty("name")
    private String nameCity;

    // Construtor padrão sem parâmetros
    public ClimateRequestDTO() {}

    // Construtor com um parâmetro 'climate' para inicializar a variável 'climate'.
    public ClimateRequestDTO(Climate climate,WindToday windToday, CityInfo cityInfo) {
        this.windToday = windToday;
        this.climate = climate;
        this.cityInfo = cityInfo;
    }

    // Método getter para acessar o objeto 'climate'.
    public Climate getClimate() {
        return climate;
    }

    // Método getter para acessar o nome da cidade 'nameCity'.
    public String getNameCity() {
        return nameCity;
    }

    public WindToday getWindToday() {
        return windToday;

    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public HourDawnNightfall getHourDawnNightfall() {
        return hourDawnNightfall;
    }
}
