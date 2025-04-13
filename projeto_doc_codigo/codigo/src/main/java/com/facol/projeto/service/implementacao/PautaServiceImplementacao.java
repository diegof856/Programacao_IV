package com.facol.projeto.service.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.facol.projeto.dto.PautaRequestDTO;
import com.facol.projeto.dto.PautaResponseDTO;
import com.facol.projeto.exceptions.PautaNaoEncontrada;
import com.facol.projeto.model.Associado;
import com.facol.projeto.model.Pauta;
import com.facol.projeto.repositories.PautaRepository;
import com.facol.projeto.service.PautaCadastrarAlterarService;
import com.facol.projeto.service.PautaConsultarService;
import com.facol.projeto.service.factory.PautaFactory;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Service
public class PautaServiceImplementacao implements PautaCadastrarAlterarService, PautaConsultarService {
	@Autowired
	private PautaRepository pautaRepositorio;

	@Autowired
	private PautaFactory pautaFactory;
	@Autowired
	@Qualifier("TituloDescricaoValidacao")
	private ValidacaoStrategy<PautaRequestDTO> validacaoStrategyTituloDescricao;

	@Override
	public void cadastrarPauta(Associado associado, PautaRequestDTO pautaRequestDTO) {
		this.validacaoStrategyTituloDescricao.validacao(pautaRequestDTO);
		pautaRepositorio.save(this.pautaFactory.criarPauta(pautaRequestDTO, associado));
	}

	@Override
	public void alterarPauta(Long id, PautaRequestDTO requestDTO) {
		Pauta pauta = this.pegarPautaDB(id);
		pauta.setDescricao(requestDTO.getDescricao());
		pauta.setTitulo(requestDTO.getTitulo());
		this.pautaRepositorio.save(pauta);
	}

	@Override
	public Page<PautaResponseDTO> buscarPautas(Pageable pageAble) {
		return this.pautaRepositorio.findAll(pageAble).map(pauta -> pautaFactory.criarPautaResponseDTO(pauta));

	}

	@Override
	public PautaResponseDTO pegarPauta(Long id) {

		return pautaFactory.criarPautaResponseDTO(this.pegarPautaDB(id));

	}

	private Pauta pegarPautaDB(Long id) {
		return this.pautaRepositorio.findById(id).orElseThrow(() -> new PautaNaoEncontrada());
	}

	@Override
	public void deletarPauta(Long id) {
		this.pautaRepositorio.deleteById(id);

	}

}
