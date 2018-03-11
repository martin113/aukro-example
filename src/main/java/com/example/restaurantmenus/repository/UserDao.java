package com.example.restaurantmenus.repository;

import com.example.restaurantmenus.entity.User;

public interface UserDao extends GenericDao<User, Long> {
    User findByUsername(String username);
}
