package com.example.restaurantmenus.repository;

import com.example.restaurantmenus.entity.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    public UserDaoImpl() {
        setEntityClass(User.class);
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("username"), username));
        Query<User> q = getCurrentSession().createQuery(query);
        try{
            user = q.getSingleResult();
        } catch (NoResultException nre) {
            System.out.println("UserDaoImpl: " + nre);
        }
        return user;
    }
}
