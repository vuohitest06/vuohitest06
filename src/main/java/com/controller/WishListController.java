package com.controller;

import com.domain.Category;
import com.domain.User;
import com.service.WishListService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akrantan on 10.4.2017.
 */
@Named
@RequestScoped
public class WishListController implements Serializable {

    @Getter
    @Setter
    @EJB
    private WishListService wishListService;

    @Getter
    @Setter
    private String newCategoryName;

    @Getter
    @Setter
    private List<Category> categoryList = new ArrayList<>();

    private User user;

    @PostConstruct
    public void init()  {
        user = wishListService.getUser();
        categoryList = wishListService.getCategoryList(user);
        newCategoryName = "";
    }

    public void onReorder(){
        wishListService.onReorder();
    }

    public void onSave(){
        wishListService.onSave(categoryList);
    }

    public void updateWishListFromSteam() throws IOException {
        wishListService.updateWishListFromSteam(user);
    }

    public void updateGamesFromSteam() throws IOException {
        wishListService.updateWishListFromSteam(user);
        categoryList = wishListService.getCategoryList(user);
    }

    public void createNewCategory(){
        wishListService.createNewCategory(newCategoryName, user);
        categoryList = wishListService.getCategoryList(user);
        newCategoryName = "";
    }

}
