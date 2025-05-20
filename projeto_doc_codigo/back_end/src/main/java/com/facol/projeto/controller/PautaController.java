package com.facol.projeto.controller;

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

import com.facol.projeto.dto.PautaRequestDTO;
import com.facol.projeto.dto.PautaResponseDTO;
import com.facol.projeto.service.AssociadoConsultaService;
import com.facol.projeto.service.PautaCadastrarAlterarService;
import com.facol.projeto.service.PautaConsultarService;


@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("v1/pautas")
public class PautaController {


    private final PautaCadastrarAlterarService pautaCadastrarAlterarService;

    private final PautaConsultarService consultaService;
    private final AssociadoConsultaService associadoService;
   
    
    public PautaController(AssociadoConsultaService associadoService, PautaCadastrarAlterarService pautaCadastrarAlterarService, PautaConsultarService consultarPautaService) {
    	this.associadoService = associadoService;
    	this.pautaCadastrarAlterarService = pautaCadastrarAlterarService;
    	this.consultaService = consultarPautaService;
    }
    
    @PostMapping("{id}")
    public ResponseEntity<Void> adicionarPauta(@PathVariable Long id,@RequestBody PautaRequestDTO pautaDTO){
    	
    this.pautaCadastrarAlterarService.cadastrarPauta(this.associadoService.buscarAssociadorPorId(id),pautaDTO);
    
    return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Page<PautaResponseDTO>> pegaPautasAberto(@PageableDefault(page = 0, size = 8) Pageable pageAble){
    	return ResponseEntity.ok(this.consultaService.pegarEmAberto(pageAble));
    }
    @GetMapping("/todasPautas")
    public ResponseEntity<Page<PautaResponseDTO>> pegarPautas(@PageableDefault(page = 0, size = 8) Pageable pageAble){
    	return ResponseEntity.ok(this.consultaService.buscarPautas(pageAble));
    }
    
    @GetMapping("{id}")
    public ResponseEntity<PautaResponseDTO> pegarPauta(@PathVariable Long id){
    	return ResponseEntity.ok(this.consultaService.pegarPauta(id));
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Void> alterarPauta(@PathVariable Long id, @RequestBody PautaRequestDTO pautaDTO){
    	this.pautaCadastrarAlterarService.alterarPauta(id, pautaDTO);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPauta(@PathVariable Long id){
    	this.pautaCadastrarAlterarService.deletarPauta(id);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
