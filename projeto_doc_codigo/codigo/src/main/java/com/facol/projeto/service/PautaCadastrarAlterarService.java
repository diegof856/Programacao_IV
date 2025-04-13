package com.facol.projeto.service;

import com.facol.projeto.dto.PautaRequestDTO;
import com.facol.projeto.model.Associado;


public interface PautaCadastrarAlterarService {
   void cadastrarPauta(Associado associado, PautaRequestDTO requestPautaDTO);
   void alterarPauta(Long id, PautaRequestDTO requestDTO);
   void deletarPauta(Long id);
}
