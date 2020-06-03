package com.algaworks.algafood.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.DTOFormaPagamento;
import com.algaworks.algafood.api.model.input.DTOFormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class DTOFormaPagamentoMapper implements DTOMapper<DTOFormaPagamento, FormaPagamento>{

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public FormaPagamento dtoToEntidade(DTOFormaPagamento dto) {
		return mapper.map(dto, FormaPagamento.class);
	}

	@Override
	public DTOFormaPagamento entidadeToDto(FormaPagamento entidade) {
		return mapper.map(entidade, DTOFormaPagamento.class);
	}

	@Override
	public List<DTOFormaPagamento> listaEntidadeToListaDto(List<FormaPagamento> listaEntidades) {
		return listaEntidades.stream()
				.map(cidade -> entidadeToDto(cidade))
				.collect(Collectors.toList());
	}

	@Override
	public List<FormaPagamento> listaDtoToListaEntidade(List<DTOFormaPagamento> listaDtos) {
		return listaDtos.stream()
				.map(dto -> dtoToEntidade(dto))
				.collect(Collectors.toList());
	} 
	
	public FormaPagamento dtoInputToEntidade(DTOFormaPagamentoInput dtoInput) {
		return mapper.map(dtoInput, FormaPagamento.class);
	}

	public void copiarToEntidade(DTOFormaPagamentoInput DTOFormaPagamentoInput, FormaPagamento cidade) {
		mapper.map(DTOFormaPagamentoInput, cidade);
	}

}
