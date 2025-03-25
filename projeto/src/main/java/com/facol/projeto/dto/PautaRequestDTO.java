package com.facol.projeto.dto;

import java.time.Instant;

public class PautaRequestDTO {


    private Instant dataCricao;
    private String descricao;
    private String titulo;

    public PautaRequestDTO(Instant dataCricao, String descricao, String titulo) {
        this.dataCricao = dataCricao;
        this.descricao = descricao;
        this.titulo = titulo;
    }

    public Instant getDataCricao() {
        return dataCricao;
    }

    public void setDataCricao(Instant dataCricao) {
        this.dataCricao = dataCricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
