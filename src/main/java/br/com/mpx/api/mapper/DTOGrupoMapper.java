package br.com.mpx.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mpx.api.model.DTOGrupo;
import br.com.mpx.api.model.input.DTOGrupoInput;
import br.com.mpx.domain.model.Grupo;

@Component
public class DTOGrupoMapper implements DTOMapper<DTOGrupo, Grupo> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Grupo dtoToEntidade(DTOGrupo dtoGrupo) {
		return modelMapper.map(dtoGrupo, Grupo.class);		
	}
	
	@Override
	public DTOGrupo entidadeToDto(Grupo grupo) {
		return modelMapper.map(grupo, DTOGrupo.class);
	}
	
	@Override
	public List<DTOGrupo> listaEntidadeToListaDto(List<Grupo> grupos){
		return grupos.stream()
				.map(grupo -> entidadeToDto(grupo))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Grupo> listaDtoToListaEntidade(List<DTOGrupo> dtoGrupos){
		return dtoGrupos.stream()
				.map(dto -> this.dtoToEntidade(dto))
				.collect(Collectors.toList());
	}
	
	public void copiarToEntidade(DTOGrupoInput grupoInput, Grupo grupo) {		
		modelMapper.map(grupoInput, grupo);
	}

}
