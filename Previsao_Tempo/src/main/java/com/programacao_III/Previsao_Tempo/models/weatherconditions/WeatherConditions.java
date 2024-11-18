package com.programacao_III.Previsao_Tempo.models.weatherconditions;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherConditions {

    // O campo 'description' armazena a descrição da condição do clima, como "Céu limpo", "Chuvoso", etc.
    @JsonProperty("Condição Esperada")  // Define o nome do campo no JSON como "Condição Esperada"
    @JsonAlias({"description"})  // Permite que o campo 'description' seja mapeado a partir do JSON como 'description'
    private String description;

    // Construtor padrão
    public WeatherConditions() {}

    // Construtor com parâmetro para inicializar a descrição
    public WeatherConditions(String description) {
        this.description = description;
    }

    // Método getter para acessar a descrição do clima
    public String getDescription() {
        return description;
    }
}
