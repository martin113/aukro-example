package com.example.restaurantmenus.repository;

import com.example.restaurantmenus.entity.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantDao extends GenericDao<Restaurant, Long> {
    List restaurantByDateView(LocalDate date);
}
