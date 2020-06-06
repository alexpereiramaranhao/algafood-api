package br.com.mpx.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOEndereco {
	
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private DTOCidadeResumo cidade;

}
