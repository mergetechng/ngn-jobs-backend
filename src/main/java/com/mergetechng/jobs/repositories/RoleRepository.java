package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
