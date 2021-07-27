package com.mergetechng.jobs.controllers.user;

import com.mergetechng.jobs.api.IUser;
import com.mergetechng.jobs.commons.dto.ApiResponseDto;
import com.mergetechng.jobs.commons.dto.NewJobSeekerDto;
import com.mergetechng.jobs.commons.util.ApiResponseUtil;
import com.mergetechng.jobs.commons.util.CycleAvoidingMappingContext;
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

@RestController
@RequestMapping("/user/")
public class RegistrationController {
    @Autowired
    private IUser iUser;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Operation(description = "Authenticate a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "IUser authenticated successfully",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(responseCode = "400", description = "IUser failed to be authenticated",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
    })
    @PostMapping(value = "/create", produces = {"application/json"})
    public ResponseEntity<ApiResponseDto> createNewUser(@Parameter(description = "New IUser Object") @RequestBody NewJobSeekerDto newJobSeekerDto) {
        ApiResponseDto apiResponseDto = ApiResponseUtil.process(
                "IUser failed to be authenticated",
                "400",
                "create new user",
                new Date(),
                null);
        try {
            boolean isCreated = iUser.createNewUser(JobApiGeneralMapper.INSTANCE.NewJobSeekerDtoToUser(newJobSeekerDto, new CycleAvoidingMappingContext()));
            if (isCreated) {
                apiResponseDto.setMessage("IUser Created successfully");
                apiResponseDto.setStatusCode("200");
                apiResponseDto.setData(null);
                apiResponseDto.setResponseDate(new Date());
                return ResponseEntity.ok(apiResponseDto);
            } else {
                apiResponseDto.setMessage("Username or email address of the new user may exist already.");
                apiResponseDto.setStatusCode("200");
                return ResponseEntity.ok(apiResponseDto);
            }
        } catch (Exception  e) {
            logger.error("ERROR", e);
            apiResponseDto.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(apiResponseDto);
        }
    }
}