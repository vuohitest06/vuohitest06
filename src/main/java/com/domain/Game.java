package com.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by akrantan on 5.4.2017.
 */
@Entity
@Data
public class Game extends DomainObject{

//    @OneToMany(cascade = CascadeType.ALL)
//    private Wish wish;

    private String name;
    private Float price;
    private String steamId;
    private String imageName;

    public Game(){
    }

    public Game(String name, Float price, String note, String steamId, String imageName) {
        this.name = name;
        this.price = price;
        this.steamId = steamId;
        this.imageName = imageName;
    }
}
