/*
 * YellowTwig 2014
 */

package com.yellowtwig.takeoff.persistance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;



/**
 *
 * @author marcprive
 */
@Entity
@Table(name = "intent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Intent.findByIdForMember", query = "SELECT i FROM Intent i WHERE i.id = ?2 AND i.memberId = ?1"),
    @NamedQuery(name = "Intent.findByActionDate", query = "SELECT i FROM Intent i WHERE i.actionDate >= ?1")})

public class Intent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = true)
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Integer id;
    @Column(name = "actiondate")
    @Temporal(TemporalType.DATE)
    private Date actionDate;
    @Column(name = "lastupdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupdate;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "starttime")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    @Size(max = 200)
    @Column(name = "comment")
    private String comment;
    @Column(name = "memberid")
    private Integer memberId;
    

    public Intent() {
    }

    public Intent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date date) {
        this.actionDate = date;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intent)) {
            return false;
        }
        Intent other = (Intent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yellowtwig.Intent[ id=" + id + " ]";
    }

    /**
     * @return the mebmerid
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * @param mebmerid the mebmerid to set
     */
    public void setMemberId(Integer mebmerid) {
        this.memberId = mebmerid;
    }
    
}
