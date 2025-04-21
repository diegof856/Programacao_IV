package com.facol.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facol.projeto.model.Votacao;

@Repository
public interface VotacaoRepositorio extends JpaRepository<Votacao,Long> {
	Votacao findByPauta_Id(Long id);
}
