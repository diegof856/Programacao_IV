package com.facol.projeto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "tb_associado")
@Entity
public class Associado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_associado;
	private String nome;
	private String cpf;
	

	@OneToMany(mappedBy = "associado")
	private List<Pauta> pautas = new ArrayList<>();

	public Associado(Long id_associado, String nome, String cpf) {
		this.id_associado = id_associado;
		this.nome = nome;
		this.cpf = cpf;
		
	}

	public Associado() {

	}

	public Long getId_associado() {
		return id_associado;
	}

	public void setId_associado(Long id_associado) {
		this.id_associado = id_associado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Pauta> getPauta() {
		return pautas;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		Associado associado = (Associado) o;
		return Objects.equals(id_associado, associado.id_associado);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id_associado);
	}
}
