package com.facol.projeto.exceptions;

public class ValidacaoCpfExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoCpfExceptions() {
		super("Cpf invalido");
	}
}
