package com.mergetechng.jobs.api;

import com.mergetechng.jobs.commons.dto.UserAccountUpdateDto;
import com.mergetechng.jobs.commons.dto.UserDto;
import com.mergetechng.jobs.commons.dto.UserResetPasswordDto;
import com.mergetechng.jobs.commons.dto.UserUpdatePasswordDto;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.exceptions.PasswordMismatchedException;
import com.mergetechng.jobs.exceptions.UserNotFoundException;
import com.mergetechng.jobs.exceptions.UserTokenException;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface IUser {
    boolean logoutUser(String username);

    User getUserByUsername(String username);

    List<User> getUserByFirstNameAndLastName(String firstName, String lastName);

    boolean createNewUser(User user) throws Exception;

    boolean userExists(String username, String email);

    String disabledUser(String username);

    String enableUser(String username);

    String updateBasicAccountInformation(UserAccountUpdateDto userAccountUpdateDto, String usernameOrEnamilOrUserId);

    User getUser(String usernameOrEmailOrUserId);

    List<UserDto> filterSearchUser(String searchBy, Integer limit, Integer offset, String operation, String operationValue, String order);

    boolean validateToken(String token, String email) throws UserTokenException ;

    boolean verifyUserEmail(String email, String token) throws Exception;

    boolean deleteUser(String username);

    boolean forgotPassword(String emailOrUsername) throws Exception;

    boolean sendUserEmailVerification(String emailOrUsername) throws Exception;

    void updateUser(User user);

    boolean updateUserPassword(UserUpdatePasswordDto passwordUpdate, String usernameOrEmailOrUserId) throws PasswordMismatchedException ;

    /**
     * @param query custom query
     * @return list of Employee
     */
    List<User> getAll(Query query);

    /**
     * Get all custom paginate data for entity Employee
     *
     * @param query    custom query
     * @param pageable pageable param
     * @return Page of entity Employee
     */
    Page<User> getPage(Query query, Pageable pageable);

    boolean resetUserPassword(String token ,UserResetPasswordDto userResetPasswordDto) throws UserNotFoundException, PasswordMismatchedException ;
}