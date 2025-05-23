package com.facol.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facol.projeto.model.Associado;

@Repository
public interface AssociadoRepositorio extends JpaRepository<Associado, Long> {
	Optional<Associado>findByCpf(String cpf); 
}
