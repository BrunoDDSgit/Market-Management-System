
package com.ucb.mercadoapi.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProdutoDTO {
    private String nome;
    private String descricao;
    private String codigoBarras;
    private Integer quantidade;
    private BigDecimal preco;
    private BigDecimal desconto;
    private LocalDate validade;
    private Integer categoriaId;
}
