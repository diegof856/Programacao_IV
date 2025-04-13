package com.facol.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facol.projeto.model.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
	
}
