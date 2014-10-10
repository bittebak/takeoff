/*
 * YellowTwig 2014
 */

package com.yellowtwig.takeoff.service.member;

import com.marviq.service.jersey.RestResource;
import com.yellowtwig.takeoff.persistance.ClubMember;
import com.yellowtwig.takeoff.persistance.Intent;
import com.yellowtwig.takeoff.persistance.User;
import com.yellowtwig.takeoff.persistance.dataservice.idp.UserDS;
import com.yellowtwig.takeoff.persistance.dataservice.member.ClubMemberDS;
import com.yellowtwig.takeoff.persistance.dataservice.member.IntentDS;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author marcprive
 */
@Path("intent")
public class IntentRS extends  RestResource {
    
    @EJB(name = "IntentDS")
    private IntentDS dataService;
    
    @EJB(name = "ClubMemberDS")
    private ClubMemberDS memberDataService;
     
    @GET
    @Path("{userName}/{intentId}")
    @Produces({"application/xml", "application/json"})
    public Intent find(@PathParam("userName") String userName, @PathParam("intentId") Integer intentId) {
        ClubMember member = getMember(userName);
        return dataService.findFirstResultForNameQuery("Intent.findByIdForMember", member.getId(), intentId );
       
    }
    
    @POST
    @Path("{username}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Intent create(Intent entity, @PathParam("username") String username) {
        ClubMember member = getMember(username);
        entity.setMemberId(member.getId());
        dataService.create(entity);
        return entity;
    }
    
    @PUT
    @Path("{username}/{intentId}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Intent edit(@PathParam("username") String username, @PathParam("intentId") Integer intentId, Intent entity) {
        ClubMember member = getMember(username);
        
        dataService.find(intentId);
        dataService.update(entity);
        return entity;
    }
    
    private ClubMember getMember(String userName) {
        return memberDataService.findUniqueResultForNamedQuery("ClubMember.findByUserName", userName);
        
    }
    
}
