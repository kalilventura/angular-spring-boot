package br.com.github.kalilventura.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.kalilventura.clientes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}