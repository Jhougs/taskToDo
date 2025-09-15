package com.juanviana.app.todoapp.juanviana_todoapp.utils;

public enum Role {
    ADMIN(1, "ADMIN"),
    USER(2, "USER"),
    MANAGER(3, "MANAGER");

    private final int code;
    private final String name;

    Role(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}