package br.com.github.kalilventura.clientes.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    O lombok gera em tempo de compilação os Getters e Setters com as anotations @Getter e @Setter
    Também podemos utilizar a @Data, onde ele cria o  ToString, @EqualsAndHashCode, @Getter, @Setter e
    @RequiredArgsConstructor
    Obs: Precisamos de um plugin na IDE para utilizar em tempo de desenvolvimento o lombok
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column(name = "data_cadastro", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    /*
     * Antes de persistir o dado no banco, ele será executada
     */
    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDate.now());
    }
}