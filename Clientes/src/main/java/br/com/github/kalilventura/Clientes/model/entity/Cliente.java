package br.com.github.kalilventura.clientes.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/*
    O lombok gera em tempo de compilação os Getters e Setters com as anotations @Getter e @Setter
    Também podemos utilizar a @Data, onde ele cria o  ToString, @EqualsAndHashCode, @Getter, @Setter e
    @RequiredArgsConstructor
    Obs: Precisamos de um plugin na IDE para utilizar em tempo de desenvolvimento o lombok
 */
@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column
    private LocalDate dataCadastro;
}