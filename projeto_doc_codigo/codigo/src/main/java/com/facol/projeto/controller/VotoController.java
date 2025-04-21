package com.facol.projeto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facol.projeto.dto.VotoRequestDTO;
import com.facol.projeto.service.VotoInsercaoService;



@RestController
@RequestMapping("v1/votos")
public class VotoController {

	private final VotoInsercaoService votoService;
	
	public VotoController(VotoInsercaoService votoService) {
		this.votoService = votoService;
	}
	@PostMapping("/{idAssociado}/{idVotacao}")
	public ResponseEntity<Void> inserirVoto(@PathVariable Long idAssociado,@PathVariable Long idVotacao, @RequestBody VotoRequestDTO votoRequest){
		this.votoService.inserirVoto(idAssociado, idVotacao,votoRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
