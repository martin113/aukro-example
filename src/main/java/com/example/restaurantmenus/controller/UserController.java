package com.example.restaurantmenus.controller;

import com.example.restaurantmenus.entity.User;
import com.example.restaurantmenus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/a/{string}", method = RequestMethod.GET)
    public User get(@PathVariable String string) {
        return userService.findByUsername(string);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list() {
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody User user) {
        userService.update(id, user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}