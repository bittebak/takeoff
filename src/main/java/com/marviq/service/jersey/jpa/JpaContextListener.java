/*
 * Copyright (c) Marviq 2014
 */

package com.marviq.service.jersey.jpa;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * 
 * @author navidallahverdi, navid.allahverdi@marviq.com
 */
//@WebListener()
public class JpaContextListener extends com.marviq.servlet.jpa.JpaContextListener {
    @Override
    public void contextInitialized(ServletContextEvent evt) {
        super.contextInitialized(evt);
    }

    @Override
    public void contextDestroyed(ServletContextEvent evt) {
        super.contextDestroyed(evt);
    }
}
