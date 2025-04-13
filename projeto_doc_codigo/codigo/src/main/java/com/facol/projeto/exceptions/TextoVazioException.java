package com.facol.projeto.exceptions;

public class TextoVazioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TextoVazioException(String mensagem) {
		super(mensagem);
	}

}
