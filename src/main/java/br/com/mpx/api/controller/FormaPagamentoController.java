package br.com.mpx.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mpx.api.mapper.DTOFormaPagamentoMapper;
import br.com.mpx.api.model.DTOFormaPagamento;
import br.com.mpx.api.model.input.DTOFormaPagamentoInput;
import br.com.mpx.domain.model.FormaPagamento;
import br.com.mpx.domain.repository.FormaPagamentoRepository;
import br.com.mpx.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
	@Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    
    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;
    
    @Autowired
    private DTOFormaPagamentoMapper dtoFormaPagamentoMapper;
    
    
    @GetMapping
    public List<DTOFormaPagamento> listar() {
        List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();
        
        return dtoFormaPagamentoMapper.listaEntidadeToListaDto(todasFormasPagamentos);
    }
    
    @GetMapping("/{formaPagamentoId}")
    public DTOFormaPagamento buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
        
        return dtoFormaPagamentoMapper.entidadeToDto(formaPagamento);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DTOFormaPagamento adicionar(@RequestBody @Valid DTOFormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = dtoFormaPagamentoMapper.dtoInputToEntidade(formaPagamentoInput);
        
        formaPagamento = cadastroFormaPagamento.salvar(formaPagamento);
        
        return dtoFormaPagamentoMapper.entidadeToDto(formaPagamento);
    }
    
    @PutMapping("/{formaPagamentoId}")
    public DTOFormaPagamento atualizar(@PathVariable Long formaPagamentoId,
            @RequestBody @Valid DTOFormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
        
        dtoFormaPagamentoMapper.copiarToEntidade(formaPagamentoInput, formaPagamentoAtual);
        
        formaPagamentoAtual = cadastroFormaPagamento.salvar(formaPagamentoAtual);
        
        return dtoFormaPagamentoMapper.entidadeToDto(formaPagamentoAtual);
    }
    
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamento.excluir(formaPagamentoId);	
    }
}
