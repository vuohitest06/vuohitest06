package com.dao;

import com.domain.User;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.io.Serializable;

/**
 * Created by GoatProphet on 16/04/2017.
 */

@Resource(name = "jdbc/WishListDB", type = javax.sql.DataSource.class, authenticationType = Resource.AuthenticationType.CONTAINER)
@NamedQueries({
        @NamedQuery(name = "getUserByEmail", query = "SELECT u FROM User u Where u.email = :email")
})
@Stateless
@LocalBean
public class UserDao extends GenericDaoImpl<User> implements Serializable {

    public User getDefaultUser() {
        User user = getUserByEmail("goatprophet@gmail.com");
        if (user == null) {
            user = new User("goatprophet@gmail.com", "", "goatprophet");
            create(user);
        }

        return user;
    }

    public User getUserByEmail(String email) {
        Query query = em.createNamedQuery("getUserByEmail", User.class);
        query.setParameter("email", "goatprophet@gmail.com");
        return (User) query.getSingleResult();
    }
}
