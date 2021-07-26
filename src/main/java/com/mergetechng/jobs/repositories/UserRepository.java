package com.mergetechng.jobs.repositories;


import com.mergetechng.jobs.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends ResourceRepository<User, String> {
    User findByUsername(String username);
    User findFirstByEmail(String username);
    boolean existsByUsernameOrEmail(String username , String email);
    boolean deleteByUsernameOrEmailOrUserId(String usernameOrEmailOrUserId);
    User findByDateRegistered(String dateRegistered);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    void deleteByUsername(String username);
    User findByUsernameOrEmailOrId(String uei, String uei1, String uei2);
}