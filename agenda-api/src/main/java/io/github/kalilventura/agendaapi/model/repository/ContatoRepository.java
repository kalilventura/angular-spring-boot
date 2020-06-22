package io.github.kalilventura.agendaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.kalilventura.agendaapi.model.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}