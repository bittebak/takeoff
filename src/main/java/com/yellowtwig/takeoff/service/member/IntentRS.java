/*
 * YellowTwig 2014
 */

package com.yellowtwig.takeoff.service.member;

import com.yellowtwig.service.jersey.RestResource;
import com.yellowtwig.takeoff.persistance.ClubMember;
import com.yellowtwig.takeoff.persistance.Intent;
import com.yellowtwig.takeoff.persistance.dataservice.member.ClubMemberDS;
import com.yellowtwig.takeoff.persistance.dataservice.member.IntentDS;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response.Status;

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
    public Intent findForUser(@PathParam("userName") String userName, @PathParam("intentId") Integer intentId) {
        ClubMember member = getMember(userName);
        return dataService.findFirstResultForNameQuery("Intent.findByIdForMember", member.getId(), intentId );
    }
    
    @GET
    @Path("bydate/{from}")
    @Produces({"application/xml", "application/json"})
    public List<Intent> find(@PathParam("from") String from ) {
        
        SimpleDateFormat simpleDateFormat;
        Date date = null;
        try{
             simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
             date = simpleDateFormat.parse(from);
        }
        catch(Exception ex){
        }
        
        if(null == date)
        {
            try{
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            date = simpleDateFormat.parse(from);
            }
            catch(Exception ex)
            {
            }
        }
        //last effor. Strip the time
        if(null == date) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = simpleDateFormat.parse(from);
            } catch (ParseException ex) {
                Logger.getLogger(IntentRS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(null != date){
            return dataService.findByNamedQuery("Intent.findByActionDate", date );
        } 
        
        
        return null;
        
    }
    
    @GET
    @Path("{userName}")
    @Produces({"application/xml", "application/json"})
    public List<Intent> findAllForMember(@PathParam("userName") String userName, @PathParam("intentId") Integer intentId) {
        ClubMember member = getMember(userName);
        return dataService.getAllWhere("memberId" , member.getId());
    }
    
    @POST
    @Path("{username}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Intent create(Intent entity, @PathParam("username") String username) {
        ClubMember member = getMember(username);
        entity.setMemberId(member.getId());
        entity.setCreated(Calendar.getInstance().getTime());
        entity.setLastupdate(entity.getCreated());
        dataService.create(entity);
        
        return entity;
    }
    
    @PUT
    @Path("{username}/{intentId}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Intent edit(@PathParam("username") String username, @PathParam("intentId") Integer intentId, Intent entity) {
        ClubMember member = getMember(username);
        
        Intent oldIntent = dataService.findFirstResultForNameQuery("Intent.findByIdForMember", member.getId(), intentId );

        if( null != oldIntent){
            //Copy non mutable information
            entity.setId( oldIntent.getId()); 
            entity.setMemberId(member.getId());
            entity.setCreated(oldIntent.getCreated());
            entity.setLastupdate(Calendar.getInstance().getTime());
            dataService.update(entity);
        }
        else{
            logAndReturn(Level.INFO, Status.NOT_FOUND,"Intent with id " + intentId + " does not exist"); 
        }
        return entity;
    }
    
    @DELETE
    @Path("{username}/{intentId}")
    @Produces({"application/xml", "application/json"})
    public Intent delete(@PathParam("username") String username, @PathParam("intentId") Integer intentId) {
        ClubMember member = getMember(username);
        
        Intent oldIntent = dataService.findFirstResultForNameQuery("Intent.findByIdForMember", member.getId(), intentId );

        if( null != oldIntent) {
            dataService.delete(intentId);
        }
        return oldIntent;
        
    }
    
    private ClubMember getMember(String userName) {
        return memberDataService.findUniqueResultForNamedQuery("ClubMember.findByUserName", userName);
        
    }
    
}
