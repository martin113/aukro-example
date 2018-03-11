package com.example.restaurantmenus.repository;

import com.example.restaurantmenus.entity.Role;

public interface RoleDao extends GenericDao<Role, Long> {
    Role findByName(String name);
}
