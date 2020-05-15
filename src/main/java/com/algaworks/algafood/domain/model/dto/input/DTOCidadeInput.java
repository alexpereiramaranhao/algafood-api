package com.algaworks.algafood.domain.model.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOCidadeInput extends DTO {
	
	@NotBlank
	private String nome;
	
	@Valid
	@NotNull
	private DTOEstadoIdInput estado;

}
