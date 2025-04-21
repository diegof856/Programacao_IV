package com.facol.projeto.service.factory.impl;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.facol.projeto.dto.VotoResponseDTO;
import com.facol.projeto.enums.TipoVoto;
import com.facol.projeto.model.Associado;
import com.facol.projeto.model.Votacao;
import com.facol.projeto.model.Voto;
import com.facol.projeto.service.factory.AssociadoFactory;
import com.facol.projeto.service.factory.VotoFactory;
import com.facol.projeto.utils.Utils;
@Component
public class VotoFactoryImplementacao implements VotoFactory {
	
	private final AssociadoFactory associadoFactory;
	private final Utils utils;
	public VotoFactoryImplementacao(AssociadoFactory associadoFactory, Utils utils) {
		this.associadoFactory = associadoFactory;
		this.utils = utils;
	}
	@Override
	public Voto criarVoto(Associado associado, TipoVoto voto,  Votacao votacao) {
	
		return new Voto(null, associado, voto, Instant.now(), votacao);
	}

	@Override
	public VotoResponseDTO criarResponseVotoDTO(Voto voto) {
		
		return new VotoResponseDTO(voto.getIdVoto(), this.associadoFactory.transformarAssociadoVotoDTO(voto.getAssociado()),this.utils.transformarData(voto.getDataVoto()),voto.getStatusVoto());
	}

	
}
