package com.programacao_III.Previsao_Tempo.models.weatherconditions;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.climates.ClimateLastTwentyFourHours;
import com.programacao_III.Previsao_Tempo.models.winds.Wind;
import com.programacao_III.Previsao_Tempo.models.winds.WindLastTwentyFourHours;

import java.util.List;


public class WeatherLastTwentyFourHours {
    @JsonProperty("Condicoes_Do_Clima")
    @JsonAlias("main")
    private ClimateLastTwentyFourHours climateLastTwentyFourHours;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonAlias({"dt"})
    private Long dt;

    @JsonProperty("Condicoes_Do_Vento")
    @JsonAlias({"wind"})
    private WindLastTwentyFourHours wind;

    public WeatherLastTwentyFourHours(WindLastTwentyFourHours wind, ClimateLastTwentyFourHours climateLastTwentyFourHours) {
        this.wind = wind;
        this.climateLastTwentyFourHours = climateLastTwentyFourHours;
    }

    public ClimateLastTwentyFourHours getClimateLastTwentyFourHours() {
        return climateLastTwentyFourHours;
    }

    public WindLastTwentyFourHours getWind() {
        return wind;
    }

    public Long getDt() {
        return dt;
    }


    public void setDt(Long dt) {
        this.dt = dt;
    }
}
