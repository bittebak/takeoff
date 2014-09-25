package com.yellowtwig.takeoff.service.identitymanager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.marviq.service.jersey.RestResource;
import com.yellowtwig.takeoff.persistance.ClubMember;
import com.yellowtwig.takeoff.persistance.dataservice.identitymanager.ClubMemberDataService;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author marcg
 */
//@Stateless
@Path("member")
public class ClubMemberServiceRest extends RestResource {
    
    @EJB(name = "ClubMemberDataService")
    private ClubMemberDataService dataService;

    @POST
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public ClubMember create(ClubMember entity) {
        getDataService().create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public ClubMember edit(@PathParam("id") Integer id, ClubMember entity) {
        getDataService().update(entity);
        return entity;
    }

    @DELETE
    @Path("{id}")
    public ClubMember deleteById(@PathParam("id") Integer id) {
        ClubMember entity = getDataService().get(id);
        getDataService().delete(id);
        return entity;
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public ClubMember find(@PathParam("id") Integer id) {
        return getDataService().find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<ClubMember> findAll() {
        return getDataService().getAll();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(getDataService().count());
    }

    ClubMemberDataService getDataService(){
        return dataService;
    }
    
}
