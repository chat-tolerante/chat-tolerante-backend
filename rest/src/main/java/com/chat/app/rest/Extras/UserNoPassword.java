package com.chat.app.rest.Extras;

import com.chat.app.rest.Model.User;

import java.io.Serializable;

public class UserNoPassword implements Serializable {
    public String name;
    public String email;
    public long id;
    public UserNoPassword(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.id = user.getId();
    }

    public User toUser(){
        User user = new User();
        user.setEmail(this.email);
        user.setId(this.id);
        user.setName(this.name);
        return user;
    }
}
