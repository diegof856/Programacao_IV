package com.facol.projeto.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.facol.projeto.enums.TipoVoto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "tb_voto")
@Entity
public class Voto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVoto;
	
	
	@ManyToOne
	@JoinColumn(name = "id_votacao")
	private Votacao votacao;
	@ManyToOne
	@JoinColumn(name = "id_associado")
	private Associado associado;
	private Integer statusVoto;
	private Instant dataVoto;

	public Voto() {

	}

	public Voto(Long idVoto, Associado associado, TipoVoto statusVoto, Instant dataVoto, Votacao votacao) {
		this.idVoto = idVoto;
		this.associado = associado;
		setStatusVoto(statusVoto);
		this.dataVoto = dataVoto;
		this.votacao = votacao;
	}

	public Long getIdVoto() {
		return idVoto;
	}

	public void setIdVoto(Long idVoto) {
		this.idVoto = idVoto;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setIdAssociado(Associado associado) {
		this.associado = associado;
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

	public Votacao getVotacao() {
		return votacao;
	}

	public Votacao setVotacao(Votacao votacao) {
		return this.votacao = votacao;
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
