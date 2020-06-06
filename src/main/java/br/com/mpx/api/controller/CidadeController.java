package br.com.mpx.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mpx.api.mapper.DTOCidadeMapper;
import br.com.mpx.api.model.DTOCidade;
import br.com.mpx.api.model.input.DTOCidadeInput;
import br.com.mpx.domain.exception.EstadoNaoEncontradoException;
import br.com.mpx.domain.exception.NegocioException;
import br.com.mpx.domain.model.Cidade;
import br.com.mpx.domain.repository.CidadeRepository;
import br.com.mpx.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	DTOCidadeMapper mapper;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@GetMapping
	public List<DTOCidade> listar() {
		return mapper.listaEntidadeToListaDto(cidadeRepository.findAll());
	}
	
	@GetMapping("/{cidadeId}")
	public DTOCidade buscar(@PathVariable Long cidadeId) {
		return mapper.entidadeToDto(cadastroCidade.buscarOuFalhar(cidadeId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DTOCidade adicionar(@RequestBody @Valid DTOCidadeInput dtoCidadeInput) {
		try {
			return mapper.entidadeToDto(cadastroCidade.salvar(mapper.dtoInputToEntidade(dtoCidadeInput)));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public DTOCidade atualizar(@PathVariable Long cidadeId,
			@RequestBody @Valid DTOCidadeInput dtoCidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
			
			mapper.copiarToEntidade(dtoCidadeInput, cidadeAtual);
						
			return mapper.entidadeToDto(cadastroCidade.salvar(cidadeAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);	
	}
	
}
