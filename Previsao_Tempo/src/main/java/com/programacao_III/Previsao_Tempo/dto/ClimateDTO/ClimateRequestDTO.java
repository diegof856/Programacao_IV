package com.programacao_III.Previsao_Tempo.dto.ClimateDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.model.Climate;

public class ClimateRequestDTO {

    // A anotação @JsonProperty é usada para mapear os nomes das propriedades no JSON para os nomes das variáveis da classe.
    // No caso, o nome "main" no JSON será mapeado para a variável 'climate'.
    @JsonProperty("main")
    private Climate climate;

    // Mapeia o nome "name" do JSON para a variável 'nameCity', que representa o nome da cidade.
    @JsonProperty("name")
    private String nameCity;

    // Construtor padrão sem parâmetros
    public ClimateRequestDTO() {}

    // Construtor com um parâmetro 'climate' para inicializar a variável 'climate'.
    public ClimateRequestDTO(Climate climate) {
        this.climate = climate;
    }

    // Método getter para acessar o objeto 'climate'.
    public Climate getClimate() {
        return climate;
    }

    // Método getter para acessar o nome da cidade 'nameCity'.
    public String getNameCity() {
        return nameCity;
    }
}
