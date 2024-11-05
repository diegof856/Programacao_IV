package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coord {
    @JsonProperty("lon")
    private String lon;
    @JsonProperty("lat")
    private String lat;

    public Coord() {
    }

    public Coord(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }
}
