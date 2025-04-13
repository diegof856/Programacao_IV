package com.facol.projeto.service.factory;

import com.facol.projeto.dto.AssociadoRequestDTO;
import com.facol.projeto.dto.AssociadoResponseDTO;
import com.facol.projeto.model.Associado;

public interface AssociadoFactory {
	Associado cricarAssociado(AssociadoRequestDTO associadoRequestDTO);

	AssociadoResponseDTO transformarAssociado(Associado associado);
}
