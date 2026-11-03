package com.ucb.mercadoapi.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // cuida da geração de ID de forma automática
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, length = 150, unique = true)
    private String email;
    @Column(nullable = true, length = 15)
    private String telefone;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false, name = "Status_Usuario")
    private Boolean statusUsuario;
    @Column(nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name= "ID_CARGO")
    private CargoEntity cargo;
}
