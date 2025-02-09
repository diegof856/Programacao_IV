package com.programacao_III.Previsao_Tempo.models.partday;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PartDay {


    @JsonProperty("Período")
    @JsonAlias({"pod"})
    private String partDay;


    public PartDay() {}


    public PartDay(String partDay) {
        this.partDay = partDay;
    }


    public String getPartDay() {
        if(this.partDay.equals("n")) {
            return "Noturno";
        } else {
            return "Diurno";
        }
    }


    public void setPartDay(String partDay) {
        this.partDay = partDay;
    }
}
