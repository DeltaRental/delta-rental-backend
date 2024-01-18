package com.delta.rental.deltarental.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER,
    ADMIN,
    MODERATOR,
    MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
