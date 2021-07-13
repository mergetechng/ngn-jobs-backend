package com.mergetechng.jobs.repository;


import com.mergetechng.jobs.entities.UserCv;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface UserRepository extends MongoRepository<UserCv, String> {
//    public Customer findByUsername(String firstName);
//    public List<Customer> findByLastName(String lastName);
}