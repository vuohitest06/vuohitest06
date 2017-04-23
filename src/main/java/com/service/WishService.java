package com.service;

import com.domain.Category;
import com.domain.User;
import com.domain.Wish;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akrantan on 10.4.2017.
 */
public class WishService implements Serializable {

    public void updateWishlist(User user){

    }

    public List<Wish> getWislist(User user){

        return new ArrayList<>();
    }

    public List<Category> getCategories(){

        return new ArrayList<>();
    }

}
