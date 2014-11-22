/*
 * YellowTwig 2014
 */
package com.yellowtwig.takeoff.service.member;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcprive
 */
@XmlRootElement
public class MemberPublic implements Serializable {
    
    public MemberPublic(){}
    
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
     
     
   
    
}
