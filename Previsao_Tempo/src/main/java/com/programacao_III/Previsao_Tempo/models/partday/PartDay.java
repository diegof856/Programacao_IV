package com.programacao_III.Previsao_Tempo.models.partday;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

// A classe PartDay representa a parte do dia (diurno ou noturno).
public class PartDay {

    // O campo 'partDay' armazena o valor da parte do dia,
    // que pode ser "n" para noturna ou "d" para diurna.
    @JsonProperty("Período")  // Define o nome do campo no JSON como "Período"
    @JsonAlias({"pod"})  // Permite que o campo 'pod' seja mapeado para 'partDay'
    private String partDay;

    // Construtor padrão (sem parâmetros).
    public PartDay() {}

    // Construtor parametrizado que inicializa o campo 'partDay' com um valor fornecido.
    public PartDay(String partDay) {
        this.partDay = partDay;  // Inicializa o campo 'partDay' com o valor fornecido.
    }

    // Método getter para acessar o valor da parte do dia.
    // Se o valor de 'partDay' for "n", retorna "Noturna"; caso contrário, "Diurna".
    public String getPartDay() {
        if(this.partDay.equals("n")) {
            return "Noturno";  // Se for "n", retorna "Noturno".
        } else {
            return "Diurno";   // Caso contrário, retorna "Diurno".
        }
    }

    // Método setter para definir o valor de 'partDay'.
    public void setPartDay(String partDay) {
        this.partDay = partDay;  // Define o valor de 'partDay' com o valor fornecido.
    }
}
