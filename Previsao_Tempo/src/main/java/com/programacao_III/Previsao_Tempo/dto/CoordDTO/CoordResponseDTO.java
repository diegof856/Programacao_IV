package com.programacao_III.Previsao_Tempo.dto.CoordDTO;

import com.programacao_III.Previsao_Tempo.model.Coord;

// O 'record' 'CoordResponseDTO' é utilizado para representar a resposta com as coordenadas geográficas.
public record CoordResponseDTO(Coord coord) {
    // O 'record' contém um único campo, 'coord', que é um objeto da classe 'Coord'.
    // O campo 'coord' provavelmente contém as coordenadas geográficas (latitude e longitude).
}
