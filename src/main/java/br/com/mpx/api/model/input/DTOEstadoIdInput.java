package br.com.mpx.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOEstadoIdInput extends DTO  {
	
	@NotNull
	private Long id;

}
