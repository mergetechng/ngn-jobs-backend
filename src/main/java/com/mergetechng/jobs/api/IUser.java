package com.mergetechng.jobs.api;

import com.mergetechng.jobs.commons.dto.UserAccountUpdateDto;
import com.mergetechng.jobs.commons.dto.UserDto;
import com.mergetechng.jobs.commons.dto.UserUpdatePasswordDto;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.exceptions.PasswordMismatchedException;

import java.util.List;

public interface IUser {
    boolean logoutUser(String username);

    User getUserByUsername(String username);

    List<User> getUserByFirstNameAndLastName(String firstName, String lastName);

    boolean createNewUser(User user);

    boolean userExists(String username, String email);

    String disabledUser(String username);

    String updateBasicAccountInformation(UserAccountUpdateDto userAccountUpdateDto, String usernameOrEnamilOrUserId);

    User getUser(String usernameOrEmailOrUserId);

    List<UserDto> filterSearchUser(String searchBy, Integer limit, Integer offset, String operation, String operationValue, String order);

    boolean validateToken(String token) throws Exception;

    boolean verifyUserEmail(String email, String token);

    boolean deleteUser(String username);

    boolean forgotPassword(String emailOrUsername);

    boolean sendUserEmailVerification(String emailOrUsername);

    void updateUser(User user);

    boolean updateUserPassword(UserUpdatePasswordDto passwordUpdate, String usernameOrEmailOrUserId) throws PasswordMismatchedException ;

}