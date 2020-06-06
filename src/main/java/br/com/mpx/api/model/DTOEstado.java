package br.com.mpx.api.model;

import br.com.mpx.api.model.input.DTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DTOEstado extends DTO {
	
	private Long id;
	private String nome;
	
}
