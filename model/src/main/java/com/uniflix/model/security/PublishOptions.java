package com.uniflix.model.security;

public enum PublishOptions {

    PUBLIC,
    PRIVATE;

    public String value() {
        return name();
    }
    @SuppressWarnings("unused")
    public static PublishOptions fromValue(String v) {
        return valueOf(v);
    }
}
