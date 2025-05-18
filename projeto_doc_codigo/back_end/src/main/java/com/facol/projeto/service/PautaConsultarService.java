package com.facol.projeto.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.facol.projeto.dto.PautaResponseDTO;
import com.facol.projeto.model.Pauta;

public interface PautaConsultarService {
	Page<PautaResponseDTO> buscarPautas(Pageable pageAble);
	PautaResponseDTO pegarPauta(Long id);
	void pegarPautaParaAtualizar(List<Pauta> pautas);
	Page<PautaResponseDTO> pegarEmAberto(Pageable pageAble);
}
