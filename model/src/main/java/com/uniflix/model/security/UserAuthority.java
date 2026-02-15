package com.uniflix.model.security;

public enum UserAuthority {

    STAFF_MEMBER,
    MANAGER,
    EVENT_MANAGER,
    SUPPORT,
    STUDENT,
    USER;

    public String value() {
        return name();
    }
    @SuppressWarnings("unused")
    public static UserAuthority fromValue(String v) {
        return valueOf(v);
    }
}