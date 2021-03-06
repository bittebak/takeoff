/*
 * YellowTwig 2014
 */
package com.yellowtwig.takeoff.persistance;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcprive
 */
@Entity
@Table(name = "member")
@XmlRootElement
@NamedQueries({
    
    @NamedQuery(name = "ClubMember.findByUserName", query = "SELECT c FROM ClubMember c WHERE c.userName = ?1")})

/*
@NamedQueries({
    @NamedQuery(name = "ClubMember.findAll", query = "SELECT c FROM ClubMember c"),
    @NamedQuery(name = "ClubMember.findById", query = "SELECT c FROM ClubMember c WHERE c.id = :id"),
    @NamedQuery(name = "ClubMember.findByFirstname", query = "SELECT c FROM ClubMember c WHERE c.firstname = :firstname"),
    @NamedQuery(name = "ClubMember.findByLastname", query = "SELECT c FROM ClubMember c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "ClubMember.findByIdpUser", query = "SELECT c FROM ClubMember c WHERE c.userName = :idpuserid"),
    @NamedQuery(name = "ClubMember.findByNickname", query = "SELECT c FROM ClubMember c WHERE c.nickname = :nickname")})
*/
public class ClubMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = true)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 45)
    @Column(name = "lastname")
    private String lastname;
    
    @Size(max = 45)
    @Column(name = "nickname")
    private String nickname;
    
     @NotNull
    @Column(name = "username")
    private String userName;
     

    public ClubMember() {
    }

    public ClubMember(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        if (!(object instanceof ClubMember)) {
            return false;
        }
        ClubMember other = (ClubMember) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yellowtwig.ClubMember[ id=" + id + " ]";
    }

    /**
     * @return the userName
     */
    public String getIdentity() {
        return userName;
    }

    /**
     * @param identity the userName to set
     */
    public void setIdentity(String identity) {
        this.userName = identity;
    }
    
}
