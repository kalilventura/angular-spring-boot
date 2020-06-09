package br.com.github.kalilventura.clientes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.github.kalilventura.clientes.model.entity.ServicoPrestado;

public interface ServicoRepository extends JpaRepository<ServicoPrestado, Integer> {

}