package com.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by akrantan on 5.4.2017.
 */
@Entity
@Data
public class User extends DomainObject{
    private String email;
    private String passwordHash;
    private String steamId;

    public User(){}

    public User(String email, String passwordHash, String steamId) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.steamId = steamId;
    }
}
