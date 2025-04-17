package com.facol.projeto.model;

import com.facol.projeto.enums.StatusVotacao;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "tb_votacao")
@Entity
public class Votacao implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  idVotacao;


    private Instant dataInicio;
    private Instant dataFim;
    private Integer tempoVotacao;
    private Integer statusVotacao;
    private Integer quantidadeVotos;

    @OneToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    public Votacao() {

    }

    public Votacao(Long idVotacao, Instant dataInicio, Pauta pauta,Instant dataFim, Integer tempoVotacao, StatusVotacao statusVotacao) {
        this.idVotacao = idVotacao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tempoVotacao = tempoVotacao;
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

    public Integer getTempoVotacao() {
        return tempoVotacao;
    }

    public void setTempoVotacao(Integer tempoVotacao) {
        this.tempoVotacao = tempoVotacao;
    }

    public StatusVotacao getStatusVotacao() {
        return StatusVotacao.valorDeVotacao(this.statusVotacao);
    }

    public void setStatusVotacao(StatusVotacao statusVotacao) {
    	if(statusVotacao != null) {
    		this.statusVotacao = statusVotacao.getCodigo();
    	}
    }
    
    public Integer getQuantidadeVotos() {
		return quantidadeVotos;
	}

	public void setQuantidadeVotos(Integer quantidadeVotos) {
		this.quantidadeVotos = quantidadeVotos;
	}

	@Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Votacao votacao = (Votacao) o;
        return Objects.equals(idVotacao, votacao.idVotacao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idVotacao);
    }
}
