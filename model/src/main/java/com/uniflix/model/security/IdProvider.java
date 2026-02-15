package com.uniflix.model.security;

public enum IdProvider {

    SSO_USER,
    LOCAL_USER;

    public String value() {
        return name();
    }
    @SuppressWarnings("unused")
    public static IdProvider fromValue(String v) {
        return valueOf(v);
    }
}
