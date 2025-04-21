package com.facol.projeto.service.validacao.impl;

import org.springframework.stereotype.Component;

import com.facol.projeto.exceptions.TempoExecedidoException;
import com.facol.projeto.model.Pauta;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Component("TempoVotacaoValidacao")
public class TempoExcedido implements ValidacaoStrategy<Pauta> {

	@Override
	public void validacao(Pauta objeto) throws RuntimeException {
		if(objeto.getTempoVotacao() > 60 ) {
			throw new TempoExecedidoException();
		}
		
	}

}
