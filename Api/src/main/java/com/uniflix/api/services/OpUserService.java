package com.uniflix.api.services;

import com.uniflix.api.repository.OpUserRepository;
import com.uniflix.model.persons.OpUser;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OpUserService {

    private final OpUserRepository repository;

    public OpUserService(OpUserRepository repository) {
        this.repository = repository;
    }

    // ------------------------------------------------
    // PAGINATED QUERY (Cache per page + size)
    // ------------------------------------------------
    @Cacheable(
            value = "userPage",
            key = "#page + '-' + #size"
    )
    public Page<OpUser> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    // ------------------------------------------------
    // FIND BY ID (Safe to cache individually)
    // ------------------------------------------------
    @Cacheable(value = "userById", key = "#id", unless = "#result == null")
    public OpUser findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ------------------------------------------------
    // SAVE (CREATE OR UPDATE)
    // ------------------------------------------------
    @Caching(evict = {
            @CacheEvict(value = "userById", key = "#result.id", condition = "#result != null"),
            @CacheEvict(value = "userPage", allEntries = true)
    })
    public OpUser save(OpUser opUser) {
        return repository.save(opUser);
    }

    // ------------------------------------------------
    // DELETE
    // ------------------------------------------------
    @Caching(evict = {
            @CacheEvict(value = "institutionById", key = "#id"),
            @CacheEvict(value = "userPage", allEntries = true)
    })
    public void delete(String id) {
        repository.deleteById(id);
    }
}
