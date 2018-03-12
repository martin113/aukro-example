package com.example.restaurantmenus.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository
public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    private Class<T> entityClass;

    @Autowired
    private SessionFactory sessionFactory;

    public final void setEntityClass(Class<T> classToSet) {
        this.entityClass = classToSet;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T findOne(PK id) {
        return getCurrentSession().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery( "from " + entityClass.getName() ).list();
    }

    @Override
    @Transactional
    public void create(T t) {
        getCurrentSession().save(t);
    }

    @Override
    public void update(PK id, T t) {
        getCurrentSession().merge(t);
        getCurrentSession().flush();
    }

    @Override
    public void delete(T t) {
        getCurrentSession().remove(t);
    }

    @Override
    @Transactional
    public void deleteById(PK id) {
        Session session = getCurrentSession();
        T entity = session.byId(entityClass).load(id);
        session.remove(entity);
    }
}