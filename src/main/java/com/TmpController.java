package com;


import com.listeners.CustomSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.http.HttpServletRequest;

public class TmpController {

    Logger logger = LoggerFactory.getLogger(TmpController.class);

    public String login(HttpServletRequest request) {

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

        String token = csrfToken.getToken();

        System.out.println("SESSSION ID " + request.getSession(false).getId());
        logger.info(token);

        return "login";
    }

}
