package com.facol.projeto.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.facol.projeto.exceptions.AssociadoNaoEncontradoExceptions;
import com.facol.projeto.exceptions.AssociadoVotoDuplicadoException;
import com.facol.projeto.exceptions.PautaNaoEncontradaExceptions;
import com.facol.projeto.exceptions.PautaReprovadaExceptions;
import com.facol.projeto.exceptions.SenhaIncorretaException;
import com.facol.projeto.exceptions.TempoExecedidoException;
import com.facol.projeto.exceptions.TextoVazioException;
import com.facol.projeto.exceptions.ValidacaoCpfExceptions;
import com.facol.projeto.exceptions.ValidacaoPautaException;
import com.facol.projeto.exceptions.VotacaoEncerradaException;
import com.facol.projeto.exceptions.VotacaoNaoEncontradaException;
import com.facol.projeto.exceptions.VotoNaoPodeSerComputadoExceptions;

@RestControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(AssociadoNaoEncontradoExceptions.class)
	private ResponseEntity<Object> usuarioNaoEncontrado(AssociadoNaoEncontradoExceptions exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
	@ExceptionHandler(TextoVazioException.class)
	private ResponseEntity<Object> textoVazio(TextoVazioException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	
	@ExceptionHandler(ValidacaoCpfExceptions.class)
	private ResponseEntity<Object> cpfInvalido(ValidacaoCpfExceptions exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	@ExceptionHandler(SenhaIncorretaException.class)
	private ResponseEntity<Object> senhaIncorreta(SenhaIncorretaException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	@ExceptionHandler(PautaNaoEncontradaExceptions.class)
	private ResponseEntity<Object> pautaNaoEncontrada(PautaNaoEncontradaExceptions exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
	@ExceptionHandler(ValidacaoPautaException.class)
	private ResponseEntity<Object> validarPauta(ValidacaoPautaException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	@ExceptionHandler(TempoExecedidoException.class)
	private ResponseEntity<Object> tempoExecedido(TempoExecedidoException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}
	@ExceptionHandler(PautaReprovadaExceptions.class)
	private ResponseEntity<Object> pautaReprovada(PautaReprovadaExceptions exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}
	@ExceptionHandler(VotacaoNaoEncontradaException.class)
	private ResponseEntity<Object> votacaoNaoEncontrada(VotacaoNaoEncontradaException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
	
	@ExceptionHandler(AssociadoVotoDuplicadoException.class)
	private ResponseEntity<Object> votoDuplicado(AssociadoVotoDuplicadoException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}
	
	@ExceptionHandler(VotoNaoPodeSerComputadoExceptions.class)
	private ResponseEntity<Object> votoNaoComputado(VotoNaoPodeSerComputadoExceptions exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}
	
	@ExceptionHandler(VotacaoEncerradaException.class)
	private ResponseEntity<Object> votacaoEncerrada(VotacaoEncerradaException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}


}
