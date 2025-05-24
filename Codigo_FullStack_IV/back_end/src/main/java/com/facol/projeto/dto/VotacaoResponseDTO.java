package com.facol.projeto.dto;

import java.util.Set;

import com.facol.projeto.enums.StatusVotacao;

public record VotacaoResponseDTO(Long id, String data_inicio, String data_fim, Integer quantidade_votos, StatusVotacao staus_votacao,PautaResponseDTO pauta, Set<VotoResponseDTO> votos) {
}
