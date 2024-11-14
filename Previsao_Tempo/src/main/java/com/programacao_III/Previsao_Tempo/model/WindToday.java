package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WindToday {
    // A velocidade do vento, em metros por segundo (m/s).
    @JsonProperty("Velocidade Do Vento")
    @JsonAlias({"speed"})  // Permite que o campo 'speed' seja mapeado a partir do JSON com a chave 'speed'
    private String speed;

    // A direção do vento, em graus (°).
    @JsonProperty("Direção do Vento")
    @JsonAlias({"deg"})  // Permite que o campo 'deg' seja mapeado a partir do JSON com a chave 'deg'
    private String windDirection;
    public WindToday(){}
    public WindToday(String windDirection, String speed) {
        this.windDirection = windDirection;
        this.speed = speed;
    }

    public String getSpeed() {
        return speed+" m/s";
    }

    public String getWindDirection() {
        return windDirection+" °";
    }
}
