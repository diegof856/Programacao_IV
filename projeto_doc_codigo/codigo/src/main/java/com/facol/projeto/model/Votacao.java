package com.facol.projeto.model;

import com.facol.projeto.enums.StatusVotacao;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Table(name = "tb_votacao")
@Entity
public class Votacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  idVotacao;
    private Pauta pauta;
    private Instant dataInicio;
    private Instant dataFim;
    private Integer tempoVotacao;
    private StatusVotacao statusVotacao;

    public Votacao() {

    }

    public Votacao(Long idVotacao, Pauta pauta, Instant dataInicio, Instant dataFim, Integer tempoVotacao, StatusVotacao statusVotacao) {
        this.idVotacao = idVotacao;
        this.pauta = pauta;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        if(tempoVotacao == null){
            this.tempoVotacao = 1;
        }else {
            this.tempoVotacao = tempoVotacao;
        }
        this.statusVotacao = statusVotacao;
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
        return statusVotacao;
    }

    public void setStatusVotacao(StatusVotacao statusVotacao) {
        this.statusVotacao = statusVotacao;
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
