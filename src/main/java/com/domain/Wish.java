package com.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by akrantan on 5.4.2017.
 */
@Entity
@Data
public class Wish extends DomainObject{

    @ManyToOne(cascade = CascadeType.ALL)
    private Game game;

//    @OneToMany(cascade = CascadeType.ALL)
//    private Category category;

    private boolean highlighted = false;
    private String note = "";

    public Wish(){}

    public Wish(Game game, boolean highlighted) {
        this.game = game;
        this.highlighted = highlighted;
    }

    public Wish(Game game) {
        this.game = game;
    }

}
