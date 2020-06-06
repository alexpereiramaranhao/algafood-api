package br.com.mpx.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mpx.api.model.input.DTOUsuarioInput;
import br.com.mpx.domain.model.Usuario;

@Component
public class DTOUsuarioInputMapper implements DTOMapper<DTOUsuarioInput, Usuario> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Usuario dtoToEntidade(DTOUsuarioInput dtoUsuario) {
		return modelMapper.map(dtoUsuario, Usuario.class);		
	}
	
	@Override
	public DTOUsuarioInput entidadeToDto(Usuario usuario) {
		return modelMapper.map(usuario, DTOUsuarioInput.class);
	}
	
	@Override
	public List<DTOUsuarioInput> listaEntidadeToListaDto(List<Usuario> usuarios){
		return usuarios.stream()
				.map(usuario -> entidadeToDto(usuario))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Usuario> listaDtoToListaEntidade(List<DTOUsuarioInput> dtoUsuarios){
		return dtoUsuarios.stream()
				.map(dto -> this.dtoToEntidade(dto))
				.collect(Collectors.toList());
	}
	
	public void copiarToEntidade(DTOUsuarioInput usuarioInput, Usuario usuario) {		
		modelMapper.map(usuarioInput, usuario);
	}

}
