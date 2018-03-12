package com.example.restaurantmenus.service;

import com.example.restaurantmenus.entity.Menu;
import com.example.restaurantmenus.entity.Restaurant;
import com.example.restaurantmenus.repository.MenuDao;
import com.example.restaurantmenus.repository.RestaurantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RestaurantDao restaurantDao;

    @Override
    public Menu findByIdAndRestaurantId(Long menu_id, Long restaurant_id) {
        return menuDao.findByIdAndRestaurantId(menu_id, restaurant_id);
    }

    @Override
    public List<Menu> findAllByRestaurant(Long restaurant_id) {
        return menuDao.findAll(restaurant_id);
    }

    @Override
    public void save(Long restaurant_id, Menu menu) {
        Menu newMenu = new Menu();
        Restaurant restaurant = restaurantDao.findOne(restaurant_id);
        newMenu.setMenuDate(menu.getMenuDate());
        newMenu.setMenuDescription(menu.getMenuDescription());
        newMenu.setRestaurant(restaurant);
        menuDao.create(newMenu);
    }

    @Override
    public void update(Long menu_id, Long restaurant_id, Menu menu) {
        Menu updatedMenu = menuDao.findByIdAndRestaurantId(menu_id, restaurant_id);
        updatedMenu.setMenuDate(menu.getMenuDate());
        updatedMenu.setMenuDescription(menu.getMenuDescription());
        menuDao.update(menu_id,updatedMenu);
    }

    @Override
    public void deleteAllByRestaurantId(Long restaurant_id) {
        List<Menu> menuList = menuDao.findAll(restaurant_id);
        menuList.forEach(menuDao::delete);
    }


    @Override
    public void deleteByIdAndRestaurantId(Long menu_id, Long restaurant_id) {
        Menu deletedMenu = menuDao.findByIdAndRestaurantId(menu_id, restaurant_id);
        menuDao.delete(deletedMenu);
    }
}
