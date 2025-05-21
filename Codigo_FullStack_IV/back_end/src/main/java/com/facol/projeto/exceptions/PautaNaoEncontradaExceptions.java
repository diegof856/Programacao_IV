package com.facol.projeto.exceptions;

public class PautaNaoEncontradaExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PautaNaoEncontradaExceptions() {
		super("Pauta não encontrada");
	}

}
