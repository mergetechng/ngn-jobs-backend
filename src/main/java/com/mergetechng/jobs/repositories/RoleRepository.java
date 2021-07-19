package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.Group1;
import com.mergetechng.jobs.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRoleId(String roleId);
}
