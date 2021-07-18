package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.Privilege;
import com.mergetechng.jobs.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrivilegeRepository extends MongoRepository<Privilege, String> {
}
