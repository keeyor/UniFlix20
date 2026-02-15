package com.uniflix.api.components;

import com.uniflix.model.dtos.DepartmentDto;
import com.uniflix.model.ref.StructureType;
import com.uniflix.model.structures.Department;
import org.springframework.stereotype.Component;


@Component
public class DepartmentMapper {

    public DepartmentDto toDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getTitle(),
                department.getTitleEn(),
                department.getIdentity(),
                StructureType.DEPARTMENT
        );
    }
}

