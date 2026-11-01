package com.ucb.mercadoapi.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ProdutoDTO {
    private String nome;
    private BigDecimal valor;
    private int quantidade;
}
