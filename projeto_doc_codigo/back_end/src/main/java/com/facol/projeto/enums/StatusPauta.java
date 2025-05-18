package com.facol.projeto.enums;

public enum StatusPauta {
	APROVADA(1), REPROVADA(2), EM_VOTOCAO(3);

	private Integer codigo;
	
	private StatusPauta(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCodigo() {
		return	this.codigo;
	}
	public static StatusPauta valorDaPauta(Integer codigoStatus) {
		for(StatusPauta valor : StatusPauta.values()) {
			if(valor.getCodigo() == codigoStatus) {
				return valor;
			}
		}
		throw new IllegalArgumentException("exise outro valor");
	}
	
}
