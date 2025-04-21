package com.facol.projeto.service.factory.impl;

import org.springframework.stereotype.Component;

import com.facol.projeto.dto.AssociadoRequestDTO;
import com.facol.projeto.dto.AssociadoResponseDTO;
import com.facol.projeto.dto.AssociadoResponseVotoDTO;
import com.facol.projeto.model.Associado;
import com.facol.projeto.service.factory.AssociadoFactory;
import com.facol.projeto.service.factory.PautaFactory;

@Component
public class AssociadoFactoryImplementacao implements AssociadoFactory {

	private final PautaFactory pautaFactory;

	public AssociadoFactoryImplementacao(PautaFactory pautaFactory) {
		this.pautaFactory = pautaFactory;
	}

	@Override
	public Associado cricarAssociado(AssociadoRequestDTO associadoRequestDTO) {

		return new Associado(null, associadoRequestDTO.getNome(), associadoRequestDTO.getCpf());
	}

	@Override
	public AssociadoResponseDTO transformarAssociado(Associado associado) {
		return new AssociadoResponseDTO(associado.getId_associado(), associado.getNome(),
				associado.getPauta().stream().map(this.pautaFactory::criarPautaResponseDTO).toList());
	}

	@Override
	public AssociadoResponseVotoDTO transformarAssociadoVotoDTO(Associado associado) {
		
		return new AssociadoResponseVotoDTO(associado.getId_associado(), associado.getNome());
	}

}
