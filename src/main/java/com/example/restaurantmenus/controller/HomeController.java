package com.example.restaurantmenus.controller;

import com.example.restaurantmenus.service.RestaurantService;
import com.example.restaurantmenus.model.RestaurantDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class HomeController {

    @Autowired
    private RestaurantService restaurantService;

    @ApiOperation(value = "date format: yyyy-MM-dd")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<RestaurantDTO> getDayMenu(@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return restaurantService.findAllByDate(date);
    }
}
