package com.algaworks.algafood.domain.model.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.dto.DTORestaurante;
import com.algaworks.algafood.domain.model.dto.input.DTORestauranteInput;

@Component
public class DTORestauranteMapper implements DTOMapper<DTORestaurante, Restaurante> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Restaurante dtoToEntidade(DTORestaurante dtoRestaurante) {
		return modelMapper.map(dtoRestaurante, Restaurante.class);		
	}
	
	@Override
	public DTORestaurante entidadeToDto(Restaurante restaurante) {
		return modelMapper.map(restaurante, DTORestaurante.class);
	}
	
	@Override
	public List<DTORestaurante> listaEntidadeToListaDto(List<Restaurante> restaurantes){
		return restaurantes.stream()
				.map(restaurante -> entidadeToDto(restaurante))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Restaurante> listaDtoToListaEntidade(List<DTORestaurante> dtoRestaurantes){
		return dtoRestaurantes.stream()
				.map(dto -> this.dtoToEntidade(dto))
				.collect(Collectors.toList());
	}
	
	public void copiarToEntidade(DTORestauranteInput restauranteInput, Restaurante restaurante) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteInput, restaurante);
	}

}
