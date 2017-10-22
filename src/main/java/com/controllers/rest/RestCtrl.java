package com.controllers.rest;

import com.User;
import com.UserPrincipal;
import com.repositories.UserRepository;
import com.services.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestCtrl {


    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionsService sessionsService;

    @RequestMapping(path = "/sessions", method = RequestMethod.GET)
    public List<Object> greeting() {

        return sessionRegistry.getAllPrincipals();

    }


    @RequestMapping(path ="/api/deleteUser", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody String id) {

        Long ID = Long.valueOf(id);
        String username = userRepository.findOne(ID).getUsername();

        userRepository.delete(ID);

        sessionsService.expireSessionsOf(username);
    }


}
