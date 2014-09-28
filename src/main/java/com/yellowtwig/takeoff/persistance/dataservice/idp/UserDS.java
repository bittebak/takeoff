/*
 * YellowTwig 2014
 */

package com.yellowtwig.takeoff.persistance.dataservice.idp;

import com.yellowtwig.takeoff.persistance.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marcprive
 */
@Stateless(name="UserDS")
public class UserDS extends DataServiceIdp<User>{

    @Override
    public Class getEntityType() {
        return User.class;
    }
    
    
}
