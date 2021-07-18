package com.mergetechng.jobs.services;

import com.mergetechng.jobs.api.IUser;
import com.mergetechng.jobs.commons.dto.UserAccountUpdateDto;
import com.mergetechng.jobs.commons.dto.UserDto;
import com.mergetechng.jobs.commons.dto.UserUpdatePasswordDto;
import com.mergetechng.jobs.commons.enums.AccountTypeEnum;
import com.mergetechng.jobs.commons.enums.GroupEnum;
import com.mergetechng.jobs.commons.enums.StatusEnum;
import com.mergetechng.jobs.entities.Group1;
import com.mergetechng.jobs.entities.TokenToEmailMap;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.exceptions.PasswordMismatchedException;
import com.mergetechng.jobs.exceptions.UserNotFoundException;
import com.mergetechng.jobs.repositories.GroupRepository;
import com.mergetechng.jobs.repositories.TokenToEmailMapRepository;
import com.mergetechng.jobs.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class JobsUserService implements IUser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobsUserService.class);
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GroupRepository groupRepository;

    private final UserRepository userRepository;
    @Autowired
    private TokenToEmailMapRepository tokenToEmailMapRepository;

    public void logInUser(String username) {
        Optional<User> userOptional = Optional.ofNullable(this.userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            userOptional.get().setOnline(true);
            this.userRepository.save(userOptional.get());
        }
    }

    public JobsUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public List<User> getUserByFirstNameAndLastName(String firstName, String lastName) {
        return this.userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public boolean createNewUser(User user) {
        if (!userExists(user.getUsername()) && !userExists(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserId(UUID.randomUUID().toString());
            user.setDateCreated(new Date());
            user.setAccountType(AccountTypeEnum.JOB_APPLICANT.name());
            userRepository.save(user);
            addUserToGroup(user, GroupEnum.JOB_APPLICANT_GROUP.name());
            sendUserEmailVerification(user.getEmail());
            LOGGER.info("Created new User: {}", user.toString());
            return true;
        }
        return false;
    }

    private void addUserToGroup(User user, String groupName) {
        if (groupRepository.findById(groupName).isPresent()) {
            user.setGroupId(List.of(groupRepository.findById(groupName).get()));
            updateUser(user);
        } else {
            //add user to general group
            Group1 defaultGroup = new Group1();
            defaultGroup.setGroupName(GroupEnum.USER_GROUP.name());
            defaultGroup.setDateCreated(new Date());
            defaultGroup.setCreatedBy("System");
            defaultGroup.setGroupId(GroupEnum.USER_GROUP.name());
            user.setGroupId(List.of(defaultGroup));
            updateUser(user);
        }
    }

    @Override
    public void updateUser(User user) {
        if (userExists(user.getUsername())) {
            userRepository.save(user);
        }
        throw new UserNotFoundException("User with username " + user.getUsername() + "not found");
    }

    @Override
    public boolean userExists(String username) {
        return Objects.nonNull(userRepository.findByUsername(username));
    }

    @Override
    public String disabledUser(String username) {
        if (userExists(username)) {
            User user = getUserWithUsername(username);
            if (Objects.nonNull(user)) {
                user.setEnabled(!user.getIsEnabled());
                updateUser(user);
                if (user.getIsEnabled()) return "Username successfully enabled";
                else {
                    return "Username successfully disabled";
                }
            }
        }
        throw new UserNotFoundException("User with username " + username + "not found");
    }

    @Override
    public String updateBasicAccountInformation(UserAccountUpdateDto userAccountUpdateDto, String usernameOrEnamilOrUserId) {
        return null;
    }

    @Override
    public User getUser(String usernameOrEmailOrUserId) {
        Optional<User> optionalUser = Optional.ofNullable(this.userRepository.findByUsernameOrEmailOrUserId(usernameOrEmailOrUserId));
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserNotFoundException("User with key " + usernameOrEmailOrUserId + "not found");
    }

    @Override
    public List<UserDto> filterSearchUser(String searchBy, Integer limit, Integer offset, String operation, String operationValue, String order) {
        return null;
    }

    /**
     * @param token The token attached to the link
     * @return
     * @throws Exception
     */
    @Override
    public boolean validateToken(String token) throws Exception {
        Optional<TokenToEmailMap> optionalTokenToEmailMap = Optional.ofNullable(tokenToEmailMapRepository.findByToken(token));
        LOGGER.info("RESULTS IS EMPTY ???? " + optionalTokenToEmailMap.isEmpty());
        if (optionalTokenToEmailMap.isPresent()) {
            TokenToEmailMap tokenToEmailMap = optionalTokenToEmailMap.get();
            if (new Date().compareTo(tokenToEmailMap.getExpiryDate()) > 0) {
                throw new Exception("Token has expired. Please request for another forget password link");
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean verifyUserEmail(String email, String token) {
        return false;
    }

    @Override
    public boolean forgotPassword(String emailOrUsername) {
        return false;
    }

    private void deleteToken(String token) throws ExecutionException, InterruptedException {
        tokenToEmailMapRepository.deleteByToken(token);
    }

    /**
     * @param username The username to search from with
     * @return
     */
    public User getUserWithUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username)).orElse(null);
    }

    /**
     * @param emailAddress The userEmail Address
     * @return boolean
     */
    @Override
    public boolean sendUserEmailVerification(String emailAddress) {
        try {
            User user = getUser(emailAddress);
            if (user != null && !user.getIsEmailVerified()) {
                String token = RandomStringUtils.randomAlphanumeric(500);
                storeToken(user.getEmail(), token, DateUtils.addDays(new Date(), 10));
                emailService.sendSimpleMessage(
                        "Ng-Jobs: New User Email Verification",
                        "Hey " + emailAddress + "!\nKindly find your Email Verification Link below \n"
                                + System.getenv("VERIFICATION_BASE_URL")
                                + "?email=" + user.getEmail() + "&"
                                + "token=" + token,
                        null,
                        emailAddress
                );
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("ERROR", e);
        }
        return false;
    }

    @Override
    public boolean updateUserPassword(UserUpdatePasswordDto passwordUpdate, String usernameOrEmailOrUserId) throws PasswordMismatchedException {
        try {
            User user = getUser(usernameOrEmailOrUserId);
            if (passwordEncoder.matches(passwordUpdate.getOldPassword(), user.getPassword())) {
                if (passwordUpdate.getConfirmPassword().equals(passwordUpdate.getNewPassword())) {
                    user.setPassword(passwordEncoder.encode(passwordUpdate.getNewPassword()));
                    user.setDateModified(new Date());
                    user.setModifiedBy(usernameOrEmailOrUserId);
                    updateUser(user);
                    return true;
                } else {
                    throw new PasswordMismatchedException("User password old and new password does not match");
                }
            } else {
                throw new PasswordMismatchedException("User confirm password and new password entered mismatched");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean logoutUser(String username) {
        if (userExists(username)) {
            User user = getUserWithUsername(username);
            if (Objects.nonNull(user)) {
                user.setStatus(StatusEnum.OFFLINE.name());
                updateUser(user);
                return true;
            }
        }
        return false;
    }

    /**
     * @param username The username of the user to deleted
     * @return
     */
    public boolean deleteUser(String username) {
        if (userExists(username) && username.equals("ng_job_admin")) return false;
        else if (userExists(username)) {
            this.userRepository.deleteByUsername(username);
            return true;
        }
        return false;
    }

    /**
     * @param email      The email Address of the user to map the token
     * @param token      The token to be mapped
     * @param expiryDate The date which the token will expire
     * @return boolean
     */
    private boolean storeToken(String email, String token, Date expiryDate) {
        TokenToEmailMap tokenToEmailMap = new TokenToEmailMap(UUID.randomUUID().toString());
        tokenToEmailMap.setEmailAddress(email);
        tokenToEmailMap.setToken(token);
        tokenToEmailMap.setDateSent(new Date());
        tokenToEmailMap.setExpiryDate(expiryDate);
        LOGGER.info("Created token for email: {} ; token {}; updateTime : {}", email, token, new Date());
        return true;
    }
}