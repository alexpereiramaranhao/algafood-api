package com.algaworks.algafood.api.model;

import com.algaworks.algafood.api.model.input.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DTOCidade extends DTO  {
	private Long id;
	private String nome;
	private DTOEstado estado;

}
