package com.facol.projeto.service;


import com.facol.projeto.dto.AssociadoRequestDTO;

public interface AssociadoCadastroAlteracaoService {

	void cadastrarAssociado(AssociadoRequestDTO associadoRequestDTO);
	void alterarAssociado(Long id, AssociadoRequestDTO associadoRequestDTO);
	void deletarAssociado(Long id);
}
