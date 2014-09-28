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
@XmlType(propOrder={"username", "password"})
public class XmlLoginRequest {
    @XmlElement(required=true, nillable=false)
    private String username;

    @XmlElement(required=true, nillable=false)
    private String password;

    public XmlLoginRequest() {}

    public XmlLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
