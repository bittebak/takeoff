/*
 * YellowTwig 2014
 */

package com.yellowtwig.takeoff.service.idp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author navidallahverdi
 */
@XmlRootElement(name="loginRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"username", "newPassword","email","token"})
public class ResetPasswordRequest {
    @XmlElement(required=true, nillable=false)
    private String username;

    @XmlElement(required=true, nillable=false)
    private String newPassword;
    
    @XmlElement(required=true, nillable=false)
    private String email;
    
     @XmlElement(required=true, nillable=false)
    private String token;


    public ResetPasswordRequest() {}

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String password) {
        this.newPassword = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}
