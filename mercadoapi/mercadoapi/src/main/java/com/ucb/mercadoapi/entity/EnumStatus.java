package com.ucb.mercadoapi.entity;

public enum EnumStatus {
    ATIVO(1),
    INATIVO(2);

    private int value;

    EnumStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
