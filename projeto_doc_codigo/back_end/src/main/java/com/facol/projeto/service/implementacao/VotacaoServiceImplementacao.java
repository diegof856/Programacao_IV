package com.facol.projeto.service.implementacao;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.facol.projeto.dto.VotacaoResponseDTO;
import com.facol.projeto.enums.StatusPauta;
import com.facol.projeto.enums.StatusVotacao;
import com.facol.projeto.enums.TipoVoto;
import com.facol.projeto.exceptions.AssociadoVotoDuplicadoException;
import com.facol.projeto.exceptions.VotacaoNaoEncontradaException;
import com.facol.projeto.model.Pauta;
import com.facol.projeto.model.Votacao;
import com.facol.projeto.model.Voto;
import com.facol.projeto.repositories.VotacaoRepositorio;
import com.facol.projeto.service.CadastrarAlterarVotacao;
import com.facol.projeto.service.VotacaoConsultaService;
import com.facol.projeto.service.factory.VotacaoFactory;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

import java.time.Duration;

@Service
public class VotacaoServiceImplementacao implements VotacaoConsultaService, CadastrarAlterarVotacao {

	private final VotacaoRepositorio votacaoRepositorio;
	private final VotacaoFactory votacaoFactory;
	private final ValidacaoStrategy<Pauta> validacaoStrategyTempo;
	@Autowired
	@Qualifier("TempoVotacaoValidacao")
	private ValidacaoStrategy<Pauta> validacaoTempoExcedido;

	public VotacaoServiceImplementacao(VotacaoRepositorio votacaoRepositorio, VotacaoFactory votacaoFactory,
			ValidacaoStrategy<Pauta> validacaoStrategyTempo) {
		this.votacaoRepositorio = votacaoRepositorio;
		this.votacaoFactory = votacaoFactory;
		this.validacaoStrategyTempo = validacaoStrategyTempo;
	}

	@Override
	public Page<VotacaoResponseDTO> consultarVotacoes(Pageable pageable) {

		return votacaoRepositorio.findAll(pageable).map(votacao -> pegarVotacaoDTO(votacao));
	}

	private VotacaoResponseDTO pegarVotacaoDTO(Votacao votacao) {

		return this.votacaoFactory.criarVotacaoDTO(votacao);
	}

	@Override
	public VotacaoResponseDTO consultarVotacao(Long id) {

		return this.votacaoFactory.criarVotacaoDTO(this.buscarVotacao(id));
	}

	public Votacao buscarVotacao(Long id) {
		Optional<Votacao> objetoVotacao = this.votacaoRepositorio.findById(id);
		objetoVotacao.orElseThrow(() -> new VotacaoNaoEncontradaException());
		this.atualizarVotacaoParaDeterminarVencendor(objetoVotacao.get());
		return objetoVotacao.get();

	}

	@Override
	public VotacaoResponseDTO buscarVotacaoPorPauta(Long idPauta) {
		Votacao votacao = votacaoRepositorio.findByPauta_Id(idPauta);

		this.atualizarVotacaoParaDeterminarVencendor(votacao);
		return this.votacaoFactory.criarVotacaoDTO(votacao);
	}

	public void atualizarVotacao(Pauta pauta) {
		this.validacaoStrategyTempo.validacao(pauta);
		Votacao votacao = votacaoRepositorio.findByPauta_Id(pauta.getId_pauta());
		votacao.setDataFim(votacao.getDataInicio().plus(Duration.ofMinutes(pauta.getTempoVotacao())));
		this.votacaoRepositorio.save(votacao);
	}

	private void atualizarVotacaoParaDeterminarVencendor(Votacao votacao) {
		if (Instant.now().isAfter(votacao.getDataFim())) {
			votacao.setStatusVotacao(StatusVotacao.FINALIZADA);
			long votosSim = votacao.getVotos().stream().filter(voto -> voto.getStatusVoto() == TipoVoto.SIM).count();
			long votosNao = votacao.getVotos().stream().filter(voto -> voto.getStatusVoto() == TipoVoto.NAO).count();
			if (votosSim > votosNao) {
				votacao.getPauta().setEstadoPauta(StatusPauta.APROVADA);
			} else {
				votacao.getPauta().setEstadoPauta(StatusPauta.REPROVADA);
			}
			this.votacaoRepositorio.save(votacao);
		}
	}

	@Override
	public void abrirVotacao(Pauta pauta) {
		validacaoStrategyTempo.validacao(pauta);
		if (pauta.getEstadoPauta() == StatusPauta.EM_VOTOCAO) {

			this.votacaoRepositorio.save(votacaoFactory.criarVotacao(pauta));
		}

	}

	@Override
	public void computarVotos(Voto voto) {

		if (voto.getVotacao().getVotos().stream().anyMatch(votoLista -> votoLista.getAssociado().getId_associado()
				.equals(voto.getAssociado().getId_associado()))) {
			throw new AssociadoVotoDuplicadoException();
		}
		voto.getVotacao().getVotos().add(voto);

	}

}
