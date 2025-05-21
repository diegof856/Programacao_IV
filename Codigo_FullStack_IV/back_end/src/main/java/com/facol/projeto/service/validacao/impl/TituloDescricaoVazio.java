package com.facol.projeto.service.validacao.impl;

import org.springframework.stereotype.Component;

import com.facol.projeto.dto.PautaRequestDTO;
import com.facol.projeto.exceptions.ValidacaoPautaException;
import com.facol.projeto.service.validacao.ValidacaoStrategy;
@Component("TituloDescricaoValidacao")
public class TituloDescricaoVazio implements ValidacaoStrategy<PautaRequestDTO> {

	@Override
	public void validacao(PautaRequestDTO objeto) throws RuntimeException {
		if(!checkTitulo(objeto.getTitulo()) || !checkDescricao(objeto.getDescricao())) {
			throw new ValidacaoPautaException();
		}
		
	}
	
	private boolean checkTitulo(String titulo) {
		if(titulo == null || titulo.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	private boolean checkDescricao(String descricao) {
		if(descricao == null || descricao.isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}

}
