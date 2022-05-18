package com.converter.convertModel.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {XMLtoJSON}
 * @Date: {2022/05/18 && 4:17 PM}
 */
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class Users {

    @XmlElement(name = "user")
    private List<User> users = null;

    private List<User> getUsers(){
        return users;
    }

    public void setUsers(List<User> users){
        this.users = users;
    }
}
