package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.Group1;
import com.mergetechng.jobs.entities.Privilege;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group1, String> {
}
