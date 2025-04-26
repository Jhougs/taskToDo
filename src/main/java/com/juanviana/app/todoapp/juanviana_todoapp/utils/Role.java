package com.juanviana.app.todoapp.juanviana_todoapp.utils;

public enum Role {
    ADMIN(1, "MANAGER"),
    USER(2, "ADMIN"),
    MANAGER(3, "USER");

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