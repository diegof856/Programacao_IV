package com.facol.projeto.service;

import com.facol.projeto.dto.PautaRequestDTO;
import com.facol.projeto.model.Pauta;
import com.facol.projeto.repositories.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {
    @Autowired
    private PautaRepository pautaRepository;

    public void cadastrarPauta(PautaRequestDTO pautaRequestDTO){
        Pauta pauta = new Pauta(null, pautaRequestDTO.getDataCricao(), pautaRequestDTO.getDescricao(),pautaRequestDTO.getTitulo());
        pautaRepository.save(pauta);
    }
}
