package com.facol.projeto.exceptions;

public class ValidacaoPautaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoPautaException() {
		super("O titulo ou a descrição não podem ser vazios");
	}
}
