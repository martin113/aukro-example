package com.example.restaurantmenus.repository;

import com.example.restaurantmenus.entity.Menu;
import com.example.restaurantmenus.model.MenuDTO;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MenuDaoImpl extends GenericDaoImpl<Menu, Long> implements MenuDao {

    public MenuDaoImpl() {
        setEntityClass(Menu.class);
    }

    public List<Menu> findAll(Long restaurant_id) {
        Query query = getCurrentSession()
                .createNativeQuery("SELECT * FROM menu m WHERE m.restaurant_idrestaurant = ?1", Menu.class)
                .setParameter(1, restaurant_id);
        return query.getResultList();
    }

    @Override
    public Menu findByIdAndRestaurantId(Long menu_id, Long restaurant_id) {
        Query query = getCurrentSession()
                .createNativeQuery("SELECT * FROM Menu m WHERE  m.idmenu = ?1 AND m.restaurant_idrestaurant = ?2", Menu.class)
                .setParameter(1, menu_id)
                .setParameter(2, restaurant_id);

        @SuppressWarnings("unchecked")
        Menu menu = (Menu) query.getSingleResult();
        return menu;
    }

    @Override
    public List<MenuDTO> findWeekMenuByRestaurant(Long restaurant_id, LocalDate startDate, LocalDate endDate) {
        Query query = getCurrentSession()
                .createNativeQuery("SELECT m.menudescription, m.menudate FROM menu m WHERE m.restaurant_idrestaurant = ?1 AND m.menudate BETWEEN ?2 AND ?3", "WeekResult")
                .setParameter(1, restaurant_id)
                .setParameter(2, startDate, TemporalType.DATE)
                .setParameter(3, endDate, TemporalType.DATE);
        return query.getResultList();
    }
}
