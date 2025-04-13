package com.facol.projeto.service.validacao.impl;

import org.springframework.stereotype.Component;

import com.facol.projeto.dto.AssociadoRequestDTO;
import com.facol.projeto.exceptions.ValidacaoCpfExceptions;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Component("cpfValidacao")
public class CpfValidacao implements ValidacaoStrategy<AssociadoRequestDTO> {

	@Override
	public void validacao(AssociadoRequestDTO associadoRequestDTO) throws RuntimeException {
		if(associadoRequestDTO.getCpf() == null || !cpfValido(associadoRequestDTO.getCpf())) {
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
