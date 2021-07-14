package com.mergetechng.jobs.configs.security;

import com.mergetechng.jobs.commons.EnvironmentVariables;
import com.mergetechng.jobs.commons.filter.JWTRequestFilter;
import com.mergetechng.jobs.exceptions.AccessDeniedExceptionHandler;
import com.mergetechng.jobs.exceptions.AuthenticationEntryPointExceptionHandling;
import com.mergetechng.jobs.services.JobsUserDetailsService;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Order(0)
public class GlobalWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private JobsUserDetailsService jobUserDetails;
    @Autowired
    private JWTRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.userDetailsService(jobUserDetails); // load the user details
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
//        auth.inMemoryAuthentication()
//                .withUser("Anonymous")
//                .password("{noop}Anonymous")
//                .roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(jobUserDetails);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedExceptionHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate")
                .permitAll()
                .antMatchers("/user/authenticate/*")
                .permitAll()
                .antMatchers("/admin/*")
                .fullyAuthenticated() //not anonymous user or remember me user
                .antMatchers("/admin/*").access("hasAuthority('ADMIN_DASHBOARD')")
                .antMatchers("/admin/wallet/*")
                .access("hasRole('ADMIN_DASHBOARD') or hasRole('SUPER_ADMIN')")
//                .antMatchers("/admin/wallet/fund/*")
//                .access("hasRole('ADMIN_DASHBOARD') or hasRole('SUPER_ADMIN')")
//                .antMatchers("/admin/trip/*")
//                .hasAnyRole("ADMIN_TRIP", "SYSTEM_ADMIN")
//                .antMatchers("/admin/trip/payment/*")
//                .hasAnyRole("ADMIN_PAYMENT", "ADMIN_PAYMENT")
                //users
//                .antMatchers("/user/delete/*").fullyAuthenticated()
//                .antMatchers("/user/update/*").fullyAuthenticated()
//                .antMatchers("/user/password/update/*").fullyAuthenticated()
//                .antMatchers("/user/disable/*").fullyAuthenticated()
//                .antMatchers("/user/filter-search/*").fullyAuthenticated()
//                .antMatchers("/user/exists/*").permitAll()
//                .antMatchers("/user/search/*").hasAnyAuthority("ADMIN_DASHBOARD")
                //contacts
//                .antMatchers("/user/contact/create/*")
//                .access("hasAuthority('RIDER_USER_ADD') or hasAuthority('ADMIN_DASHBOARD_ADD') or hasAuthority('DRIVER_USER_ADD')")
//                .antMatchers("/user/contact/delete/*").authenticated()
//                .antMatchers("/user/contact/update/*").authenticated()
//                .antMatchers("/user/contact/search/*").authenticated()
//                .antMatchers("/user/contact/exists/*").authenticated()
//                .antMatchers("/user/contacts/filter-search/*").authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointExceptionHandling())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .rememberMe()
                .key(EnvironmentVariables.SECRET_KEY);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}