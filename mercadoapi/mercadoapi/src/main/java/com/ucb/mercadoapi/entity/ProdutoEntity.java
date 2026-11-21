
package com.ucb.mercadoapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "produto")
@Entity(name = "produto")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "quantidade_estoque")
    private Integer quantidade;

    private BigDecimal preco;

    private BigDecimal desconto;

    private LocalDate validade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

    public ProdutoEntity(String nome, String descricao, String codigoBarras, Integer quantidade, BigDecimal preco, BigDecimal desconto, LocalDate validade, CategoriaEntity categoria
    ) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigoBarras = codigoBarras;
        this.quantidade = quantidade;
        this.preco = preco;
        this.desconto = desconto;
        this.validade = validade;
        this.categoria = categoria;
    }
}
