
package com.ucb.mercadoapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String cargo;
}
