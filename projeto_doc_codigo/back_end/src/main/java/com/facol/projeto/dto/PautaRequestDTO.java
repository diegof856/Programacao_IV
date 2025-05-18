package com.facol.projeto.dto;

import java.time.Instant;

public class PautaRequestDTO {


    
    private String descricao;
    private String titulo;
    private Integer tempoVotacao;

    public PautaRequestDTO(Instant dataCricao, String descricao, String titulo, Integer tempoVotacao) {
      
        this.descricao = descricao;
        this.titulo = titulo;
        this.tempoVotacao = tempoVotacao;
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

	public Integer getTempoVotacao() {
		return tempoVotacao;
	}

	public void setTempoVotacao(Integer tempoVotacao) {
		this.tempoVotacao = tempoVotacao;
	}
    
    
}
