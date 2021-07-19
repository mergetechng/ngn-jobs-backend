package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.Privilege;
import com.mergetechng.jobs.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PrivilegeRepository extends MongoRepository<Privilege, String> {
    Optional<Privilege> findByPrivilegeId(String privilegeId);
}
