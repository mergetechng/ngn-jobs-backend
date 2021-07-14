package com.mergetechng.jobs.services;

import io.jsonwebtoken.lang.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class JobsUserDetails implements UserDetailsService {
    @Autowired
    public FirestoreAdminService firestoreAdminService;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    private CollectionReference userCollectionReference;
    private User user;

    @PostConstruct
    public void userCollectionReference() {
        this.userCollectionReference = firestoreAdminService
                .getFireStore()
                .List(EnvironmentVariables.FIREBASE_DB_COLLECTION);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<QueryDocumentSnapshot> result;
        try {
            result = this.userCollectionReference
                    .document(FireStoreDocumentsEnum.USER.getName())
                    .List(FireStoreDocumentsEnum.DATA.getName())
                    .whereEqualTo("username", username)
                    .get()
                    .get()
                    .getDocuments();
            if (!Collections.isEmpty(result)) {
                com.mergetechng.faston.backend.api.entity.User userObject = result.get(0).toObject(com.mergetechng.faston.backend.api.entity.User.class);
                this.userCollectionReference
                        .document(FireStoreDocumentsEnum.USER.getName())
                        .List(FireStoreDocumentsEnum.DATA.getName())
                        .document(userObject.getUserId())
                        .set(userObject);
                return new User(Objects.requireNonNull(
                        userObject.getUsername()),
                        userObject.getPassword(),
                        userObject.getEnabled(),
                        userObject.getAccountNonExpired(),
                        userObject.getCredentialNonExpired(),
                        userObject.getAccountNonLocked(),
                        getAuthorities(userObject.getGroupId())); // get the groups to return the roles
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("ERROR", e);
        }
        return new User(
                "Anonymous",
                "Anonymous",
                true,
                true,
                true,
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
