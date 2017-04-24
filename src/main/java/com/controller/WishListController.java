package com.controller;

import com.domain.Category;
import com.domain.User;
import com.service.WishListService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

    @Inject
    private Logger logger;

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

        logger.info("********");
        logger.info("********");
        logger.info("init()");
        logger.info("********");
        logger.info("********");
        logger.info("********");
    }

    public void onReorder(){
        wishListService.onReorder();
    }

    public void onSave(){
        wishListService.onSave(categoryList);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "updateGamesFromSteam end") );
    }

    public void updateWishListFromSteam() throws IOException {
        wishListService.updateWishListFromSteam(user);
    }

    public void updateGamesFromSteam() throws IOException {
        wishListService.updateWishListFromSteam(user);
        categoryList = wishListService.getCategoryList(user);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "updateGamesFromSteam end") );
    }

    public void createNewCategory(){
        wishListService.createNewCategory(newCategoryName, user);
        categoryList = wishListService.getCategoryList(user);
        newCategoryName = "";

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful",  "createNewCategory end") );
    }

}
