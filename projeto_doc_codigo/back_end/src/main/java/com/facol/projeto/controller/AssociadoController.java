package com.facol.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.facol.projeto.dto.AssociadoRequestDTO;
import com.facol.projeto.dto.AssociadoResponseDTO;
import com.facol.projeto.dto.LoginRequestDTO;
import com.facol.projeto.dto.LoginResponseDTO;
import com.facol.projeto.service.AssociadoCadastroAlteracaoService;
import com.facol.projeto.service.AssociadoConsultaService;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("v1/associados")
public class AssociadoController {
	@Autowired
	private AssociadoCadastroAlteracaoService associadoCadastroAlteracaoService;
	@Autowired
	private AssociadoConsultaService associadoConsultaService;

	@PostMapping
	public ResponseEntity<Void> cadastrarAssociado(@RequestBody AssociadoRequestDTO requestAssociadoDTO) {
		associadoCadastroAlteracaoService.cadastrarAssociado(requestAssociadoDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestLoginDTO){
		return ResponseEntity.ok(associadoConsultaService.buscarPorCpf(requestLoginDTO));
		
	}
	@GetMapping
	public ResponseEntity<Page<AssociadoResponseDTO>> listarAssociados(@PageableDefault(page = 0, size = 8) Pageable pageAble){
		return ResponseEntity.ok(associadoConsultaService.buscarAssociados(pageAble));
	}
	@GetMapping("{id}")
	public ResponseEntity<AssociadoResponseDTO> buscarAssociado(@PathVariable Long id){
		return ResponseEntity.ok(associadoConsultaService.buscarAssociadoPorIdDTO(id));
	}
	@PutMapping("{id}")
	public ResponseEntity<Void> alterarAssociado(@PathVariable Long id, @RequestBody AssociadoRequestDTO assiadoRequestDTO){
		associadoCadastroAlteracaoService.alterarAssociado(id, assiadoRequestDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletarAssociado(@PathVariable Long id){
		associadoCadastroAlteracaoService.deletarAssociado(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
