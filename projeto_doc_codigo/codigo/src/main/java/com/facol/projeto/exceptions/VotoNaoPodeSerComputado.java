package com.facol.projeto.exceptions;

public class VotoNaoPodeSerComputado extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public VotoNaoPodeSerComputado(String msg) {
		super(msg);
	}

}
