package com.facol.projeto.model;

import com.facol.projeto.enums.TipoVoto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Table(name = "tb_associado")
@Entity
public class Associado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id_associado;
    private String nome;
    private String cpf;
    private TipoVoto statusVoto;

    public Associado(Long id_associado, String nome, String cpf, TipoVoto statusVoto) {
        this.id_associado = id_associado;
        this.nome = nome;
        this.cpf = cpf;
        this.statusVoto = statusVoto;
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

    public TipoVoto getStatusVoto() {
        return statusVoto;
    }

    public void setStatusVoto(TipoVoto statusVoto) {
        this.statusVoto = statusVoto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Associado associado = (Associado) o;
        return Objects.equals(id_associado, associado.id_associado);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_associado);
    }
}
