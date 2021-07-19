package com.mergetechng.jobs.controllers.user;


import com.mergetechng.jobs.api.IUser;
import com.mergetechng.jobs.commons.dto.ApiResponseDto;
import com.mergetechng.jobs.commons.dto.UserAccountUpdateDto;
import com.mergetechng.jobs.commons.dto.UserDto;
import com.mergetechng.jobs.commons.util.ApiResponseUtil;
import com.mergetechng.jobs.commons.util.CycleAvoidingMappingContext;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.exceptions.UserNotFoundException;
import com.mergetechng.jobs.exceptions.UserTokenException;
import com.mergetechng.jobs.services.api.JobApiGeneralMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user/")
public class JobUserRestController {

    @Autowired
    private IUser iUser;
    private static final Logger logger = LoggerFactory.getLogger(JobUserRestController.class);


    @Operation(description = "Authenticate a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "No result found", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Searched Successful",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )})
    @PostMapping(value = "/search/{username}", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> searchUserWithUsername(@PathVariable String username) throws Exception {
        return null ;
    }

    @Operation(description = "Search for username")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Username exists",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @GetMapping(value = "/exists", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> userExists(
            @Parameter(description = "The username to search")
            @RequestParam(value = "username") String username) throws Exception {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Username is not available to be used",
                "400",
                "search existing username",
                new Date(),
                null);
        try {
            if (!iUser.userExists(username, "")) {
                apiResponseDto.setStatusCode("200");
                apiResponseDto.setMessage("Username is available to used");
                apiResponseDto.setData(null);
            }
            return ResponseEntity.badRequest().body(apiResponseDto);
        } catch (Exception e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Disable user with the provided username")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User disabled successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Username does exists and can not be disabled",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @PutMapping(value = "/disable", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> disableUser(
            @Parameter(description = "The username to be disabled")
            @RequestParam(value = "username") String username) throws Exception {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "username was unable to be disabled",
                "400",
                "DISABLE_USER",
                new Date(),
                null);
        try {
            String message = iUser.disabledUser(username);
            apiResponseDto.setMessage(message);
            apiResponseDto.setStatusCode("200");
            return ResponseEntity.ok(apiResponseDto);
        } catch (Exception e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Update user basic information")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User basic information Successfully Updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Failed to update user basic Information",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @PutMapping(value = "/update", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> updateUser(
            @Parameter(description = "The username to be disabled")
            @RequestBody UserAccountUpdateDto userAccountUpdateDto,
            @Parameter(description = "The usernameOrEmailOrUserId to be updated")
            @RequestParam String usernameOrEmailOrUserId) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Failed to update user basic Information",
                "400",
                "UPDATE_USER",
                new Date(),
                null);
        try {
            String message = iUser.updateBasicAccountInformation(userAccountUpdateDto, usernameOrEmailOrUserId);
            apiResponseDto.setMessage(message);
            apiResponseDto.setStatusCode("200");
            return ResponseEntity.ok(apiResponseDto);
        } catch (Exception e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Logout user with the provided username")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User logout successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Username does exists and can not be logout",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @PutMapping(value = "/logout", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> logoutUser(
            @Parameter(description = "The username to be disabled")
            @RequestParam(value = "username") String username) throws Exception {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "user does not exists",
                "400",
                "LOGOUT_USER",
                new Date(),
                null);
        try {
            boolean isLoggedOut = iUser.logoutUser(username);
            apiResponseDto.setMessage("User is logged out ->" + isLoggedOut);
            apiResponseDto.setStatusCode("200");
            return ResponseEntity.ok(apiResponseDto);
        } catch (Exception e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Logout user with the provided username")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User logout successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Username does exists and can not be logout",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @GetMapping(value = "/search", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> getUser(
            @Parameter(description = "The username to get")
            @RequestParam(value = "usernameOrEmailOrUserId") String usernameOrEmailOrUserId) throws Exception {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "user does not exists",
                "400",
                "SEARCH_USER",
                new Date(),
                null);
        try {
            User result = iUser.getUser(usernameOrEmailOrUserId);
            if (Objects.nonNull(result)) apiResponseDto.setMessage("User gotten successfully");
            apiResponseDto.setData(JobApiGeneralMapper.INSTANCE.userToUserDto(result, new CycleAvoidingMappingContext()));
            apiResponseDto.setStatusCode("200");
            return ResponseEntity.ok(apiResponseDto);
        } catch (Exception e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Logout user with the provided username")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User logout successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Username does exists and can not be logout",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @GetMapping(value = "/filter-search", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> filterSearchUsers(
            @Parameter(description = "The field to search")
            @RequestParam String searchBy,
            @Parameter(description = "The limits of data to be returned")
            @RequestParam Integer limit,
            @Parameter(description = "The starting point where the data will start from")
            @RequestParam Integer offset,
            @Parameter(description = "The Operation eg whereEquals, whereGreaterThan etc")
            @RequestParam(required = false) String operation,
            @Parameter(description = "The operation argument value")
            @RequestParam(required = false) String operationValue,
            @Parameter(description = "The field to the compared with operator value")
            @RequestParam String order) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "user does not exists",
                "400",
                "FILTER_SEARCH",
                new Date(),
                null);
        try {
            List<UserDto> result = iUser.filterSearchUser(searchBy, limit, offset, operation, operationValue, order);
            if (Objects.nonNull(result)) apiResponseDto.setMessage("User(s) gotten successfully");
            apiResponseDto.setData(result);
            apiResponseDto.setStatusCode("200");
            return ResponseEntity.ok(apiResponseDto);
        } catch (Exception e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Validate the forgot password token using the email address attached to the link")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = true + "-The token is valid and can be used.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = false + "-The token is invalid please request for another forgot password link",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @GetMapping(value = "/token/validate", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> validateToken(
            @Parameter(description = "The token attached to the link")
            @RequestParam(value = "token") String token
    ) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                false + "-The token is invalid.",
                "400",
                "VALIDATE_TOKEN",
                new Date(),
                null);

        try {
            if (iUser.validateToken(token)) {
                apiResponseDto.setMessage(true + "-> token valid");
                apiResponseDto.setStatusCode("200");
                return ResponseEntity.ok(apiResponseDto);
            } else {
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }


    @Operation(description = "Validate and verify user account email with the token attached to the link")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = true + "- The user account is verified and account is enabled",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = false + "-The user account email failed to be verified. Ensure link is still valid",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @PutMapping(value = "/verify-account-email", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> verifyUserAccountEmail(
            @Parameter(description = "The email attached to the link")
            @RequestParam(value = "email") String email,
            @Parameter(description = "The token attached to the link")
            @RequestParam(value = "token") String token
    ) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                false + "-The user account email failed to be verified. Ensure link is still valid",
                "400",
                "VERIFY_USER_EMAIL_ACCOUNT",
                new Date(),
                null);
        try {
            if (iUser.verifyUserEmail(email, token)) {
                apiResponseDto.setMessage(true + "-The user account is verified and account is enabled");
                apiResponseDto.setStatusCode("200");
                return ResponseEntity.ok(apiResponseDto);
            } else {
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            logger.error("ERROR", e);
            if (e instanceof UserTokenException) apiResponseDto.setStatusCode("404");
            if (e instanceof UserNotFoundException) apiResponseDto.setStatusCode("404");
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Delete user with username")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User with username deleted successfully",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )
    })
    @DeleteMapping(value = "/delete", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> deleteUserByUsername(
            @Parameter(description = "The username to be deleted")
            @RequestParam String username) throws Exception {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Email or Username does not exists",
                "400",
                "DELETE_USER",
                new Date(),
                null);
        try {
            if (iUser.deleteUser(username)) {
                apiResponseDto.setMessage(true + "-User successfully deleted from the system");
                apiResponseDto.setStatusCode("200");
                return ResponseEntity.ok(apiResponseDto);
            } else {
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Allow an existing user to reset password")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Mail has be sent to the provided email address",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ), @ApiResponse(
            responseCode = "400",
            description = "Email or Username does not exists",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
            }
    )
    })
    @GetMapping(value = "/forgot-password-link", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> forgotPasswordUsingEmailOrUsername(
            @Parameter(description = "The username or email address search and send forgot password link to")
            @RequestParam String emailOrUsername) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Email or Username does not exists",
                "400",
                "CREATE_FORGOT_PASSWORD_LINK",
                new Date(),
                null);
        try {
            if (iUser.forgotPassword(emailOrUsername)) {
                apiResponseDto.setMessage("Mail has be sent to the provided email address");
                apiResponseDto.setStatusCode("200");
                return ResponseEntity.ok(apiResponseDto);
            } else {
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }

    @Operation(description = "Allow an existing user to reset password")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Mail has be sent to the provided email address",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ), @ApiResponse(
            responseCode = "400",
            description = "Email or Username does not exists",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
            }
    )
    })
    @GetMapping(value = "/email-confirmation-link", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> sendEmailConfirmationLink(
            @Parameter(description = "The username or email address search and send confirmation mail link to")
            @RequestParam String emailOrUsername) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "Email or Username does not exists",
                "400",
                "SEND_EMAIL_CONFIRMATION",
                new Date(),
                null);
        try {
            if (iUser.sendUserEmailVerification(emailOrUsername)) {
                apiResponseDto.setMessage("Mail has be sent to your email address");
                apiResponseDto.setStatusCode("200");
                return ResponseEntity.ok(apiResponseDto);
            } else {
                return ResponseEntity.badRequest().body(apiResponseDto);
            }
        } catch (Exception e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }
}
