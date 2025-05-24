package com.facol.projeto.service.validacao.impl;

import org.springframework.stereotype.Component;

import com.facol.projeto.enums.StatusVotacao;
import com.facol.projeto.model.Votacao;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Component("FinalizarVotacao")
public class FinalizarVotacao implements ValidacaoStrategy<Votacao>{

	@Override
	public void validacao(Votacao votacao) throws RuntimeException {
		votacao.setStatusVotacao(StatusVotacao.FINALIZADA);
		
	}
	

}
