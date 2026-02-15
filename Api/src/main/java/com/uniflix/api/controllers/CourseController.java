package com.uniflix.api.controllers;

import com.uniflix.api.components.CourseMapper;
import com.uniflix.api.data.CreateCourseRequest;
import com.uniflix.api.data.PageResponse;
import com.uniflix.api.data.UpdateCourseRequest;
import com.uniflix.api.services.CourseService;
import com.uniflix.model.dtos.CourseDto;
import com.uniflix.model.resources.Course;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Validated
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;
    private final CourseMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable String id) {

        Course course = service.findById(id);
        CourseDto dto = mapper.toDto(course);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<PageResponse<CourseDto>> get(
            Pageable pageable
    ) {

        Page<Course> result = service.findAll(pageable);

        List<CourseDto> dtoList = result.getContent()
                .stream()
                .map(mapper::toDto)
                .toList();

        PageResponse<CourseDto> response = new PageResponse<>(
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
    public ResponseEntity<CourseDto> create(
            @Valid @RequestBody CreateCourseRequest request
    ) {

        Course course = new Course();
        course.setTitle(request.title());
        course.setTitleEn(request.titleEn());
        course.setDepartmentId(request.departmentId());
        course.setDateModified(Instant.now());

        Course saved = service.save(course);
        CourseDto dto = mapper.toDto(saved);

        URI location = URI.create("/api/courses/" + saved.getId());

        return ResponseEntity
                .created(location)
                .body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(
            @PathVariable String id,
            @Valid @RequestBody UpdateCourseRequest request
    ) {

        Course updated = service.update(
                id,
                request.title(),
                request.titleEn()
        );

        CourseDto dto = mapper.toDto(updated);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

