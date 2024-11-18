package com.programacao_III.Previsao_Tempo.models.hourdawnnightfall;

import com.fasterxml.jackson.annotation.JsonProperty;

// A classe HourDawnNightfall armazena informações sobre o nascer e o pôr do sol.
public class HourDawnNightfall {

    // O campo 'sunrise' representa a hora do nascer do sol.
    @JsonProperty("Nascer do sol")  // Mapeia o nome "Nascer do sol" no JSON para o campo 'sunrise'
    private String sunrise;

    // O campo 'sunset' representa a hora do pôr do sol.
    @JsonProperty("Pôr Do Sol")  // Mapeia o nome "Pôr Do Sol" no JSON para o campo 'sunset'
    private String sunset;

    // Construtor padrão que não inicializa nenhum valor (usado para criação sem parâmetros).
    public HourDawnNightfall(){}

    // Construtor parametrizado que permite inicializar os campos com valores fornecidos.
    public HourDawnNightfall(String sunrise, String sunset) {
        this.sunrise = sunrise;  // Inicializa a hora do nascer do sol.
        this.sunset = sunset;    // Inicializa a hora do pôr do sol.
    }

    // Método getter para acessar a hora do nascer do sol.
    public String getSunrise() {
        return sunrise;  // Retorna a hora do nascer do sol.
    }

    // Método setter para definir a hora do nascer do sol.
    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;  // Define a hora do nascer do sol.
    }

    // Método getter para acessar a hora do pôr do sol.
    public String getSunset() {
        return sunset;  // Retorna a hora do pôr do sol.
    }

    // Método setter para definir a hora do pôr do sol.
    public void setSunset(String sunset) {
        this.sunset = sunset;  // Define a hora do pôr do sol.
    }
}
