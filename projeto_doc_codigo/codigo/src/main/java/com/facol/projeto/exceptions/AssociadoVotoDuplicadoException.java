package com.facol.projeto.exceptions;

public class AssociadoVotoDuplicadoException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public AssociadoVotoDuplicadoException() {
		super("O associado não pode votar duas vezes");
	}

}
