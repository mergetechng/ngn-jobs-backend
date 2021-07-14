package com.mergetechng.jobs.services;

import com.mergetechng.jobs.api.IUser;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobsUserService implements IUser {

    private final UserRepository userRepository ;
    public void logInUser(String username) {
        Optional<User> userOptional= Optional.ofNullable(this.userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            userOptional.get().setOnline(true);
            this.userRepository.save(userOptional.get());
        }
    }

    public JobsUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public boolean logoutUser(String username) {
        User user = this.userRepository.findByUsername(username);
        if (user !=null){
            user.setOnline(false);
            return true;
        }
        return false;
    }

    public User getUserByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

    public List<User> getUserByFirstNameAndLastName(String firstName , String lastName) {
        return this.userRepository.findByFirstNameAndLastName(firstName , lastName);
    }
}