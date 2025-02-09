package com.programacao_III.Previsao_Tempo.models.cities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CityInfo {


    @JsonProperty("Cidade")
    @JsonAlias({"name"})
    private String cityName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double population;


    @JsonProperty("Quantidade_De_Pessoas")
    private String quantityPopulation;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long sunrise;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long sunset;

    @JsonProperty("Nascer_Do_Sol")
    private String sunriseHour;


    @JsonProperty("Por_Do_Sol")
    private String sunsetHour;


    public CityInfo() {
    }

   public CityInfo(Double population, Long sunset, Long sunrise, String cityName) {
        this.population = population;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.cityName = cityName;
    }


    public Double getPopulation() {
        return population;
    }

    public String getCityName() {
        return cityName;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public String getSunsetHour() {
        return sunsetHour;
    }

    public void setSunsetHour(String sunsetHour) {
        this.sunsetHour = sunsetHour;
    }

    public String getSunriseHour() {
        return sunriseHour;
    }

    public void setSunriseHour(String sunriseHour) {
        this.sunriseHour = sunriseHour;
    }

    public String getQuantityPopulation() {
        return quantityPopulation;
    }

    public void setQuantityPopulation(String quantityPopulation) {
        this.quantityPopulation = quantityPopulation;
    }
}
