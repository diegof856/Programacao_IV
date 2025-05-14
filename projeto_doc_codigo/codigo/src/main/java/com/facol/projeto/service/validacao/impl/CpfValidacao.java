package com.facol.projeto.service.validacao.impl;

import org.springframework.stereotype.Component;

import com.facol.projeto.exceptions.ValidacaoCpfExceptions;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Component("cpfValidacao")
public class CpfValidacao implements ValidacaoStrategy<String> {

	@Override
	public void validacao(String cpf) throws RuntimeException {
		if(cpf == null || !cpfValido(cpf)) {
			throw new ValidacaoCpfExceptions();
		}
	}
	private boolean cpfValido(String cpf) {
		if(cpf.length() != 11) {
			return false;
		}else {
			return true;
		}
		
	}

}
