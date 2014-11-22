/*
 * Copyright (c) Marviq 2014
 */

package com.yellowtwig.service.jersey.jpa;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * This class is responsible for handling JPA destructing/closing actions, for Jersey resource providers.
 * 
 * @author navidallahverdi, navid.allahverdi@marviq.com
 */
@Provider
public class JpaJerseyResponseFilter implements ContainerResponseFilter {
    private static final Logger logger = Logger.getLogger(JpaJerseyResponseFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        final String method = requestContext.getMethod();
        final Integer statusCode = responseContext.getStatus();
        final String requestUri = requestContext.getUriInfo().getRequestUri().toString();

        logger.log(Level.INFO, "Status code {0} returned for request {1} {2}", new Object[] { statusCode, method, requestUri });

        if (statusCode >= 400) {
            logger.log(Level.SEVERE, "An error {0} occured while handling request {1} {2}, there for rolling back", new Object[] { statusCode, method, requestUri });

            //JTA managed. No transaction management requiered
//            if (method.equals("post") || method.equals("put") || method.equals("delete")) {
//                Em.rollbackAndEndRequest();
//            } else {
//                Em.endRequest();
//            }
        } 
//        else {
//            Em.endRequest();
//        }
    }
}
