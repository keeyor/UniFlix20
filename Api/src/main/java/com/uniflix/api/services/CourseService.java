package com.uniflix.api.services;

import com.uniflix.api.exceptions.CourseNotFoundException;
import com.uniflix.api.repository.CourseRepository;
import com.uniflix.model.resources.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    // ------------------------------------------------
    // PAGINATED QUERY (Cache per page + size)
    // ------------------------------------------------
    @Cacheable(
            value = "coursePage",
            key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()"
    )
    public Page<Course> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // ------------------------------------------------
    // FIND BY ID (Safe to cache individually)
    // ------------------------------------------------
    @Cacheable(value = "courseById", key = "#id", unless = "#result == null")
    public Course findById(String id) {

        return repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    // ------------------------------------------------
    // SAVE (CREATE OR UPDATE)
    // ------------------------------------------------
    @Caching(evict = {
            @CacheEvict(value = "courseById", key = "#result.id", condition = "#result != null"),
            @CacheEvict(value = "coursePage", allEntries = true)
    })
    public Course save(Course course) {
        return repository.save(course);
    }

    @Caching(evict = {
            @CacheEvict(value = "coursePage", allEntries = true)
    })
    public Course update(String id, String title, String titleEn) {

        Course existing = repository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        existing.setTitle(title);
        existing.setTitleEn(titleEn);

        return save(existing); // uses your cached save()
    }

    // ------------------------------------------------
    // DELETE
    // ------------------------------------------------
    @Caching(evict = {
            @CacheEvict(value = "courseById", key = "#id"),
            @CacheEvict(value = "coursePage", allEntries = true)
    })
    public void delete(String id) {

        if (!repository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }

        repository.deleteById(id);
    }
}
