package com.example.restaurantmenus.util;

import com.example.restaurantmenus.entity.Role;
import com.example.restaurantmenus.entity.User;
import com.example.restaurantmenus.repository.RoleDao;
import com.example.restaurantmenus.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

@Component
public class SetupDataLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


/**
     * Handle an application event.
     *
     * @param event the event to respond to
     */

    @Override
    @Transactional
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        createRoleIfNotFound(1L, "ROLE_ADMIN");
        createRoleIfNotFound(2L, "ROLE_USER");
        final Role adminRole = roleDao.findByName("ROLE_ADMIN");
        final Role userRole = roleDao.findByName("ROLE_USER");
        if (userDao.findByUsername("Admin") == null) {
            final User user = new User();
            user.setUsername("Admin");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
            user.setEnabled(true);
            user.setCreated(new Date());
            userDao.create(user);
        }
        if (userDao.findByUsername("User") == null) {
            User user = new User();
            user.setUsername("User");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
            user.setEnabled(true);
            user.setCreated(new Date());
            userDao.create(user);
        }
    }

    @Transactional
    Role createRoleIfNotFound(final Long id, final String name) {
        Role role = roleDao.findByName(name);
        if (role == null) {
            role = new Role(id, name);
            roleDao.create(role);
        }
        return role;
    }
}