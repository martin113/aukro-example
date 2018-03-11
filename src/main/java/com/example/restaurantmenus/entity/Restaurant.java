package com.example.restaurantmenus.entity;

import com.example.restaurantmenus.model.RestaurantDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@SqlResultSetMapping(
        name="DayResult",
        classes = { @ConstructorResult(targetClass = RestaurantDTO.class,
                columns = {
                        @ColumnResult(name="name"),
                        @ColumnResult(name="menudescription")})
        })
@Entity(name = "restaurant")
public class Restaurant implements Serializable {

    @Id
    @SequenceGenerator(name="restaurant_idrestaurant_seq", sequenceName="restaurant_idrestaurant_seq", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="restaurant_idrestaurant_seq")
    @Column(name = "idrestaurant", columnDefinition = "serial", updatable=false)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Menu> menu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Menu> getMenu() {
        return menu;
    }

    public void setMenu(Set<Menu> menu) {
        this.menu = menu;
    }
}
