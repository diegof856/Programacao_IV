package com.facol.projeto.exceptions;

public class AssociadoNaoEncontradoExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AssociadoNaoEncontradoExceptions() {
		super("Associado não encontrado");
	}

}
