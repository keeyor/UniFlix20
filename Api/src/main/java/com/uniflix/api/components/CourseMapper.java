package com.uniflix.api.components;

import com.uniflix.model.dtos.CourseDto;
import com.uniflix.model.resources.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDto toDto(Course course) {
        return new CourseDto(
                course.getId(),
                course.getTitle(),
                course.getTitleEn(),
                course.getDepartmentId(),
                course.getDateModified()
        );
    }
}

