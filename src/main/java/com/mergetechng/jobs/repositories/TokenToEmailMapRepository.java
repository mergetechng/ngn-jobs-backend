package com.mergetechng.jobs.repositories;

import com.mergetechng.jobs.entities.TokenToEmailMap;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenToEmailMapRepository extends MongoRepository<TokenToEmailMap, String> {
    void deleteByExpiredIsTrue();
    TokenToEmailMap findByToken( String token);
    void deleteByToken(String tokenToDelete);
}