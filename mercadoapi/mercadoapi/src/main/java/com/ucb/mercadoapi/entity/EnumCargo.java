package com.ucb.mercadoapi.entity;

public enum EnumCargo {
    USER(1, (short)0),
    ADMIN(2, (short)1);

    private int value;
    private short permissa_admin;

    EnumCargo(int value, short permissao) {
        this.value = value;
        this.permissa_admin = permissao;
    }

    public int getValue() {
        return value;
    }

    public short getPermissaoAdmin() {
        return permissa_admin;
    }
}
