package com.uniflix.api.controllers;

import com.uniflix.api.components.DepartmentMapper;
import com.uniflix.api.data.CreateDepartmentRequest;
import com.uniflix.api.data.PageResponse;
import com.uniflix.api.data.UpdateDepartmentRequest;
import com.uniflix.api.services.DepartmentService;
import com.uniflix.model.dtos.DepartmentDto;
import com.uniflix.model.structures.Department;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Validated
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;
    private final DepartmentMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getById(@PathVariable String id) {

        Department department = service.findById(id);

        DepartmentDto dto = mapper.toDto(department);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<PageResponse<DepartmentDto>> get(Pageable pageable)
    {

        Page<Department> result = service.findAll(pageable);

        List<DepartmentDto> dtoList = result.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();

        PageResponse<DepartmentDto> response = new PageResponse<>(
                dtoList,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> create(
            @Valid @RequestBody CreateDepartmentRequest request
    ) {

        Department department = new Department();
        department.setTitle(request.title());
        department.setTitleEn(request.titleEn());
        department.setIdentity(request.identity());

        Department saved = service.save(department);

        DepartmentDto dto = mapper.toDto(saved);

        URI location = URI.create("/api/departments/" + saved.getId());

        return ResponseEntity
                .created(location)
                .body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> update(
            @PathVariable String id,
            @Valid @RequestBody UpdateDepartmentRequest request
    ) {

        Department updated = service.update(
                id,
                request.title(),
                request.titleEn(),
                request.identity()
        );

        DepartmentDto dto = mapper.toDto(updated);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

