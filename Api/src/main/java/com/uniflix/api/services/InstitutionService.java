package com.uniflix.api.services;

import com.uniflix.api.exceptions.InstitutionNotFoundException;
import com.uniflix.api.repository.InstitutionRepository;
import com.uniflix.model.structures.Institution;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstitutionService {

    private final InstitutionRepository repository;

    public InstitutionService(InstitutionRepository repository) {
        this.repository = repository;
    }

    // ------------------------------------------------
    // PAGINATED QUERY (Cache per page + size)
    // ------------------------------------------------
    @Cacheable(
            value = "institutionPage",
            key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort.toString()"
    )
    public Page<Institution> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // ------------------------------------------------
    // FIND BY ID (Safe to cache individually)
    // ------------------------------------------------
    @Cacheable(value = "institutionById", key = "#id", unless = "#result == null")
    public Institution findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new InstitutionNotFoundException(id));
    }

    // ------------------------------------------------
    // SAVE (CREATE OR UPDATE)
    // ------------------------------------------------
    @Caching(evict = {
            @CacheEvict(value = "institutionById", key = "#result.id", condition = "#result != null"),
            @CacheEvict(value = "institutionPage", allEntries = true)
    })
    public Institution save(Institution institution) {
        return repository.save(institution);
    }

    @Caching(evict = {
            @CacheEvict(value = "institutionPage", allEntries = true)
    })
    public Institution update(String id, String title, String titleEn, String identity) {

        Institution existing = repository.findById(id)
                .orElseThrow(() -> new InstitutionNotFoundException(id));

        existing.setTitle(title);
        existing.setTitleEn(titleEn);
        existing.setIdentity(identity);

        return save(existing); // uses your cached save()
    }

    // ------------------------------------------------
    // DELETE
    // ------------------------------------------------
    @Caching(evict = {
            @CacheEvict(value = "institutionById", key = "#id"),
            @CacheEvict(value = "institutionPage", allEntries = true)
    })
    public void delete(String id) {

        if (!repository.existsById(id)) {
            throw new InstitutionNotFoundException(id);
        }

        repository.deleteById(id);
    }
}
