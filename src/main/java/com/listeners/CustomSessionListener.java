package com.listeners;


import org.apache.catalina.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class CustomSessionListener implements HttpSessionListener {

    Logger logger = LoggerFactory.getLogger(CustomSessionListener.class);



    @Override
    public void sessionCreated(HttpSessionEvent se) {

        logger.info("Session {} has been created, ID is : {}", se.getSession(), se.getSession().getId());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("Session {} has been destroyed, ID is : {}", se.getSession(), se.getSession().getId());
    }
}
