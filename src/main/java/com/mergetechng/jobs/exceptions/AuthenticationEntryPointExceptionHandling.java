package com.mergetechng.jobs.exceptions;


import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("authenticationEntryPointExceptionHandling")
public class AuthenticationEntryPointExceptionHandling implements AuthenticationEntryPoint {
    public AuthenticationEntryPointExceptionHandling() {}
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, org.springframework.security.core.AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getOutputStream().println("{ \"message\": \"" + e.getMessage() + "\" }");
    }
}