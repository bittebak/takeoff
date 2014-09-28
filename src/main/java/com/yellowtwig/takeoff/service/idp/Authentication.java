/*
 * YellowTwig 2014
 */

package com.yellowtwig.takeoff.service.idp;

import com.marviq.service.jersey.RestResource;
import com.yellowtwig.takeoff.persistance.User;
import com.yellowtwig.takeoff.persistance.dataservice.idp.UserDS;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author marcprive
 */
@Path("idp")
public class Authentication extends  RestResource {
    
    @EJB(name = "UserDS")
    private UserDS dataService;
    
    
    @POST
    @Path("authenticate")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public XmlLoginResponse authenticate(final XmlLoginRequest loginRequest) {
   
        
        XmlLoginResponse response = new XmlLoginResponse();
        
        User user = dataService.get(loginRequest.getUsername());
        if( user.validatePassword(loginRequest.getPassword())){
        response.setUserId(user.getUsername());
            
        }
        return response;
    }

   UserDS getDataService(){
        return dataService;
    }
   
    
}
