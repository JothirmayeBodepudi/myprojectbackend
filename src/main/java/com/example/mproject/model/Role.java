package com.example.mproject.model;


public enum Role {
    USER(1),
    ADMIN(2);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public static Role fromInt(int i) {
        for (Role role : Role.values()) {
            if (role.getValue() == i) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + i);
    }

    public int getValue() {
        return value;
    }
}
