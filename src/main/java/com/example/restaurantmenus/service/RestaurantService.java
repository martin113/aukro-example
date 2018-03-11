package com.example.restaurantmenus.service;

import com.example.restaurantmenus.entity.Restaurant;
import com.example.restaurantmenus.model.RestaurantDTO;
import com.example.restaurantmenus.model.RestaurantDetailDTO;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {
    Restaurant findById(Long id);
    List<Restaurant> findAll();
    void save(Restaurant restaurant);
    void update(Long id, Restaurant restaurant);
    void delete(Long id);

    List<RestaurantDTO> findAllByDate(LocalDate date);
    RestaurantDetailDTO findDetail(Long id, LocalDate date);
}
