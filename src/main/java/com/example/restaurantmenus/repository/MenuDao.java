package com.example.restaurantmenus.repository;

import com.example.restaurantmenus.entity.Menu;
import com.example.restaurantmenus.model.MenuDTO;

import java.time.LocalDate;
import java.util.List;

public interface MenuDao extends GenericDao<Menu, Long> {

    List<Menu> findAll(Long restaurant_id);
    Menu findByIdAndRestaurantId(Long menu_id, Long restaurant_id);
    List<MenuDTO> findWeekMenuByRestaurant(Long restaurant_id, LocalDate startDate, LocalDate endDate);

}