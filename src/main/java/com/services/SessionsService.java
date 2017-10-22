package com.services;

import com.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SessionsService  {

    @Autowired
    private SessionRegistry sessionRegistry;


    public void expireSessionsOf(String username) {
        sessionRegistry.getAllPrincipals().stream()
                .filter(principal -> principal instanceof UserPrincipal)
                .map(principal -> (UserDetails) principal)
                .filter(userDetails -> userDetails.getUsername().equals(username))
                .flatMap(userDetails -> sessionRegistry.getAllSessions(userDetails, true)
                .stream()).forEach(SessionInformation::expireNow);
    }

}
