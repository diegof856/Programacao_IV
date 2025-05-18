package com.facol.projeto.service.validacao.impl;

import org.springframework.stereotype.Component;

import com.facol.projeto.exceptions.TextoVazioException;
import com.facol.projeto.service.validacao.ValidacaoStrategy;
@Component("nomeValidacao")
public class NomeVazio implements ValidacaoStrategy<String> {

	@Override
	public void validacao(String texto) throws RuntimeException{
		
		 if (texto == null || texto.trim().isEmpty()) {
	            throw new TextoVazioException("Nome não pode ser vazio.");
	        }
		 
	}

}
