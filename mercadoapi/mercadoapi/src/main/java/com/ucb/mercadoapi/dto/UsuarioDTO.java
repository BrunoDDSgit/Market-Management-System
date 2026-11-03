package com.ucb.mercadoapi.dto;

import com.ucb.mercadoapi.database.model.CargoEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.NotFound;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UsuarioDTO {
    @NotBlank // nem nula nem vazia
    private String nome;
    @NotBlank
    private String email;
    private String telefone;
    @NotBlank
    private String senha;
    @NotNull
    private Boolean statusUsuario;
    @NotNull
    private LocalDateTime dataCadastro;
}
