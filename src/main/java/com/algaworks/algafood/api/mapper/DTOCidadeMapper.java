package com.algaworks.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.DTOCidade;
import com.algaworks.algafood.api.model.input.DTOCidadeInput;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class DTOCidadeMapper implements DTOMapper<DTOCidade, Cidade>{
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Cidade dtoToEntidade(DTOCidade dto) {
		return mapper.map(dto, Cidade.class);
	}

	@Override
	public DTOCidade entidadeToDto(Cidade entidade) {
		return mapper.map(entidade, DTOCidade.class);
	}

	@Override
	public List<DTOCidade> listaEntidadeToListaDto(List<Cidade> listaEntidades) {
		return listaEntidades.stream()
				.map(cidade -> entidadeToDto(cidade))
				.collect(Collectors.toList());
	}

	@Override
	public List<Cidade> listaDtoToListaEntidade(List<DTOCidade> listaDtos) {
		return listaDtos.stream()
				.map(dto -> dtoToEntidade(dto))
				.collect(Collectors.toList());
	} 
	
	public Cidade dtoInputToEntidade(DTOCidadeInput dtoInput) {
		return mapper.map(dtoInput, Cidade.class);
	}

	public void copiarToEntidade(DTOCidadeInput dtoCidadeInput, Cidade cidade) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.algaworks.algafood.domain.model.Cozinha was altered from 1 to 2
		cidade.setEstado(new Estado());
		
		mapper.map(dtoCidadeInput, cidade);
	}


}
