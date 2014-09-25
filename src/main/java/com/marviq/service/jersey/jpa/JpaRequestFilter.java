/*
 * Copyright (c) Marviq 2014
 */

package com.marviq.service.jersey.jpa;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is responsible for handling JPA initialization actions for normal Servlets.
 * 
 * @author navidallahverdi, navid.allahverdi@marviq.com
 */
@WebFilter(urlPatterns={"/park"})
public class JpaRequestFilter extends com.marviq.servlet.jpa.JpaRequestFilter {
    private static final Logger logger = Logger.getLogger(JpaRequestFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;
        final String method = req.getMethod().toLowerCase();
        final String requestUri = req.getRequestURL().toString();

        logger.log(Level.FINE, "Starting em-managed request: {0} {1}", new Object[] { method, requestUri });

        try {
            chain.doFilter(request, response);

            if (resp.getStatus() >= 400) {
                logger.log(Level.SEVERE, "An error {0} occured while handling request {1} {2}", new Object[] { resp.getStatus(), method, requestUri });
            }
        } catch (Throwable t) {
            logger.log(Level.SEVERE, "servlet chain throws exception", t);
            throw new ServletException(t);
        }
    }
}
