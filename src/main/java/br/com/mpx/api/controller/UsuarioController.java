package br.com.mpx.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mpx.api.mapper.DTOUsuarioInputMapper;
import br.com.mpx.api.mapper.DTOUsuarioMapper;
import br.com.mpx.api.model.DTOUsuario;
import br.com.mpx.api.model.input.DTOSenhaInput;
import br.com.mpx.api.model.input.DTOUsuarioComSenhaInput;
import br.com.mpx.api.model.input.DTOUsuarioInput;
import br.com.mpx.domain.model.Usuario;
import br.com.mpx.domain.repository.UsuarioRepository;
import br.com.mpx.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CadastroUsuarioService cadastroUsuario;
    
    @Autowired
    private DTOUsuarioMapper mapper;
    
    @Autowired
    private DTOUsuarioInputMapper mapperInput;
    
    @GetMapping
    public List<DTOUsuario> listar() {
        List<Usuario> todasUsuarios = usuarioRepository.findAll();
        
        return mapper.listaEntidadeToListaDto(todasUsuarios);
    }
    
    @GetMapping("/{usuarioId}")
    public DTOUsuario buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
        
        return mapper.entidadeToDto(usuario);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DTOUsuario adicionar(@RequestBody @Valid DTOUsuarioComSenhaInput usuarioInput) {
        Usuario usuario = mapperInput.dtoToEntidade(usuarioInput);
        usuario = cadastroUsuario.salvar(usuario);
        
        return mapper.entidadeToDto(usuario);
    }
    
    @PutMapping("/{usuarioId}")
    public DTOUsuario atualizar(@PathVariable Long usuarioId,
            @RequestBody @Valid DTOUsuarioInput usuarioInput) {
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        mapperInput.copiarToEntidade(usuarioInput, usuarioAtual);
        usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
        
        return mapper.entidadeToDto(usuarioAtual);
    }
    
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid DTOSenhaInput senha) {
        cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }            
}
