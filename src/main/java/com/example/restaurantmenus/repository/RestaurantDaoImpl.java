package com.example.restaurantmenus.repository;

import com.example.restaurantmenus.entity.Restaurant;
import com.example.restaurantmenus.model.RestaurantDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.List;

@Repository
public class RestaurantDaoImpl extends GenericDaoImpl<Restaurant, Long> implements RestaurantDao {

    public RestaurantDaoImpl() {
        setEntityClass(Restaurant.class);
    }

    @Override
    public List<RestaurantDTO> restaurantByDateView(LocalDate date) {
        Query query = getCurrentSession()
                .createNativeQuery("SELECT r.name, m.menudescription FROM restaurant r, menu m WHERE r.idrestaurant = m.restaurant_idrestaurant AND m.menudate = ?1", "DayResult")
                .setParameter(1, date, TemporalType.DATE);
        return query.getResultList();
    }
}