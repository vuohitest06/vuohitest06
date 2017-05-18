package com.service;

import com.dao.CategoryDao;
import com.dao.GameDao;
import com.dao.UserDao;
import com.dao.WishDao;
import com.domain.Category;
import com.domain.Game;
import com.domain.User;
import com.domain.Wish;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by GoatProphet on 17/04/2017.
 */
@Stateless
@Named("wishListServiceBean")
public class WishListServiceBean implements Serializable {

//    private final static Logger LOGGER = Logger.getLogger(WishListServiceBean.class.getName());

//    @Getter
//    @Setter
//    private String newCategoryName;

    @Inject
    private SteamConnector steamConnector;

    @Inject
    private UserDao userDao;

    @Inject
    private GameDao gameDao;

    @Inject
    private WishDao wishDao;

    @Inject
    private CategoryDao categoryDao;

    public void onReorder() {
    }

    public String getServiceTest() {
        return "Service Test";
    }

    public void onSave(List<Category> categoryList) {
        for (Category category : categoryList) {
            categoryDao.update(category);
        }
        throw new NullPointerException();
    }

    public List<Category> getCategoryList(User user) {
        return (categoryDao.getCategoryList(user));
    }

    public User getUser() {
        return (userDao.getDefaultUser());
    }

    public void updateWishListFromSteam(User user) throws IOException {
        Category defaultCategory = categoryDao.getDefaultCategory(user);

        List<Game> dbGameList = gameDao.getGameList(user);
        List<Game> steamGameList = steamConnector.getWishListFromSteam(user);

        Collection<Game> newGamesList = CollectionUtils.subtract(steamGameList, dbGameList);
        Collection<Game> removedGamesList = CollectionUtils.subtract(dbGameList, steamGameList);

        for (Game game : newGamesList) {
            game = gameDao.checkAndCreateNewGame(game);
            Wish newWish = new Wish(game);
            defaultCategory.getWishList().add(newWish);
        }
        categoryDao.update(defaultCategory);

        for (Game game : removedGamesList) {
            wishDao.deleteWish(user, game);
        }
    }

    public void updateGamesFromSteam(User user) {
//        return steamConnector.getWishListFromSteam(user);
    }

    public void createNewCategory(String newCategoryName, User user) {
        Category category = new Category(user, newCategoryName);
        categoryDao.create(category);
        throw new NullPointerException();
    }

}
