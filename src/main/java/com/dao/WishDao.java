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

/**
 * Created by akrantan on 5.4.2017.
 */

@Resource(name = "jdbc/WishListDB", type = javax.sql.DataSource.class, shareable = true, authenticationType = Resource.AuthenticationType.CONTAINER)
@NamedQueries({
        @NamedQuery(name = "deleteWishByUserAndGame", query = "DELETE FROM Wish w Where wish.category.user = :user AND w.game = :game"),
})
@Stateless
@LocalBean
public class WishDao extends GenericDaoImpl<Wish> implements Serializable {

    public void deleteWish(User user, Game game) {
        Query query = em.createNamedQuery("deleteWishByUserAndGame", Wish.class);
        query.setParameter("user", user);
        query.setParameter("game", game);
        query.executeUpdate();
    }

}
