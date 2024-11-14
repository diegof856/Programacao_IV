package com.programacao_III.Previsao_Tempo.dtos.CoordDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.model.Coord;

public class CoordRequestDTO {

    // A anotação @JsonProperty é usada para mapear o nome da propriedade no JSON para a variável 'coord' na classe.
    // Neste caso, o campo "coord" do JSON será mapeado para a variável 'coord' da classe.
    @JsonProperty("coord")
    private Coord coord;

    // Construtor padrão sem parâmetros, necessário para a serialização e deserialização do JSON
    public CoordRequestDTO() {}

    // Construtor que inicializa o campo 'coord' com um objeto 'Coord'.
    public CoordRequestDTO(Coord coord) {
        this.coord = coord;
    }

    // Método getter para acessar o valor do campo 'coord'.
    public Coord getCoord() {
        return coord;
    }
}
