package com.ucb.mercadoapi.entity;

public enum EnumCargo {
    USER(1),
    ADMIN(2);

    private int value;

    EnumCargo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
