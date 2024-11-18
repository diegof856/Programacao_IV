package com.programacao_III.Previsao_Tempo.models.winds;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WindLastTwentyFourHours {
    // A velocidade do vento, em metros por segundo (m/s).
    @JsonProperty("Velocidade Do Vento")  // Define o nome do campo no JSON como "Velocidade Do Vento"
    @JsonAlias({"speed"})  // Permite que o campo 'speed' seja mapeado a partir do JSON com a chave 'speed'
    private String speed;

    // A direção do vento, em graus (°).
    @JsonProperty("Direção do Vento")  // Define o nome do campo no JSON como "Direção do Vento"
    @JsonAlias({"deg"})  // Permite que o campo 'deg' seja mapeado a partir do JSON com a chave 'deg'
    private String windDirection;


    // Construtor padrão
    public WindLastTwentyFourHours(){}

    // Construtor com parâmetros para inicializar os valores de velocidade e direção do vento
    public WindLastTwentyFourHours(String speed, String windDirection) {
        this.speed = speed;
        this.windDirection = windDirection;
    }

    // Método getter para acessar a velocidade do vento
    public String getSpeed() {
        return speed + "m/s";  // Retorna a velocidade do vento com a unidade "m/s"
    }

    // Método getter para acessar a direção do vento
    public String getWindDirection() {
        return windDirection + "º";  // Retorna a direção do vento com o símbolo "º" (graus)
    }

}
