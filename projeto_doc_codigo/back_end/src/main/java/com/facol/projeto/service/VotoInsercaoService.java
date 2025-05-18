package com.facol.projeto.service;

import com.facol.projeto.dto.VotoRequestDTO;

public interface VotoInsercaoService {

	void inserirVoto(Long idAssociado, Long idVotacao,VotoRequestDTO votoRequest);
}
