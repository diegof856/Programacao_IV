package com.facol.projeto.service.validacao;

public interface ValidacaoStrategy<T>  {
	void validacao(T objeto) throws RuntimeException;
	
}
