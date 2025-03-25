package com.facol.projeto.controller;

import com.facol.projeto.dto.SessaoVotacaoRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/sessaoVotacao")
public class VotacaoController {

    public ResponseEntity<Void> adicionarSessaoVotacao(@RequestBody SessaoVotacaoRequestDTO votacaoDTO){
        this.
    }

}
