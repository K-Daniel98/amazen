package hu.unideb.web.amazen.core.user.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SELLER,BUYER;

    public String getAuthority() {
        return name();
    }

}