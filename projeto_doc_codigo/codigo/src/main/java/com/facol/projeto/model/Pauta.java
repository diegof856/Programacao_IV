package com.facol.projeto.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.facol.projeto.enums.StatusPauta;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "tb_pauta")
@Entity
public class Pauta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_pauta;

	private Instant dataCricao;
	private String descricao;
	private String titulo;
	private Integer estadoPauta;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_associado")
	private Associado associado;
	@OneToOne(mappedBy = "pauta")
	private Votacao votacao;


	public Pauta(Long id_pauta, Instant dataCricao, String descricao, String titulo, StatusPauta statusPauta,
				 Associado associado) {
		this.id_pauta = id_pauta;
		this.dataCricao = dataCricao;
		this.descricao = descricao;
		this.titulo = titulo;
		setEstadoPauta(statusPauta);
		this.associado = associado;
	}

	public Pauta() {

	}


	public Long getId_pauta() {
		return id_pauta;
	}

	public void setId_pauta(Long id_pauta) {
		this.id_pauta = id_pauta;
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

	public StatusPauta getEstadoPauta() {
		return StatusPauta.valorDaPauta(this.estadoPauta);
	}

	public void setEstadoPauta(StatusPauta estadoPauta) {
		if (estadoPauta != null) {
			this.estadoPauta = estadoPauta.getCodigo();
		}

	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Pauta pauta = (Pauta) o;
		return Objects.equals(id_pauta, pauta.id_pauta);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id_pauta);
	}
}
