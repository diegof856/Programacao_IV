package com.facol.projeto.infra;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.facol.projeto.exceptions.AssociadoNaoEncontrado;
import com.facol.projeto.exceptions.AssociadoVotoDuplicadoException;
import com.facol.projeto.exceptions.PautaNaoEncontrada;
import com.facol.projeto.exceptions.TempoExecedidoException;
import com.facol.projeto.exceptions.TextoVazioException;
import com.facol.projeto.exceptions.ValidacaoCpfExceptions;
import com.facol.projeto.exceptions.ValidacaoPautaException;
import com.facol.projeto.exceptions.VotacaoEncerradaException;
import com.facol.projeto.exceptions.VotacaoNaoEncontradaException;
import com.facol.projeto.exceptions.VotoNaoPodeSerComputado;

@RestControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(AssociadoNaoEncontrado.class)
	private ResponseEntity<Object> usuarioNaoEncontrado(AssociadoNaoEncontrado exception) {
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
	@ExceptionHandler(PautaNaoEncontrada.class)
	private ResponseEntity<Object> PautaNaoEncontrada(PautaNaoEncontrada exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
	@ExceptionHandler(ValidacaoPautaException.class)
	private ResponseEntity<Object> cpfInvalido(ValidacaoPautaException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	@ExceptionHandler(TempoExecedidoException.class)
	private ResponseEntity<Object> cpfInvalido(TempoExecedidoException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}
	@ExceptionHandler(VotacaoNaoEncontradaException.class)
	private ResponseEntity<Object> cpfInvalido(VotacaoNaoEncontradaException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}
	
	@ExceptionHandler(AssociadoVotoDuplicadoException.class)
	private ResponseEntity<Object> cpfInvalido(AssociadoVotoDuplicadoException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}
	
	@ExceptionHandler(VotoNaoPodeSerComputado.class)
	private ResponseEntity<Object> cpfInvalido(VotoNaoPodeSerComputado exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}
	
	@ExceptionHandler(VotacaoEncerradaException.class)
	private ResponseEntity<Object> cpfInvalido(VotacaoEncerradaException exception) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("mensagem", exception.getMessage());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
	}


}
