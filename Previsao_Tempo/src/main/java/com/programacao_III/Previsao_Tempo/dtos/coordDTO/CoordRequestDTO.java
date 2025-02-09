package com.programacao_III.Previsao_Tempo.dtos.coordDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.coord.Coord;

public class CoordRequestDTO {

    @JsonProperty("coord")
    private Coord coord;

    public CoordRequestDTO() {}

    public CoordRequestDTO(Coord coord) {
        this.coord = coord;
    }


    public Coord getCoord() {
        return coord;
    }
}
