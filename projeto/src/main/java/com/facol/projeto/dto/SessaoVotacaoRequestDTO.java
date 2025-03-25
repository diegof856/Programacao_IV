package com.facol.projeto.dto;

import com.facol.projeto.model.Pauta;

import java.time.Instant;

public class SessaoVotacaoRequestDTO {
    private String status;
    private Instant dataInicio;
    private Instant dataFim;
    private Pauta idPauta;

    public SessaoVotacaoRequestDTO(String status, Instant dataInicio, Instant dataFim, Pauta idPauta) {
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.idPauta = idPauta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Pauta getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Pauta idPauta) {
        this.idPauta = idPauta;
    }
}
