package com.facol.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.facol.projeto.model.Voto;

public interface VotoRepositorio extends JpaRepository<Voto, Long> {

}
