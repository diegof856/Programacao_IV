package com.facol.projeto.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.facol.projeto.dto.PautaRequestDTO;
import com.facol.projeto.dto.PautaResponseDTO;
import com.facol.projeto.enums.StatusPauta;
import com.facol.projeto.exceptions.PautaNaoEncontradaExceptions;
import com.facol.projeto.exceptions.PautaReprovadaExceptions;
import com.facol.projeto.model.Associado;
import com.facol.projeto.model.Pauta;
import com.facol.projeto.repositories.PautaRepositorio;
import com.facol.projeto.repositories.VotacaoRepositorio;
import com.facol.projeto.service.CadastrarAlterarVotacao;
import com.facol.projeto.service.PautaCadastrarAlterarService;
import com.facol.projeto.service.PautaConsultarService;
import com.facol.projeto.service.VotacaoConsultaService;
import com.facol.projeto.service.factory.PautaFactory;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Service
public class PautaServiceImplementacao implements PautaCadastrarAlterarService, PautaConsultarService {
	@Autowired
	private PautaRepositorio pautaRepositorio;
	@Autowired
	private CadastrarAlterarVotacao votacaoCadastrar;
	@Autowired
	private PautaFactory pautaFactory;

	@Autowired
	private VotacaoConsultaService votacaoService;
	@Autowired
	private VotacaoRepositorio votacaoRepositorio;
	@Autowired
	@Qualifier("TituloDescricaoValidacao")
	private ValidacaoStrategy<PautaRequestDTO> validacaoStrategyTituloDescricao;
	


	@Override
	public void cadastrarPauta(Associado associado, PautaRequestDTO pautaRequestDTO) {
		this.validacaoStrategyTituloDescricao.validacao(pautaRequestDTO);
		Pauta pauta = this.pautaFactory.criarPauta(pautaRequestDTO, associado);
		pautaRepositorio.save(pauta);
		abrirVotacao(pauta);
	}

	private void abrirVotacao(Pauta pauta) {
		this.votacaoCadastrar.abrirVotacao(pauta);
	}

	@Override
	public void alterarPauta(Long id, PautaRequestDTO requestDTO) {
		
		Pauta pauta = this.pegarPautaDB(id);
		if(pauta.getEstadoPauta() == StatusPauta.EM_VOTOCAO) {
			pauta.setDescricao(requestDTO.getDescricao());
			pauta.setTitulo(requestDTO.getTitulo());
			pauta.setTempoVotacao(requestDTO.getTempoVotacao());
			this.pautaRepositorio.save(pauta);
			this.votacaoCadastrar.atualizarVotacao(pauta);
		}else {
			throw new PautaReprovadaExceptions();
		}
		
	}

	@Override
	public Page<PautaResponseDTO> buscarPautas(Pageable pageAble) {
		this.pegarPautaParaAtualizar(this.pautaRepositorio.findAll());
		return this.pautaRepositorio.findAll(pageAble).map(pauta -> pautaFactory.criarPautaResponseDTO(pauta));

	}
	@Override
	public Page<PautaResponseDTO> pegarEmAberto(Pageable pageAble) {
		Page<PautaResponseDTO> pautas = this.buscarPautas(pageAble);
		List<PautaResponseDTO> filtradas = pautas.stream().filter(pauta -> pauta.status_Pauta() == StatusPauta.EM_VOTOCAO).toList();
		return new PageImpl<>(filtradas, pageAble, filtradas.size());
	}


	@Override
	public PautaResponseDTO pegarPauta(Long id) {

		return pautaFactory.criarPautaResponseDTO(this.pegarPautaDB(id));

	}

	private Pauta pegarPautaDB(Long id) {
		Pauta pauta = this.pautaRepositorio.findById(id).orElseThrow(() -> new PautaNaoEncontradaExceptions());
		this.votacaoService.buscarVotacaoPorPauta(id);
		;
		return pauta;
	}

	@Override
	public void deletarPauta(Long id) {
		this.votacaoRepositorio.deleteById(this.pegarPautaDB(id).getVotacao().getIdVotacao());
		this.pautaRepositorio.deleteById(id);

	}

	@Override
	public void pegarPautaParaAtualizar(List<Pauta> pautas) {
		pautas.forEach(pauta -> this.votacaoService.buscarVotacaoPorPauta(pauta.getId_pauta()));
	}

	
}
