package com.uniflix.model.dtos;

import java.time.Instant;

public record CourseDto(
        String id,
        String title,
        String titleEn,
        String departmentId,
        Instant modifiedAt
) {}

