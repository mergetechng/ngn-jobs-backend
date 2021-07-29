package com.mergetechng.jobs.services;

import com.mergetechng.jobs.api.IAdvanceSearch;
import com.mergetechng.jobs.api.IUser;
import com.mergetechng.jobs.commons.dto.UserAccountUpdateDto;
import com.mergetechng.jobs.commons.dto.UserDto;
import com.mergetechng.jobs.commons.dto.UserResetPasswordDto;
import com.mergetechng.jobs.commons.dto.UserUpdatePasswordDto;
import com.mergetechng.jobs.commons.enums.AccountTypeEnum;
import com.mergetechng.jobs.commons.enums.GroupEnum;
import com.mergetechng.jobs.commons.enums.MessageEnum;
import com.mergetechng.jobs.commons.enums.StatusEnum;
import com.mergetechng.jobs.entities.Group1;
import com.mergetechng.jobs.entities.TokenToEmailMap;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.exceptions.*;
import com.mergetechng.jobs.repositories.GroupRepository;
import com.mergetechng.jobs.repositories.TokenToEmailMapRepository;
import com.mergetechng.jobs.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class JobsUserService implements IUser, IAdvanceSearch<User> {
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

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String VERIFICATION_BASE_URL = "http://localhost:8081/user/verify-account-email/";
    private static final String FORGOT_PASSWORD_BASE_URL = "http://localhost:8081/user/reset-account-password/";


    public void logInUser(String username) {
        Optional<com.mergetechng.jobs.entities.User> userOptional = Optional.ofNullable(this.userRepository.findByUsername(username));
        if (userOptional.isPresent()) {
            userOptional.get().setOnline(true);
            this.userRepository.save(userOptional.get());
        }
    }

    public JobsUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public com.mergetechng.jobs.entities.User getUserByUsername(String username) {
        return this.userRepository.findByUsernameOrEmailOrId(username, null, null);
    }

    public List<com.mergetechng.jobs.entities.User> getUserByFirstNameAndLastName(String firstName, String lastName) {
        return this.userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public boolean createNewUser(com.mergetechng.jobs.entities.User user) throws Exception {
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
            user.setAccountNonExpired(true);
            user.setCredentialNonExpired(false);
            user.setAccountNonLocked(true);
            user.setEnabled(false);
            user.setCreatedBy("System");
            userRepository.insert(user);
            addUserToGroup(user, GroupEnum.JOB_APPLICANT_GROUP.name());
            sendUserEmailVerification(user.getEmail());
            LOGGER.info("Created new IUser: {}", user.toString());
            return true;
        }
        return false;
    }

    private void addUserToGroup(com.mergetechng.jobs.entities.User user, String groupName) {
        if (groupRepository.findById(groupName).isPresent()) {
            LOGGER.info("The Group name is present " + groupName);
            user.setGroupId(List.of(groupRepository.findById(groupName).get()));
            updateUser(user);
        } else {
            if (groupRepository.findById(GroupEnum.USER_GROUP.name()).isPresent()) {
                user.setGroupId(List.of(groupRepository.findById(GroupEnum.USER_GROUP.name()).get()));
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
    public void updateUser(com.mergetechng.jobs.entities.User user) {
        if (userExists(user.getUsername(), user.getEmail())) {
            user.setDateModified(new Date());
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
            com.mergetechng.jobs.entities.User user = getUserWithUsername(username);
            if (Objects.nonNull(user)) {
                user.setEnabled(false);
                updateUser(user);
                return "Username successfully disabled";
            }
        }
        throw new UserNotFoundException("IUser with username " + username + " not found");
    }

    @Override
    public String enableUser(String username) {
        if (userExists(username, "")) {
            com.mergetechng.jobs.entities.User user = getUserWithUsername(username);
            if (Objects.nonNull(user)) {
                user.setEnabled(true);
                updateUser(user);
                return "Username successfully enabled";
            }
        }
        throw new UserNotFoundException("IUser with username " + username + "not found");
    }

    @Override
    public String updateBasicAccountInformation(UserAccountUpdateDto userAccountUpdateDto, String usernameOrEmailOrUserId) throws UserAlreadyExistsException {
        com.mergetechng.jobs.entities.User user = getUser(usernameOrEmailOrUserId);
        if (Objects.nonNull(user)) {
            user.setEmail(userAccountUpdateDto.getEmail());
            /*send email verification link to the new email*/
            user.setPhone(userAccountUpdateDto.getPhoneNumber());
            user.setLastName(userAccountUpdateDto.getLastName());
            user.setFirstName(userAccountUpdateDto.getFirstName());
            if (userExists(userAccountUpdateDto.getUsername(), null)) {
                user.setUsername(userAccountUpdateDto.getUsername());
                throw new UserAlreadyExistsException("Username " + userAccountUpdateDto.getUsername() + " already exist");
            }
            updateUser(user);
            return "IUser basic information updated successfully";
        }
        throw new UserAccountUpdateException("Failed to update user basic information");
    }

    @Override
    public com.mergetechng.jobs.entities.User getUser(String uei) throws UserNotFoundException {
        Optional<com.mergetechng.jobs.entities.User> optionalUser = Optional.ofNullable(this.userRepository.findByUsernameOrEmailOrId(uei, uei, uei));
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserNotFoundException("IUser with key " + uei + " not found");
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
    public boolean validateToken(String token, String email) throws UserTokenException {
        Optional<TokenToEmailMap> optionalTokenToEmailMap = Optional.ofNullable(tokenToEmailMapRepository.findByToken(token));
        if (optionalTokenToEmailMap.isPresent() && optionalTokenToEmailMap.get().getEmailAddress().equals(email)) {
            TokenToEmailMap tokenToEmailMap = optionalTokenToEmailMap.get();
            if (new Date().compareTo(tokenToEmailMap.getExpiryDate()) > 0) {
                throw new UserTokenException("Token has expired. Please request for another forget password link");
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * @param email The user email address
     * @param token The IUser token
     * @return
     * @throws UserTokenException    This is thrown when the user token does not exists
     * @throws UserNotFoundException This is thrown when the user is not found
     */
    @Override
    public boolean verifyUserEmail(String email, String token) throws ResourceNotFoundException, UserNotFoundException, UserTokenException {
        com.mergetechng.jobs.entities.User user = getUser(email);
        if (!validateToken(token, email)) {
            throw new ResourceNotFoundException("The token " + token + " was not found");
        } else {
            deleteToken(token);
            user.setEmailVerified(true);
            user.setAccountNonLocked(true);
            user.setEnabled(true);
            updateUser(user);
            return true;
        }
    }

    @Override
    public boolean forgotPassword(String emailOrUsername) throws Exception {
        try {
            com.mergetechng.jobs.entities.User user = getUser(emailOrUsername);
            if (user != null) {
                String token = RandomStringUtils.randomAlphanumeric(500);
                storeToken(user.getEmail(), token, DateUtils.addDays(new Date(), 10));
                String emailText = "Please check your forgot password link below. \nThe " +
                        "link below will expire after 10 days. \nClick on the link below to proceed\n\n" +
                        "" + FORGOT_PASSWORD_BASE_URL
                        + "?email=" + user.getEmail() + "&"
                        + "token=" + token;
                emailService.sendSimpleMessage(
                        "IUser Account Forget Password-"
                        , emailText,
                        null,
                        3,
                        user.getEmail()
                );
                return true;
            }
        } catch (Exception e) {
            LOGGER.error("ERROR :", e);
            throw new Exception(e.getMessage());
        }
        return false;
    }


    /**
     * @param token The token to be deleted
     */
    private void deleteToken(String token) {
        tokenToEmailMapRepository.deleteByToken(token);
    }

    /**
     * @param username The username to search from with
     * @return
     */
    public com.mergetechng.jobs.entities.User getUserWithUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsernameOrEmailOrId(username, null, null)).orElse(null);
    }

    /**
     * @param uei The userEmail Address
     * @return boolean
     */
    @Override
    public boolean sendUserEmailVerification(String uei) throws Exception {
        Optional<com.mergetechng.jobs.entities.User> optionalUser = Optional.ofNullable(userRepository.findByUsernameOrEmailOrId(uei, uei, uei));
        if (optionalUser.isPresent()) {
            if (!optionalUser.get().getIsEmailVerified()) {
                LOGGER.info("Yes I was able to run this...");
                String token = RandomStringUtils.randomAlphanumeric(500);
                com.mergetechng.jobs.entities.User user = optionalUser.get();
                storeToken(user.getEmail(), token, DateUtils.addDays(new Date(), 10));
                CompletableFuture<String> completableFuture =
                        emailService.sendSimpleMessage(
                                "Ng-Jobs: New IUser Email Verification",
                                "Hello " + uei + "!\nKindly find your Email Verification Link below \n"
                                        + System.getenv("VERIFICATION_BASE_URL")
                                        + "?email=" + user.getEmail() + "&"
                                        + "token=" + token,
                                null,
                                uei
                        );
                if (completableFuture.isCompletedExceptionally()) {
                    LOGGER.info("Failed to send message");
                    throw new Exception("Failed to successfully send new confirmation link");
                } else if (completableFuture.isDone() && completableFuture.get().equals(MessageEnum.MAIL_SENDING_SUCCESSFUL.getMessage())) {
                    LOGGER.info("Sent mail successfully");
                    return true;
                } else if (completableFuture.isDone() && completableFuture.get().equals(MessageEnum.MAIL_SENDING_FAILED.getMessage())) {
                    LOGGER.info("Failed to send mail");
                    return false;
                } else if (completableFuture.isDone() && !completableFuture.isCompletedExceptionally()) {
                    LOGGER.info("Message from mailer: " + completableFuture.get() + uei);
                    /* Use another thread to send the mail again after 60 Secs
                     *  Try sending for like 10 trials
                     **/
                    return true; // this should be logged and can be scheduled for resending again after some time
                }
            } else if (optionalUser.get().getIsEmailVerified()) {
                LOGGER.info(String.format("Account %s already verified", uei));
                throw new UserAccountAlreadyVerifiedException("IUser account is already verified. No link sent");
            }
        }
        throw new UserNotFoundException(String.format("IUser credential %s not found", uei));
    }

    @Override
    public boolean updateUserPassword(UserUpdatePasswordDto passwordUpdate, String usernameOrEmailOrUserId) throws PasswordMismatchedException, UserNotFoundException {
        com.mergetechng.jobs.entities.User user = getUser(usernameOrEmailOrUserId);
        LOGGER.info("RAW PASSWORD : {}", passwordUpdate.getOldPassword());
        LOGGER.info("ENCODED PASSWORD : {}", user.getPassword());
        LOGGER.info("PASSWORD MATCHED: {}", passwordEncoder.matches(passwordUpdate.getOldPassword(), user.getPassword()));

        if (passwordEncoder.matches(passwordUpdate.getOldPassword(), user.getPassword())) {
            if (passwordUpdate.getConfirmPassword().equals(passwordUpdate.getNewPassword())) {
                user.setPassword(passwordEncoder.encode(passwordUpdate.getNewPassword()));
                user.setDateModified(new Date());
                user.setModifiedBy(usernameOrEmailOrUserId);
                updateUser(user);
                return true;
            } else {
                throw new PasswordMismatchedException("Confirm password & new password mismatched!");
            }
        } else {
            throw new PasswordMismatchedException("Incorrect old password");
        }
    }


    @Override
    public boolean resetUserPassword(String token, UserResetPasswordDto userResetPasswordDto) throws UserNotFoundException, PasswordMismatchedException {
        com.mergetechng.jobs.entities.User user = getUser(userResetPasswordDto.getUsername());
        if (userResetPasswordDto.getConfirmPassword().equals(userResetPasswordDto.getNewPassword())) {
            user.setPassword(passwordEncoder.encode(userResetPasswordDto.getNewPassword()));
            user.setDateModified(new Date());
            user.setModifiedBy(userResetPasswordDto.getUsername());
            tokenToEmailMapRepository.deleteByToken(token);
            updateUser(user);
            return true;
        } else {
            throw new PasswordMismatchedException("Confirm password & new password mismatched!");
        }
    }

    @Override
    public boolean logoutUser(String username) {
        if (userExists(username, "")) {
            com.mergetechng.jobs.entities.User user = getUserWithUsername(username);
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
        if (userExists(username, "") && !username.equals("ng_jobs_admin")) {
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
        tokenToEmailMapRepository.save(tokenToEmailMap);
        LOGGER.info("Created token for email: {} ; token {}; updateTime : {}", email, token, new Date());
    }

    @Override
    public Page<User> getAllWithPageable(Query query, Pageable pageable) {
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query.with(pageable), User.class)
                        .stream()
                        .peek(user -> user.setPassword("********"))
                        .collect(Collectors.toList()),
                pageable,
                () -> mongoTemplate.count(query, User.class));
    }

    @Override
    public List<User> getAllWithoutPageable(Query query) {
        return mongoTemplate.find(query, User.class);
    }
}