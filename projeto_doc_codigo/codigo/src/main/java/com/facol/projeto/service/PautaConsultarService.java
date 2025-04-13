package com.facol.projeto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.facol.projeto.dto.PautaResponseDTO;

public interface PautaConsultarService {
	Page<PautaResponseDTO> buscarPautas(Pageable pageAble);
	PautaResponseDTO pegarPauta(Long id);
}
