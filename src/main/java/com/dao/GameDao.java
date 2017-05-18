package com.dao;

import com.domain.Game;
import com.domain.User;
import com.domain.Wish;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by akrantan on 5.4.2017.
 */
@Resource(name = "jdbc/WishListDB", type = javax.sql.DataSource.class, shareable = true, authenticationType = Resource.AuthenticationType.CONTAINER)
@NamedQueries({
        @NamedQuery(name = "getGameListByUser", query = "SELECT g FROM Game g Where g.wish.category.user = :user"),
        @NamedQuery(name = "getGameBySteamId", query = "SELECT g FROM Game g Where g.steamId = :steamId"),
})
@Stateless
@LocalBean
public class GameDao extends GenericDaoImpl<Game> implements Serializable {

    public List<Game> getGameList(User user) {
        Query query = em.createNamedQuery("getGameListByUser", Game.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public Game checkAndCreateNewGame(Game newGame) {
        Query query = em.createNamedQuery("getGameBySteamId", Game.class);
        query.setParameter("steamId", newGame.getSteamId());
        Game gameFromDb = (Game) query.getSingleResult();

        if (gameFromDb == null) {
            create(newGame);
            return newGame;
        }

        gameFromDb.setImageName(newGame.getImageName());
        gameFromDb.setName(newGame.getName());
        gameFromDb.setPrice(newGame.getPrice());
        update(gameFromDb);

        return gameFromDb;
    }
}
