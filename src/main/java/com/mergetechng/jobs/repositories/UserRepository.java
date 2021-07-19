package com.mergetechng.jobs.repositories;


import com.mergetechng.jobs.entities.User;
import org.apache.el.stream.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    boolean existsByUsernameOrEmail(String username , String email);
    User findByDateRegistered(String dateRegistered);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    void deleteByUsername(String username);
    User findByUsernameOrEmailOrUserId(String usernameOrEmail);
}