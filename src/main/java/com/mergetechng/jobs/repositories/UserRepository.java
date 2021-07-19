package com.mergetechng.jobs.repositories;


import com.mergetechng.jobs.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findFirstByEmail(String username);
    boolean existsByUsernameOrEmail(String username , String email);
    User findByDateRegistered(String dateRegistered);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    void deleteByUsername(String username);
    User findFirstByUsernameOrEmailOrUserId(String username, String email , String userId);
}