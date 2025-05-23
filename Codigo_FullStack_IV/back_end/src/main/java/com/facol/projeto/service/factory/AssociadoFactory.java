package com.facol.projeto.service.factory;

import com.facol.projeto.dto.AssociadoRequestDTO;
import com.facol.projeto.dto.AssociadoResponseDTO;
import com.facol.projeto.dto.AssociadoResponseVotoDTO;
import com.facol.projeto.dto.LoginResponseDTO;
import com.facol.projeto.model.Associado;

public interface AssociadoFactory {
	Associado criarAssociado(AssociadoRequestDTO associadoRequestDTO);

	AssociadoResponseDTO transformarAssociado(Associado associado);
	AssociadoResponseVotoDTO transformarAssociadoVotoDTO(Associado associado);
	LoginResponseDTO criarLogin(Associado associado);
}
