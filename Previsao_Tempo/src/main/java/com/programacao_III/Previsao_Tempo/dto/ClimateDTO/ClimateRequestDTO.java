package com.programacao_III.Previsao_Tempo.dto.ClimateDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.model.Climate;

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
