package com.programacao_III.Previsao_Tempo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PartDay {

    // O campo 'partDay' armazena o valor da parte do dia,
    // que pode ser "n" para noturna ou "d" para diurna.
    @JsonProperty("Período")  // Define o nome do campo no JSON como "Período"
    @JsonAlias({"pod"})  // Permite que o campo 'pod' seja mapeado para 'partDay'
    private String partDay;

    // Construtor padrão
    public PartDay() {}

    // Construtor parametrizado para inicializar o campo 'partDay'
    public PartDay(String partDay) {
        this.partDay = partDay;
    }

    // Método getter para acessar o valor da parte do dia.
    // Se o valor de 'partDay' for "n", retorna "Noturna"; caso contrário, "Diurna".
    public String getPartDay() {
        if(this.partDay.equals("n")) {
            return "Noturna";  // Se for "n", retorna "Noturna"
        } else {
            return "Diurna";   // Caso contrário, retorna "Diurna"
        }
    }

    // Método setter para definir o valor de 'partDay'.
    public void setPartDay(String partDay) {
        this.partDay = partDay;
    }
}
