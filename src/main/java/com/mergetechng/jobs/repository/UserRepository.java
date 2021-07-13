package com.mergetechng.jobs.repository;


import com.mergetechng.jobs.entities.UserJpa;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<UserJpa, String> {
//    public Customer findByUsername(String firstName);
//    public List<Customer> findByLastName(String lastName);
}