
package com.ucb.mercadoapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaDTO {
    private String nome;
    private String descricao;
}
