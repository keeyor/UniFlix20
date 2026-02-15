package com.uniflix.api.services;

import com.uniflix.api.exceptions.DepartmentNotFoundException;
import com.uniflix.api.repository.DepartmentRepository;
import com.uniflix.model.structures.Department;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    // ------------------------------------------------
    // PAGINATED QUERY (Cache per page + size)
    // ------------------------------------------------
    @Cacheable(
            value = "departmentPage",
            key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()"
    )
    public Page<Department> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // ------------------------------------------------
    // FIND BY ID (Safe to cache individually)
    // ------------------------------------------------
    @Cacheable(value = "departmentById", key = "#id", unless = "#result == null")
    public Department findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    // ------------------------------------------------
    // SAVE (CREATE OR UPDATE)
    // ------------------------------------------------
    @Caching(evict = {
            @CacheEvict(value = "departmentById", key = "#result.id", condition = "#result != null"),
            @CacheEvict(value = "departmentPage", allEntries = true)
    })
    public Department save(Department department) {
        return repository.save(department);
    }

    @Caching(evict = {
            @CacheEvict(value = "departmentPage", allEntries = true)
    })
    public Department update(String id, String title, String titleEn, String identity) {

        Department existing = repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        existing.setTitle(title);
        existing.setTitleEn(titleEn);
        existing.setIdentity(identity);

        return save(existing); // uses your cached save()
    }

    // ------------------------------------------------
    // DELETE
    // ------------------------------------------------
    @Caching(evict = {
            @CacheEvict(value = "departmentById", key = "#id"),
            @CacheEvict(value = "departmentPage", allEntries = true)
    })
    public void delete(String id) {

        if (!repository.existsById(id)) {
            throw new DepartmentNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
