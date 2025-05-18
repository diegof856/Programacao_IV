package com.facol.projeto.service.validacao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.facol.projeto.exceptions.ValidacaoCpfExceptions;
import com.facol.projeto.model.Associado;
import com.facol.projeto.repositories.AssociadoRepositorio;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Component("cpfValidacao")
public class CpfValidacao implements ValidacaoStrategy<String> {

	@Autowired
	private AssociadoRepositorio associadoRepository;
	@Override
	public void validacao(String cpf) throws RuntimeException {
		if(cpf == null || !cpfValido(cpf)) {
			throw new ValidacaoCpfExceptions("O cpf precisa de 11 caracteres meu patrão(a)");
		}
		if(!validarCpfUnicos(associadoRepository.findAll(), cpf)) {
			throw new ValidacaoCpfExceptions("O cpf já cadastrado");
		}
	}
	private boolean cpfValido(String cpf) {
		if(cpf.length() != 11) {
			return false;
		}else {
			return true;
		}
		
	}
	private boolean validarCpfUnicos(List<Associado> associados,String cpf) {
		for(Associado associado:associados) {
			if(associado.getCpf().equalsIgnoreCase(cpf)) {
			return false;
			}
		}
		
		return true;
	}

}
