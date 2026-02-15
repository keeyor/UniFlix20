package com.uniflix.api.components;

import com.uniflix.model.dtos.InstitutionDto;
import com.uniflix.model.ref.StructureType;
import com.uniflix.model.structures.Institution;
import org.springframework.stereotype.Component;

@Component
public class InstitutionMapper {

    public InstitutionDto toDto(Institution institution) {
        return new InstitutionDto(
                institution.getId(),
                institution.getTitle(),
                institution.getTitleEn(),
                institution.getIdentity(),
                StructureType.INSTITUTION
        );
    }
}

