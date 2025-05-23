package com.facol.projeto.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facol.projeto.dto.VotacaoResponseDTO;
import com.facol.projeto.service.VotacaoConsultaService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("v1/votacoes")
public class VotacaoController {
	
	private final VotacaoConsultaService votacaoService;
	
	public VotacaoController(VotacaoConsultaService votacaoService) {
		this.votacaoService = votacaoService;
	}
	@GetMapping
    public ResponseEntity<Page<VotacaoResponseDTO>> PegarSessoesVotacao(@PageableDefault(page = 0, size = 8) Pageable pageAble){
        return ResponseEntity.ok(this.votacaoService.consultarVotacoes(pageAble));
    }
	@GetMapping("{id}")
	public ResponseEntity<VotacaoResponseDTO> pegarSessaoVotacao(@PathVariable Long id){
		return ResponseEntity.ok(this.votacaoService.consultarVotacao(id));
	}
	@GetMapping("pauta/{idPauta}")
	public ResponseEntity<VotacaoResponseDTO> pegarVotacaoPorPauta(@PathVariable Long idPauta){
		return ResponseEntity.ok(this.votacaoService.buscarVotacaoPorPauta(idPauta));
	}
	@GetMapping("quantidadeVotos/{idVotacao}")
	public ResponseEntity<List<Long>> pegarPocentagem(@PathVariable Long idVotacao){
		return ResponseEntity.ok(this.votacaoService.buscarPocentagemVotos(idVotacao));
	}
}
