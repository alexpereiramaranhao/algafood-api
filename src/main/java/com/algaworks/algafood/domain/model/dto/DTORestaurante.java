package com.algaworks.algafood.domain.model.dto;

import java.math.BigDecimal;

import com.algaworks.algafood.domain.model.dto.input.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTORestaurante extends DTO {
	private String nome;
	private BigDecimal taxaFrete;
	private DTOCozinhaRestaurante cozinha;

}
