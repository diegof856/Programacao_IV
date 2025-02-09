package com.programacao_III.Previsao_Tempo.models.cities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CityInfoFifteenDaysForecast {

    @JsonProperty("Cidade")
    @JsonAlias({"name"})
    private String cityName;

    @JsonProperty("Pais")
    @JsonAlias({"country"})
    private String countryName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double population;


    @JsonProperty("Quantidade_De_Pessoas")
    private String quantityPopulation;

    public CityInfoFifteenDaysForecast() {}

    public CityInfoFifteenDaysForecast(Double population, String countryName, String cityName) {
        this.population = population;
        this.countryName = countryName;
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public Double getPopulation() {
        return population;
    }

    public String getQuantityPopulation() {
        return quantityPopulation;
    }

    public void setQuantityPopulation(String quantityPopulation) {
        this.quantityPopulation = quantityPopulation;
    }
}
