package com.facol.projeto.service.validacao.impl;

import org.springframework.stereotype.Component;

import com.facol.projeto.dto.VotoRequestDTO;
import com.facol.projeto.exceptions.VotoNaoPodeSerComputadoExceptions;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Component("ValidacaoVoto")
public class VotoValidacao implements ValidacaoStrategy<VotoRequestDTO> {

	@Override
	public void validacao(VotoRequestDTO objeto) throws RuntimeException {
			System.out.println(objeto.getVoto());
		 if (objeto.getVoto() == null || objeto.getVoto().trim().isEmpty()) {
	            throw new VotoNaoPodeSerComputadoExceptions("O voto não pode ser vazio.");
	        }
		  switch (objeto.getVoto().toLowerCase()) {
          case "s":
          case "sim":
          case "n":
          case "nao":
              break; 
          default:
              throw new VotoNaoPodeSerComputadoExceptions("O sistema não computa este tipo de voto: '" + objeto.getVoto() + "'. Tente: s, sim, n, nao.");
      }
		
	}

	

}
