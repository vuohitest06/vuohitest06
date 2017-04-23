package com.dao;

import com.domain.Category;
import com.domain.User;
import com.domain.Wish;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akrantan on 5.4.2017.
 */

@Resource(name = "jdbc/WishListDB", type = javax.sql.DataSource.class, shareable = true, authenticationType = Resource.AuthenticationType.CONTAINER)
@NamedQueries({
        @NamedQuery(name="getCategoryListByUser", query="SELECT c FROM Category c Where c.user = :user"),
        @NamedQuery(name="getDefaultCategoryByUser", query="SELECT c FROM Category c Where c.user = :user and c.defaultCategory = true")
})
@Stateless
public class CategoryDao extends GenericDaoImpl<Category> implements Serializable {

    public List<Category> getCategoryList(User user){
        Query query = em.createNamedQuery("getCategoryListByUser", Category.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public Category getDefaultCategory(User user){
        Query query = em.createNamedQuery("getDefaultCategoryByUser", Category.class);
        query.setParameter("user", user);
        Category defaultCategory = (Category)query.getSingleResult();

        if(defaultCategory == null){
            defaultCategory = new Category(user, "Games", true, new ArrayList<Wish>());

        }

        return defaultCategory;
    }
}
