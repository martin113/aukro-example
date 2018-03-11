package com.example.restaurantmenus.entity;

import com.example.restaurantmenus.model.MenuDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@SqlResultSetMapping(
        name="WeekResult",
        classes = { @ConstructorResult(targetClass = MenuDTO.class,
                columns = {
                        @ColumnResult(name="menuDescription"),
                        @ColumnResult(name="menuDate")})
        })
@Entity(name = "menu")
public class Menu implements Serializable {

    @Id
    @SequenceGenerator(name="menu_idmenu_seq", sequenceName="menu_idmenu_seq", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="menu_idmenu_seq")
    @Column(name = "idmenu", columnDefinition = "serial", updatable=false)
    private Long id;

    private String menuDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Prague")
    @Temporal(TemporalType.DATE)
    private Date menuDate;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
