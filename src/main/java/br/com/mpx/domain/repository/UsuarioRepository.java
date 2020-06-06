package br.com.mpx.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.mpx.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

}