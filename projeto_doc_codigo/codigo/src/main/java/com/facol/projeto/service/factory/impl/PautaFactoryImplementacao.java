package com.facol.projeto.service.factory.impl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.facol.projeto.dto.PautaRequestDTO;
import com.facol.projeto.dto.PautaResponseDTO;
import com.facol.projeto.enums.StatusPauta;
import com.facol.projeto.model.Associado;
import com.facol.projeto.model.Pauta;
import com.facol.projeto.service.factory.PautaFactory;

@Component
public class PautaFactoryImplementacao implements PautaFactory {

	@Override
	public PautaResponseDTO criarPautaResponseDTO(Pauta pauta) {
		
		return new PautaResponseDTO(pauta.getId_pauta(),mudarData(pauta.getDataCricao()), pauta.getTitulo(), pauta.getDescricao(), pauta.getEstadoPauta());
	}
	private String mudarData(Instant data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());
		return formatter.format(data);
	}
	@Override
	public Pauta criarPauta(PautaRequestDTO pautaRequestDTO, Associado associado) {
		 return new Pauta(null, Instant.now(), pautaRequestDTO.getDescricao(), pautaRequestDTO.getTitulo(), StatusPauta.EM_VOTOCAO, associado);
		
	}
	
	
	
}
