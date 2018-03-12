package com.example.restaurantmenus.service;


import com.example.restaurantmenus.entity.Restaurant;
import com.example.restaurantmenus.model.MenuDTO;
import com.example.restaurantmenus.model.RestaurantDTO;
import com.example.restaurantmenus.model.RestaurantDetailDTO;
import com.example.restaurantmenus.repository.MenuDao;
import com.example.restaurantmenus.repository.RestaurantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Transactional
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private MenuDao menuDao;

    @Override
    public Restaurant findById(Long id) {
        return restaurantDao.findOne(id);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantDao.findAll();
    }

    @Transactional
    @Override
    public void save(Restaurant restaurant) {
        restaurantDao.create(restaurant);
    }

    @Transactional
    @Override
    public void update(Long id, Restaurant restaurant) {
        restaurantDao.update(id, restaurant);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Restaurant restaurant = restaurantDao.findOne(id);
        restaurantDao.delete(restaurant);
    }

    @Override
    public List<RestaurantDTO> findAllByDate(LocalDate date) {
        if(date == null) date = LocalDate.now();
        return restaurantDao.restaurantByDateView(date);
    }

    @Override
    public RestaurantDetailDTO findDetail(Long id, LocalDate weekDate) {

        if(weekDate == null) weekDate = LocalDate.now();
        TemporalField weekFields = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumber = weekDate.get(weekFields);

        LocalDate startDate = LocalDate.now().with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDate = LocalDate.now().with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, weekNumber).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        Restaurant restaurant = restaurantDao.findOne(id);
        List<MenuDTO> menuList = menuDao.findWeekMenuByRestaurant(id, startDate, endDate);

        RestaurantDetailDTO detailView = new RestaurantDetailDTO();
        detailView.setName(restaurant.getName());
        detailView.setDescription(restaurant.getDescription());
        detailView.setWeekMenu(menuList);

        return detailView;
    }
}
