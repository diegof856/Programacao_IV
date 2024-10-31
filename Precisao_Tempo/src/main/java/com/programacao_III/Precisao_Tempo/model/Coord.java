package com.programacao_III.Precisao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coord {
    @JsonProperty("lon")
    private String longitude;
    @JsonProperty("lat")
    private String latitude;

    public Coord() {
    }

    public Coord(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
