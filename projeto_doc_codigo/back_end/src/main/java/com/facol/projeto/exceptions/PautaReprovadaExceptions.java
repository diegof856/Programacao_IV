package com.facol.projeto.exceptions;

public class PautaReprovadaExceptions extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public PautaReprovadaExceptions() {
		super("A pauta já se encontra reprovada não será possível atualiza-la");
	}
}
