package com.facol.projeto.enums;

public enum TipoVoto {
	SIM(1), NÃO(2);

	private Integer codigo;

	private TipoVoto(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public static TipoVoto valorDoTipoVoto(int codigoVoto) {
		for (TipoVoto valor : TipoVoto.values()) {
			if (valor.getCodigo() == codigoVoto) {
				return valor;
			}
		}
		throw new IllegalArgumentException("exise outro valor");
	}

}
