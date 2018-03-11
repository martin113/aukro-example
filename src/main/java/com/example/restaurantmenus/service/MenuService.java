package com.example.restaurantmenus.service;

import com.example.restaurantmenus.entity.Menu;

import java.util.List;

public interface MenuService {
    Menu findByIdAndRestaurantId(Long menu_id, Long restaurant_id);
    List<Menu> findAllByRestaurant(Long restaurant_id);
    void save(Long restaurant_id, Menu menu);
    void update(Long menu_id, Long restaurant_id, Menu menu);
    void deleteAllByRestaurantId(Long restaurant_id);
    void deleteByIdAndRestaurantId(Long menu_id, Long restaurant_id);
}
