package com.facol.projeto.exceptions;

public class AssociadoNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AssociadoNaoEncontrado() {
		super("Associado não encontrado");
	}

}
