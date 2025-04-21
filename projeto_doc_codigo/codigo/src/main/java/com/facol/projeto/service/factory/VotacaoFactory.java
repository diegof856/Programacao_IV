package com.facol.projeto.service.factory;

import com.facol.projeto.dto.VotacaoResponseDTO;
import com.facol.projeto.model.Votacao;

public interface VotacaoFactory {
	VotacaoResponseDTO criarVotacaoDTO(Votacao votacao);
}
