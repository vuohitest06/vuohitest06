package com.service;

import com.dao.CategoryDao;
import com.dao.GameDao;
import com.dao.UserDao;
import com.dao.WishDao;
import com.domain.Category;
import com.domain.Game;
import com.domain.User;
import com.domain.Wish;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by GoatProphet on 17/04/2017.
 */
@Stateless
public class WishListService implements Serializable {

    private final static Logger LOGGER = Logger.getLogger(WishListService.class.getName());
    
//    @Getter
//    @Setter
//    private String newCategoryName;

    @EJB
    private SteamConnector steamConnector;

    @EJB
    private UserDao userDao;

    @EJB
    private GameDao gameDao;

    @EJB
    private WishDao wishDao;

    @EJB
    private CategoryDao categoryDao;

    @Getter
    @Setter
    private List<Category> categoryList = new ArrayList<>();

    private User user;

    public List<Wish> getWishList(int index){
        return categoryList.get(index).getWishList();
    }

    public void setWishList(int index, List<Wish> wishList){
        categoryList.get(index).setWishList(wishList);
    }

    @PostConstruct
    public void init() throws IOException {
        user = userDao.getDefaultUser();
        updateCategoryList();
        newCategoryName = "";
        LOGGER.setLevel(Level.SEVERE);
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "init");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        throw new NullPointerException();
    }

    public void onReorder(){
    }

    public void onSave(){
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "onSave");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        for(Category category : categoryList){
            categoryDao.update(category);
        }
        throw new NullPointerException();
    }

    private void updateCategoryList(){
        categoryList = categoryDao.getCategoryList(user);
    }

    public void updateWishListFromSteam() throws IOException {
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "updateWishListFromSteam");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        Category defaultCategory = categoryDao.getDefaultCategory(user);

        List<Game> dbGameList = gameDao.getGameList(user);
        List<Game> steamGameList = steamConnector.getWishListFromSteam(user);

        Collection<Game> newGamesList = CollectionUtils.subtract(steamGameList, dbGameList);
        Collection<Game> removedGamesList = CollectionUtils.subtract(dbGameList, steamGameList);

        for(Game game : newGamesList){
            game = gameDao.checkAndCreateNewGame(game);
            Wish newWish = new Wish(game);
            defaultCategory.getWishList().add(newWish);
        }
        categoryDao.update(defaultCategory);

        for(Game game : removedGamesList){
            wishDao.deleteWish(user, game);
        }

        updateCategoryList();
        throw new NullPointerException();
    }

    public void updateGamesFromSteam(){
        // wishList = steamConnector.getWishListFromSteam();
    }

    public void createNewCategory(String newCategoryName){
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "createNewCategory");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        LOGGER.log(Level.SEVERE, "*****************");
        Category category = new Category(user, newCategoryName);
        categoryDao.create(category);
        updateCategoryList();
        throw new NullPointerException();
    }

}
