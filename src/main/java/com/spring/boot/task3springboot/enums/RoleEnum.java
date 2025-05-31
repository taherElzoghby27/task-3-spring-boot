package com.spring.boot.task3springboot.enums;

public enum RoleEnum {
    USER("USER"), MANAGER("MANAGER"), ADMIN("ADMIN");
    private String role;

    RoleEnum() {
    }

    RoleEnum(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
