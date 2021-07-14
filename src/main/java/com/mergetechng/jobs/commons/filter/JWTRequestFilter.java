package com.mergetechng.jobs.commons.filter;


import com.mergetechng.jobs.commons.util.JWTUtil;
import com.mergetechng.jobs.services.JobsUserDetailsService;
import com.mergetechng.jobs.services.JobsUserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JobsUserDetailsService jobsUserDetailsService;
    @Autowired
    private JWTUtil jwtUtil;
    private static Logger logger = LoggerFactory.getLogger(JobsUserService.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            final String authorizationHeader = request.getHeader("Authorization");
            String username = null;
            String jwt = null;
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(jwt);
            }else if(authorizationHeader !=null){
                jwt =  authorizationHeader ;
                username = jwtUtil.extractUsername(authorizationHeader);
            }if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = jobsUserDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (ExpiredJwtException eje) {
            logger.info("Security exception for user {} - {}", eje.getClaims().getSubject(), eje.getMessage());
            logger.trace("Security exception trace:", eje);
            (response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        chain.doFilter(request, response);
    }
}