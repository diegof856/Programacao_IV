package com.facol.projeto.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.facol.projeto.enums.StatusVotacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "tb_votacao")
@Entity
public class Votacao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVotacao;

	private Instant dataInicio;
	private Instant dataFim;

	private Integer statusVotacao;
	private Integer quantidadeVotos;

	@OneToOne
	@JoinColumn(name = "id_pauta")
	private Pauta pauta;

	@OneToMany(mappedBy = "votacao", cascade = CascadeType.REMOVE)
	private Set<Voto> votos = new HashSet<>();

	public Votacao() {

	}

	public Votacao(Long idVotacao, Instant dataInicio, Pauta pauta, Instant dataFim, StatusVotacao statusVotacao) {
		this.idVotacao = idVotacao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.pauta = pauta;
		setStatusVotacao(statusVotacao);
	}

	public Long getIdVotacao() {
		return idVotacao;
	}

	public void setIdVotacao(Long idVotacao) {
		this.idVotacao = idVotacao;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Instant getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Instant dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Instant getDataFim() {
		return dataFim;
	}

	public void setDataFim(Instant dataFim) {
		this.dataFim = dataFim;
	}


	public StatusVotacao getStatusVotacao() {
		return StatusVotacao.valorDeVotacao(this.statusVotacao);
	}

	public void setStatusVotacao(StatusVotacao statusVotacao) {
		if (statusVotacao != null) {
			this.statusVotacao = statusVotacao.getCodigo();
		}
	}

	public Integer getQuantidadeVotos() {
		return quantidadeVotos;
	}

	public void setQuantidadeVotos(Integer quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}
	
	
	public Set<Voto> getVotos() {
		return votos;
	}


	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Votacao votacao = (Votacao) o;
		return Objects.equals(idVotacao, votacao.idVotacao);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(idVotacao);
	}
}
