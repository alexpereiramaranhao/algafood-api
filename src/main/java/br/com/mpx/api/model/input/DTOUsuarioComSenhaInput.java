package br.com.mpx.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOUsuarioComSenhaInput extends DTOUsuarioInput{
	
	@NotBlank
    private String senha;

}
