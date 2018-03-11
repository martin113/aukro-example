package com.example.restaurantmenus.model;

import java.util.Date;

public class MenuDTO {

    private String menuDescription;
    private Date menuDate;

    public MenuDTO(String menuDescription, Date menuDate) {
        this.menuDescription = menuDescription;
        this.menuDate = menuDate;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public Date getMenuDate() {
        return menuDate;
    }

    public void setMenuDate(Date menuDate) {
        this.menuDate = menuDate;
    }
}
