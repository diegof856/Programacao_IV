package com.facol.projeto.service.factory.impl;

import java.time.Duration;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.facol.projeto.dto.VotacaoResponseDTO;
import com.facol.projeto.enums.StatusVotacao;
import com.facol.projeto.model.Pauta;
import com.facol.projeto.model.Votacao;
import com.facol.projeto.service.factory.PautaFactory;
import com.facol.projeto.service.factory.VotacaoFactory;
import com.facol.projeto.service.factory.VotoFactory;
import com.facol.projeto.utils.Utils;
@Component
public class VotacaoFactoryImplementacao implements VotacaoFactory {

	private final PautaFactory pautaFactory;
	private final VotoFactory votoFactory;
	private final Utils utils;
	public VotacaoFactoryImplementacao(PautaFactory pautaFactory, VotoFactory votoFactory, Utils utils) {
		this.pautaFactory = pautaFactory;
		this.votoFactory = votoFactory;
		this.utils = utils;
	}
	@Override
	public VotacaoResponseDTO criarVotacaoDTO(Votacao votacao) {
		
		return new VotacaoResponseDTO(votacao.getIdVotacao(),
				this.utils.transformarData(votacao.getDataInicio()), 
				this.utils.transformarData(votacao.getDataFim()), 
				votacao.getVotos().size(),votacao.getStatusVotacao(),
				this.pautaFactory.criarPautaResponseDTO(votacao.getPauta()), 
				votacao.getVotos().stream().map(this.votoFactory::criarResponseVotoDTO).collect(Collectors.toSet()));
	}
	@Override
	public Votacao criarVotacao(Pauta pauta) {

		return new Votacao(null, pauta.getDataCricao(), pauta,
				pauta.getDataCricao().plus(Duration.ofMinutes(pauta.getTempoVotacao())), StatusVotacao.EM_ANDAMENTO);

	}
	
	

	
}
