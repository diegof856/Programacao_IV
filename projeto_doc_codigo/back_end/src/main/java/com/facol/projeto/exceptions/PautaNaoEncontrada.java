package com.facol.projeto.exceptions;

public class PautaNaoEncontrada extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PautaNaoEncontrada() {
		super("Pauta não encontrada");
	}

}
