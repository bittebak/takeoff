/*
 * YellowTwig 2014
 */
package com.yellowtwig.takeoff.persistance.dataservice.member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcprive
 */
public abstract class DataService<T> extends com.marviq.persistance.jpa.DataService<T> {

    @PersistenceContext(unitName = "com.yellowtwig.takeoff.identitymanager")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
