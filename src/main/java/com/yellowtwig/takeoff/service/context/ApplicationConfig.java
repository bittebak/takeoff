/*
 * Copyright (c) YellowTwig 2014
 */

package com.yellowtwig.takeoff.service.context;

import com.yellowtwig.service.jersey.jpa.JpaJerseyExceptionHandler;
import com.yellowtwig.service.jersey.jpa.JpaJerseyRequestFilter;
import com.yellowtwig.service.jersey.jpa.JpaJerseyResponseFilter;
import com.yellowtwig.service.jersey.PreflightCorsFilter;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import com.yellowtwig.service.jersey.CorsFilter;

/**
 * 
 * @author marc geelen
 */
@ApplicationPath("/rest")
public class ApplicationConfig extends ResourceConfig {
    
    public ApplicationConfig() {
        packages("com.yellowtwig.takeoff.service"); // that last one ensures the superior jackson json engine is used

        
        // register(MultiPartFeature.class);
        // request filters
        register(PreflightCorsFilter.class);
        register(JpaJerseyRequestFilter.class);
       
        // exception handlers
        register(JpaJerseyExceptionHandler.class);

        // response filters
        register(JpaJerseyResponseFilter.class);
        //Cors is now part of the iAPI
        //register(CorsFilter.class);
    }
}