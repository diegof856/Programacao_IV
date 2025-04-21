package com.facol.projeto.service.implementacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.facol.projeto.dto.VotoRequestDTO;
import com.facol.projeto.enums.TipoVoto;
import com.facol.projeto.model.Votacao;
import com.facol.projeto.model.Voto;
import com.facol.projeto.repositories.VotoRepositorio;
import com.facol.projeto.service.AssociadoConsultaService;
import com.facol.projeto.service.VotacaoConsultaService;
import com.facol.projeto.service.VotoInsercaoService;
import com.facol.projeto.service.factory.VotoFactory;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Service
public class VotoImplementacao implements VotoInsercaoService {

	private final AssociadoConsultaService associadoConsultaService;
	private final VotoFactory votoFactory;
	private final VotoRepositorio votoRepositorio;
	private final VotacaoConsultaService votacaoConsultaService;

	@Qualifier("TempoVotacaoValidacao")
	private final ValidacaoStrategy<VotoRequestDTO> votoValidacao;
	@Qualifier("ValidacaoVotacaoEncerrada")
	private final ValidacaoStrategy<Votacao> votacaoValidacao; 
	public VotoImplementacao(AssociadoConsultaService associadoConsultaService, VotoFactory votoFactory,
			VotoRepositorio votoRepositorio, VotacaoConsultaService votacaoConsultaService,
			ValidacaoStrategy<VotoRequestDTO> votoValidacao, ValidacaoStrategy<Votacao> votacaoValidacao) {
		this.associadoConsultaService = associadoConsultaService;
		this.votoFactory = votoFactory;
		this.votoRepositorio = votoRepositorio;
		this.votacaoConsultaService = votacaoConsultaService;
		this.votoValidacao = votoValidacao;
		this.votacaoValidacao = votacaoValidacao;
	}

	@Override
	public void inserirVoto(Long idAssociado, Long idVotacao, VotoRequestDTO votoRequest) {
		
		this.votoValidacao.validacao(votoRequest);
		this.votacaoValidacao.validacao(this.votacaoConsultaService.buscarVotacao(idVotacao));
		Voto voto = this.votoFactory.criarVoto(this.associadoConsultaService.buscarAssociadorPorId(idAssociado), this.tratarVoto(votoRequest.getVoto()), this.votacaoConsultaService.buscarVotacao(idVotacao));
		
		this.votacaoConsultaService.computarVotos(voto);
		this.votoRepositorio.save(voto);

	}
	private TipoVoto tratarVoto(String voto) {
		if(voto.equalsIgnoreCase("sim")||voto.equalsIgnoreCase("s")) {
			return TipoVoto.SIM;
		}else {
			return TipoVoto.NAO;
		}
	}

}
