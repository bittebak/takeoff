/*
 * Copyright (c) Marviq 2014
 */

package com.marviq.service.jersey.jpa;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 * This class is responsible for handling JPA initialization actions for Jersey resource providers.
 * 
 * @author navidallahverdi, navid.allahverdi@marviq.com
 */
@Provider
@PreMatching
public class JpaJerseyRequestFilter implements ContainerRequestFilter {
    private static final Logger logger = Logger.getLogger(JpaJerseyRequestFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        final String method = requestContext.getMethod().toLowerCase();
        final String requestUri = requestContext.getUriInfo().getRequestUri().toString();

        logger.log(Level.FINE, "Starting em-managed request: {0} {1}", new Object[] { method, requestUri });
       
    }
}
