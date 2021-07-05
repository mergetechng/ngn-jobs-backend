package com.mergetechng.jobs.A.job.application.project.to.create.view.and.Send.Job.Seekers.Information.to.the.employer.controllers.admin;


import com.mergetechng.jobs.A.job.application.project.to.create.view.and.Send.Job.Seekers.Information.to.the.employer.common.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController(value = "/admin/authentication")
@RestControllerAdvice
@RequestMapping("/authentication")
//@Api(value = "/admin", description = "Login related operations", consumes = "application/json", tags = {"authentication"})
public class AdminLoginController {
    @GetMapping(value = "/login")
    @Operation(description = "Allow a admin user to sign in to the")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login was successful",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ), @ApiResponse(
            responseCode = "400",
            description = "You are not authorized to use this endpoint",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto[].class))
            }
    )})
    public ResponseEntity<Map<String, String>> login(@RequestBody(required = false) Map<String, String> loginBody) {
        return ResponseEntity.ok(new HashMap<>());
    }
}