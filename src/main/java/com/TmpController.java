package com;


import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.http.HttpServletRequest;

public class TmpController {

    public String login(HttpServletRequest request) {

        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

        String token = csrfToken.getToken();

        System.out.println("SESSSION ID " + request.getSession(false).getId());
        System.out.println(token);

        return "login";
    }

}
