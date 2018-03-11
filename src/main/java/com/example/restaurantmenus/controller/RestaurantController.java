package com.example.restaurantmenus.controller;

import com.example.restaurantmenus.entity.Restaurant;
import com.example.restaurantmenus.service.RestaurantService;
import com.example.restaurantmenus.model.RestaurantDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Restaurant get(@PathVariable Long id) {
        return restaurantService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Restaurant> list() {
        return restaurantService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody Restaurant restaurant) {
        restaurantService.save(restaurant);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        restaurantService.update(id, restaurant);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        restaurantService.delete(id);
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    public RestaurantDetailDTO getDetail(@PathVariable Long id, @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
         return restaurantService.findDetail(id, date);
    }
}