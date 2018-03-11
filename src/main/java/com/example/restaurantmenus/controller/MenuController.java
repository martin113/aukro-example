package com.example.restaurantmenus.controller;

import com.example.restaurantmenus.entity.Menu;
import com.example.restaurantmenus.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant/")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/{restaurant_id}/menu/", method = RequestMethod.GET)
    public List<Menu> getAllByRestaurantId(@PathVariable Long restaurant_id) {
        return menuService.findAllByRestaurant(restaurant_id);
    }

    @RequestMapping(value = "/{restaurant_id}/menu/{menu_id}", method = RequestMethod.GET)
    public Menu getMenuById(@PathVariable Long restaurant_id, @PathVariable Long menu_id) {
        return menuService.findByIdAndRestaurantId(menu_id, restaurant_id);
    }

    @RequestMapping(value = "/{restaurant_id}/menu/", method = RequestMethod.POST)
    public void create(@PathVariable Long restaurant_id, @RequestBody Menu menu){
        menuService.save(restaurant_id, menu);
    }

    @RequestMapping(value = "/{restaurant_id}/menu/{menu_id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long restaurant_id, @PathVariable Long menu_id, @RequestBody Menu menu) {
        menuService.update(menu_id, restaurant_id, menu);
    }

    @RequestMapping(value = "/{restaurant_id}/menu/", method = RequestMethod.DELETE)
    public void deleteAllByRestaurantId(@PathVariable Long restaurant_id) {
        menuService.deleteAllByRestaurantId(restaurant_id);
    }

    @RequestMapping(value = "/{restaurant_id}/menu/{menu_id}", method = RequestMethod.DELETE)
    public void removeByIdAndRestaurantId(@PathVariable Long restaurant_id, @PathVariable Long menu_id) {
        menuService.deleteByIdAndRestaurantId(menu_id, restaurant_id);
    }
}
