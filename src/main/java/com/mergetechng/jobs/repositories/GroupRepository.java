package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.Group1;
import com.mergetechng.jobs.entities.Privilege;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends MongoRepository<Group1, String> {
    List<Group1> findAllByGroupId(String groupId);
    Optional<Group1> findById(String groupId);
}
