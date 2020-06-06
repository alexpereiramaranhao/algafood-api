package br.com.mpx.api.model;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOGrupo {
	
	@NotNull
	private String nome;

}
