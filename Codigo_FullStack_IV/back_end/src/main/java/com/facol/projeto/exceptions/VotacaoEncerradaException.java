package com.facol.projeto.exceptions;

public class VotacaoEncerradaException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	public VotacaoEncerradaException() {
		super("Votação encerrada");
	}

}
