/*
 * YellowTwig 2014
 */

package com.yellowtwig.takeoff.persistance.dataservice.member;

import com.yellowtwig.takeoff.persistance.Intent;
import javax.ejb.Stateless;

/**
 *
 * @author marcprive
 */
@Stateless(name="IntentDS")
public class IntentDS extends DataService<Intent>{

    @Override
    public Class<Intent> getEntityType() {
        return Intent.class;
    }
    
}
