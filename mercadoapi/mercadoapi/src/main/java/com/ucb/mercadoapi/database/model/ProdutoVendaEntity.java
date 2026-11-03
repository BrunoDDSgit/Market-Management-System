package com.ucb.mercadoapi.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO_VENDA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoVendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO_VENDA")
    private Integer idProdutoVenda;

    @Column(name = "QUANTIDADE",nullable = false)
    private Integer quantidade;

    @Column(name = "PRECO_UNITARIO",nullable = false, precision = 10, scale = 2)
    private BigDecimal precoreUnitario;

    @Column(name = "SUB_TOTAL",nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "ID_VENDA")
    private VendaEntity venda;

}
