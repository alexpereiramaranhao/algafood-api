package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.mapper.DTORestauranteMapper;
import com.algaworks.algafood.api.model.DTORestaurante;
import com.algaworks.algafood.api.model.input.DTORestauranteInput;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private DTORestauranteMapper dtoRestauranteMapper;

	@GetMapping
	public List<DTORestaurante> listar() {
		return dtoRestauranteMapper.listaEntidadeToListaDto(restauranteRepository.findAll());
	}

	@GetMapping("/{restauranteId}")
	public DTORestaurante buscar(@PathVariable Long restauranteId) {
		return dtoRestauranteMapper.entidadeToDto(cadastroRestaurante.buscarOuFalhar(restauranteId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {

		try {
			return cadastroRestaurante.salvar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public DTORestaurante atualizar(@PathVariable Long restauranteId, @RequestBody @Valid DTORestauranteInput dtoRestauranteInput) {
		try {
			Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

			dtoRestauranteMapper.copiarToEntidade(dtoRestauranteInput, restauranteAtual);

			return dtoRestauranteMapper.entidadeToDto(cadastroRestaurante.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
}
