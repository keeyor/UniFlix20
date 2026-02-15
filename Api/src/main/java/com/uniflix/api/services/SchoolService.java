package com.uniflix.api.services;

import com.uniflix.api.exceptions.DepartmentNotFoundException;
import com.uniflix.api.exceptions.SchoolNotFoundException;
import com.uniflix.api.repository.SchoolRepository;
import com.uniflix.model.structures.School;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class SchoolService {

    private final SchoolRepository repository;

    public SchoolService(SchoolRepository repository) {
        this.repository = repository;
    }

    // ------------------------------------------------
    // PAGINATED QUERY (Cache per page + size)
    // ------------------------------------------------
    @Cacheable(
            value = "schoolPage",
            key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()"
    )
    public Page<School> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // ------------------------------------------------
    // FIND BY ID (Safe to cache individually)
    // ------------------------------------------------
    @Cacheable(value = "schoolById", key = "#id", unless = "#result == null")
    public School findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new SchoolNotFoundException(id));
    }

    // ------------------------------------------------
    // SAVE (CREATE OR UPDATE)
    // ------------------------------------------------
    @Caching(evict = {
            @CacheEvict(value = "schoolById", key = "#result.id", condition = "#result != null"),
            @CacheEvict(value = "schoolPage", allEntries = true)
    })
    public School save(School school) {
        return repository.save(school);
    }

    @Caching(evict = {
            @CacheEvict(value = "schoolPage", allEntries = true)
    })
    public School update(String id, String title, String titleEn, String identity) {

        School existing = repository.findById(id)
                .orElseThrow(() -> new SchoolNotFoundException(id));

        existing.setTitle(title);
        existing.setTitleEn(titleEn);
        existing.setIdentity(identity);

        return save(existing); // uses your cached save()
    }

    // ------------------------------------------------
    // DELETE
    // ------------------------------------------------
    @Caching(evict = {
            @CacheEvict(value = "schoolById", key = "#id"),
            @CacheEvict(value = "schoolPage", allEntries = true)
    })
    public void delete(String id) {

        if (!repository.existsById(id)) {
            throw new DepartmentNotFoundException(id);
        }

        repository.deleteById(id);
    }
}
