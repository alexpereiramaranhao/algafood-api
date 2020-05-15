package com.algaworks.algafood.domain.model.dto;

import com.algaworks.algafood.domain.model.dto.input.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DTOCidade extends DTO  {
	private Long id;
	private String nome;
	private DTOEstado estado;

}
