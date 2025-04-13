package com.facol.projeto.service.implementacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.facol.projeto.dto.AssociadoRequestDTO;
import com.facol.projeto.dto.AssociadoResponseDTO;
import com.facol.projeto.exceptions.AssociadoNaoEncontrado;
import com.facol.projeto.model.Associado;
import com.facol.projeto.repositories.AssociadoRepository;
import com.facol.projeto.service.AssociadoCadastroAlteracaoService;
import com.facol.projeto.service.AssociadoConsultaService;
import com.facol.projeto.service.factory.AssociadoFactory;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Service
public class AssociadoServiceImplementacao implements AssociadoCadastroAlteracaoService, AssociadoConsultaService {
	@Autowired
	private AssociadoRepository associadoRepository;
	@Autowired
	private AssociadoFactory associadoFactory;
	@Autowired
	@Qualifier("cpfValidacao")
	private ValidacaoStrategy<AssociadoRequestDTO> validacaoStrategy;
	@Autowired
	@Qualifier("nomeValidacao")
	private ValidacaoStrategy<String> validacaoStrategyNome;

	@Override
	public void cadastrarAssociado(AssociadoRequestDTO associadoRequestDTO) {

		this.validacaoStrategy.validacao(associadoRequestDTO);
		this.validacaoStrategyNome.validacao(associadoRequestDTO.getNome());
		Associado associado = associadoFactory.cricarAssociado(associadoRequestDTO);
		associadoRepository.save(associado);

	}

	@Override
	public Page<AssociadoResponseDTO> buscarAssociados(Pageable pageable) {
		return associadoRepository.findAll(pageable).map(associado -> this.associadoFactory.transformarAssociado(associado));

	}

	@Override
	public Associado buscarAssociadorPorId(Long id) {
		Optional<Associado> objAssociado = associadoRepository.findById(id);
		return objAssociado.orElseThrow(() -> new AssociadoNaoEncontrado());
	}

	

	@Override
	public AssociadoResponseDTO buscarAssociadoPorIdDTO(Long id) {
		return this.associadoFactory.transformarAssociado(this.buscarAssociadorPorId(id));
	}

	@Override
	public void alterarAssociado(Long id, AssociadoRequestDTO associadoRequestDTO) {
		Associado associado = this.buscarAssociadorPorId(id);
		associado.setNome(associadoRequestDTO.getNome());
		associado.setCpf(associadoRequestDTO.getCpf());
			this.associadoRepository.save(associado);
	}

	@Override
	public void deletarAssociado(Long id) {
		this.associadoRepository.deleteById(id);
		
	}

}
