package com.mergetechng.jobs.api;

import com.mergetechng.jobs.commons.dto.UserAccountUpdateDto;
import com.mergetechng.jobs.commons.dto.UserDto;
import com.mergetechng.jobs.commons.dto.UserResetPasswordDto;
import com.mergetechng.jobs.commons.dto.UserUpdatePasswordDto;
import com.mergetechng.jobs.exceptions.PasswordMismatchedException;
import com.mergetechng.jobs.exceptions.UserNotFoundException;
import com.mergetechng.jobs.exceptions.UserTokenException;

import java.util.List;

public interface IUser{
    boolean logoutUser(String username);

    com.mergetechng.jobs.entities.User getUserByUsername(String username);

    List<com.mergetechng.jobs.entities.User> getUserByFirstNameAndLastName(String firstName, String lastName);

    boolean createNewUser(com.mergetechng.jobs.entities.User user) throws Exception;

    boolean userExists(String username, String email);

    String disabledUser(String username);

    String enableUser(String username);

    String updateBasicAccountInformation(UserAccountUpdateDto userAccountUpdateDto, String usernameOrEnamilOrUserId);

    com.mergetechng.jobs.entities.User getUser(String usernameOrEmailOrUserId);

    List<UserDto> filterSearchUser(String searchBy, Integer limit, Integer offset, String operation, String operationValue, String order);

    boolean validateToken(String token, String email) throws UserTokenException ;

    boolean verifyUserEmail(String email, String token) throws Exception;

    boolean deleteUser(String username);

    boolean forgotPassword(String emailOrUsername) throws Exception;

    boolean sendUserEmailVerification(String emailOrUsername) throws Exception;

    void updateUser(com.mergetechng.jobs.entities.User user);

    boolean updateUserPassword(UserUpdatePasswordDto passwordUpdate, String usernameOrEmailOrUserId) throws PasswordMismatchedException ;

    boolean resetUserPassword(String token ,UserResetPasswordDto userResetPasswordDto) throws UserNotFoundException, PasswordMismatchedException ;

}