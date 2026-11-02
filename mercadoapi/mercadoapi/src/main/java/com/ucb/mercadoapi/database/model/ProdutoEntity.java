package com.ucb.mercadoapi.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;

    @Column(name = "NOME_PRODUTO", nullable = false, length = 100)
    private String nomeProduto;

    @Column(name = "DESCRICAO_PRODUTO", nullable = false, length = 250)
    private String descricaoProduto;

    @Column(name = "CODIGO_BARRAS", nullable = false, unique = true, length = 50)
    private String codigoBarras;

    @Column(name = "QUANTIDADE_ESTOQUE", nullable = false)
    private Integer quantidadeEstoque;

    @Column(name = "PRECO_ATUAL_PRODUTO", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoAtualProduto;

    @ManyToOne
    @JoinColumn
    private CategoriaEntity categoria;
}
