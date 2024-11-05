package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherConditions {
    @JsonProperty("id")
    private  Long id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("main")
    private String main;
    @JsonProperty("icon")
    private String icon;

    public WeatherConditions(){};

    public WeatherConditions(Long id, String description, String icon, String main) {
        this.id = id;
        this.description = description;
        this.icon = icon;
        this.main = main;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getMain() {
        return main;
    }
}
