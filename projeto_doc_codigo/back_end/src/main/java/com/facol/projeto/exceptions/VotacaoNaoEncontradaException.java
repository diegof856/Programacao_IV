package com.facol.projeto.exceptions;

public class VotacaoNaoEncontradaException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	public VotacaoNaoEncontradaException() {
		super("Votação não encontrada");
	}
}
