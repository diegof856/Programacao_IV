package com.facol.projeto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.facol.projeto.dto.AssociadoResponseDTO;
import com.facol.projeto.dto.LoginResponseDTO;
import com.facol.projeto.model.Associado;

public interface AssociadoConsultaService {

	Page<AssociadoResponseDTO> buscarAssociados(Pageable pageable);

	Associado buscarAssociadorPorId(Long id);

	AssociadoResponseDTO buscarAssociadoPorIdDTO(Long id);
	
	LoginResponseDTO buscarPorCpf(String cpf);
}
