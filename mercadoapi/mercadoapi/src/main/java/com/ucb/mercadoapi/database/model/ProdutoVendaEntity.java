package com.ucb.mercadoapi.database.model;

import jakarta.persistence.*;
import lombok.*;

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


}
