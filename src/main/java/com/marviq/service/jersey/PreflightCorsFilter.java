/*
 * Copyright (c) Marviq 2014
 */
package com.marviq.service.jersey;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author navidallahverdi, navid.allahverdi@marviq.com
 */
@Provider
@PreMatching
public class PreflightCorsFilter implements ContainerRequestFilter {

    private static final Logger log = Logger.getLogger(PreflightCorsFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext req) throws IOException {
        log.info("Executing REST request filter");

        // When HttpMethod comes as OPTIONS, just acknowledge that it accepts...
        if (req.getRequest().getMethod().equals("OPTIONS")) {

            log.info("HTTP Method (OPTIONS) - Detected!");
            
            Response.ResponseBuilder responseBuilder = Response.status(Response.Status.OK);
            
            
            String reqHead = getHeaderValue("Access-Control-Request-Headers",req);

            String originHeader = getHeaderValue("Origin",req);
            
            if (null != originHeader && !"".equals(originHeader)) {
                responseBuilder.header("Access-Control-Allow-Origin", originHeader);
                responseBuilder.header("Access-Control-Allow-Credentials", "true");
            } else {
                responseBuilder.header("Access-Control-Allow-Origin", "*");
            }

            if (null != reqHead && !reqHead.equals("")) {
                responseBuilder.header("Access-Control-Allow-Headers", reqHead);
            }
            
            responseBuilder.header("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
            
            Response response = responseBuilder.build();

            // Just send a OK signal back to the browser
            req.abortWith(response);

        }

    }
    
    private String getHeaderValue(String headerName, ContainerRequestContext req ) {
        
            if(req.getHeaderString(headerName) != null) {
                return req.getHeaderString(headerName);
            } else {
                return req.getHeaderString("_"+headerName);
            }
    }

}
