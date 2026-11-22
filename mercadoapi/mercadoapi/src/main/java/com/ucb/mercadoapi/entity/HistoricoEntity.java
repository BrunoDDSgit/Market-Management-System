
package com.ucb.mercadoapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "historico")
@Entity(name = "historico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class HistoricoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime horario;

    private String tipoAcao;

    private String usuario;

    private String cargo;

    @Column(length = 500)
    private String mensagem;
}
