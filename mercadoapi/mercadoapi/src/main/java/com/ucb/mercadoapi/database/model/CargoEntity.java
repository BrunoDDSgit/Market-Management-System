package com.ucb.mercadoapi.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CARGO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CargoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARGO")
    private Integer idCargo;

    @Column(nullable = false, length = 50, name = "NOME_CARGO", unique = true)
    private String nomeCargo;

    @Column(nullable = false, name = "PERMISSAO_ADMINISTRADOR")
    private Boolean permissaoAministrador;

    @OneToMany(mappedBy = "cargo")
    private Set<UsuarioEntity> usuarios = new HashSet<UsuarioEntity>();
}
