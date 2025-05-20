package com.facol.projeto.service.factory;

import com.facol.projeto.dto.PautaRequestDTO;
import com.facol.projeto.dto.PautaResponseDTO;
import com.facol.projeto.model.Associado;
import com.facol.projeto.model.Pauta;

public interface PautaFactory {

	PautaResponseDTO criarPautaResponseDTO(Pauta pauta);
	Pauta criarPauta(PautaRequestDTO pautaRequestDTO, Associado associado);
	
	
}
