package com.ucb.mercadoapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Cargo")
@Entity(name = "Cargo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CargoEntity {
    @Id
    private int id;
    private String nome;
    private Short permissaAdmin;

    public CargoEntity(EnumCargo enumCargo) {
        this.id = enumCargo.getValue();
    }
}
