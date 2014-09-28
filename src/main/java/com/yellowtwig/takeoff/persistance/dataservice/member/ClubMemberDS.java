/*
 * YellowTwig 2014
 */
package com.yellowtwig.takeoff.persistance.dataservice.member;

import com.yellowtwig.takeoff.persistance.ClubMember;
import javax.ejb.Stateless;

/**
 *
 * @author marcprive
 */
@Stateless(name="ClubMemberDS")
public class ClubMemberDS extends DataService<ClubMember>{
    
    @Override
    public Class getEntityType() {
        return ClubMember.class;
    }
    
}
