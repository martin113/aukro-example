package com.example.restaurantmenus.repository;

import com.example.restaurantmenus.entity.Role;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class RoleDaoImpl  extends GenericDaoImpl<Role, Long> implements RoleDao  {

    public RoleDaoImpl() {
        setEntityClass(Role.class);
    }

    @Override
    public Role findByName(String name) {
        Role role = null;
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.select(root).where(builder.equal(root.get("name"), name));
        Query<Role> q = getCurrentSession().createQuery(query);

        try{
            role = q.getSingleResult();
        } catch (NoResultException nre) {
            System.out.println("RoleDaoImpl: " + nre);
        }
        return role;
    }
}