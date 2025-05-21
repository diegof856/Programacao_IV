package com.facol.projeto.dto;

import com.facol.projeto.enums.TipoVoto;

public record VotoResponseDTO(Long idVoto, AssociadoResponseVotoDTO associado, String data_voto, TipoVoto voto) {

}
