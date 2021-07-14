package com.mergetechng.jobs.services;

//import io.jsonwebtoken.lang.Lists;
import com.mergetechng.jobs.entities.Group1;
import com.mergetechng.jobs.entities.Privilege;
import com.mergetechng.jobs.entities.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobsUserDetailsService implements UserDetailsService {
    public JobsUserService jobJobsUserService;
    private static Logger logger = LoggerFactory.getLogger(JobsUserDetailsService.class);

    public JobsUserDetailsService(JobsUserService jobJobsUserService){
        this.jobJobsUserService = jobJobsUserService ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        com.mergetechng.jobs.entities.User userFound;
            userFound = this.jobJobsUserService.getUserByUsername(username);
            if (userFound !=  null) {
                userFound.setOnline(true);
                return new User(Objects.requireNonNull(
                        userFound.getUsername()),
                        userFound.getPassword(),
                        userFound.getIsEnabled(),
                        userFound.isAccountNonExpired(),
                        userFound.getIsGetCredentialNonExpired(),
                        userFound.getIsAccountNonLocked(),
                        getAuthorities(userFound.getGroupId())); // get the groups to return the roles
            }
        return new User(
                "Anonymous",
                "Anonymous",
                true,
                false,
                false,
                true,
                new ArrayList<>());
    }

    private List<? extends GrantedAuthority> getAuthorities(
            List<Group1> group1s) {
        return getGrantedAuthorities(getPrivilegesAndRoles(group1s));
    }

    private List<String> getPrivilegesAndRoles(List<Group1> group1s) {
        List<String> List = new ArrayList<>();
        for (Group1 group1 : group1s) {
            List<Role> roles = group1.getRoleId();
            roles.forEach(role -> {
                List.addAll(role.getPrivilegeId()
                        .stream()
                        .map(Privilege::getPrivilegeId)
                        .collect(Collectors.toList()));
                List.add(role.getName()); // add the role to the list so that spring can use hasRole or hasAnyRole()
            });
        }
        return new ArrayList<>(List);
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
