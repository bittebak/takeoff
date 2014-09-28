/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.yellowtwig.takeoff.persistance.dataservice.identitymanager;


import com.yellowtwig.takeoff.persistance.ClubMember;
import javax.ejb.Stateless;

/**
 *
 * @author marcprive
 */
@Stateless(name="ClubMemberDataService")
public class ClubMemberDataService extends DataServiceIdentityManager<ClubMember>{
    
    @Override
    public Class getEntityType() {
        return ClubMember.class;
    }
    
}
