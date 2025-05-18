package com.facol.projeto.enums;

public enum StatusVotacao {
	EM_ANDAMENTO(1), FINALIZADA(2);

	private Integer codigo;

	private StatusVotacao(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return this.codigo;
	}
	public static StatusVotacao valorDeVotacao(int codigoVotacao) {
		for (StatusVotacao valor : StatusVotacao.values()) {
			if (valor.getCodigo() == codigoVotacao) {
				return valor;
			}
		}
		throw new IllegalArgumentException("exise outro valor");
	}

}
