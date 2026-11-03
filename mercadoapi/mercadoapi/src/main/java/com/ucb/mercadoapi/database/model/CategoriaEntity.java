package com.ucb.mercadoapi.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORIA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private Integer idCategoria;

    @Column(nullable = false, length = 80, unique = true, name = "NOME_CATEGORIA")
    private String nomeCategoria;
    @Column(nullable = false, name = "DESCRICAO_CATEGORIA")
    private String descricaoCategoria;

    @OneToMany(mappedBy = "categoria")
    private Set<ProdutoEntity> produtos = new HashSet<ProdutoEntity>();

}
