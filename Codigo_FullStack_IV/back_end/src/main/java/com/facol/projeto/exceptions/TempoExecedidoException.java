package com.facol.projeto.exceptions;

public class TempoExecedidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	 public TempoExecedidoException() {
		super("O tempo não pode exceder 60 minutos");
	}
}
