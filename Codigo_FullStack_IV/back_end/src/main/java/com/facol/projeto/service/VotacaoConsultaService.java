package com.facol.projeto.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.facol.projeto.dto.VotacaoResponseDTO;
import com.facol.projeto.model.Votacao;
import com.facol.projeto.model.Voto;

public interface VotacaoConsultaService {
	Page<VotacaoResponseDTO> consultarVotacoes(Pageable pageable);

	VotacaoResponseDTO consultarVotacao(Long id);

	VotacaoResponseDTO buscarVotacaoPorPauta(Long id);

	void computarVotos(Voto voto);

	Votacao buscarVotacao(Long id);
	
	List<Long> buscarPocentagemVotos(Long idVotacao);
}
