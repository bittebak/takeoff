/*
 * YellowTwig 2014
 */
package com.yellowtwig.takeoff.service.member;



import com.marviq.service.jersey.RestResource;
import com.yellowtwig.takeoff.persistance.dataservice.member.ClubMemberDS;
import com.yellowtwig.takeoff.persistance.ClubMember;
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
import javax.ws.rs.QueryParam;

/**
 *
 * @author marcg
 */
//@Stateless
@Path("member")
public class ClubMemberRS extends RestResource {
    
    @EJB(name = "ClubMemberDS")
    private ClubMemberDS dataService;

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
    @Path("{username}")
    @Produces({"application/xml", "application/json"})
    public ClubMember find(@PathParam("username") String id) {
        return getMember(id);
    }
    
    @GET
    @Path("all")
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

     private ClubMember getMember(String userName) {
        return dataService.findUniqueResultForNamedQuery("ClubMember.findByUserName", userName);
        
    }
    
    private ClubMemberDS getDataService(){
        return dataService;
    }
    
}
