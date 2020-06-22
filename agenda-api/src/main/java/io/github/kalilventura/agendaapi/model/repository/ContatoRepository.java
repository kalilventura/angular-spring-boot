package io.github.kalilventura.agendaapi.model.repository;

import io.github.kalilventura.agendaapi.model.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {
}