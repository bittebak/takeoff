/*
 * YellowTwig 2014
 */
package com.yellowtwig.takeoff.persistance.dataservice.idp;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcprive
 */
public abstract class DataServiceIdp<T> extends com.yellowtwig.persistance.jpa.DataService<T> {

    @PersistenceContext(unitName = "com.yellowtwig.takeoff.identitymanager")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
