package com.uniflix.api.repository;

import com.uniflix.model.persons.OpUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpUserRepository extends MongoRepository<OpUser, String> {
}
