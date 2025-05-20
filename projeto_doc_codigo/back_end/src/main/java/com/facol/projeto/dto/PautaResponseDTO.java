package com.facol.projeto.dto;

import com.facol.projeto.enums.StatusPauta;

public record PautaResponseDTO(Long id, String data_criacao, String titulo, String descricao, StatusPauta status_Pauta, Integer Tempo_Votacao, Long idAutor, String autor) {

}
