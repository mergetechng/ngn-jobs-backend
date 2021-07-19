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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
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
        LOGGER.info("Username: {}", user.getUsername());
        LOGGER.info("Email: {}", user.getEmail());
        LOGGER.info("Password: {}", user.getPassword());
        LOGGER.info("Id: {}", user.getUserId());
        LOGGER.info("USERNAME_OR_EMAIL_EXISTS : {}", userExists(user.getUsername(), user.getEmail()));

        if (!userExists(user.getUsername(), user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserId(UUID.randomUUID().toString());
            user.setDateCreated(new Date());
            user.setAccountType(AccountTypeEnum.JOB_APPLICANT.name());
            userRepository.insert(user);
            addUserToGroup(user, GroupEnum.JOB_APPLICANT_GROUP.name());
            sendUserEmailVerification(user.getEmail());
            LOGGER.info("Created new User: {}", user.toString());
            return true;
        }
        return false;
    }

    private void addUserToGroup(User user, String groupName) {
        if (groupRepository.findByGroupId(groupName).isPresent()) {
            LOGGER.info("The Group name is present " + groupName);
            user.setGroupId(List.of(groupRepository.findByGroupId(groupName).get()));
            updateUser(user);
        } else {
            if (groupRepository.findByGroupId(GroupEnum.USER_GROUP.name()).isPresent()) {
                user.setGroupId(List.of(groupRepository.findByGroupId(GroupEnum.USER_GROUP.name()).get()));
            } else {
                LOGGER.info("Default USER_GROUP Not found. Adding a default a default user group");
                Group1 defaultGroup = new Group1();
                defaultGroup.setGroupName(GroupEnum.USER_GROUP.name());
                defaultGroup.setDateCreated(new Date());
                defaultGroup.setCreatedBy("System");
                defaultGroup.setGroupId(GroupEnum.USER_GROUP.name());
                user.setGroupId(List.of(defaultGroup));
            }
            updateUser(user);
        }
    }

    @Override
    public void updateUser(User user) {
        if (userExists(user.getUsername(), user.getEmail())) {
            userRepository.save(user);
            return;
        }
        throw new UserNotFoundException("User with username " + user.getUsername() + " not found");
    }

    @Override
    public boolean userExists(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public String disabledUser(String username) {
        if (userExists(username, "")) {
            User user = getUserWithUsername(username);
            if (Objects.nonNull(user)) {
                user.setEnabled(false);
                updateUser(user);
                return "Username successfully disabled";
            }
        }
        throw new UserNotFoundException("User with username " + username + "not found");
    }

    @Override
    public String enableUser(String username) {
        if (userExists(username, "")) {
            User user = getUserWithUsername(username);
            if (Objects.nonNull(user)) {
                user.setEnabled(true);
                updateUser(user);
                return "Username successfully enabled";
            }
        }
        throw new UserNotFoundException("User with username " + username + "not found");
    }

    @Override
    public String updateBasicAccountInformation(UserAccountUpdateDto userAccountUpdateDto, String usernameOrEnamilOrUserId) {
        return null;
    }

    @Override
    public User getUser(String uei) {
        Optional<User> optionalUser = Optional.ofNullable(this.userRepository.findFirstByUsernameOrEmailOrUserId(uei, uei, uei));
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserNotFoundException("User with key " + uei + "not found");
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
        return Optional.ofNullable(userRepository.findFirstByUsernameOrEmailOrUserId(username, null, null)).orElse(null);
    }

    /**
     * @param emailAddress The userEmail Address
     * @return boolean
     */
    @Override
    public boolean sendUserEmailVerification(String emailAddress) {
        try {
            Optional<User> optionalUser = Optional.ofNullable(userRepository.findFirstByEmail(emailAddress));
            if (optionalUser.isPresent() && !optionalUser.get().getIsEmailVerified()) {
                String token = RandomStringUtils.randomAlphanumeric(500);
                User user = optionalUser.get();
                storeToken(user.getEmail(), token, DateUtils.addDays(new Date(), 10));
                CompletableFuture<String> completableFuture = emailService.sendSimpleMessage(
                        "Ng-Jobs: New User Email Verification",
                        "Hey " + emailAddress + "!\nKindly find your Email Verification Link below \n"
                                + System.getenv("VERIFICATION_BASE_URL")
                                + "?email=" + user.getEmail() + "&"
                                + "token=" + token,
                        null,
                        emailAddress
                ).toCompletableFuture();
                if (completableFuture.isCompletedExceptionally()) {
                    LOGGER.info("Failed to send message");
                } else {
                    LOGGER.info("Message from mailer : " + completableFuture.get() + emailAddress);
                    return true;
                }
                return false;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("USER_EMAIL_SEND_ERROR ", e);
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
    public List<User> getAll(Query query) {
        return null;
    }

    @Override
    public Page<User> getPage(Query query, Pageable pageable) {
        return null;
    }

    @Override
    public boolean logoutUser(String username) {
        if (userExists(username, "")) {
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
        if (userExists(username, "") && !username.equals("ng_job_admin")) return false;
        else if (userExists(username, "")) {
            this.userRepository.deleteByUsername(username);
            return true;
        }
        return false;
    }

    /**
     * @param email      The email Address of the user to map the token
     * @param token      The token to be mapped
     * @param expiryDate The date which the token will expire
     */
    private void storeToken(String email, String token, Date expiryDate) {
        TokenToEmailMap tokenToEmailMap = new TokenToEmailMap(UUID.randomUUID().toString());
        tokenToEmailMap.setEmailAddress(email);
        tokenToEmailMap.setToken(token);
        tokenToEmailMap.setDateSent(new Date());
        tokenToEmailMap.setExpiryDate(expiryDate);
        LOGGER.info("Created token for email: {} ; token {}; updateTime : {}", email, token, new Date());
    }
}