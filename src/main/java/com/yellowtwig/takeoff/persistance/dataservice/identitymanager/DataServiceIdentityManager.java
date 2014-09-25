/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yellowtwig.takeoff.persistance.dataservice.identitymanager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcprive
 */
public abstract class DataServiceIdentityManager<T> extends com.marviq.persistance.jpa.DataService<T> {

    @PersistenceContext(unitName = "com.yellowtwig.takeoff.identitymanager")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
