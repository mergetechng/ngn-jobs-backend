package com.mergetechng.jobs.controllers.user;

import com.mergetechng.jobs.commons.dto.ApiResponseDto;
import com.mergetechng.jobs.commons.dto.AuthenticationLoginDto;
import com.mergetechng.jobs.commons.util.ApiResponseUtil;
import com.mergetechng.jobs.commons.util.JWTUtil;
import com.mergetechng.jobs.services.JobsUserDetailsService;
import com.mergetechng.jobs.services.JobsUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class LoginAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JobsUserDetailsService jobsUserDetailsService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private JobsUserService jobsUserService;

    private static final Logger logger = LoggerFactory.getLogger(LoginAuthenticationController.class);

    @Operation(description = "Authenticate a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Credentials", content = @Content),
            @ApiResponse(
                    responseCode = "200",
                    description = "Authentication Successful",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            )})
    @PostMapping(value = "/authenticate", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto<String>> createAuthenticationTokenForUser(@RequestBody AuthenticationLoginDto userAuthenticationLogin) throws Exception {
        ApiResponseDto<String> apiResponseDto = ApiResponseUtil.process(
                "Failed to generate token",
                "400",
                "CREATE_AUTH_TOKEN",
                new Date(),
                null);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthenticationLogin.getUsername(), userAuthenticationLogin.getPassword()));
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", userAuthenticationLogin.getUsername());
            String token = jwtUtil.generateToken(jobsUserDetailsService.loadUserByUsername(userAuthenticationLogin.getUsername()), claims);
            jobsUserService.logInUser(userAuthenticationLogin.getUsername());

            apiResponseDto.setData(token);
            apiResponseDto.setMessage("Token Generated Successfully");
            apiResponseDto.setStatusCode("200");
            return ResponseEntity.ok(apiResponseDto);
        } catch (Exception exception) {
            logger.error("Username: " + userAuthenticationLogin.getUsername() + " tried accessing with wrong credential", exception);
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }
}