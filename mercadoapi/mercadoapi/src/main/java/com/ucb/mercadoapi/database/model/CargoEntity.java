package com.ucb.mercadoapi.database.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cargo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CargoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50, name = "NOME_CARGO", unique = true)
    private String nomeCargo;

    @Column(nullable = false, name = "PERMISSAO_ADMINISTRADOR")
    private Boolean permissaoAministrador;
}
