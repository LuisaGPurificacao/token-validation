package br.com.jwt.validation.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ADMIN, MEMBER, EXTERNAL;

    @JsonCreator
    public static UserRole fromString(String value) {
        return UserRole.valueOf(value.toUpperCase());
    }

}
