/*
 * YellowTwig 2014
 */
package com.yellowtwig.takeoff.service.idp;

//import com.marviq.gateway.service.client.security.UserPrincipal;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author navidallahverdi
 */
@XmlRootElement(name="loginResponse")
public class XmlLoginResponse {
    private String userName;
    private String userToken;
    private String userId;
    private List<String> roles;
    private String SSOToken;

    public String getSSOToken() {
        return SSOToken;
    }

    public void setSSOToken(String SSOToken) {
        this.SSOToken = SSOToken;
    }
    
    public XmlLoginResponse() {}
    
    

    @XmlElementWrapper(name="roles")
    @XmlElement(name = "role")
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @XmlElement(name = "userid")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @XmlElement(name = "username")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlElement(name = "token")
    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
