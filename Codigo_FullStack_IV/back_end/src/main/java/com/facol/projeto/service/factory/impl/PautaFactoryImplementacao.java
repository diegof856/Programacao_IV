package com.facol.projeto.service.factory.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.facol.projeto.dto.PautaRequestDTO;
import com.facol.projeto.dto.PautaResponseDTO;
import com.facol.projeto.enums.StatusPauta;
import com.facol.projeto.model.Associado;
import com.facol.projeto.model.Pauta;
import com.facol.projeto.service.factory.PautaFactory;
import com.facol.projeto.utils.Utils;

@Component
public class PautaFactoryImplementacao implements PautaFactory {
	
	@Autowired
	private Utils utils;
	

	
	@Override
	public PautaResponseDTO criarPautaResponseDTO(Pauta pauta) {

		return new PautaResponseDTO(pauta.getId_pauta(), this.utils.transformarData(pauta.getDataCricao()),
				pauta.getTitulo(), pauta.getDescricao(), pauta.getEstadoPauta(),
				pauta.getTempoVotacao(), pauta.getAssociado().getId_associado(), pauta.getAssociado().getNome());
	}



	@Override
	public Pauta criarPauta(PautaRequestDTO pautaRequestDTO, Associado associado) {
		if (pautaRequestDTO.getTempoVotacao() == null) {
			return new Pauta(null, Instant.now(), pautaRequestDTO.getDescricao(), pautaRequestDTO.getTitulo(),
					StatusPauta.EM_VOTOCAO, associado, 1);

		} else {
			return new Pauta(null, Instant.now(), pautaRequestDTO.getDescricao(), pautaRequestDTO.getTitulo(),
					StatusPauta.EM_VOTOCAO, associado, pautaRequestDTO.getTempoVotacao());

		}

	}
	
	public Pauta alterarPauta(PautaRequestDTO pautaRequest,Pauta pauta) {
		if(pautaRequest.getTempoVotacao() == null) {
			pauta.setDescricao(pautaRequest.getDescricao());
			pauta.setTitulo(pautaRequest.getTitulo());
			pauta.setDataCricao(Instant.now());
			pauta.setTempoVotacao(1);
			
			return pauta;
		}else {
			pauta.setDescricao(pautaRequest.getDescricao());
			pauta.setTitulo(pautaRequest.getTitulo());
			pauta.setTempoVotacao(pautaRequest.getTempoVotacao());
			return pauta;
		}
	}

	

}
