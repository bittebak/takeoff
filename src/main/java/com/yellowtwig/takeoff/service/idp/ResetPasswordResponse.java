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
@XmlRootElement(name="ResetPasswordResponse")
public class ResetPasswordResponse {
    private String message;

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    
}
