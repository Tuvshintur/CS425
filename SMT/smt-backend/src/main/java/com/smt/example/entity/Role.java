package com.smt.example.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role @author Turuu
 */

public enum Role implements GrantedAuthority {

    ROLE_CLIENT, ROLE_ADMIN;

    public String getAuthority() {
        return name();
    }

}