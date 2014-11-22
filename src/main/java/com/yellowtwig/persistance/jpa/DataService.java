/*
 * Copyright (c) Marviq 2014
 */

package com.yellowtwig.persistance.jpa;

//import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
//import nl.ezorg.domain.persistence.entity.DomainObject;

/**
 * 
 * @author navidallahverdi, navid.allahverdi@marviq.com
 * @param <T>
 */
public abstract class DataService<T> extends com.marviq.servlet.jpa.DataService<T> {

    @SuppressWarnings("unchecked")
//    private final Class<T> klaz = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    
    public Query createQuery(String jql) {
        return getEntityManager().createQuery(jql);
    }
    
    public void detach(Collection<T> forgetAboutThese) {
        for (T item : forgetAboutThese) {
            getEntityManager().detach(item);
        }
    }

    /**
     * Same as getWhere in superclass, but returns null if no result found instead
     * of throwing an exception.
     * @param whereProperty
     * @param whereValue
     * @return 
     */
    @Override
    public T getWhere(String whereProperty, Object whereValue) {
        T result = null;
        try {
            result = super.getWhere(whereProperty, whereValue);
        } catch (NoResultException nre) {
            // This is possible, but no reason to throw an exception
            // Just let the method return null
        }
        
        return result;
    }

    public Collection<T> getAllWithLike(String whereProperty, String likeValue) {
        Collection<T> result = null;
        try {
            Query likeQuery = createQuery("SELECT t from " + getShortClassName() + " t WHERE t." + whereProperty + " LIKE :likeValue");
            likeQuery.setParameter("likeValue", "%" + likeValue + "%");

            result = likeQuery.getResultList();
        } catch (NoResultException nre) {
            // This is possible, but no reason to throw an exception
            // Just let the method return null
        }

        return result;
    }
    
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(getEntityType());
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public T find(Object id) {
        return getEntityManager().find(getEntityType(), id);
    }
    
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(getEntityType()));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(getEntityType()));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    
        
    
    
    
}
