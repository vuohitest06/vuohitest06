package com.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by akrantan on 5.4.2017.
 */
@Entity
@Data
public class Category extends DomainObject{

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    private String name;

    private boolean defaultCategory = false;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Wish> wishList;

    public Category(User user, String name, boolean defaultCategory, List<Wish> wishList) {
        this.user = user;
        this.name = name;
        this.defaultCategory = defaultCategory;
        this.wishList = wishList;
    }

    public Category(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public Category() {
    }
}
