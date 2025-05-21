package com.facol.projeto.exceptions;

public class SenhaIncorretaException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public SenhaIncorretaException() {
		super("Senha Incorreta");
	}

}
