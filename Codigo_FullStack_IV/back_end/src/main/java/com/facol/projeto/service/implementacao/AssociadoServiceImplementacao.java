package com.facol.projeto.service.implementacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.facol.projeto.dto.AssociadoRequestDTO;
import com.facol.projeto.dto.AssociadoResponseDTO;
import com.facol.projeto.dto.LoginRequestDTO;
import com.facol.projeto.dto.LoginResponseDTO;
import com.facol.projeto.exceptions.AssociadoNaoEncontradoExceptions;
import com.facol.projeto.exceptions.SenhaIncorretaException;
import com.facol.projeto.model.Associado;
import com.facol.projeto.repositories.AssociadoRepositorio;
import com.facol.projeto.service.AssociadoCadastroAlteracaoService;
import com.facol.projeto.service.AssociadoConsultaService;
import com.facol.projeto.service.PautaConsultarService;
import com.facol.projeto.service.factory.AssociadoFactory;
import com.facol.projeto.service.validacao.ValidacaoStrategy;

@Service
public class AssociadoServiceImplementacao implements AssociadoCadastroAlteracaoService, AssociadoConsultaService {
	
	private final AssociadoRepositorio associadoRepository;
	
	private final AssociadoFactory associadoFactory;
	
	private final PautaConsultarService pautaService;
	@Autowired
	@Qualifier("cpfValidacao")
	private ValidacaoStrategy<String> validacaoStrategy;
	@Autowired
	@Qualifier("nomeValidacao")
	private ValidacaoStrategy<String> validacaoStrategyNome;

	public AssociadoServiceImplementacao(AssociadoRepositorio associadoRepositorio, AssociadoFactory associadoFactory, PautaConsultarService pautaService) {
		this.associadoFactory = associadoFactory;
		this.associadoRepository = associadoRepositorio;
		this.pautaService = pautaService;
	
	}
	

	@Override
	public void cadastrarAssociado(AssociadoRequestDTO associadoRequestDTO) {

		this.validacaoStrategy.validacao(associadoRequestDTO.getCpf());
		this.validacaoStrategyNome.validacao(associadoRequestDTO.getNome());
		Associado associado = associadoFactory.criarAssociado(associadoRequestDTO);
		associadoRepository.save(associado);
		

	}

	@Override
	public Page<AssociadoResponseDTO> buscarAssociados(Pageable pageable) {
		this.associadoRepository.findAll()
				.forEach(associado -> this.pautaService.pegarPautaParaAtualizar(associado.getPauta()));
		return associadoRepository.findAll(pageable)
				.map(associado -> this.associadoFactory.transformarAssociado(associado));

	}

	@Override
	public Associado buscarAssociadorPorId(Long id) {
		Optional<Associado> objAssociado = associadoRepository.findById(id);
		objAssociado.orElseThrow(() -> new AssociadoNaoEncontradoExceptions());
		this.pautaService.pegarPautaParaAtualizar(objAssociado.get().getPauta());
		return objAssociado.get();
	}

	@Override
	public AssociadoResponseDTO buscarAssociadoPorIdDTO(Long id) {
		return this.associadoFactory.transformarAssociado(this.buscarAssociadorPorId(id));
	}
	@Override
	public LoginResponseDTO buscarPorCpf(LoginRequestDTO loginRequest) {
		Associado associado = this.associadoRepository.findByCpf(loginRequest.getCpf()).orElseThrow(() -> new AssociadoNaoEncontradoExceptions());
		if(!associado.getSenha().equalsIgnoreCase(loginRequest.getSenha())) {
			throw new SenhaIncorretaException();
		}
		return this.associadoFactory.criarLogin(associado);
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
