package com.uniflix.model.security;

public enum AccessOptions {

    OPEN,
    SSO,
    PASSCODE,
    ECLASS;

    public String value() {
        return name();
    }
    @SuppressWarnings("unused")
    public static AccessOptions fromValue(String v) {
        return valueOf(v);
    }
}