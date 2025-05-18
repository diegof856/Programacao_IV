package com.facol.projeto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.facol.projeto.dto.VotacaoResponseDTO;
import com.facol.projeto.model.Votacao;
import com.facol.projeto.model.Voto;

public interface VotacaoConsultaService {
	Page<VotacaoResponseDTO> consultarVotacoes(Pageable pageable);

	VotacaoResponseDTO consultarVotacao(Long id);

	void buscarVotacaoPorPauta(Long id);
	
	void computarVotos(Voto voto);
	Votacao buscarVotacao(Long id);
}
