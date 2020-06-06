package br.com.mpx.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mpx.api.model.DTOEndereco;
import br.com.mpx.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper getModelMapper() {
		
		ModelMapper mapper = new ModelMapper();
		
		mapper.createTypeMap(Endereco.class, DTOEndereco.class)
		.<String>addMapping(
				endereco -> endereco.getCidade().getEstado().getNome(),
				(dtoEndereco, valor) -> dtoEndereco.getCidade().setEstado(valor));
		
		
		
		return mapper;
	}

}
