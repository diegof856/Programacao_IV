package com.facol.projeto.service.factory;

import com.facol.projeto.dto.VotoResponseDTO;
import com.facol.projeto.enums.TipoVoto;
import com.facol.projeto.model.Associado;
import com.facol.projeto.model.Votacao;
import com.facol.projeto.model.Voto;


public interface VotoFactory {

	Voto criarVoto(Associado associado, TipoVoto voto, Votacao votacao);
	VotoResponseDTO criarResponseVotoDTO(Voto voto);
}
