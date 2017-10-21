package com.controllers.rest;

import com.User;
import com.UserPrincipal;
import com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestCtrl {


    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(path = "/sessions", method = RequestMethod.GET)
    public List<Object> greeting() {

        return sessionRegistry.getAllPrincipals();

    }


    @RequestMapping(path ="/api/deleteUser", method = RequestMethod.GET)
    public void deleteUser(@RequestParam Long id) {



        String username = userRepository.findOne(id).getUsername();

        userRepository.delete(id);

        expireUserSessions(username);

    }

    private void expireUserSessions(String username) {
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            if (principal instanceof UserPrincipal) {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails.getUsername().equals(username)) {
                    for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, true)) {
                        information.expireNow();
                    }
                }
            }
        }
    }

}
