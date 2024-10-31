package com.programacao_III.Precisao_Tempo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Precisao_Tempo.model.Climate;

public class ClimateRequestDTO {
    @JsonProperty("main")
    private Climate climate;

    public ClimateRequestDTO(){}

    public ClimateRequestDTO(Climate climate) {
        this.climate = climate;
    }

    public Climate getClimate() {
        return climate;
    }
}
