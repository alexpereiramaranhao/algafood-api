package br.com.mpx.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DTOFormaPagamentoInput {
	@NotBlank
    private String descricao;
}