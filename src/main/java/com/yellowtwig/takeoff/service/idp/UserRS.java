/*
 * YellowTwig 2014
 */

package com.yellowtwig.takeoff.service.idp;

import com.marviq.service.jersey.RestResource;
import com.yellowtwig.takeoff.persistance.ClubMember;
import com.yellowtwig.takeoff.persistance.User;
import com.yellowtwig.takeoff.persistance.dataservice.idp.UserDS;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author marcprive
 */
@Path("idp/admin/user")
public class UserRS extends  RestResource {
    
    @EJB(name = "UserDS")
    private UserDS dataService;
     
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public User find(@PathParam("id") String id) {
        return dataService.find(id);
    }
    
    @POST
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public User create(User entity) {
        dataService.create(entity);
        return entity;
    }
    
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public User edit(@PathParam("id") String id, User entity) {
        dataService.update(entity);
        return entity;
    }
    @POST
    @Path("{id}/setPassword")
    @Produces({"application/xml", "application/json"})
    public User setPasword(@PathParam("id") String id, @QueryParam("newPassword") String password) {
        User user = dataService.find(id);
        user.setPassword(password);
        dataService.update(user);
        return user;
    }
}
