/*
 * Copyright (c) Marviq 2014
 */

package com.marviq.service.jersey;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;


/**
 * 
 * @author navidallahverdi, navid.allahverdi@marviq.com
 */
@Provider
public class CorsFilter implements ContainerResponseFilter {
    
    private static final Logger Log = Logger.getLogger(CorsFilter.class.getName());
        
    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext resp) throws IOException {

        String requiredHeaders = req.getHeaderString("Access-Control-Request-Headers");
        
        String originHeader = req.getHeaderString("Origin");

        if(null != originHeader && !"".equals(originHeader)) {
            resp.getHeaders().putSingle("Access-Control-Allow-Origin", originHeader);
            resp.getHeaders().putSingle("Access-Control-Allow-Credentials", "true" );
        } else {
            resp.getHeaders().putSingle("Access-Control-Allow-Origin", "*");    
        }

        if (null != requiredHeaders && !requiredHeaders.equals("")) {
            resp.getHeaders().putSingle("Access-Control-Allow-Headers", requiredHeaders);
        } 
        
        resp.getHeaders().putSingle("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        
    }

}
