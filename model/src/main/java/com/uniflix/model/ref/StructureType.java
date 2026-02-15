package com.uniflix.model.ref;

public enum StructureType {

    INSTITUTION,
    SCHOOL,
    DEPARTMENT,
    OTHER;

    public String value() {
        return name();
    }

    @SuppressWarnings("unused")
    public static StructureType fromValue(String v) {
        return valueOf(v);
    }

}
