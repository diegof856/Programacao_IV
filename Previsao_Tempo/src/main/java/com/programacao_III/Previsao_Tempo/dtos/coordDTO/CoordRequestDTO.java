package com.programacao_III.Previsao_Tempo.dtos.coordDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.programacao_III.Previsao_Tempo.models.coord.Coord;

// A classe 'CoordRequestDTO' é um Data Transfer Object (DTO) usado para transportar dados de coordenadas geográficas.
public class CoordRequestDTO {

    // A anotação @JsonProperty é usada para mapear o nome do campo no JSON para a variável correspondente na classe.
    // O campo "coord" no JSON será deserializado para o atributo 'coord' desta classe.
    @JsonProperty("coord")
    private Coord coord;

    // Construtor padrão sem parâmetros:
    // Necessário para que bibliotecas de serialização/deserialização, como Jackson, possam instanciar o objeto.
    public CoordRequestDTO() {}

    // Construtor com parâmetro:
    // Permite criar uma instância da classe inicializando o campo 'coord' com um objeto 'Coord'.
    public CoordRequestDTO(Coord coord) {
        this.coord = coord;
    }

    // Getter para o campo 'coord':
    // Permite acessar o valor armazenado na variável 'coord'.
    // Geralmente usado em frameworks ou serviços que consomem ou produzem objetos deste tipo.
    public Coord getCoord() {
        return coord;
    }
}
