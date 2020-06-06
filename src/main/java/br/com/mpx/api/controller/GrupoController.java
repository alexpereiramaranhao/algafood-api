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

import br.com.mpx.api.mapper.DTOGrupoMapper;
import br.com.mpx.api.model.DTOGrupo;
import br.com.mpx.api.model.input.DTOGrupoInput;
import br.com.mpx.domain.exception.CozinhaNaoEncontradaException;
import br.com.mpx.domain.exception.GrupoNaoEncontradoException;
import br.com.mpx.domain.exception.NegocioException;
import br.com.mpx.domain.model.Grupo;
import br.com.mpx.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private DTOGrupoMapper dtoGrupoMapper;


	@GetMapping
	public List<DTOGrupo> listar() {
		return dtoGrupoMapper.listaEntidadeToListaDto(cadastroGrupo.buscarTodos());
	}

	@GetMapping("/{grupoId}")
	public DTOGrupo buscar(@PathVariable Long grupoId) {
		return dtoGrupoMapper.entidadeToDto(cadastroGrupo.buscarOuFalhar(grupoId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Grupo adicionar(@RequestBody @Valid Grupo grupo) {

		try {
			return cadastroGrupo.salvar(grupo);
		} catch (GrupoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{grupoId}")
	public DTOGrupo atualizar(@PathVariable Long grupoId, @RequestBody @Valid DTOGrupoInput dtoGrupoInput) {
		try {
			Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);

			dtoGrupoMapper.copiarToEntidade(dtoGrupoInput, grupoAtual);

			return dtoGrupoMapper.entidadeToDto(cadastroGrupo.salvar(grupoAtual));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
    	cadastroGrupo.excluir(grupoId);
    }
}
