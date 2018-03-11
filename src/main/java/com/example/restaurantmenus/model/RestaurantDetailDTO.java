package com.example.restaurantmenus.model;

import com.example.restaurantmenus.entity.Menu;
import com.example.restaurantmenus.model.MenuDTO;

import java.util.List;

public class RestaurantDetailDTO {

    private String name;

    private String description;

    private List<MenuDTO> weekMenu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuDTO> getWeekMenu() {
        return weekMenu;
    }

    public void setWeekMenu(List<MenuDTO> weekMenu) {
        this.weekMenu = weekMenu;
    }
}