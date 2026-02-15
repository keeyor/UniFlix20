package com.uniflix.api.controllers;

import com.uniflix.api.components.SchoolMapper;
import com.uniflix.api.data.CreateSchoolRequest;
import com.uniflix.api.data.PageResponse;
import com.uniflix.api.data.UpdateSchoolRequest;
import com.uniflix.api.services.SchoolService;
import com.uniflix.model.dtos.SchoolDto;
import com.uniflix.model.structures.School;
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
@RequestMapping("/api/schools")
@Validated
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService service;
    private final SchoolMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDto> getById(@PathVariable String id) {

        School school = service.findById(id);

        SchoolDto dto = mapper.toDto(school);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<PageResponse<SchoolDto>> get(Pageable pageable)
    {

        Page<School> result = service.findAll(pageable);

        List<SchoolDto> dtoList = result.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();

        PageResponse<SchoolDto> response = new PageResponse<>(
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
    public ResponseEntity<SchoolDto> create(
            @Valid @RequestBody CreateSchoolRequest request
    ) {

        School school = new School();
        school.setTitle(request.title());
        school.setTitleEn(request.titleEn());
        school.setIdentity(request.identity());

        School saved = service.save(school);

        SchoolDto dto = mapper.toDto(saved);

        URI location = URI.create("/api/departments/" + saved.getId());

        return ResponseEntity
                .created(location)
                .body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolDto> update(
            @PathVariable String id,
            @Valid @RequestBody UpdateSchoolRequest request
    ) {

        School updated = service.update(
                id,
                request.title(),
                request.titleEn(),
                request.identity()
        );

        SchoolDto dto = mapper.toDto(updated);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

