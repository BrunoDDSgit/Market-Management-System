package com.ucb.mercadoapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Table(name = "Status")
@Entity(name = "Status")
@NoArgsConstructor
@AllArgsConstructor
public class StatusEntity {
    @Id
    private int id;
    private String nome;

    StatusEntity(EnumStatus status) {
        this.id = status.getValue();
    }
}
