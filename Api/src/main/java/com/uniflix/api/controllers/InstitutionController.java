package com.uniflix.api.controllers;

import com.uniflix.api.components.InstitutionMapper;
import com.uniflix.api.data.CreateInstitutionRequest;
import com.uniflix.api.data.PageResponse;
import com.uniflix.api.data.UpdateInstitutionRequest;
import com.uniflix.api.services.InstitutionService;
import com.uniflix.model.dtos.InstitutionDto;
import com.uniflix.model.structures.Institution;
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
@RequestMapping("/api/institutions")
@Validated
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService service;
    private final InstitutionMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionDto> getById(@PathVariable String id) {

        Institution institution = service.findById(id);

        InstitutionDto dto = mapper.toDto(institution);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<PageResponse<InstitutionDto>> get(Pageable pageable)
    {

        Page<Institution> result = service.findAll(pageable);

        List<InstitutionDto> dtoList = result.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();

        PageResponse<InstitutionDto> response = new PageResponse<>(
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
    public ResponseEntity<InstitutionDto> create(
            @Valid @RequestBody CreateInstitutionRequest request
    ) {

        Institution institution = new Institution();
        institution.setTitle(request.title());
        institution.setTitleEn(request.titleEn());
        institution.setIdentity(request.identity());

        Institution saved = service.save(institution);

        InstitutionDto dto = mapper.toDto(saved);

        URI location = URI.create("/api/institutions/" + saved.getId());

        return ResponseEntity
                .created(location)
                .body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionDto> update(
            @PathVariable String id,
            @Valid @RequestBody UpdateInstitutionRequest request
    ) {

        Institution updated = service.update(
                id,
                request.title(),
                request.titleEn(),
                request.identity()
        );

        InstitutionDto dto = mapper.toDto(updated);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

