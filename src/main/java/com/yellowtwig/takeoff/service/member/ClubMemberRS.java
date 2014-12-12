/*
 * YellowTwig 2014
 */
package com.yellowtwig.takeoff.service.member;



import com.yellowtwig.service.jersey.RestResource;
import com.yellowtwig.takeoff.persistance.dataservice.member.ClubMemberDS;
import com.yellowtwig.takeoff.persistance.ClubMember;
import java.util.List;
import java.util.logging.Level;
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
import javax.ws.rs.core.Response;

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
    
    /**
     * Get the public form of the Member
     * @param id
     * @return
     */
    @GET
    @Path("public/{id}")
    @Produces("application/json")
    public MemberPublic getPublic(@PathParam("id") Integer id) {
        ClubMember entity = getDataService().get(id);
        
        if (null == entity) {
            logAndReturn(Level.FINE, Response.Status.NOT_FOUND, "entity with id :" + id + "not found");
            return null;
        }
        MemberPublic memberPublic = new MemberPublic();
        
        memberPublic.setNickname(entity.getNickname());
        return memberPublic;
    }

     private ClubMember getMember(String userName) {
        return dataService.findUniqueResultForNamedQuery("ClubMember.findByUserName", userName);
        
    }
     
    @PUT
    @Path("nickname/{username}")
    @Produces({"application/xml", "application/json"})
    public ClubMember editPublic(@PathParam("username") String username, @QueryParam("nickname") String nickname)   {
        ClubMember entity = getMember(username);
        
        if (null == entity) {
            logAndReturn(Level.FINE, Response.Status.NOT_FOUND, "entity with id :" + username + "not found");
            return null;
        }
        entity.setNickname(nickname);
        getDataService().update(entity);
        return entity;
    }
    
    private ClubMemberDS getDataService(){
        return dataService;
    }
    
}
