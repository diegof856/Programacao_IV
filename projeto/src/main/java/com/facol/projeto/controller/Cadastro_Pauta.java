package com.facol.projeto.controller;

import com.facol.projeto.dto.PautaRequestDTO;
import com.facol.projeto.dto.SessaoVotacaoRequestDTO;
import com.facol.projeto.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/cadastropauta")
public class Cadastro_Pauta {

    @Autowired
    private PautaService pautaService;

    @PostMapping
    public ResponseEntity<Void> adicionarPauta(@RequestBody PautaRequestDTO pautasDTO){
    this.pautaService.cadastrarPauta(pautasDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
