package com.facol.projeto.model;

import com.facol.projeto.enums.TipoVoto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Table(name = "tb_voto")
@Entity
public class Voto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVoto;
	private Votacao idVotacao;
	private Associado idAssociado;
	private Integer statusVoto;
	private Instant dataVoto;

	public Voto() {

	}

	public Voto(Long idVoto, Votacao idVotacao, Associado idAssociado, TipoVoto statusVoto, Instant dataVoto) {
		this.idVoto = idVoto;
		this.idVotacao = idVotacao;
		this.idAssociado = idAssociado;
		setStatusVoto(statusVoto);
		this.dataVoto = dataVoto;
	}

	public Long getIdVoto() {
		return idVoto;
	}

	public void setIdVoto(Long idVoto) {
		this.idVoto = idVoto;
	}

	public Votacao getIdVotacao() {
		return idVotacao;
	}

	public void setIdVotacao(Votacao idVotacao) {
		this.idVotacao = idVotacao;
	}

	public Associado getIdAssociado() {
		return idAssociado;
	}

	public void setIdAssociado(Associado idAssociado) {
		this.idAssociado = idAssociado;
	}

	public TipoVoto getStatusVoto() {
		return TipoVoto.valorDoTipoVoto(this.statusVoto);
	}

	public void setStatusVoto(TipoVoto statusVoto) {
		if (statusVoto != null) {
			this.statusVoto = statusVoto.getCodigo();
		}

	}

	public Instant getDataVoto() {
		return dataVoto;
	}

	public void setDataVoto(Instant dataVoto) {
		this.dataVoto = dataVoto;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Voto voto = (Voto) o;
		return Objects.equals(idVoto, voto.idVoto);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idVoto);
	}
}
