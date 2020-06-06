package br.com.mpx.api.mapper;

import java.util.List;

public interface DTOMapper<D, E> {

	E dtoToEntidade(D dto);

	D entidadeToDto(E entidade);

	List<D> listaEntidadeToListaDto(List<E> listaEntidades);

	List<E> listaDtoToListaEntidade(List<D> listaDtos);

}