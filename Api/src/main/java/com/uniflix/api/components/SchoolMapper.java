package com.uniflix.api.components;

import com.uniflix.model.dtos.SchoolDto;
import com.uniflix.model.ref.StructureType;
import com.uniflix.model.structures.School;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

    public SchoolDto toDto(School school) {
        return new SchoolDto(
                school.getId(),
                school.getTitle(),
                school.getTitleEn(),
                school.getIdentity(),
                StructureType.SCHOOL
        );
    }
}

