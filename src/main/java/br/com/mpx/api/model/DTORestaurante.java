package br.com.mpx.api.model;

import java.math.BigDecimal;

import br.com.mpx.api.model.input.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTORestaurante extends DTO {
	private String nome;
	private BigDecimal taxaFrete;
	private DTOCozinhaRestaurante cozinha;
	private DTOEndereco endereco;

}
