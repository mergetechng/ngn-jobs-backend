package com.mergetechng.jobs.api;

import com.mergetechng.jobs.entities.User;

import java.util.List;

public interface IUser {
    boolean logoutUser(String username);
    User getUserByUsername(String username);
    List<User> getUserByFirstNameAndLastName(String firstName , String lastName);
}