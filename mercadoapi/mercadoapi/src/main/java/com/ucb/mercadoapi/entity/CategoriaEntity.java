
package com.ucb.mercadoapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "categoria")
@Entity(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    public CategoriaEntity(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
