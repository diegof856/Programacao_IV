package com.facol.projeto.service.validacao.impl;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.facol.projeto.exceptions.VotacaoEncerradaException;
import com.facol.projeto.model.Votacao;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Component("ValidacaoVotacaoEncerrada")
public class VotacaoEncerrada implements ValidacaoStrategy<Votacao> {

	@Override
	public void validacao(Votacao objeto) throws RuntimeException {
		if(Instant.now().isAfter(objeto.getDataFim())) {
			throw new VotacaoEncerradaException();
		}
		
	}

}
