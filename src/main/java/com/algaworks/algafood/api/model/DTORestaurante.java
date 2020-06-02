package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import com.algaworks.algafood.api.model.input.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTORestaurante extends DTO {
	private String nome;
	private BigDecimal taxaFrete;
	private DTOCozinhaRestaurante cozinha;

}
