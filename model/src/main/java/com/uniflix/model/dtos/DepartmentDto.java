package com.uniflix.model.dtos;


import com.uniflix.model.ref.StructureType;

public record DepartmentDto(
        String id,
        String title,
        String titleEn,
        String identity,
        StructureType structureType
) {}

