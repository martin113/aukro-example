package com.example.restaurantmenus.service;

import com.example.restaurantmenus.entity.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    void save(User user);
    void update(Long id, User user);
    void delete(Long id);
}
