package com.mergetechng.jobs.controllers.user;


import com.mergetechng.jobs.commons.dto.ApiResponseDto;
import com.mergetechng.jobs.commons.dto.UserResetPasswordDto;
import com.mergetechng.jobs.commons.dto.UserUpdatePasswordDto;
import com.mergetechng.jobs.commons.util.ApiResponseUtil;
import com.mergetechng.jobs.entities.User;
import com.mergetechng.jobs.exceptions.PasswordMismatchedException;
import com.mergetechng.jobs.exceptions.UserNotFoundException;
import com.mergetechng.jobs.exceptions.UserTokenException;
import com.mergetechng.jobs.repositories.UserRepository;
import com.mergetechng.jobs.services.JobsUserService;
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

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RestControllerAdvice
@RequestMapping(value = "/user/password")
public class PasswordRestController {
    @Autowired
    private JobsUserService userService;
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(PasswordRestController.class);

    @Operation(description = "Update an authenticated user password")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Password successfully updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ), @ApiResponse(
            responseCode = "400",
            description = "Email or Username does not exists",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
            }
    )
    })
    @PutMapping(value = "/update")
    public ResponseEntity<ApiResponseDto> updateUserPassword(
            @Parameter(description = "The password update body")
            @RequestBody UserUpdatePasswordDto userUpdatePasswordDto,
            @Parameter(description = "User email, username or userId")
            @RequestParam(value = "usernameOrEmailOrUserId") String usernameOrEmailOrUserId) {
        ApiResponseDto responseDto = ApiResponseUtil.process(
                "Bad Request",
                "400",
                "PASSWORD_UPDATE",
                new Date(),
                "BAD_REQUEST"
        );
        try {
            if (userService.updateUserPassword(userUpdatePasswordDto, usernameOrEmailOrUserId)) {
                responseDto.setMessage("Password successfully updated");
                responseDto.setStatusCode("200");
                responseDto.setData("Password Updated");
                return ResponseEntity.ok(responseDto);
            }
        } catch (PasswordMismatchedException | UserNotFoundException e) {
            logger.error("ERROR", e);
            responseDto.setMessage(e.getMessage());
        }
        return ResponseEntity.badRequest().body(responseDto);
    }

    @Operation(description = "Update an authenticated user password")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Password successfully reset",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ), @ApiResponse(
            responseCode = "400",
            description = "User with eui does not exists | Token Validation Error | Confirm password & new password mismatched! ",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
            }
    )
    })
    @PutMapping(value = "/reset")
    public ResponseEntity<ApiResponseDto> resetUserPassword(
            @Parameter(description = "The password reset body")
            @RequestBody UserResetPasswordDto userResetPasswordDto,
            @Parameter(description = "User email, username or userId")
            @RequestParam(name = "token") String token
    ) {
        ApiResponseDto responseDto = ApiResponseUtil.process(
                "Invalid token!",
                "400",
                "PASSWORD_RESET",
                new Date(),
                "BAD_REQUEST"
        );
        try {
            Optional<User> optionalUser = Optional.ofNullable(this.userRepository.findByUsername(userResetPasswordDto.getUsername()));
            if (optionalUser.isPresent()) {
                if (userService.validateToken(token, optionalUser.get().getEmail())) {
                    if (userService.resetUserPassword(token, userResetPasswordDto)) {
                        responseDto.setMessage("Password successfully reset");
                        responseDto.setStatusCode("200");
                        responseDto.setData("Password reset");
                        return ResponseEntity.ok(responseDto);
                    } else {
                        responseDto.setMessage("Failed to update password");
                        responseDto.setStatusCode("400");
                        responseDto.setData(null);
                        return ResponseEntity.ok(responseDto);
                    }
                } else {
                    responseDto.setMessage("Token is not valid to reset password");
                    responseDto.setStatusCode("400");
                    responseDto.setData(null);
                    return ResponseEntity.ok(responseDto);
                }
            }else {
                responseDto.setMessage(String.format("User %s is not found", userResetPasswordDto.getUsername()));
                responseDto.setStatusCode("400");
                responseDto.setData(null);
                return ResponseEntity.ok(responseDto);
            }
        } catch (PasswordMismatchedException | UserNotFoundException | UserTokenException e) {
            logger.error("ERROR", e);
            responseDto.setMessage(e.getMessage());
        }
        return ResponseEntity.badRequest().body(responseDto);
    }
}