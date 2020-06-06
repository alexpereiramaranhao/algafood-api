package br.com.mpx.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DTOCozinhaIdInput {
	@NotNull
	private Long id;
}