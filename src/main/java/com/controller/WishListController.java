package com.controller;

import com.domain.Category;
import com.domain.Wish;
import com.service.WishListService;
import lombok.Getter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by akrantan on 10.4.2017.
 */
@Stateless
@Named("wishListController")
public class WishListController implements Serializable {

    @EJB
    @Getter
    private WishListService wishListService;

    public List<Category> getCategoryList(){
        return wishListService.getCategoryList();
    }

    public void setCategoryList(List<Category> categoryList){
        wishListService.setCategoryList(categoryList);
    }

    public void getWishList(int index){
        wishListService.getWishList(index);
    }

    public void setWishList(int index, List<Wish> wishList){
        wishListService.setWishList(index, wishList);
    }

    public void onReorder(){
        wishListService.onReorder();
    }

    public void onSave(){
        wishListService.onSave();
    }

    public void updateWishListFromSteam() throws IOException {
        wishListService.updateWishListFromSteam();
    }

    public void updateGamesFromSteam(){
        wishListService.updateGamesFromSteam();
    }

    public void createNewCategory(){
        wishListService.createNewCategory();
    }

    public void setNewCategoryName(String newCategoryName){
        wishListService.setNewCategoryName(newCategoryName);
    }

    public String getNewCategoryName(){
        return wishListService.getNewCategoryName();
    }
}
