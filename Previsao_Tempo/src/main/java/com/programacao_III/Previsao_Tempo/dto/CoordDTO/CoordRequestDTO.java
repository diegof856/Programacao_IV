package com.programacao_III.Previsao_Tempo.dto.CoordDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.model.Coord;

public class CoordRequestDTO {
    @JsonProperty("coord")
    private Coord coord;

    public CoordRequestDTO(){}

    public CoordRequestDTO(Coord coord) {
      this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }


}
